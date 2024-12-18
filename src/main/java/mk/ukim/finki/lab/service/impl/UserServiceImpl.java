package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.model.User;
import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.lab.model.exceptions.UsernameExistsException;
import mk.ukim.finki.lab.repository.jpa.UserRepository;
import mk.ukim.finki.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatpassword, String firstName, String lastName, Role role) {
        if(username == null || password == null || repeatpassword == null || firstName == null || lastName == null)
            throw new InvalidArgumentsException();
        if(!password.equals(repeatpassword))
            throw new PasswordsDoNotMatchException();

        if(this.userRepository.findByUsername(username).isPresent() ||
                !this.userRepository.findByUsername(username).isEmpty())
            throw new UsernameExistsException(username);

        User user=new User(username,passwordEncoder.encode(password),firstName,lastName, role);

        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
