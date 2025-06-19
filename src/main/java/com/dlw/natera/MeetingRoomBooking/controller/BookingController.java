package com.dlw.natera.MeetingRoomBooking.controller;

import com.dlw.natera.MeetingRoomBooking.model.Booking;
import com.dlw.natera.MeetingRoomBooking.model.CreateBookingRequest;
import com.dlw.natera.MeetingRoomBooking.service.BookingService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@Log4j2
public class BookingController {

	@Autowired
	private BookingService bookingService;

	/**
	 * Creates a new booking.
	 *
	 * @param request the booking request containing details of the booking
	 * @return the created booking
	 */
	@PostMapping
	public ResponseEntity<Optional<Booking>> createBooking(@RequestBody CreateBookingRequest request) {
		log.info("Creating booking with request: {}", request);

		if (request.getStartDateTime().isAfter(request.getEndDateTime())) {
			log.error("Start date cannot be after end date");
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.createBooking(request));

	}

	/**
	 * Retrieves all bookings.
	 *
	 * @param roomId the ID of the room to filter bookings by (optional)
	 * @param startDateTime the start date and time to filter bookings (optional)
	 * @param endDateTime the end date and time to filter bookings (optional)
	 * @return a list of all bookings
	 */
	@GetMapping
	public List<Booking> getAllBookings(
			@RequestParam(value = "roomId", required = false) Long roomId,
			@RequestParam(value = "startDateTime", required = false) LocalDateTime startDateTime,
			@RequestParam(value = "endDateTime", required = false) LocalDateTime endDateTime) {
		log.info("Retrieving all bookings with filters - roomId: {}, startDateTime: {}, endDateTime: {}", 
				roomId, startDateTime, endDateTime);

		return bookingService.getAllBookings();
	}

	/**
	 * Retrieves a booking by its ID.
	 *
	 * @param id the ID of the booking
	 * @return the booking with the specified ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Booking> getMeetingRoomById(@PathVariable Long id) {
		log.info("Retrieving booking with ID: {}", id);

		return bookingService.getBookingById(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Deletes a booking by its ID.
	 *
	 * @param id the ID of the booking to delete
	 * @return a response entity with no content
	 */
	@DeleteMapping("/{id}")
	public String deleteMeetingRoom(@PathVariable long id) {
		log.info("Deleting booking with ID: {}", id);

		bookingService.deleteBooking(id);
		return "Booking " + id + " deleted or never existed.";
	}
}