package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.state.AddedState;
import com.states.EmployeeStatesMS.state.State;

public class BeginCheckTrigger implements EventTrigger {

    private final State addedState;

    public BeginCheckTrigger() {
        this.addedState = new AddedState();
    }

    @Override
    public void execute(Employee employee) {
        if (addedState.get().equals(employee.getEmployeeState())) {
            addedState.getNextState();
            employee.setEmployeeState(addedState.get());
        } else {
            throw new UnsupportedOperationException(String.format("Invalid operation for state %s", employee.getEmployeeState()));
        }
    }
}
