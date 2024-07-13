package learnJwt.app.Config;
import learnJwt.app.Services.JwtAuthenticationFilter;
import learnJwt.app.Services.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyUserDetailService myUserDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(MyUserDetailService myUserDetailService ,JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.myUserDetailService = myUserDetailService;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }

    @Bean
public SecurityFilterChain configure(HttpSecurity http) throws Exception {

    http.csrf((AbstractHttpConfigurer::disable)).authorizeHttpRequests((athrz) ->  {
        athrz.requestMatchers("/v2/api-docs" ,
                        "/v3/api-docs" ,
                        "/swagger-resources/**" ,
                        "/swagger-ui.html" ,
                        "/swagger-ui/**" ,
                        "/webjars/**" ,
                        "/v3/api-docs/swagger-config")
                .permitAll();
        athrz.requestMatchers("/authenticate","/registerUser","/swagger-ui.html" ).permitAll();
        athrz.requestMatchers("/products","postProduct","/uploadImage").hasAnyRole("USER","ADMIN");
        athrz.anyRequest().authenticated();
    } );
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
}


@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
   provider.setUserDetailsService(myUserDetailService);
    provider.setPasswordEncoder(passwordEncoder());

return provider;
}



@Bean
public AuthenticationManager authenticationManager(){
    return new ProviderManager(authenticationProvider());
}



//this is for encypting and decrypting the password
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}



@Bean
public UserDetailsService userDetailsService(){
    return myUserDetailService;
}


}
