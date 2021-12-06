package app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("userDetailServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    public void configureGlobal(@Autowired AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/app/client/*").permitAll()
                    .antMatchers("/app/admin/*").hasRole("ADMIN")
                    .antMatchers("/app/user/*").hasRole("USER")
                    .antMatchers("/app/qlkh/*").hasRole("QLKH")
                    .antMatchers("/app/cskh/*").hasRole("CSKH")
                .and()
                .formLogin()
                    .loginPage("/app/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .loginProcessingUrl("/app/login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .failureUrl("/app/login?error=true")

                .and()
                .logout()
                    .logoutUrl("/app/logout")
                    .logoutSuccessUrl("/app/login")
                    .logoutSuccessUrl("/app/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling().accessDeniedPage("/app/login403")

                .and()
                .rememberMe()
                    .key("uniqueAndSecret")
                    .authenticationSuccessHandler(myAuthenticationSuccessHandler());
        http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired=true");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println(b.encode("test1234"));
    }
}
