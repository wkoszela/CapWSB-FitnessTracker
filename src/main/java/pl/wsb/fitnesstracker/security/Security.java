package pl.wsb.fitnesstracker.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class Security {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password") // {noop} oznacza brak szyfrowania
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}adminpass")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // dla REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/v1/users/**").hasAnyRole("USER", "ADMIN")
                        // POST, PUT, PATCH, DELETE tylko dla ADMIN
                        .requestMatchers(HttpMethod.POST, "/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/users/**").hasRole("ADMIN")// Actuator bez auth
                        .anyRequest().authenticated() // reszta wymaga auth
                )
                .httpBasic(Customizer.withDefaults()); // włącza Basic Auth

        return http.build();
    }

}
