package com.passIn.passin.services;

import com.passIn.passin.domain.attendee.Attendee;
import com.passIn.passin.domain.event.Event;
import com.passIn.passin.domain.event.exceptions.EventNotFoundException;
import com.passIn.passin.dto.attendee.AttendeeIdDTO;
import com.passIn.passin.dto.attendee.AttendeeRequestDTO;
import com.passIn.passin.dto.event.EventIdDTO;
import com.passIn.passin.dto.event.EventRequestDTO;
import com.passIn.passin.dto.event.EventResponseDTO;
import com.passIn.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {


    private final EventRepository eventRepository;
    private final AttendeeService AttendeeService;
    public EventResponseDTO getEventDetails(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.AttendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }
    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }



}
