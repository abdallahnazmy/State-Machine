package com.states.EmployeeStatesMS.state;

import com.states.EmployeeStatesMS.enums.EmployeeState;

public interface State {
    void getPreviousState();

    void getNextState();

    EmployeeState get();
}
