package com.microservices.hotelrating.event;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 

public class RatingSubmittedEvent {
    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String correlationId;
    private RatingEventPayload payload;
    

}
