package com.dlw.natera.MeetingRoomBooking.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * Request model for creating a new meeting room.
 */    
@Data
@Builder
public class CreateMeetingRoomRequest {
    @NonNull
    private String name;
    private String location;
}