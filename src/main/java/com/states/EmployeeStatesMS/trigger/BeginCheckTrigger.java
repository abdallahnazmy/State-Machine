package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.EmployeeState;
import com.states.EmployeeStatesMS.enums.Event;

public class BeginCheckTrigger implements EventTrigger {

    @Override
    public void changeState(Employee employee) {
        if (employee.getEmployeeState().equals(EmployeeState.ADDED)) {
            employee.setEmployeeState(EmployeeState.IN_CHECK);
        } else {
            throwUnsupportedTriggerException(Event.BEGIN_CHECK, employee);
        }
    }
}
