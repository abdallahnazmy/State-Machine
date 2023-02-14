package com.states.EmployeeStatesMS.service;

import com.states.EmployeeStatesMS.entity.Employee;
import com.states.EmployeeStatesMS.trigger.EventTrigger;

public interface EmployeeService {

    Employee addEmployee(Employee employee) throws Exception;

    Employee getEmployee(Long id) throws Exception;

    Employee updateEmployeeState(EventTrigger eventTrigger, Long id) throws Exception;
}
