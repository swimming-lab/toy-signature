package swm.toy.signature.application.security;

import static org.springframework.http.HttpMethod.POST;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import swm.toy.signature.domain.jwt.JWTDeserializer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(
                        "/favicon.ico",
                        "/error",
                        "/h2/**",
                        "/swagger-ui.html",
                        "/swagger/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v3/api-docs",
                        "/v3/api-docs/swagger-config");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors()
                .configurationSource(
                        request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(
                                    List.of("http://localhost:8080", "https://editor.swagger.io"));
                            cors.setAllowedMethods(
                                    List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                            cors.setAllowedHeaders(List.of("*"));
                            return cors;
                        });
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and();
        http = http.formLogin().disable().headers().frameOptions().sameOrigin().and();
        http.logout().disable();
        http.addFilterBefore(
                new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                //                .antMatchers(GET, "/admin/**").hasRole("ADMIN")
                .antMatchers(POST, "/users", "/users/login")
                .permitAll()
                //                .antMatchers(GET, "/profiles/*")
                //                .permitAll()
                //                .antMatchers(GET, "/articles/**")
                //                .permitAll()
                //                .antMatchers(GET, "/tags/**")
                //                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    JWTAuthenticationProvider jwtAuthenticationProvider(JWTDeserializer jwtDeserializer) {
        return new JWTAuthenticationProvider(jwtDeserializer);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
