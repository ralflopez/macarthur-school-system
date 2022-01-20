package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.question.dto.CreateQuestionDto;
import com.group11.schoolmanagementsystem.question.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
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

    @GetMapping("/task")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTaskId(@RequestParam("taskId") Long taskId) {
        List<QuestionDto> questionDtos = questionService.getQuestionByTaskId(taskId);
        return new ResponseEntity<List<QuestionDto>>(questionDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<QuestionDto> deleteQuestionById(@PathVariable("id") Long id) {
        QuestionDto questionDto = questionService.delete(id);
        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

}
