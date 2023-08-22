package saboroso.saborosoburguer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.user.InputUserDTO;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(InputUserDTO userDTO) {
        UserEntity newUser = new UserEntity(userDTO);
        userRepository.save(newUser);
    }
}
