package com.states.EmployeeStatesMS.entity;

import com.states.EmployeeStatesMS.enums.EmployeeState;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

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
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate startDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmployeeState employeeState;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

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

    public String getNumber() {
        return phoneNumber;
    }

    public void setNumber(String number) {
        this.phoneNumber = number;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public EmployeeState getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(EmployeeState employeeState) {
        this.employeeState = employeeState;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public boolean validateEmployee() {
        return this.firstName != null
                && this.lastName != null
                && this.age != null
                && this.phoneNumber != null
                && this.contract != null;
    }
}
