package com.group11.schoolmanagementsystem.question;

import com.group11.schoolmanagementsystem.enums.QuestionType;
import com.group11.schoolmanagementsystem.exception.ApiRequestException;
import com.group11.schoolmanagementsystem.question.converter.QuestionDtoConverter;
import com.group11.schoolmanagementsystem.question.dto.*;
import com.group11.schoolmanagementsystem.student.Student;
import com.group11.schoolmanagementsystem.student.StudentRepository;
import com.group11.schoolmanagementsystem.task.Task;
import com.group11.schoolmanagementsystem.task.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private TaskRepository taskRepository;
    private QuestionDtoConverter questionDtoConverter;
    private StudentRepository studentRepository;
    private StudentAnswerRepository studentAnswerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, TaskRepository taskRepository, QuestionDtoConverter questionDtoConverter, StudentRepository studentRepository, StudentAnswerRepository studentAnswerRepository, ModelMapper modelMapper) {
        this.questionRepository = questionRepository;
        this.taskRepository = taskRepository;
        this.questionDtoConverter = questionDtoConverter;
        this.studentRepository = studentRepository;
        this.studentAnswerRepository = studentAnswerRepository;
        this.modelMapper = modelMapper;
    }

    public List<QuestionDto> getQuestionByTaskId(Long taskId) {
        Optional<List<Question>> questions = questionRepository.findQuestionsByTask_Id(taskId);
        if (questions.isEmpty()) {
            throw new ApiRequestException("Questions Not Found");
        }

        for (Question q : questions.get()) {
            System.out.println(q.getChoices().toString());
        }

        return questions.get().stream().map(q -> questionDtoConverter.questionToDto(q)).collect(Collectors.toList());
    }

    @Transactional
    public QuestionDto delete(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (question.isEmpty()) {
            throw new ApiRequestException("Question Not Found");
        }

        questionRepository.delete(question.get());

        return questionDtoConverter.questionToDto(question.get());
    }

    public QuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
        Optional<Task> task = taskRepository.findById(createQuestionDto.getTaskId());
        if (task.isEmpty()) {
            throw new ApiRequestException("Task Not Found");
        }

        List<ChoiceDto> choiceDtos = createQuestionDto.getChoices();
        Question question = Question.builder()
                .type(QuestionType.valueOf(createQuestionDto.getType()))
                .point(createQuestionDto.getPoint())
                .question(createQuestionDto.getQuestion())
                .task(task.get())
                .choices(choiceDtos.stream().map(c -> {
                    Choice choice = Choice.builder()
                            .choice(c.getChoice())
                            .isAnswer(c.isAnswer())
                            .build();
                    return choice;
                }).collect(Collectors.toList()))
                .build();

        questionRepository.save(question);

        return questionDtoConverter.questionToDto(question);
    }

    public QuestionDto update(Long id, CreateQuestionDto createQuestionDto) {
        Optional<Task> task = taskRepository.findById(createQuestionDto.getTaskId());
        if (task.isEmpty()) {
            throw new ApiRequestException("Task Not Found");
        }

        List<ChoiceDto> choiceDtos = createQuestionDto.getChoices();
        Question question = Question.builder()
                .id(id)
                .type(QuestionType.valueOf(createQuestionDto.getType()))
                .point(createQuestionDto.getPoint())
                .question(createQuestionDto.getQuestion())
                .task(task.get())
                .choices(choiceDtos.stream().map(c -> {
                    Choice choice = Choice.builder()
                            .choice(c.getChoice())
                            .isAnswer(c.isAnswer())
                            .build();
                    return choice;
                }).collect(Collectors.toList()))
                .build();

        questionRepository.save(question);

        return questionDtoConverter.questionToDto(question);
    }


    public List<StudentAnswerResultDto> answerQuestion(Long studentId, AnswerQuestionDto answerQuestionDto) {
        List<StudentAnswerDto> studentAnswerDtos = answerQuestionDto.getAnswers();
        List<Long> questionIds = new ArrayList<>();
        HashMap<Long, Long> studentAnswersMap = new HashMap<>();
        int score = 0;
        List<StudentAnswer> studentAnswers = new ArrayList<>();

        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new ApiRequestException("Student Not Found");
        }

        // Get all question id
        for (StudentAnswerDto s : studentAnswerDtos) {
            questionIds.add(s.getQuestionId());
            studentAnswersMap.put(s.getQuestionId(), s.getChoiceId());
        }

        // Query questions
        Optional<List<Question>> questions = questionRepository.findQuestionsByIdList(questionIds);
        if (questions.isEmpty()) {
            throw new ApiRequestException("Questions Not Found");
        }

        for (Question q : questions.get()) {
            if (q.getType() == QuestionType.OPEN) {
                continue;
            }

            // entity
            StudentAnswer studentAnswer = StudentAnswer.builder()
                    .student(student.get())
                    .question(q)
                    .build();

            for (Choice c : q.getChoices()) {
                boolean isStudentsAnswer = c.getId() == studentAnswersMap.get(q.getId());
                if (isStudentsAnswer) {
                    studentAnswer.setChoice(c);
                }
                if (c.isAnswer() == true && isStudentsAnswer) {
                    score++;
                    studentAnswer.setCorrect(true);
                    break;
                }
            }

            studentAnswers.add(studentAnswer);
        }

        List<StudentAnswer> studentAnswer = studentAnswerRepository.saveAll(studentAnswers);

        return studentAnswer.stream().map(s -> modelMapper.map(s, StudentAnswerResultDto.class)).collect(Collectors.toList());
    }

}
