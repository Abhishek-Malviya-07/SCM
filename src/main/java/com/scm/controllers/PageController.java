package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.services.UserService;



@Controller
public class PageController {

    private final UserService userService;
    @Autowired
    public PageController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page Handler :");
        model.addAttribute("name", "Abhishek Malviya is Here");
        model.addAttribute("description", "I am a Engineer");
        return "home";
    }

    
    @RequestMapping("/about")
    public String about(){
        System.out.println("about page loader");
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        System.out.println("services page loader");
        return "services";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "Contact";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
          UserForm userForm = new UserForm();
          model.addAttribute( "userForm", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@ModelAttribute UserForm userForm){
        //fetch form data
        //user form
        System.out.println(userForm);
        // validate form data
        //save to databse

        //userService
      // user form se data nikal kr user me dala he
        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .phoneNumber(userForm.getPhoneNumber())
        .about(userForm.getAbout())
        .profilePic("/static/images/progilepic.png")
        .build();
        
       User Saveduser =  userService.saveUser(user);
       System.out.println("User is Saved");
       return "redirect:/login";
    }
    
}
