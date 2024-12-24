package com.app.posts.service.implementation;

import com.app.posts.persistence.entity.RoleEntity;
import com.app.posts.persistence.entity.UserEntity;
import com.app.posts.presentation.dto.request.auth.LoginRequest;
import com.app.posts.presentation.dto.request.auth.SingUpRequest;
import com.app.posts.presentation.dto.response.AuthResponse;
import com.app.posts.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userService.findUserEntityByUsername(username);

        List<SimpleGrantedAuthority> authorities = this.getAuthorities(userEntity);

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialsNonExpired(),
                userEntity.isAccountNonLocked(),
                authorities
        );
    }

    public AuthResponse logIn(LoginRequest loginRequest) {
        String username = loginRequest.username();
        String password = loginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwtUtils.createJwt(authentication);
        return new AuthResponse(username, "User authenticated successfully", jwt, true);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid credentials");
        }

        if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse singUp(SingUpRequest singUpRequest) {
        String username = singUpRequest.username();
        String email = singUpRequest.email();
        String password = singUpRequest.password();
        List<String> roleNames = singUpRequest.roleRequest().roleNames();

        Set<RoleEntity> roles = this.roleService.findRoleEntitiesByRoleEnumIn(roleNames);

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .email(email)
                .password(this.passwordEncoder.encode(password))
                .isEnabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .roles(roles)
                .build();

        userEntity = this.userService.save(userEntity);

        List<SimpleGrantedAuthority> authorities = this.getAuthorities(userEntity);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwtUtils.createJwt(authentication);
        return new AuthResponse(
                userEntity.getUsername(),
                "User registered successfully",
                jwt,
                true
        );
    }

    private List<SimpleGrantedAuthority> getAuthorities(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles()
                .forEach(role -> authorities
                        .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userEntity.getRoles()
                .stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities
                        .add(new SimpleGrantedAuthority(permission.getName())));
        return authorities;
    }
}
