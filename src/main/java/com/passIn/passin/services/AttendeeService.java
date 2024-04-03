package com.passIn.passin.services;

import com.passIn.passin.domain.attendee.Attendee;
import com.passIn.passin.domain.checkin.CheckIn;
import com.passIn.passin.dto.attendee.AttendeeDetails;
import com.passIn.passin.dto.attendee.AttendeesListResponseDTO;
import com.passIn.passin.repositories.AttendeeRepository;
import com.passIn.passin.repositories.CheckInRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    
    private final AttendeeRepository attendeeRepository;
    private final CheckInRespository checkInRespository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }
    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkInRespository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }

}
