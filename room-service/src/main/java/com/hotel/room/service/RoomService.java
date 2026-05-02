package com.hotel.room.service;

import com.hotel.room.dto.RoomRequest;
import com.hotel.room.model.Room;
import com.hotel.room.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found with id: " + id));
    }

    public Room getRoomByNumber(String roomNumber) {
        return roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new EntityNotFoundException("Room not found: " + roomNumber));
    }

    public List<Room> searchRooms(String bedType, String availability, boolean availableOnly) {
        if (bedType != null && !bedType.isBlank()) {
            if (availableOnly) {
                return roomRepository.findByAvailabilityAndBedType("Available", bedType);
            }
            return roomRepository.findByBedType(bedType);
        }
        if (availability != null && !availability.isBlank()) {
            return roomRepository.findByAvailability(availability);
        }
        return roomRepository.findAll();
    }

    public Room createRoom(RoomRequest request) {
        if (roomRepository.existsByRoomNumber(request.getRoomNumber())) {
            throw new IllegalArgumentException("Room number already exists: " + request.getRoomNumber());
        }
        Room room = new Room();
        mapToRoom(request, room);
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, RoomRequest request) {
        Room room = getRoomById(id);
        mapToRoom(request, room);
        return roomRepository.save(room);
    }

    public Room updateAvailability(String roomNumber, String availability) {
        Room room = getRoomByNumber(roomNumber);
        room.setAvailability(availability);
        return roomRepository.save(room);
    }

    public Room updateCleanStatus(String roomNumber, String cleanStatus) {
        Room room = getRoomByNumber(roomNumber);
        room.setCleanStatus(cleanStatus);
        return roomRepository.save(room);
    }

    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new EntityNotFoundException("Room not found with id: " + id);
        }
        roomRepository.deleteById(id);
    }

    private void mapToRoom(RoomRequest request, Room room) {
        room.setRoomNumber(request.getRoomNumber());
        room.setAvailability(request.getAvailability() != null ? request.getAvailability() : "Available");
        room.setCleanStatus(request.getCleanStatus() != null ? request.getCleanStatus() : "Clean");
        room.setPrice(request.getPrice());
        room.setBedType(request.getBedType());
    }
}
