package com.hdstudents.hds.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (("admin".equals(username) && "Matej1709".equals(password)) || ("guest".equals(username) && "Pass0000".equals(password))) {
            session.setAttribute("authenticated", true);
            return ResponseEntity.ok("You are logged in");
        } else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout Successful");
    }

}

@Getter
@Setter
class LoginRequest {
    private String username;
    private String password;
}

