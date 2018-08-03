package com.app.demo.controller;

import com.app.demo.model.*;
import com.app.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/users/")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/getData")
    public List<User> getData(){
        return apiService.getInfo();
    }

    @PostMapping("/postData")
    public User postData(@RequestBody User user){
        return apiService.saveData(user);
    }

    @PatchMapping("/patchData/{id}")
    public User getPatchData(@RequestBody User user, @PathVariable Long id){
        return apiService.patchData(user, id);
    }

    @DeleteMapping("/deleteData/{id}")
    public void deleteData(@PathVariable Long id){
        apiService.deleteData(id);
    }

}
