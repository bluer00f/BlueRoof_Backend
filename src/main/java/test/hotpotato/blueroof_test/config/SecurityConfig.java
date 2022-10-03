package test.hotpotato.blueroof_test.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import test.hotpotato.blueroof_test.jwt.JwtAccessDeniedHandler;
import test.hotpotato.blueroof_test.jwt.JwtAuthenticationEntryPoint;
import test.hotpotato.blueroof_test.jwt.TokenProvider;

@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 토큰 사용
                .csrf().disable()
                // Exception을 핸들링할 때 우리가 만든 클래스 추가
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // 세션을 사용하지 않기 때문에 STATELESS로 세션 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인, 회원가입 AOU는 토큰이 없는 상태에서 요청이 들어옴
                .and()
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .antMatchers("/api/v1/user/login").permitAll()
                .antMatchers("/api/v1/user/signup").permitAll()
                .antMatchers("/api/v1/user/reissue").permitAll()
                .antMatchers("/api/v1/user/check/**").permitAll()
                .antMatchers("/api/v1/user/userInfo/**").permitAll()
                //api 테스트용(아래 한줄)
                .antMatchers("/api/ApplyhomeInfoDetailSvc/v1/**").permitAll()
                .anyRequest().authenticated()

                // JwtFilter을 addFilterBefore로 등록했던 JwtSecurityConfig 클래스 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

    }

    // //허용 메서드
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(defaultHttpFirewall());
    }

    private HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

}
