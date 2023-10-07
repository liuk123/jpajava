package com.example.demo.service;
import com.example.demo.db.model.UserContribution;
import com.example.demo.db.repository.UserContributionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserContributionService {
    private final UserContributionRepository userContributionRepository;

    public UserContributionService(UserContributionRepository userContributionRepository) {
        this.userContributionRepository = userContributionRepository;
    }

    public UserContribution get(Long id){
        return this.userContributionRepository.findFirstById(id);
    }
    public UserContribution save(UserContribution userContribution){
        return this.userContributionRepository.save(userContribution);
    }
    public void delOne(Long id){
        this.userContributionRepository.deleteById(id);
    }
}
