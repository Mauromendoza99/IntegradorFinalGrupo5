package com.ar.cac.IntegradorFinalGrupo5.services;

import com.ar.cac.IntegradorFinalGrupo5.entities.Employee;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.EmployeeDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.LoginDto;
import com.ar.cac.IntegradorFinalGrupo5.repositories.EmployeeRepository;
import com.ar.cac.IntegradorFinalGrupo5.response.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
//    String addEmployee(EmployeeDto employeeDto);

//    LoginResponse loginEmployee(LoginDto loginDto);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
//    private PasswordEncoder passwordEncoder;

//    GENERAR UN EMPLOYEE
    public String addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getEmail(),
                employeeDto.getPassword()
        );

        employeeRepository.save(employee);

        return employee.getName();
    }

//    VALIDACIONES DE LOGIN
    public LoginResponse loginEmployee(LoginDto loginDto) {
        String msg = "";
        Employee employee1 = employeeRepository.findByEmail(loginDto.getEmail());
        if (employee1 != null){
            String password = loginDto.getPassword();

            if (employee1.getPassword().equals(loginDto.getPassword())){
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDto.getEmail(), password);
                if (employee.isPresent()){
                    return new LoginResponse("Login Succes",true);

                }else {
                    return new LoginResponse("Login Failed",false);
                }

            }else {
                return new LoginResponse("Password not match",false);
            }

        }else {
            return new LoginResponse("Email not exists",false);
        }
    }
}
