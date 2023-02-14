package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.EmployeeState;
import com.states.EmployeeStatesMS.enums.Event;

public class UnapproveTrigger implements EventTrigger {
    @Override
    public void changeState(Employee employee) {
        if (employee.getEmployeeState().equals(EmployeeState.APPROVED)) {
            employee.setEmployeeState(EmployeeState.IN_CHECK);
        } else {
            throwUnsupportedTriggerException(Event.UNAPPROVE, employee);
        }
    }
}
