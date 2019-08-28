package com.Romael.ShoppingCart.api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class LoginController {

    @RequestMapping(value = "/login")
    public String login(Principal principal) {

        if(principal != null){
            return "redirect:/products";
        }
        return "/login";
    }
}
