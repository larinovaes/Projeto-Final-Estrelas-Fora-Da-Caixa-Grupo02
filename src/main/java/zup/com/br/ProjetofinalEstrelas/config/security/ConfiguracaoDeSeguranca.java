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

    private static final String[] ENDPOINT = {
            "/beneficio/**",
            "/usuario/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().configurationSource(configurarCORS());


        http.authorizeRequests()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
                        "/swagger-resources/configuration/security", "/swagger-ui/**", "/webjars/**").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.GET, ENDPOINT).hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/funcionario").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/funcionario/{\\d}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/atividadefisica").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.GET, "/atividadefisica/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/beneficio").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/funcionario").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/atividadefisica").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/usuario/{\\d}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/beneficio/{\\d}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/funcionario/{\\d}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/atividadefisica/{\\d}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/funcionario").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/atividadefisica/{\\d}").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, ENDPOINT).hasRole("ADMIN")
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
