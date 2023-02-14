package com.states.EmployeeStatesMS.trigger;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.Event;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventName")
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = BeginCheckTrigger.class, name = "beginCheck"),
                @JsonSubTypes.Type(value = ApproveTrigger.class, name = "approve"),
                @JsonSubTypes.Type(value = UnapproveTrigger.class, name = "unapprove"),
                @JsonSubTypes.Type(value = ActivateTrigger.class, name = "activate")
        })
public interface EventTrigger {
    void changeState(Employee employee);

    default void throwUnsupportedTriggerException(Event event, Employee employee) {
        throw new UnsupportedOperationException
                (String.
                        format("Invalid state transition from %s using %s trigger",
                                employee.getEmployeeState(), event));
    }

}
