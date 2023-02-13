package com.states.EmployeeStatesMS.entity;

import com.states.EmployeeStatesMS.enums.EmployeeState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "APP_EMPLOYEE")
public class Employee {

    public Employee() {
        this.employeeState = EmployeeState.ADDED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
    @NotNull
    @NumberFormat
    private Integer age;

    @NotNull
    @NumberFormat
    private Integer number;

    @NotNull
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @NotNull
    private EmployeeState employeeState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }

}
