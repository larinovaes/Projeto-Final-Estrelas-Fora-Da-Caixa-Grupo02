package zup.com.br.ProjetofinalEstrelas.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.FiltroDeAutenticacaoJWT;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.FiltroDeAutorizacaoJWT;
import zup.com.br.ProjetofinalEstrelas.config.security.JWT.JWTComponent;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTComponent jwtComponent;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().configurationSource(configurarCORS());

        http.authorizeHttpRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(new FiltroDeAutenticacaoJWT(jwtComponent, authenticationManager()));
        http.addFilter(new FiltroDeAutorizacaoJWT(authenticationManager(), jwtComponent, userDetailsService));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource configurarCORS() {
        UrlBasedCorsConfigurationSource cors = new UrlBasedCorsConfigurationSource();
        cors.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return cors;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}



