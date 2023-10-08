package com.example.demo.service;
import com.example.demo.db.model.User;
import com.example.demo.db.model.UserContribution;
import com.example.demo.db.repository.UserRepository;
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

//    @Transactional(readOnly = true)
    public User getUserById(Long id){
        User user = this.userRepository.findFirstById(id);
        user.setContributions(user.getUserContributions().stream().map(UserContribution::getRepository).toList());
        return user;
    }
    public void save(User user){
        this.userRepository.save(user);
    }
    public void delOne(Long id){
        this.userRepository.deleteById(id);
    }


    public User getUserByUsername(String username){
        User user = this.userRepository.findByUsername(username);
        user.setContributions(user.getUserContributions().stream().map(UserContribution::getRepository).toList());
        return user;
    }
}
