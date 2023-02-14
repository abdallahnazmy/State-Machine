package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.EmployeeState;
import com.states.EmployeeStatesMS.enums.Event;

public class ApproveTrigger implements EventTrigger {

    @Override
    public void changeState(Employee employee) {
        if (employee.getEmployeeState().equals(EmployeeState.IN_CHECK)) {
            employee.setEmployeeState(EmployeeState.APPROVED);
        } else {
            throwUnsupportedTriggerException(Event.APPROVE, employee);
        }
    }
}

