package com.example.demo.services;

import com.example.demo.repositories.StudentRepository;
import com.example.demo.student.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Estudiante> listarEstudiante(){
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante estudiante1 = new Estudiante(1L,"john","jhon@otnf.com", LocalDate.now(),21);
        Estudiante estudiante2 = new Estudiante(2L,"juan","xxss@otnf.com", LocalDate.now(),35);
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        return estudiantes;
    }

    public List<Estudiante> listarTodos(){
        List<Estudiante> estudiantes = new ArrayList<>();
        studentRepository.findAll().forEach(estudiantes::add);
        return estudiantes;
    }

}
