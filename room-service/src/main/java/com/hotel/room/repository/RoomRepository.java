package com.hotel.room.repository;

import com.hotel.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNumber(String roomNumber);

    List<Room> findByBedType(String bedType);

    List<Room> findByAvailability(String availability);

    List<Room> findByAvailabilityAndBedType(String availability, String bedType);

    boolean existsByRoomNumber(String roomNumber);
}
