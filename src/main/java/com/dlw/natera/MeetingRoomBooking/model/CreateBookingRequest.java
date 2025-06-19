package com.dlw.natera.MeetingRoomBooking.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Request model for creating a new booking for a meeting room.
 */
@Data
@Builder
public class CreateBookingRequest {
    private long roomId;
    @NonNull
    private String userName;
    @NonNull
    private LocalDateTime startDateTime;
    @NonNull
    private LocalDateTime endDateTime;
}
