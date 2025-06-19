package com.dlw.natera.MeetingRoomBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlw.natera.MeetingRoomBooking.entity.MeetingRoomEntity;
import com.dlw.natera.MeetingRoomBooking.model.CreateMeetingRoomRequest;
import com.dlw.natera.MeetingRoomBooking.model.MeetingRoom;
import com.dlw.natera.MeetingRoomBooking.repository.MeetingRoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingRoomService {

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	/**
	 * Retrieves all meeting rooms.
	 *
	 * @return a list of all meeting rooms
	 */
	public List<MeetingRoom> getAllMeetingRooms() {
		return meetingRoomRepository.findAll()
				.stream()
				.map(this::toMeetingRoom)
				.toList();
	}

	/**
	 * Retrieves a meeting room by its ID.
	 *
	 * @param id the ID of the meeting room
	 * @return an Optional containing the meeting room if found, or empty if not found
	 */
	public Optional<MeetingRoom> getMeetingRoomById(Long id) {
		return meetingRoomRepository.findById(id)
				.map(this::toMeetingRoom);
	}

	/**
	 * Creates a new meeting room.
	 *
	 * @param meetingRoom the meeting room to create
	 * @return the created meeting room
	 */
	public Optional<MeetingRoom> createMeetingRoom(CreateMeetingRoomRequest request) {
		try {
			return Optional.of(toMeetingRoom(meetingRoomRepository.save(toMeetingRoomEntity(request))));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	/**
	 * Deletes a meeting room by its ID.
	 *
	 * @param id the ID of the meeting room to delete
	 */
	public void deleteMeetingRoom(Long id) {
		meetingRoomRepository.deleteById(id);
	}

	private MeetingRoom toMeetingRoom(MeetingRoomEntity entity) {
		return MeetingRoom.builder()
				.id(entity.getId())
				.name(entity.getName())
				.location(entity.getLocation())
				.build();
	}

	private MeetingRoomEntity toMeetingRoomEntity(CreateMeetingRoomRequest request) {
		return MeetingRoomEntity.builder()
				.name(request.getName())
				.location(request.getLocation())
				.build();
	}
}