package com.example.demo.db.repository;

import com.example.demo.db.model.UserContribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContributionRepository extends JpaRepository<UserContribution, Long> {
    UserContribution findFirstById(Long id);
}
