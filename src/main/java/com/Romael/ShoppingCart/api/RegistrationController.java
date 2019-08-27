package com.Romael.ShoppingCart.api;

import com.Romael.ShoppingCart.model.User;
import com.Romael.ShoppingCart.service.UserService;
import com.Romael.ShoppingCart.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/registration");

        return modelAndView;
    }

    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public ModelAndView createNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists != null){
            bindingResult.
                    rejectValue("email", "error.user", "User already registered with this email");
        }


        if(bindingResult.hasErrors()){
            modelAndView.setViewName("/registration");
        }

            userService.saveUser(user);

            modelAndView.addObject("successMsg", "User successfully registered");
            modelAndView.addObject("user",new User());
            modelAndView.setViewName("/registration?success");

        return modelAndView;
    }
}

