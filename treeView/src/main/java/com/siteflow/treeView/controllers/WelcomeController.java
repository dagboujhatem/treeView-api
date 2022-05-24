package com.siteflow.treeView.controllers;

import com.siteflow.treeView.playload.responses.ApiResponse;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ApiResponse<Object> welcome(){
        return new ApiResponse<>(200, "Welcome to siteflow API." ,null);
    }
}
