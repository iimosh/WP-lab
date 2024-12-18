package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.User;
import mk.ukim.finki.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.exceptions.InvalidUserCredentialException;
import mk.ukim.finki.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.lab.model.exceptions.UsernameExistsException;
import mk.ukim.finki.lab.repository.jpa.UserRepository;
import mk.ukim.finki.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User login(String username, String password) {
        if(username == null || password == null || username.isEmpty() || password.isEmpty())
            throw new InvalidArgumentsException();
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialException::new);
    }


}
