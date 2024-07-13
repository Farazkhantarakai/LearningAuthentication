package learnJwt.app.Services;


import learnJwt.app.Modle.MyUser;
import learnJwt.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<MyUser> user=    userRepository.findByUsername(username);

    if(user.isPresent()) {

   var userObj=user.get();

   return User.builder().username(userObj.getUsername()).password(userObj.getPassword()).roles(getRole(userObj)).build();
    }else {
        throw new UsernameNotFoundException(username);
    }
    }


    private String[]   getRole(MyUser user) {

        if(user.getRoles()==null ) {
            return new String[]{"User"};
        }else {
         return    user.getRoles().split(",");
        }

    }


}
