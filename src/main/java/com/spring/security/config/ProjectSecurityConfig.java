package com.spring.security.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.spring.security.filter.AuthoritiesLoggingAfterFilter;
import com.spring.security.filter.AuthoritiesLoggingAtFilter;
import com.spring.security.filter.JWTTokenGeneratorFilter;
import com.spring.security.filter.JWTTokenValidatorFilter;
import com.spring.security.filter.RequestValidationBeforeFilter;

@Configuration
public class ProjectSecurityConfig {
	/**
	 * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to
	 * encourage users to move towards a component-based security configuration. It
	 * is recommended to create a bean of type SecurityFilterChain for security
	 * related configurations.
	 * 
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().cors()
				.configurationSource(new CorsConfigurationSource() {
					@Override
					public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
						CorsConfiguration config = new CorsConfiguration();
						config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
						config.setAllowedMethods(Collections.singletonList("*"));
						config.setAllowCredentials(true);
						config.setAllowedHeaders(Collections.singletonList("*"));
						config.setExposedHeaders(Arrays.asList("Authorization"));
						config.setMaxAge(3600L);
						return config;
					}
				}).and().csrf().disable()
				//.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
				.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
				.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
				.authorizeHttpRequests((auth) -> auth.antMatchers("/myAccount").hasRole("USER")
						.antMatchers("/myBalance").hasAnyRole("USER", "ADMIN").antMatchers("/myLoans").hasRole("ROOT")
						.antMatchers("/myCards").hasAnyRole("USER", "ADMIN").antMatchers("/user").authenticated()
						.antMatchers("/notices", "/contact").permitAll())
				.httpBasic(Customizer.withDefaults());
		return http.build();

	}

	/*
	 * @Bean SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
	 * throws Exception { /** Default configurations which will secure all the
	 * requests
	 * 
	 * ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests()
	 * .anyRequest()). authenticated(); http.formLogin(); http.httpBasic(); return
	 * (SecurityFilterChain)http.build();
	 * 
	 *//**
		 * Custom configurations as per our requirement
		 */
	/*
	 * 
	 * http.authorizeHttpRequests( (auth)->auth
	 * .antMatchers("/myAccount","/myBalance","/myLoans","/myCards").authenticated()
	 * .antMatchers("/notices","/contact").permitAll()
	 * ).httpBasic(Customizer.withDefaults()); return http.build(); CORS
	 * Configuration http.cors().configurationSource(new CorsConfigurationSource() {
	 * 
	 * @Override public CorsConfiguration getCorsConfiguration(HttpServletRequest
	 * request) { CorsConfiguration config = new CorsConfiguration();
	 * config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	 * config.setAllowedMethods(Collections.singletonList("*"));
	 * config.setAllowCredentials(true);
	 * config.setAllowedHeaders(Collections.singletonList("*"));
	 * config.setMaxAge(3600L); return config; } })
	 * .and().csrf().ignoringAntMatchers("/contact").
	 * csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) .and()
	 * .addFilterBefore(new RequestValidationBeforeFilter(),
	 * BasicAuthenticationFilter.class) .addFilterAfter(new
	 * AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
	 * .addFilterAt(new AuthoritiesLoggingAtFilter(),
	 * BasicAuthenticationFilter.class) .authorizeHttpRequests((auth) -> auth
	 * .antMatchers("/myAccount").hasRole("USER")
	 * .antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
	 * .antMatchers("/myLoans").hasRole("ROOT") .antMatchers( "/myCards",
	 * "/user").authenticated() .antMatchers("/notices", "/contact").permitAll()
	 * ).httpBasic(Customizer.withDefaults()); return http.build();
	 * 
	 * 
	 *//**
		 * Configuration to deny all the requests
		 */
	/*
	 * http.authorizeHttpRequests( (auth)->auth .anyRequest().denyAll())
	 * .httpBasic(Customizer.withDefaults()); return http.build();
	 * 
	 *//**
		 * Configuration to permit all the requests
		 *//*
			 * http.authorizeHttpRequests( (auth)->auth .anyRequest().permitAll())
			 * .httpBasic(Customizer.withDefaults()); return http.build(); }
			 */

	/* In Memeory Database */
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsService() {
	 * //InMemoryUserDetailsManager userDetailsService = new
	 * InMemoryUserDetailsManager(); UserDetails admin =
	 * User.withUsername("admin").password("12345").authorities("admin").build();
	 * UserDetails user =
	 * User.withUsername("user").password("12345").authorities("read").build();
	 * List<UserDetails> userDetails = new ArrayList<>(); userDetails.add(user);
	 * userDetails.add(admin); return new InMemoryUserDetailsManager(userDetails);
	 * //userDetailsService.createUser(admin);
	 * //userDetailsService.createUser(user); //return userDetailsService; }
	 */
	/* JDBC Database */
	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */
	/**
	 * NoOpPasswordEncoder is not recommended for production usage. Use only for
	 * non-prod.
	 *
	 * @return
	 */
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
