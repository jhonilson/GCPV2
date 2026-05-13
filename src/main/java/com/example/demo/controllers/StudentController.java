package com.example.demo.controllers;

import com.example.demo.services.StudentService;
import com.example.demo.student.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/api/estudiante")
public class StudentController {

    @Autowired
    private  StudentService studentService;


    @GetMapping
    public String hello(){
        return "hola mundo";
    }

    @GetMapping("/listar")
    public List<Estudiante> listar(){
        return studentService.listarEstudiante();
    }

    @GetMapping("/listarTodos")
    public List<Estudiante> listarTodos(){
        return studentService.listarTodos();
    }

}
