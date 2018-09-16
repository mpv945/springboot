package org.haijun.study.batchJob.controller;

import org.haijun.study.batchJob.model.User;
import org.haijun.study.batchJob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "list")
    public String listUsers(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model){
        Page<User> useres =  userService.findAll(page,size);
        model.addAttribute("users",useres.getContent());
        return "userList";
    }
}
