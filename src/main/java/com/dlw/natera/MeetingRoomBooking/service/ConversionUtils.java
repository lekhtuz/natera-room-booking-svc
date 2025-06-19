package com.dlw.natera.MeetingRoomBooking.service;

import com.dlw.natera.MeetingRoomBooking.entity.BookingEntity;
import com.dlw.natera.MeetingRoomBooking.entity.MeetingRoomEntity;
import com.dlw.natera.MeetingRoomBooking.model.Booking;
import com.dlw.natera.MeetingRoomBooking.model.CreateBookingRequest;
import com.dlw.natera.MeetingRoomBooking.model.CreateMeetingRoomRequest;
import com.dlw.natera.MeetingRoomBooking.model.MeetingRoom;

import lombok.NoArgsConstructor;

/**
 * Utility class for converting between entity and model objects.
 * This class should not be instantiated.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ConversionUtils {

	/**
	 * Converts a BookingEntity to a Booking model.
	 *
	 * @param e the BookingEntity to convert
	 * @return the converted Booking model
	 */
	public static Booking toBooking(BookingEntity e) {
		return Booking.builder()
				.id(e.getId())
				.roomId(e.getMeetingRoom().getId())
				.userName(e.getUserName())
				.startDateTime(e.getStartDateTime())
				.endDateTime(e.getEndDateTime())
				.build();
	}

	/**
	 * Converts a CreateBookingRequest to a BookingEntity.
	 *
	 * @param request the CreateBookingRequest to convert
	 * @param room    the MeetingRoomEntity associated with the booking
	 * @return the converted BookingEntity
	 */
	public static BookingEntity toBookingEntity(CreateBookingRequest request, MeetingRoomEntity room) {
		return BookingEntity.builder()
				.meetingRoom(room)
				.userName(request.getUserName())
				.startDateTime(request.getStartDateTime())
				.endDateTime(request.getEndDateTime())
				.build();
	}

	/**
	 * Converts a MeetingRoomEntity to a MeetingRoom model.
	 *
	 * @param entity the MeetingRoomEntity to convert
	 * @return the converted MeetingRoom model
	 */
	public static MeetingRoom toMeetingRoom(MeetingRoomEntity entity) {
		return MeetingRoom.builder()
				.id(entity.getId())
				.name(entity.getName())
				.location(entity.getLocation())
				.build();
	}

	/**
	 * Converts a CreateMeetingRoomRequest to a MeetingRoomEntity.
	 *
	 * @param request the CreateMeetingRoomRequest to convert
	 * @return the converted MeetingRoomEntity
	 */
	public static MeetingRoomEntity toMeetingRoomEntity(CreateMeetingRoomRequest request) {
		return MeetingRoomEntity.builder()
				.name(request.getName())
				.location(request.getLocation())
				.build();
	}

/**
	 * Converts a MeetingRoom model to a MeetingRoomEntity.
	 *
	 * @param room the MeetingRoom model to convert
	 * @return the converted MeetingRoomEntity
	 */
	public static MeetingRoomEntity toMeetingRoomEntity(MeetingRoom room) {
		return MeetingRoomEntity.builder()
				.id(room.getId())
				.name(room.getName())
				.location(room.getLocation())
				.build();
	}
}
