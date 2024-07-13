package learnJwt.app.Controller;

import learnJwt.app.Modle.MyUser;
import learnJwt.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterationController {
    @Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


   @PostMapping(
           "/registerUser")
    public MyUser  registerUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}


