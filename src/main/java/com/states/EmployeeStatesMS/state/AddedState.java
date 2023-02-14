package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public class AddedState implements State {

    private EmployeeState currentState;

    public AddedState() {
        this.currentState = EmployeeState.ADDED;
    }

    @Override
    public void getPreviousState() {
        throw new UnsupportedOperationException(String.format("This operation is not allowed for state %s", currentState));
    }

    @Override
    public void getNextState() {
        this.currentState = EmployeeState.IN_CHECK;
    }

    @Override
    public EmployeeState get() {
        return currentState;
    }
}
