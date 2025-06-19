package com.dlw.natera.MeetingRoomBooking.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.dlw.natera.MeetingRoomBooking.entity.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    @NativeQuery("""
        SELECT COUNT(*) 
        FROM bookings 
        WHERE meeting_room_id = ?1 AND (
            start_date_time BETWEEN ?2 AND ?3 OR 
            end_date_time BETWEEN ?2 AND ?3 OR
            ?2 BETWEEN start_date_time AND end_date_time OR
            ?3 BETWEEN start_date_time AND end_date_time
        )
        """)
    public int getConflictsCount(long meetingRoomId, LocalDateTime start, LocalDateTime end);
}