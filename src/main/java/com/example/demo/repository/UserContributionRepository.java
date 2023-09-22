package com.example.demo.repository;

import com.example.demo.model.UserContribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserContributionRepository extends JpaRepository<UserContribution, Long> {
    UserContribution findFirstById(Long id);
}
