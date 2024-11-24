package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // CRUD
    // Get
    @GetMapping("/getUsers")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    // Post
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("New User Added."));
    }

    // Update
    @PutMapping("/updateUser/{ID}")
    public ResponseEntity updateUser(@PathVariable String ID, @RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(userService.updateUser(ID,user)){
            return ResponseEntity.status(200).body(new ApiResponse("User Updated."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found."));
    }

    // Delete
    @DeleteMapping("/deleteUser/{ID}")
    public ResponseEntity deleteUser(@PathVariable String ID){
        if(userService.deleteUser(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID Not Found."));
    }

    // getUsersByBalance
    @GetMapping("/getUsersByBalance/{balance}")
    public ResponseEntity getUsersByBalance(@PathVariable double balance){
        if(userService.getUsersByBalance(balance) != null){
            return ResponseEntity.status(200).body(userService.getUsersByBalance(balance));
        }
        return ResponseEntity.status(400).body(new ApiResponse("There Are No Users With This Balance Or Above."));
    }

    // getUsersByAge
    @GetMapping("/getUsersByAge/{age}")
    public ResponseEntity getUsersByAge(@PathVariable int age){
        if(userService.getUsersByAge(age) != null){
            return ResponseEntity.status(200).body(userService.getUsersByAge(age));
        }
        return ResponseEntity.status(400).body(new ApiResponse("There Are No Users With This Age Or Above."));
    }
}