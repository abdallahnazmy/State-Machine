package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public interface State {
    void getPreviousState();

    void getNextState();

    EmployeeState get();

    default void throwInvalidOperation(EmployeeState employeeState) {
        throw new UnsupportedOperationException(String.format("This operation is not allowed for current state %s", employeeState));
    }
}
