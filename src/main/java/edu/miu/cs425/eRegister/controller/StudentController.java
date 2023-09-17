package edu.miu.cs425.eRegister.controller;

import edu.miu.cs425.eRegister.model.Student;
import edu.miu.cs425.eRegister.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/add";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "student/edit";
    }

    @PostMapping("/students/edit/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute("student") Student student) {

        if (!id.equals(student.getStudentId())) {
            throw new IllegalArgumentException("Student ID mismatch");
        }

        studentRepository.save(student);
        return "redirect:/students/list";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students/list";
    }

    @GetMapping("/students/list")
    public String listStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "student/list";
    }
}
