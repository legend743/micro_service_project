package com.microservices.userservice.event;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmissionEvent {

    private String eventId;
    private String eventType;
    private Instant timestamp;
    private String corealtionId;
    private UserServicePayload userServicePayload;

}
