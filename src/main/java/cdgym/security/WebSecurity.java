package cdgym.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import cdgym.service.JpaUserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                .requestMatchers(mvc.pattern("/"),mvc.pattern(""), mvc.pattern("/index")).permitAll()
                .requestMatchers(mvc.pattern("/login"),mvc.pattern("registro"), 
                    mvc.pattern("/empleado/instructores"), mvc.pattern("/validarRegistro"), 
                    mvc.pattern("/clientes")).permitAll()
                .requestMatchers(mvc.pattern("/empleado/listar"), mvc.pattern("/empleado/informacion/**"))
                    .hasAnyRole("USER")
                .requestMatchers(mvc.pattern("/empleado/registrarMensualidad/**"),
                    mvc.pattern("/empleado/asistencia/**")).hasAnyRole("GESTOR")
                .requestMatchers(mvc.pattern("/empleado/crear"), mvc.pattern("/empleado/eliminar/**"))
                    .hasAnyRole("ADMIN")
        
                .anyRequest().authenticated()
                
            )
            .exceptionHandling(exception -> exception.accessDeniedPage("/error403"))
            .headers(headers -> headers.frameOptions(frame -> frame.disable()))
            .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")))
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll())
            .authenticationProvider(authenticationProvider());
        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    UserDetailsService userDetailsService(){
        return new JpaUserDetailService();
    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
