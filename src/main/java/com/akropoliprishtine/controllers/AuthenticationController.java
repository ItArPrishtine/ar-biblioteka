package com.akropoliprishtine.controllers;

import com.akropoliprishtine.entities.ApplicationUser;
import com.akropoliprishtine.entities.JwtRequest;
import com.akropoliprishtine.entities.JwtResponse;
import com.akropoliprishtine.entities.book.Borrow;
import com.akropoliprishtine.services.ApplicationUserService;
import com.akropoliprishtine.services.book.BorrowService;
import com.akropoliprishtine.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ApplicationUserService userService;

    @Autowired
    private BorrowService borrowService;

    @GetMapping("/p1/testheroku")
    public String testHeroku() {
        return "App is deplyed successfully!!";
    }

    @PostMapping(value = "/p1/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        try {

            String usernameOrEmail = authenticationRequest.getUsername();
            Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

            ApplicationUser userByEmail;
            String username;

            if (emailPattern.matcher(usernameOrEmail).matches()) {
                userByEmail = userService.findByEmail(usernameOrEmail);
                username = userByEmail.getUsername();
                authenticate(userByEmail.getUsername(), authenticationRequest.getPassword());
            } else {
                username = authenticationRequest.getUsername();
                authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            }

            ApplicationUser user = userService.loadUserByUsernameAndPassword(
                    username,
                    authenticationRequest.getPassword());

            Borrow borrow = borrowService.getBorrowedBookOfUser(user);

            final String token = jwtTokenUtil.generateToken(user, borrow);

            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<?>) ResponseEntity.notFound();
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
