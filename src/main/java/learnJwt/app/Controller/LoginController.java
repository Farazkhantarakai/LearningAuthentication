package learnJwt.app.Controller;


import learnJwt.app.Modle.Login;
import learnJwt.app.Services.JwtService;
import learnJwt.app.Services.MyUserDetailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//this will help us to authenticate the user with name and password
    private final AuthenticationManager authenticationManager;

//    this is for generating the token when user login
    private final JwtService jwtService;

//    this is giving the user details to the generator function
    private final MyUserDetailService userDetailService;

    LoginController(AuthenticationManager authenticationManager,JwtService jwtService,MyUserDetailService userDetailService){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
        this.userDetailService=userDetailService;
    }




    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody Login login) {
    Authentication authentication=   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.username(),login.password()));

    if(authentication.isAuthenticated()){
       return  jwtService.generateToken(userDetailService.loadUserByUsername(login.username()));
    }else{
        throw new UsernameNotFoundException("Invalid credentials");
    }



    }




}
