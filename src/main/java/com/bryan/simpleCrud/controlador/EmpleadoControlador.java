package com.bryan.simpleCrud.controlador;


import com.bryan.simpleCrud.modelo.Empleado;
import com.bryan.simpleCrud.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepository repository;

    @GetMapping("empleados")
    public List<Empleado> listarEmpleados(){
        return repository.findAll();
    }

    @PostMapping("empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado){
        return repository.save(empleado);
    }
}
