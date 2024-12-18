package mk.ukim.finki.lab.web.controller;




import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.lab.model.User;
import mk.ukim.finki.lab.model.exceptions.InvalidUserCredentialException;
import mk.ukim.finki.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller//vrakja view, restcontreoller vrakja podatoci.
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //@RequestMapping(method = RequestMethod.GET, value = "")
    @GetMapping
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user=null;
        try{
            user=this.authService.login(request.getParameter("username"),request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/events";
        }
        catch (InvalidUserCredentialException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

}
