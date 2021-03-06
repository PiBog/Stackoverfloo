package ua.pp.sola.stackoverfloo.config;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Configuration
@EnableResourceServer
@ComponentScan("ua.pp.sola.stackoverfloo")
@Component
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //-- define URL patterns to enable OAuth2 security
        http.
                anonymous().disable()
                .requestMatchers().antMatchers("/rest/**")
                .and().authorizeRequests()
                .antMatchers("/rest/**").access("hasRole('ADMIN') or hasRole('USER')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }


}

