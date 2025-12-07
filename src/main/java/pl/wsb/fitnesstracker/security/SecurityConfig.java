package pl.wsb.fitnesstracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Wyłączamy CSRF dla REST API (ułatwia testy w Postmanie)
                .authorizeHttpRequests(auth -> auth
                        // 1. Actuator dostępny dla wszystkich (bez logowania)
                        .requestMatchers("/actuator/**").permitAll()

                        // 2. Metody GET dostępne dla każdego zalogowanego użytkownika (USER lub ADMIN)
                        .requestMatchers(HttpMethod.GET, "/v1/users/**").authenticated()

                        // 3. Metody modyfikujące (POST, PUT, DELETE) tylko dla ADMINA
                        .requestMatchers(HttpMethod.POST, "/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/users/**").hasRole("ADMIN")

                        // Wszystkie inne żądania muszą być uwierzytelnione
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()); // Używamy Basic Auth

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        // Użytkownik zwykły (tylko odczyt)
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("user123"))
                .roles("USER")
                .build();

        // Administrator (pełny dostęp)
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}