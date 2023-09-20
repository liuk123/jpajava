package com.example.demo.service;
import com.example.demo.model.User;
import com.example.demo.model.UserContribution;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User get(Long id){
        User user = this.userRepository.findFirstById(id);
        user.setContributions(user.getUserContributions().stream().map(UserContribution::getRepository).toList());
        return user;
    }
    public User save(User user){
        return this.userRepository.save(user);
    }
    public void delOne(Long id){
        this.userRepository.deleteById(id);
    }
}
