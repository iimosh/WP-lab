package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.User;

public interface AuthService {

    User login(String username, String password);
    //User register(String username, String password, String repeatpassword,String firstName, String lastName);
}
