package com.example.rewardyourteachersq011bjavapode.serviceImpl;
import com.example.rewardyourteachersq011bjavapode.config.Security.JwtUtil;
import com.example.rewardyourteachersq011bjavapode.dto.LoginDTO;
import com.example.rewardyourteachersq011bjavapode.dto.PrincipalDto;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.models.Wallet;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.repository.WalletRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.SocialLoginRequest;
import com.example.rewardyourteachersq011bjavapode.service.AuthService;
import com.example.rewardyourteachersq011bjavapode.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.rewardyourteachersq011bjavapode.enums.Role.STUDENT;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserUtil userUtil;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    @Override
    public ApiResponse<PrincipalDto> loginUser(LoginDTO loginDTO) {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
        } catch (AuthenticationException ex) {
            log.error(ex.getMessage());
            throw new ResourceNotFoundException("Invalid username or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        User loggedInUser = userUtil.getUserByEmail(loginDTO.getEmail());

        return new ApiResponse<>("success", LocalDateTime.now(), new PrincipalDto(loggedInUser.getId(), loggedInUser.getName(), loggedInUser.getEmail(), loggedInUser.getRole().toString(),jwtUtil.generateToken(loginDTO.getEmail())));

    }

    @Override
    public ApiResponse<PrincipalDto> socialLogin(SocialLoginRequest socialLoginRequest) {
        socialLoginRequest.setPassword("");
        User user = userRepository.findByEmail(socialLoginRequest.getEmail());
        if (user == null) {
            User socialUser = User.builder()
                    .name(socialLoginRequest.getName())
                    .password(passwordEncoder.encode(socialLoginRequest.getPassword()))
                    .email(socialLoginRequest.getEmail())
                    .role(STUDENT)
                    .build();
            socialUser = userRepository.save(socialUser);
            Wallet userWallet = new Wallet(new BigDecimal("0"), socialUser);
            walletRepository.save(userWallet);
            return new ApiResponse<>("success", LocalDateTime.now(), new PrincipalDto(socialUser.getId(), socialUser.getName(), socialUser.getEmail(), socialUser.getRole().toString(), jwtUtil.generateToken(socialUser.getEmail())));
        }
        return new ApiResponse<>("success", LocalDateTime.now(), new PrincipalDto(user.getId(), user.getName(), user.getEmail(), user.getRole().toString(), jwtUtil.generateToken(user.getEmail())));
    }

}
