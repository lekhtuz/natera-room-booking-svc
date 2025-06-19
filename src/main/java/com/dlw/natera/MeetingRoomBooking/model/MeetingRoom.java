package com.dlw.natera.MeetingRoomBooking.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class MeetingRoom {
    private Long id;
    @NonNull
    private String name;
    private String location;
}