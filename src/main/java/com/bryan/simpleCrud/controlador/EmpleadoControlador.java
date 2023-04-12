package com.bryan.simpleCrud.controlador;


import com.bryan.simpleCrud.excepciones.ResourceNotFoundException;
import com.bryan.simpleCrud.modelo.Empleado;
import com.bryan.simpleCrud.repositorio.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
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

    @PutMapping("empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable long id, @RequestBody Empleado detallesEmpleado){
        Empleado empleado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no existe el id"));

        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizar = repository.save(empleado);
        return ResponseEntity.ok(empleadoActualizar);
    }

    @GetMapping("empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable long id){
        Empleado empleado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no existe el id"));
        return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleadoPorId(@PathVariable long id) {
        Empleado empleado = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no existe el id"));
        repository.delete(empleado);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
