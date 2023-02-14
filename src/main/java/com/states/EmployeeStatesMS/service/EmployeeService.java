package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.enums.StateEvent;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee getEmployee(Long id) throws Exception;

    Employee changeEmployeeState(StateEvent stateEvent, Long id) throws Exception;
}
