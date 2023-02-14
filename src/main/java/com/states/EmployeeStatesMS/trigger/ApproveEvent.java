package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.state.InCheckState;
import com.states.EmployeeStatesMS.state.State;

public class ApproveEvent implements EventTrigger {

    private final State inCheckState;

    public ApproveEvent() {
        this.inCheckState = new InCheckState();
    }

    @Override
    public void execute(Employee employee) {
        if (inCheckState.get().equals(employee.getEmployeeState())) {
            inCheckState.getNextState();
            employee.setEmployeeState(inCheckState.get());
        } else {
            throw new UnsupportedOperationException(String.format("Invalid operation for state %s", employee.getEmployeeState()));
        }
    }
}

