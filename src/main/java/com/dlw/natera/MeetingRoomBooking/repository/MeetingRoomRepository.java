package com.dlw.natera.MeetingRoomBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dlw.natera.MeetingRoomBooking.entity.MeetingRoomEntity;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoomEntity, Long> {
}