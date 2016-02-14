package org.alexg.mypage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	RequestMatcher csrfRequestMatcher = new RequestMatcher() {
    		  
    		  // Always allow the HTTP GET method
    		  private Pattern allowedMethods = Pattern.compile("^GET$");
    		  
    		  // Disable CSFR protection on the following urls:
    		  private AntPathRequestMatcher[] requestMatchers = {
    		    new AntPathRequestMatcher("/login"),
    		    new AntPathRequestMatcher("/logout"),
    		    new AntPathRequestMatcher("/api/**")
    		  };

    		  @Override
    		  public boolean matches(HttpServletRequest request) {
    		    // Skip allowed methods
    		    if (allowedMethods.matcher(request.getMethod()).matches()) {
    		      return false;
    		    }   

    		    // If the request match one url the CSFR protection will be disabled
    		    for (AntPathRequestMatcher rm : requestMatchers) {
    		      if (rm.matches(request)) { return false; }
    		    }

    		    return true;
    		  } // method matches

    		};	
    	
      http
	   // Enable csrf only on some request matches
	      .csrf()
		        .requireCsrfProtectionMatcher(csrfRequestMatcher)
		        .and()
	   // Other security configurations ...
	      .httpBasic()
		      .and()
		        .authorizeRequests()
		          .antMatchers("/index.html","/","/img/*","/js/*", "/home.html", "/login.html", "/*", "/api/**").permitAll()
		          .anyRequest().authenticated();
    }
  }

