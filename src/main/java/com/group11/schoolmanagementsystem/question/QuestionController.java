package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.question.dto.AnswerQuestionDto;
import com.group11.schoolmanagementsystem.question.dto.CreateQuestionDto;
import com.group11.schoolmanagementsystem.question.dto.QuestionDto;
import com.group11.schoolmanagementsystem.question.dto.StudentAnswerResultDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@CrossOrigin
@Api(tags = "Task Question")
public class QuestionController {
    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/create")
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody CreateQuestionDto createQuestionDto) {
        QuestionDto questionDto = questionService.createQuestion(createQuestionDto);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTaskId(@PathVariable("id") Long id) {
        List<QuestionDto> questionDtos = questionService.getQuestionByTaskId(id);
        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable("id") Long id, @RequestBody CreateQuestionDto createQuestionDto) {
        QuestionDto questionDto = questionService.update(id, createQuestionDto);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<QuestionDto> deleteQuestionById(@PathVariable("id") Long id) {
        QuestionDto questionDto = questionService.delete(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @PostMapping("/answer/{studentId}")
    public ResponseEntity<List<StudentAnswerResultDto>> answerQuestion(@PathVariable("studentId") Long studentId, @RequestBody AnswerQuestionDto answerQuestionDto) {
        List<StudentAnswerResultDto> answerResultDto = questionService.answerQuestion(studentId, answerQuestionDto);
        return new ResponseEntity<>(answerResultDto, HttpStatus.OK);
    }

}
