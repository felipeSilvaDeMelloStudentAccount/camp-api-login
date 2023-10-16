package camp.api.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        //InMemoryUserDetailsManager
        UserDetails user1 = User.withUsername("user1")
                .password(encoder().encode("userPass"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(encoder().encode("user2Pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);

    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // http builder configurations for authoize request and form login
        return http.build();
    }

    /**
     * BCrypt, however, will internally generate a random salt instead.
     * This is important to understand because it means that each call will have a different result,
     * so we only need to encode the password once.
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
