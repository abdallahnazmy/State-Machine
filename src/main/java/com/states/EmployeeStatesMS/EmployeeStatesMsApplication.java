package com.states.EmployeeStatesMS;

import com.states.EmployeeStatesMS.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeStatesMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeStatesMsApplication.class, args);
    }

    @Autowired
    private EmployeeService employeeService;

//    @Bean
//    CommandLineRunner commandLineRunner() {
//
//        return args -> {
//            Contract c = new Contract();
//            c.setId(1L);
//            c.setSalary(10000);
//            c.setContractType(ContractType.FULL_TIME);
//            c.setStartDate(LocalDateTime.now());
//
//            Employee e = new Employee();
//            e.setNumber("01276690195");
//            e.setContract(c);
//            e.setAge(25);
//            e.setFirstName("Ahmed");
//            e.setLastName("Mohammed");
//
//
////            employeeService.addEmployee(e);
////            employeeService.getEmployee(2L);
////            employeeService.updateEmployeeState(Event.BEGIN_CHECK, 5L);
//        };
//    }
}
