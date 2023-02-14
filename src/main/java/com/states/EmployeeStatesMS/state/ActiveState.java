package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public class ActiveState implements State {

    private EmployeeState currentState;

    public ActiveState() {
        this.currentState = EmployeeState.ACTIVE;
    }

    @Override
    public void getPreviousState() {
        throw new UnsupportedOperationException(String.format("This operation is not allowed for state %s", currentState));
    }

    @Override
    public void getNextState() {
        throw new UnsupportedOperationException(String.format("This operation is not allowed for state %s", currentState));
    }

    @Override
    public EmployeeState get() {
        return currentState;
    }
}
