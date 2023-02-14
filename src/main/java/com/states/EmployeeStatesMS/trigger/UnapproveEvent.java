package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.state.ApprovedState;
import com.states.EmployeeStatesMS.state.State;

public class UnapproveEvent implements EventTrigger {

    private final State approvedState;

    public UnapproveEvent() {
        this.approvedState = new ApprovedState();
    }

    @Override
    public void execute(Employee employee) {
        if (approvedState.get().equals(employee.getEmployeeState())) {
            approvedState.getPreviousState();
            employee.setEmployeeState(approvedState.get());
        } else {
            throw new UnsupportedOperationException(String.format("Invalid operation for state %s", employee.getEmployeeState()));
        }
    }
}