package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.enums.QuestionType;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.question.converter.QuestionDtoConverter;
import com.group11.schoolmanagementsystem.question.dto.CreateQuestionDto;
import com.group11.schoolmanagementsystem.question.dto.QuestionDto;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private TaskRepository taskRepository;
    private QuestionDtoConverter questionDtoConverter;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, TaskRepository taskRepository, QuestionDtoConverter questionDtoConverter) {
        this.questionRepository = questionRepository;
        this.taskRepository = taskRepository;
        this.questionDtoConverter = questionDtoConverter;
    }

    public QuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
        Optional<Task> task = taskRepository.findById(createQuestionDto.getTaskId());
        if (task.isEmpty()) {
            throw new ApiRequestException("Task Not Found");
        }

        Question question = Question.builder()
                .task(task.get())
                .point(createQuestionDto.getPoint())
                .question(createQuestionDto.getQuestion())
                .type(QuestionType.valueOf(createQuestionDto.getType()))
                .build();

        List<Choice> choices = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();

        for (String c: createQuestionDto.getChoices()) {
            Choice choice = Choice.builder()
                    .choice(c)
                    .question(question)
                    .build();
            choices.add(choice);
        }

        for (String a: createQuestionDto.getAnswers()) {
            Answer answer = Answer.builder()
                    .answer(a)
                    .question(question)
                    .build();
            answers.add(answer);
        }

        question.setAnswers(answers);
        question.setChoices(choices);

        Question questionResult = questionRepository.save(question);
        return questionDtoConverter.questionToDto(questionResult);
    }

    public List<QuestionDto> getQuestionByTaskId(Long taskId) {
        Optional<List<Question>> questions = questionRepository.findQuestionsByTask_Id(taskId);
        if (questions.isEmpty()) {
            throw new ApiRequestException("Questions Not Found");
        }

        return questions.get().stream().map(q -> questionDtoConverter.questionToDto(q)).collect(Collectors.toList());
    }

    public QuestionDto delete(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new ApiRequestException("Question Not Found");
        }

        questionRepository.delete(question.get());

        return questionDtoConverter.questionToDto(question.get());
    }
}
