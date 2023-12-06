package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Employee;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.EmployeeDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.LoginDto;
import com.ar.cac.IntegradorFinalGrupo5.repositories.EmployeeRepository;
import com.ar.cac.IntegradorFinalGrupo5.response.LoginRegisterResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //    GENERAR UN EMPLOYEE
    public LoginRegisterResponse addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getPassword()
        );
        if (employee.getName() != null) {
            employeeRepository.save(employee);
            return new LoginRegisterResponse("Register Succes", true);

        } else {
            return new LoginRegisterResponse("Register Failed", false);
        }


    }

    //    VALIDACIONES DE LOGIN
    public LoginRegisterResponse loginEmployee(LoginDto loginDto) {
        String msg = "";
        Employee employee1 = employeeRepository.findByEmail(loginDto.getEmail());
        if (employee1 != null) {
            String password = loginDto.getPassword();

            if (employee1.getPassword().equals(loginDto.getPassword())) {
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDto.getEmail(), password);
                if (employee.isPresent()) {
                    return new LoginRegisterResponse("Login Succes", true);


                } else {
                    return new LoginRegisterResponse("Login Failed", false);
                }

            } else {
                return new LoginRegisterResponse("Password not match", false);
            }

        } else {
            return new LoginRegisterResponse("Email not exists", false);
        }
    }
}
