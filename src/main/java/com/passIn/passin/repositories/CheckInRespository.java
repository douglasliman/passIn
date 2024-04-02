package com.passIn.passin.repositories;

import com.passIn.passin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRespository extends JpaRepository<CheckIn, String> {
}
