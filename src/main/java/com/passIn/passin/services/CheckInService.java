package com.passIn.passin.services;

import com.passIn.passin.domain.attendee.Attendee;
import com.passIn.passin.domain.checkin.CheckIn;
import com.passIn.passin.repositories.CheckInRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRespository checkInRepository;

    public void registerCheckIn(Attendee attendee){
        //       this.verifyCheckInExists(attendee.getId());
        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());
        this.checkInRepository.save(newCheckIn);
    }

 //   private void verifyCheckInExists(String attendeeId){
    //        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);
    //        if(isCheckedIn.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in");
    //   }

    public Optional<CheckIn> getCheckIn(String attendeeId){
        return this.checkInRepository.findByAttendeeId(attendeeId);

    }
    
}
