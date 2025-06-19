package com.dlw.natera.MeetingRoomBooking.service;

import static com.dlw.natera.MeetingRoomBooking.service.ConversionUtils.toBooking;
import static com.dlw.natera.MeetingRoomBooking.service.ConversionUtils.toBookingEntity;

import com.dlw.natera.MeetingRoomBooking.entity.MeetingRoomEntity;
import com.dlw.natera.MeetingRoomBooking.model.Booking;
import com.dlw.natera.MeetingRoomBooking.model.CreateBookingRequest;
import com.dlw.natera.MeetingRoomBooking.repository.BookingRepository;
import com.dlw.natera.MeetingRoomBooking.repository.MeetingRoomRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	/**
	 * Creates a new booking.
	 *
	 * @param request the booking request containing details of the booking
	 * @return the created booking or empty() if the room is not available
	 * @throws IllegalArgumentException if the meeting room is not found
	 */
	public Optional<Booking> createBooking(CreateBookingRequest request) {
		return meetingRoomRepository.findById(request.getRoomId())
				.filter(room -> isRoomAvailable(room.getId(), request.getStartDateTime(), request.getEndDateTime()))
				.map(room -> toBooking(bookingRepository.save(toBookingEntity(request, room))));
	}

	/**
	 * Retrieves all bookings.
	 *
	 * @return a list of all bookings
	 */
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll()
				.stream()
				.map(ConversionUtils::toBooking)
				.toList();
	}

	/**
	 * Retrieves a booking by its ID.
	 *
	 * @param id the ID of the booking
	 * @return an Optional containing the booking if found, or empty if not found
	 */
	public Optional<Booking> getBookingById(Long id) {
		return bookingRepository.findById(id)
				.map(ConversionUtils::toBooking);
	}

	/**
	 * Deletes a booking by its ID.
	 *
	 * @param id the ID of the booking to delete
	 */
	public void deleteBooking(long id) {
		bookingRepository.deleteById(id);
	}

	/**
	 * Checks if a room is available for booking during the specified time period.
	 *
	 * @param roomId the ID of the room
	 * @param start the start date and time of the booking
	 * @param end the end date and time of the booking
	 * @return true if the room is available, false otherwise
	 */
	private boolean isRoomAvailable(Long roomId, LocalDateTime start, LocalDateTime end) {
		return bookingRepository.getConflictsCount(roomId, start, end) == 0;
	}
}