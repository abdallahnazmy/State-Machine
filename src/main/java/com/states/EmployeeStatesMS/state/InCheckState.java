package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public class InCheckState implements State {

    private EmployeeState currentState;

    public InCheckState() {
        this.currentState = EmployeeState.IN_CHECK;
    }

    @Override
    public void getPreviousState() {
        throwInvalidOperation(currentState);
    }

    @Override
    public void getNextState() {
        this.currentState = EmployeeState.APPROVED;
    }

    @Override
    public EmployeeState get() {
        return currentState;
    }
}
