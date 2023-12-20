package cdgym.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AntPathRequestMatcher.antMatcher("/console-h2/**")).permitAll()
                .anyRequest().permitAll()
                
            )
            .headers(header -> header.frameOptions(frame -> frame.disable()))
            .csrf(csrf -> csrf
                .disable())
                // .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll());

        return http.build();
    }

}
