package com.states.EmployeeStatesMS.trigger;

import com.states.EmployeeStatesMS.entity.Employee;

public interface EventTrigger {
    void execute(Employee employee);
}
