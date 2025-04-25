package com.javatecharc.learning.controller;

import com.javatecharc.learning.demo.Student;
import com.javatecharc.learning.exception.SpringLearningException;
import com.javatecharc.learning.service.MathsOperationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maths")
public class MathematicsOperationController {
    /*@Autowired
    private MathsOperationService mathsOperationService;*/

    private final MathsOperationService mathsOperationService;

    private final Student student;

    public MathematicsOperationController(MathsOperationService mathsOperationService, Student student) {
        this.mathsOperationService = mathsOperationService;
        this.student = student;
    }

    @GetMapping("/add")
    @Operation(summary = "Addition of two numbers")
    public ResponseEntity<Integer> add(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b) {
        Integer c = mathsOperationService.mathOperation(a, b, "add");
         mathsOperationService.abc();
         student.registerStudent();
        System.out.println("Addition of two numbers is: " + c);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @GetMapping("/sub")
    public String sub() {
        return "sub";
    }

    @GetMapping("/mul")
    public String mul() {
        return "mul";
    }

    @GetMapping("/division")
    public ResponseEntity<Integer> div(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b) {
        int div = 0;

        try {
            div = mathsOperationService.mathOperation(a, b, "div");
        } catch (Exception exception) {
            System.out.println(exception);
            throw new SpringLearningException("Hey Mr. XYZ, you have passed input, division by zero, which is not allowed.");
        }

        return new ResponseEntity<>(div, HttpStatus.OK);
    }
}
