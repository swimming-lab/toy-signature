package swm.toy.signature.base.context.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  public static final String[] PUBLIC =
      new String[] {
        "/api/sign-in",
        "/api/sign-in/refresh-token",
        "/api/sign-out",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs",
        "/v3/api-docs/**",
        "/api/v1/**",
      };

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.httpBasic()
        .disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(PUBLIC)
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(
            new JwtAuthenticationFilter(this.authenticationManagerBean()),
            UsernamePasswordAuthenticationFilter.class)
        .csrf()
        .disable()
        .cors()
        .configurationSource(this.corsConfigurationSource());
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("http://localhost:8081");
    configuration.addAllowedOrigin("http://localhost:8082");
    configuration.addAllowedOrigin("http://127.0.0.1:8081");
    configuration.addAllowedOrigin("http://127.0.0.1:8082");
    configuration.addAllowedOrigin("https://admin-sandbox.github.bestheroz.io/");
    configuration.addAllowedOrigin("https://admin-qa.github.bestheroz.io/");
    configuration.addAllowedOrigin("https://admin-prod.github.bestheroz.io/");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(true);
    configuration.addExposedHeader("accessToken");
    configuration.addExposedHeader("refreshToken");
    configuration.addExposedHeader("Content-Disposition");

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Pbkdf2PasswordEncoder();
  }
}
