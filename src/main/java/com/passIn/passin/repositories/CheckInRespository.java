package com.passIn.passin.repositories;

import com.passIn.passin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRespository extends JpaRepository<CheckIn, String> {

    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
