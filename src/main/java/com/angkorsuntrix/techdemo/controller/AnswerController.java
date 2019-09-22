package com.angkorsuntrix.techdemo.controller;

import com.angkorsuntrix.techdemo.entity.Answer;
import com.angkorsuntrix.techdemo.exception.ResourceNotFoundException;
import com.angkorsuntrix.techdemo.repository.AnswerRepository;
import com.angkorsuntrix.techdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions/{id}/answers")
    public List<Answer> getByQuestionId(@PathVariable Long id) {
        return answerRepository.findByQuestionId(id);
    }

    @PostMapping("/questions/{id}/answers")
    public Answer create(@PathVariable Long id, @Valid @RequestBody Answer answer) {
        return questionRepository.findById(id)
                .map(question -> {
                    answer.setQuestion(question);
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    @PutMapping("/questions/{questionId}/answers/{answerId}")
    public Answer update(@PathVariable Long questionId, @PathVariable Long answerId, @Valid @RequestBody Answer request) {
        if (answerRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Not found");
        }
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(request.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }

    public HttpEntity delete(@PathVariable Long questionId, @PathVariable Long answerId) {
        if (!questionRepository.existsById(questionId)) {
            throw new ResourceNotFoundException("Not found");
        }
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Not found"));
    }
}
