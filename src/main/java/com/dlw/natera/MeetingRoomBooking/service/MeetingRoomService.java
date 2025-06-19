package com.dlw.natera.MeetingRoomBooking.service;

import static com.dlw.natera.MeetingRoomBooking.service.ConversionUtils.toMeetingRoom;
import static com.dlw.natera.MeetingRoomBooking.service.ConversionUtils.toMeetingRoomEntity;

import com.dlw.natera.MeetingRoomBooking.model.CreateMeetingRoomRequest;
import com.dlw.natera.MeetingRoomBooking.model.MeetingRoom;
import com.dlw.natera.MeetingRoomBooking.repository.MeetingRoomRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
				.map(ConversionUtils::toMeetingRoom)
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
				.map(ConversionUtils::toMeetingRoom);
	}

	/**
	 * Creates a new meeting room.
	 *
	 * @param request the meeting room to create
	 * @return the created meeting room or empty()
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
}