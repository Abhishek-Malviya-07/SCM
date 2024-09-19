package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



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
    public String processRegister(@Valid @ModelAttribute UserForm userForm , BindingResult bindingResult , HttpSession session){
        //fetch form data
        //user form
        System.out.println(userForm);
        // validate form data

        if(bindingResult.hasErrors()){
        
            return "register";
        }
        //save to databse

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("/static/images/progilepic.png");
        User savedUser =  userService.saveUser(user);
        System.out.println("User is Saved");

        Message message = Message.builder()
        .content("Registration Successful")
        .type(MessageType.blue)  // You can omit this line to use the default blue type
        .build();
       
        session.setAttribute("message", message);

        

        //rediret to another page
        return "redirect:/register";
    } 
    
}
