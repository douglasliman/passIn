package com.passIn.passin.controllers;

import com.passIn.passin.dto.attendee.AttendeeIdDTO;
import com.passIn.passin.dto.attendee.AttendeeRequestDTO;
import com.passIn.passin.dto.attendee.AttendeesListResponseDTO;
import com.passIn.passin.dto.event.EventIdDTO;
import com.passIn.passin.dto.event.EventRequestDTO;
import com.passIn.passin.dto.event.EventResponseDTO;
import com.passIn.passin.services.AttendeeService;
import com.passIn.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final AttendeeService attendeeService;
    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
        EventResponseDTO event = this.eventService.getEventDetails(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeesListResponseDTO> registerParticipant(@PathVariable String eventId){
        AttendeesListResponseDTO attendeesListResponseDTO = this.attendeeService.getEventsAttendee(eventId);

        return ResponseEntity.ok(attendeesListResponseDTO);
    }

}
