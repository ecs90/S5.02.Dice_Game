package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.config;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.JWTAuthorizationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Key signingKey = generateKey();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/players", "/players/", "/auth", "/error").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JWTAuthorizationFilter(signingKey), UsernamePasswordAuthenticationFilter.class)
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public Key generateKey() {
        String secretKey = "EC9987C0F0AD495F241436E730085B07F94FCA2A99CF297159D249C9DA4A581C48B2F6ECF10D4D9ACAE30124A65397CE23AA8EBA4D9971F8A26B75FC0D95A09B";
        return new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName());
    }
}
