package mk.ukim.finki.lab.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path={"/","","/home"})
public class HomeController {
    @GetMapping
    public String getHomePage() {
        return "redirect:/events";
    }
    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        //model.addAttribute("bodyContent", "access-denied");
        return "access-denied";
    }
}
