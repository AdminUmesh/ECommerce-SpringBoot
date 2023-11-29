package com.umesh.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.umesh.demo.Model.UserDetails;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        return "index.html";
    }

    @GetMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout.html";
    }

    @GetMapping("/register")
    public String Register() {
        return "register.html";
    }

    @GetMapping("/user")
    public String user() {
        return "user.html";
    }

    @GetMapping("/demo")
    public String demo() {
        return "demo.html";
    }

    @GetMapping("/demoo")
    public String demoo() {
        return "demoo.html";
    }

    @GetMapping("/products")
    public String products() {
        return "products.html";
    }

    @GetMapping("/cards")
    public String cards() {
        return "Cards.html";
    }

    @GetMapping("/update")
    public String update(@ModelAttribute UserDetails userDetails) {

        // Your logic for updating the user or preparing data

        return "update.html";
    }

}