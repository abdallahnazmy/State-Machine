package com.states.EmployeeStatesMS.model;

import com.states.EmployeeStatesMS.trigger.EventTrigger;

public class TriggerRequest {

    private Long id;
    private EventTrigger event;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventTrigger getEvent() {
        return event;
    }

    public void setEvent(EventTrigger event) {
        this.event = event;
    }
}
