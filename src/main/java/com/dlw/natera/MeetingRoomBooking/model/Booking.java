package com.dlw.natera.MeetingRoomBooking.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Represents a booking for a meeting room.
 */
@Data
@Builder
public class Booking {
    private Long id;
    @NonNull
    private Long roomId;
    @NonNull
    private String userName;
    @NonNull
    private LocalDateTime startDateTime;
    @NonNull
    private LocalDateTime endDateTime;
}
