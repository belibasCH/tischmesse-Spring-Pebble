package com.example.Tischmesse.service;

import com.example.Tischmesse.model.User;
import com.example.Tischmesse.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<User> findAllUsers() {
        return repo.findAll();

    }

    public void addUser(String username, String pw) {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var newUser = new User(username, encoder.encode(pw), Set.of("EDITOR"));
            repo.save(newUser);

    }

    public void deleteUser(int id) {
        repo.delete(repo.findById(id).orElseThrow(UserNotFound::new));
    }

    public void editUser(int id, String username, String pw) {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User currentUser = repo.findById(id).orElseThrow(UserNotFound::new);
        currentUser.setPassword(encoder.encode(pw));
        currentUser.setUsername(username);
    }

    public User findUserById(int id) {
       return repo.findById(id).orElseThrow(UserNotFound::new);
    }

    private static class UserNotFound extends RuntimeException {
    }
}
