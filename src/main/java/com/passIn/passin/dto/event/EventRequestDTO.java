package com.passIn.passin.dto.event;

public record EventRequestDTO(
        String title,
        String details,
        Integer maximumAttendees
) {

}
