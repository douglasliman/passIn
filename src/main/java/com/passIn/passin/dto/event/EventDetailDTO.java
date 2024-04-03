package com.passIn.passin.dto.event;

public record EventDetailDTO(
        String id,
        String title,
        String details,
        String slug,
        Integer maximumAttendee,
        Integer attendeesAmount
) {



}
