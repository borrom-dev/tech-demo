package com.angkorsuntrix.techdemo.repository;

import com.angkorsuntrix.techdemo.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findByQuestionId(Long questionId);
}
