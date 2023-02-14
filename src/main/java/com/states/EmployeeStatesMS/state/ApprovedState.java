package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public class ApprovedState implements State {

    private EmployeeState currentState;

    public ApprovedState() {
        this.currentState = EmployeeState.APPROVED;
    }

    @Override
    public void getPreviousState() {
        this.currentState = EmployeeState.IN_CHECK;
    }

    @Override
    public void getNextState() {
        this.currentState = EmployeeState.ACTIVE;
    }

    @Override
    public EmployeeState get() {
        return currentState;
    }
}
