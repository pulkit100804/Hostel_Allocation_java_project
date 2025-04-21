-- 1. Create (or select) the hostel database
drop database if exists hostel_management;
create database hostel_management;
use hostel_management;


-- 2. Rooms: lists every room, its capacity, and live occupancy/vacancy counts
CREATE TABLE IF NOT EXISTS rooms (
  room_number INT PRIMARY KEY,
  capacity INT NOT NULL DEFAULT 3,
  current_occupants INT NOT NULL DEFAULT 0,
  vacancies INT AS (capacity - current_occupants) STORED
);

-- 3. Allocations: tracks which student is in which room, with timestamps
CREATE TABLE IF NOT EXISTS allocations (
  allocation_id INT AUTO_INCREMENT PRIMARY KEY,
  room_number INT NOT NULL,
  student_id VARCHAR(20) NOT NULL,
  allocation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (room_number) REFERENCES rooms(room_number)
);


-- 5. Triggers to maintain current_occupants in rooms
DELIMITER //

-- After a student is allocated, increment count
CREATE TRIGGER trg_after_alloc_insert
AFTER INSERT ON allocations
FOR EACH ROW
BEGIN
  UPDATE rooms
    SET current_occupants = current_occupants + 1
  WHERE room_number = NEW.room_number;
END;//

DELIMITER ;

-- 6. Reports: you can build roomwise reports via SQL queries over allocations
SELECT r.room_number, r.capacity, r.current_occupants, r.vacancies
FROM rooms r;

select * from allocations;

INSERT INTO rooms (room_number, capacity, current_occupants)
VALUES 
  (101, 3, 0),
  (102, 3, 0),   
  (103, 3, 0),
  (104, 3, 0),   
  (105, 3, 0),
  (106, 3, 0),   
  (107, 3, 0),   
  (108, 3, 0),
  (109, 3, 0),   
  (110, 3, 0);   


show tables
