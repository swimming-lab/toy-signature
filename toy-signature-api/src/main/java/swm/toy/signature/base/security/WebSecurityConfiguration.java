package swm.toy.signature.base.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http =
                http.cors()
                        .configurationSource(
                                request -> {
                                    var cors = new CorsConfiguration();
                                    cors.setAllowedOrigins(
                                            List.of("http://localhost:8080", "https://editor.swagger.io"));
                                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                                    cors.setAllowedHeaders(List.of("*"));
                                    return cors;
                                })
                        .and()
                        .csrf()
                        .disable();

        // Set session management to stateless
        http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // Set unauthorized requests exception handler
        http =
                http.exceptionHandling()
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                        .and();

        // Disable formlogin and enable h2-console
        http = http.formLogin().disable()
                .headers().frameOptions().sameOrigin().and();

        // Set permissions on endpoints
        http.authorizeRequests()
                .antMatchers("/", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs")
                .permitAll()
                .antMatchers("/h2/**")
                .permitAll()
                // Our public endpoints
                .antMatchers(HttpMethod.POST, "/users", "/users/login")
                .permitAll()
//                .antMatchers(HttpMethod.GET, "/api/articles/**", "/api/profiles/**", "/api/tags")
//                .permitAll()
                // Our private endpoints
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
