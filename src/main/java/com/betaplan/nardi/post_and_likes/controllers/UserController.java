package com.betaplan.nardi.post_and_likes.controllers;

import com.betaplan.nardi.post_and_likes.models.User;
import com.betaplan.nardi.post_and_likes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "registrationPage";
    }


    @PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, Model model) {
        if(!user.getPassword().equals(user.getPasswordConfirmation())){
            model.addAttribute("error", "Passwords do not match!");
            return "registrationPage";
        }

        if(userService.findByEmail(user.getEmail())!=null){
            model.addAttribute("error", "This user already exists!");
            return "registrationPage";
        }

        if(result.hasErrors()){
            return "registrationPage";
        }else{
            userService.registerUser(user);
            session.setAttribute("userId", user.getId());
            return "redirect:/home";
        }
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("user") User user,Model model) {
        model.addAttribute("user",user);
        return "loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        boolean isAuthenticated = userService.authenticateUser(username, password);
        if(isAuthenticated){
            Long userId = userService.findByUsername(username).getId();
            session.setAttribute("userId", userId);
            return "redirect:/home";
        }else{
            model.addAttribute("error", "The email username or password is incorrect");
            return "loginPage";
        }
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        Long userId = ((Long) session.getAttribute("userId"));
        if(userId==null){
            return "redirect:/login";
        }else{
        User authenticatedUser = userService.findUserById(userId);
        model.addAttribute("user", authenticatedUser);
        return "homePage";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/login";
    }
}