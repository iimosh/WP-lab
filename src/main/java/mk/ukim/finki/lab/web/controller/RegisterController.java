package mk.ukim.finki.lab.web.controller;

import mk.ukim.finki.lab.model.enumerations.Role;
import mk.ukim.finki.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.lab.service.AuthService;
import mk.ukim.finki.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final AuthService authService;

    public RegisterController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public String getRegister(@RequestParam(required = false) String error, Model model) {
       if (error != null && !error.isEmpty()) {
           model.addAttribute("hasError", true);
           model.addAttribute("error", error);

       }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname) {
        try{
    this.userService.register(username, password, repeatedPassword, name, surname, Role.ROLE_USER);
    return "redirect:/login";}
        catch (PasswordsDoNotMatchException | InvalidArgumentsException e){
            return "redirect:/register?error"+e.getMessage();
        }
    }
}
