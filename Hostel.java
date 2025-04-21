import java.sql.*;

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
    public int allocateRoom(String username) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement("SELECT room_number FROM allocations WHERE student_id = ?");
            checkStmt.setString(1, username);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next()) {
                throw new RoomAlreadyAllocatedException("❗ User already has a room: Room " + checkRs.getInt("room_number"));
            }

            Statement stmt = conn.createStatement();
            ResultSet roomSet = stmt.executeQuery("SELECT room_number FROM rooms WHERE vacancies > 0 LIMIT 1");

            if (roomSet.next()) {
                int roomNo = roomSet.getInt("room_number");
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO allocations (room_number, student_id) VALUES (?, ?)");
                insertStmt.setInt(1, roomNo);
                insertStmt.setString(2, username);
                insertStmt.executeUpdate();
                return roomNo;
            } else {
                throw new RoomAllocationLimitReachedException("❗ All rooms are already allocated. Hostel full!");
            }

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
            ResultSet rs = stmt.executeQuery("SELECT r.room_number, a.student_id FROM allocations a JOIN rooms r ON a.room_number = r.room_number ORDER BY r.room_number");

            if (!rs.isBeforeFirst()) {
                throw new RoomDataFetchException("No room data available to display.");
            }

            while (rs.next()) {
                System.out.println("Room " + rs.getInt("room_number") + " -> " + rs.getString("student_id"));
            }

            rs.close();
        } catch (RoomDataFetchException e) {
            System.out.println("⚠ " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ SQL Error while fetching room data: " + e.getMessage());
        }
    }
}
