package saboroso.saborosoburguer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import saboroso.saborosoburguer.security.SecurityFilter;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    private final SecurityFilter securityFilter;
    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain setFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Autenticação:
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/token").permitAll()

                        // Usuários:
                        .requestMatchers(HttpMethod.POST, "/api/create-user").permitAll()

                        // Hambúrgueres:
                        .requestMatchers(HttpMethod.GET, "/api/highlight-burgers").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/save-burger").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/update-burger").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/delete-burger").hasRole("ADMIN")

                        // Cardápio:
                        .requestMatchers(HttpMethod.GET, "/api/get-menu").permitAll()

                        // Ingredientes:
                        .requestMatchers(HttpMethod.GET, "/api/menu-ingredients").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/update-ingredient").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/insert-ingredient").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/remove-ingredient/{identifier}").hasRole("ADMIN")

                        // Categorias:
                        .requestMatchers(HttpMethod.PUT, "/api/edit-category").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/remove-category/{identifier}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/get-all-categories").hasRole("ADMIN")

                        // Porções:
                        .requestMatchers(HttpMethod.GET, "/api/get-all-portions").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/add-category").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/remove-category/{identifier}").hasRole("ADMIN")


                        // Gestão:
                        .requestMatchers("/api/**-management").hasRole("ADMIN")
                        .anyRequest().hasRole("ADMIN")
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder();}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://saboroso-burguer-ng.vercel.app", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}