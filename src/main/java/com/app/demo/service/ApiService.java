package com.app.demo.service;

import com.app.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ApiService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public List<User> getInfo() {

        String url = "http://localhost:8090/api/user/users";

        ResponseEntity<List<User>> result = restTemplateBuilder.build().
                exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});

        List<User> userList = new ArrayList<>();
        userList.addAll(result.getBody());

        return userList;
    }

    public User saveData(User requestUser) {

        String url = "http://localhost:8090/api/user/addUser";

        ResponseEntity<User> result = restTemplateBuilder.build().postForEntity(url, requestUser, User.class);

        User user = new User();
        user.setEmail(result.getBody().getEmail());
        user.setName(result.getBody().getName());

        return user;
    }

    public User patchData(User requestUser, Long id) {

        String url = "http://localhost:8090/api/user/patchUser/" + id;

        Object result = restTemplateBuilder.build().patchForObject(url, requestUser, User.class);

        User user = new User();

        if(result != null) {
            if (requestUser.getName() != null) {
                user.setName(((User) result).getName());
            }
            if (requestUser.getEmail() != null) {
                user.setEmail(((User) result).getEmail());
            }
        }
        return user;
    }

    public void deleteData(Long id) {

        String url = "http://localhost:8090/api/user/deleteUser/" + id;

        try{
            restTemplateBuilder.build().delete(url);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
