package com.dlw.natera.MeetingRoomBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlw.natera.MeetingRoomBooking.entity.BookingEntity;
import com.dlw.natera.MeetingRoomBooking.entity.MeetingRoomEntity;
import com.dlw.natera.MeetingRoomBooking.model.Booking;
import com.dlw.natera.MeetingRoomBooking.model.CreateBookingRequest;
import com.dlw.natera.MeetingRoomBooking.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * Creates a new booking.
	 *
	 * @param request the booking request containing details of the booking
	 * @return the created booking
	 */
	public Optional<Booking> createBooking(CreateBookingRequest request) {
		if (isRoomAvailable(request.getRoomId(), request.getStartDateTime(), request.getEndDateTime())) {
			return Optional.of(toBooking(bookingRepository.save(toBookingEntity(request))));
		}
		
		return Optional.empty();
	}

	/**
	 * Retrieves all bookings.
	 *
	 * @return a list of all bookings
	 */
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll()
				.stream()
				.map(this::toBooking)
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
				.map(this::toBooking);
	}

	/**
	 * Deletes a booking by its ID.
	 *
	 * @param id the ID of the booking to delete
	 */
	public void deleteBooking(long id) {
		bookingRepository.deleteById(id);
	}

	private Booking toBooking(BookingEntity e) {
		return Booking.builder()
				.id(e.getId())
				.roomId(e.getMeetingRoom().getId())
				.userName(e.getUserName())
				.startDateTime(e.getStartDateTime())
				.endDateTime(e.getEndDateTime())
				.build();
	}

	private BookingEntity toBookingEntity(CreateBookingRequest request) {
		return BookingEntity.builder()
				.meetingRoom(MeetingRoomEntity.builder()
						.id(request.getRoomId())
						.build())
				.userName(request.getUserName())
				.startDateTime(request.getStartDateTime())
				.endDateTime(request.getEndDateTime())
				.build();
	}

	public boolean isRoomAvailable(Long roomId, LocalDateTime start, LocalDateTime end) {
		int n = bookingRepository.getConflictsCount(roomId, start, end);
		return n == 0;
	}
}