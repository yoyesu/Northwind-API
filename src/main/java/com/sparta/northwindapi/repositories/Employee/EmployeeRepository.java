package com.sparta.northwindapi.repositories.Employee;

import com.sparta.northwindapi.entities.Employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
