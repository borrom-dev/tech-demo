package com.angkorsuntrix.techdemo.repository;

import com.angkorsuntrix.techdemo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
