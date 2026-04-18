package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Auth.User;
import com.jhonathan.loja3d.domain.Auth.UserRole;
import com.jhonathan.loja3d.dto.UserDTO;
import com.jhonathan.loja3d.exception.ResourceNotFoundException;
import com.jhonathan.loja3d.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAll(){
        List<User> users = userRepo.findAll();
        return users;
    }

    public User findId(Long id){
        User find = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found!"));
        return find;
    }

    public User createU(UserDTO dto){
        User user = new User();


        if (
                dto.getName() == null
                && dto.getEmail() == null
                && dto.getPassword() == null
        ){
            throw new ResourceNotFoundException("Preencha todos os campos!");
        } else if (dto.getEmail().isEmpty()) {
            throw new ResourceNotFoundException("Email ja em uso!");
        }else {

            user.setName(dto.getName());
            user.setEmail(dto.getEmail());
            user.setPassword(dto.getPassword());
            user.setRole(UserRole.CLIENT);
            user.setCreatAt(LocalDateTime.now());
        }

        return userRepo.save(user);
    }

    public User updateU(Long id, UserDTO dto){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (dto.getName() != null){
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null){
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null){
            user.setPassword(dto.getPassword());
        }

        return userRepo.save(user);
    }

    public void deleteById(Long id){
        User deleted = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found1"));
        userRepo.delete(deleted);
    }
}
