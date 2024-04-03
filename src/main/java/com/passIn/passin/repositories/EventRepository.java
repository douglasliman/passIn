package com.passIn.passin.repositories;

import com.passIn.passin.domain.attendee.Attendee;
import com.passIn.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventRepository extends JpaRepository<Event, String> {



}
