package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1,
                "Ramesh",
                "Fadatare"
        );

        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok().header("custom-header", "remesh")
                .body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "a", "b"));
        students.add(new Student(2, "c", "d"));
        students.add(new Student(3, "e", "f"));
        students.add(new Student(4, "g", "h"));

        return ResponseEntity.ok(students);
    }


    // spring boot rest api with path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/ramesh/fada
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                                       @PathVariable("first-name") String firstName,
                                                       @PathVariable("last-name") String lastName) {
        return ResponseEntity.ok(new Student(studentId, firstName, lastName));
    }

    // spring boot rest api with Request param
    // http://localhost:8080/students/query?id=1&firstName=a&lastName=b
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                                          @RequestParam("first-Name") String firstName,
                                                          @RequestParam String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // spring boot rest api that handles http post request - creating new resource
    // @PostMapping and @RequestBody

    @PostMapping("create")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // spring boot rest api that handles PUT request - updating existing resource
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentID) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // spring boot delete request
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        System.out.println(studentId);
        return "Successfully delete the student";
    }
}
