
import java.sql.*;
import java.util.*;

class RoomAlreadyAllocatedException extends Exception {
    public RoomAlreadyAllocatedException(String message) {
        super(message);
    }
}

class RoomAllocationLimitReachedException extends Exception {
    public RoomAllocationLimitReachedException(String message) {
        super(message);
    }
}

class RoomDataFetchException extends Exception {
    public RoomDataFetchException(String message) {
        super(message);
    }
}

public class Hostel {
    private static final int totalRooms = 10;

    public int allocateRoom(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            // Check if user already has a room
            PreparedStatement checkStmt = conn.prepareStatement("SELECT room_no FROM room_allocations WHERE student_username = ?");
            checkStmt.setString(1, username);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next()) {
                throw new RoomAlreadyAllocatedException("❗ User already has a room: Room " + checkRs.getInt("room_no"));
            }

            Statement stmt = conn.createStatement();
            for (int i = 1; i <= totalRooms; i++) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM room_allocations WHERE room_no = " + i);
                if (!rs.next()) {
                    PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO room_allocations (room_no, student_username) VALUES (?, ?)");
                    insertStmt.setInt(1, i);
                    insertStmt.setString(2, username);
                    insertStmt.executeUpdate();
                    return i;
                }
                rs.close();
            }

            throw new RoomAllocationLimitReachedException("❗ All rooms are already allocated. Hostel full!");

        } catch (RoomAlreadyAllocatedException | RoomAllocationLimitReachedException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error while allocating room: " + e.getMessage());
        }
        return -1;
    }
        
    public void showRoomAllocation() {
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM room_allocations");

            if (!rs.isBeforeFirst()) {
                throw new RoomDataFetchException("No room data available to display.");
            }

            while (rs.next()) {
                System.out.println("Room " + rs.getInt("room_no") + " -> " + rs.getString("student_username"));
            }

            rs.close();
        } catch (RoomDataFetchException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error while fetching room data: " + e.getMessage());
        }
    }
}
