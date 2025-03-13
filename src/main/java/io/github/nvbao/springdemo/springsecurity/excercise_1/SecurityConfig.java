package io.github.nvbao.springdemo.springsecurity.excercise_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     *
     * @param http The HttpSecurity object to configure security settings.
     * @return A configured SecurityFilterChain instance.
     * @throws Exception If an error occurs during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Allows access to "/admin/**" only for users with the "ADMIN" role.
                        .requestMatchers("/user/**").hasRole("USER") // Allows access to "/user/**" only for users with the "USER" role.
                        .anyRequest().authenticated() // Requires authentication for all other requests.
                )
                .formLogin(withDefaults()) // Enables default form-based login authentication.
                .logout(LogoutConfigurer::permitAll); // Allows all users to log out.

        return http.build(); // Builds and returns the configured security filter chain.
    }

    /**
     * Defines an in-memory user details service with predefined users.
     *
     * @return A UserDetailsService containing user credentials.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        /**
         * Creates an admin user with username "admin" and password "admin123".
         * The user has the role "ADMIN".
         */
        UserDetails admin = User.withDefaultPasswordEncoder() // Creates a user with an encoded password.
                .username("admin") // Sets the username as "admin".
                .password("admin123") // Sets the password as "admin123".
                .roles("ADMIN") // Assigns the "ADMIN" role to this user.
                .build();

        /**
         * Creates a regular user with username "user" and password "user123".
         * The user has the role "USER".
         */
        UserDetails user = User.withDefaultPasswordEncoder() // Creates another user.
                .username("user") // Sets the username as "user".
                .password("user123") // Sets the password as "user123".
                .roles("USER") // Assigns the "USER" role to this user.
                .build();

        return new InMemoryUserDetailsManager(admin, user); // Stores the users in memory for authentication.
    }
}
