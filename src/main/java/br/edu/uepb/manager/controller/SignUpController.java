package br.edu.uepb.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.uepb.manager.dto.UserDTO;
import br.edu.uepb.manager.mapper.UserMapper;
import br.edu.uepb.manager.services.user.UserService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api/v1/signup", produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@Api(value = "Sign Up", tags = "Sign Up")
public class SignUpController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping
    public void signUp(@RequestBody UserDTO userDTO){
        userService.signUpUser(userMapper.convertFromUserDTO(userDTO));
    }
}