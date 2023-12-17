package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.EmployeeDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.LoginDto;
import com.ar.cac.IntegradorFinalGrupo5.response.LoginRegisterResponse;
import com.ar.cac.IntegradorFinalGrupo5.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        LoginRegisterResponse registerResponse = employeeService.addEmployee(employeeDto);
        if (!registerResponse.getStatus()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not created");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("The User " + employeeDto.getName() + " has been created.");
        }

    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> loginEmployee(@RequestBody LoginDto loginDto) {

        LoginRegisterResponse loginResponse = employeeService.loginEmployee(loginDto);
        if (!loginResponse.getStatus()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: " + loginResponse.getMessage() + ", Status: " + loginResponse.getStatus());
        } else return ResponseEntity.status(HttpStatus.OK).body(loginResponse.getMessage() + ", "+ loginResponse.getStatus());

    }

}
