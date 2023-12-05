package com.ar.cac.IntegradorFinalGrupo5.controllers;

import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.EmployeeDto;
import com.ar.cac.IntegradorFinalGrupo5.entities.dtos.LoginDto;
import com.ar.cac.IntegradorFinalGrupo5.response.LoginResponse;
import com.ar.cac.IntegradorFinalGrupo5.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
@RequestMapping("/api/security")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDto employeeDto) {
        String id = employeeService.addEmployee(employeeDto);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDto loginDto){

    LoginResponse loginResponse = employeeService.loginEmployee(loginDto);
    return  ResponseEntity.ok(loginResponse);
    }

}
