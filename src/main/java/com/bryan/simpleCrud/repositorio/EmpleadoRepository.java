package com.bryan.simpleCrud.repositorio;

import com.bryan.simpleCrud.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
