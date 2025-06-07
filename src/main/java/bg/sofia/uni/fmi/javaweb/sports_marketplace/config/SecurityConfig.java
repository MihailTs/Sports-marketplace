package bg.sofia.uni.fmi.javaweb.sports_marketplace.config;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util.JWTFilter;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JWTFilter authenticationJWTTokenFilter(){
        return new JWTFilter();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        /*http.csrf(csrf-> csrf.disable())
                .cors(cors->cors.disable())
                .exceptionHandling(exceptionHandling->exceptionHandling.authenticationEntryPoint(unauth))


                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/auth/login", "/api/users/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form)->form.loginPage("/api/users/auth/login").permitAll())
                .logout((logout)->logout.permitAll());
*/
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth.requestMatchers("/api/users/auth/**")
                        .permitAll().anyRequest().authenticated())
                .addFilterBefore(authenticationJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
