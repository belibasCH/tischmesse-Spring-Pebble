package com.example.Tischmesse.service;

import com.example.Tischmesse.model.Exhibitor;
import com.example.Tischmesse.model.User;
import com.example.Tischmesse.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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

    public void addUser(String username, String pw, List<Exhibitor> exhibitors) {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        var newUser = new User(username, encoder.encode(pw), Set.of("EDITOR"), exhibitors);
            repo.save(newUser);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public void editUser(int id, String username, String pw) {
        var encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User currentUser = repo.findById(id).orElse(new User(username, pw, Set.of("EDITOR"), Collections.emptyList()));
        currentUser.setPassword(encoder.encode(pw));
        currentUser.setUsername(username);
    }

    public Optional<User> findUserById(int id) {
       return repo.findById(id);
    }

    public Optional<User> findUser(String name) {
        System.out.println(name);
        return repo.findByUsername(name);
    }

}
