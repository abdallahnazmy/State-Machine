package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public class ActiveState implements State {

    private EmployeeState currentState;

    public ActiveState() {
        this.currentState = EmployeeState.ACTIVE;
    }

    @Override
    public void getPreviousState() {
        throwInvalidOperation(currentState);
    }

    @Override
    public void getNextState() {
        throwInvalidOperation(currentState);
    }

    @Override
    public EmployeeState get() {
        return currentState;
    }
}
