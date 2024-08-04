package com.application.seller.config;
import com.application.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//   @Autowired
//   private SellerService sellerService;

//    @Bean
//    public AuthenticationProvider authProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(sellerService);
//        return provider;
//    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/seller/register"),
                                new AntPathRequestMatcher("/api/seller/login"))
                        .permitAll().anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/api/seller/login")
                        .defaultSuccessUrl("/api/products",true)
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                );

        return http.build();
    }
}
