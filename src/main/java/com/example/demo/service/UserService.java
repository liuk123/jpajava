package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.model.UserContribution;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(Long id){
        User account = this.userRepository.findFirstById(id);
        account.setContributions(account.getUserContributions().stream().map(UserContribution::getRepository).toList());
        return account;
    }
    public void delOne(Long id){
        this.userRepository.deleteById(id);
    }
}
