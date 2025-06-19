package com.dlw.natera.MeetingRoomBooking.controller;

import com.dlw.natera.MeetingRoomBooking.model.CreateMeetingRoomRequest;
import com.dlw.natera.MeetingRoomBooking.model.MeetingRoom;
import com.dlw.natera.MeetingRoomBooking.service.MeetingRoomService;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Log4j2
public class MeetingRoomController {

	@Autowired
	private MeetingRoomService meetingRoomService;

	/**
	 * Creates a new meeting room.
	 *
	 * @param request the meeting room to create
	 * @return the created meeting room
	 */
	@PostMapping
	public ResponseEntity<MeetingRoom> createRoom(@RequestBody CreateMeetingRoomRequest request) {
		log.info("Creating meeting room with request: {}", request);

		return meetingRoomService.createMeetingRoom(request)
				.map(room -> ResponseEntity.status(HttpStatus.CREATED).body(room))
				.orElse(ResponseEntity.badRequest().build());
	}

	/**
	 * Retrieves all meeting rooms.
	 *
	 * @return a list of all meeting rooms
	 */
	@GetMapping
	public List<MeetingRoom> getAllMeetingRooms() {
		log.info("Retrieving all meeting rooms");

		return meetingRoomService.getAllMeetingRooms();
	}

	/**
	 * Retrieves a meeting room by its ID.
	 *
	 * @param id the ID of the meeting room
	 * @return the meeting room with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<MeetingRoom> getMeetingRoomById(@PathVariable long id) {
		log.info("Retrieving meeting room with ID: {}", id);

		return meetingRoomService.getMeetingRoomById(id)
				.map(room -> ResponseEntity.ok().body(room))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Deletes a meeting room by its ID.
	 *
	 * @param id the ID of the meeting room to delete
	 * @return a response entity with no content
	 */
	@DeleteMapping("/{id}")
	public String deleteMeetingRoom(@PathVariable long id) {
		log.info("Deleting meeting room with ID: {}", id);

		meetingRoomService.deleteMeetingRoom(id);
		return "Meeting room " + id + " successfully deleted or has never existed.";
	}
}
