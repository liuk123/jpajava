package com.example.demo.repository;

import com.example.demo.db.UserContribution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContributionRepository extends JpaRepository<UserContribution, Long> {
    UserContribution findFirstById(Long id);
}
