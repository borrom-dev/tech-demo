package com.angkorsuntrix.techdemo.controller;

import com.angkorsuntrix.techdemo.entity.Question;
import com.angkorsuntrix.techdemo.exception.ResourceNotFoundException;
import com.angkorsuntrix.techdemo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/questions")
    public Page<Question> getAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @PostMapping("/questions")
    public Question create(@Valid @RequestBody Question question) {
        return questionRepository.save(question);
    }

    public Question update(@PathVariable Long id, @Valid @RequestBody Question question) {
        return questionRepository.findById(id)
                .map(question1 -> {
                    question.setTitle(question.getTitle());
                    question.setDescription(question.getDescription());
                    return questionRepository.save(question);
                }).orElseThrow(()-> new ResourceNotFoundException("Not found"));
    }

    @DeleteMapping("/questions/{id}")
    public HttpEntity delete(@PathVariable Long id) {
        return questionRepository.findById(id)
                .map(question -> {
                    questionRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("Not found"));
    }
}
