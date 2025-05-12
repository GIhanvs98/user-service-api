package com.gihanvs.quickcart.user_service_api.service.impl;

import com.gihanvs.quickcart.user_service_api.Entity.Otp;
import com.gihanvs.quickcart.user_service_api.Entity.User;
import com.gihanvs.quickcart.user_service_api.config.KeycloackSecurityUtil;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserDto;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserLoginRequest;
import com.gihanvs.quickcart.user_service_api.dto.response.ResponseUserDto;
import com.gihanvs.quickcart.user_service_api.exception.DuplicateEntryException;
import com.gihanvs.quickcart.user_service_api.repo.OtpRepo;
import com.gihanvs.quickcart.user_service_api.repo.UserRepo;
import com.gihanvs.quickcart.user_service_api.service.EmailService;
import com.gihanvs.quickcart.user_service_api.service.UserService;
import com.gihanvs.quickcart.user_service_api.util.FileDataExtractor;
import com.gihanvs.quickcart.user_service_api.util.OtpGenerator;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.UUID;

import com.devstack.system.service.impl.JwtService;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final KeycloackSecurityUtil keycloackSecurityUtil;
    private final EmailService emailService;
    private final UserRepo userRepo;
    private final OtpRepo otpRepo;
    private JwtService jwtService;
    private OtpGenerator otpGenerator;
    private final FileDataExtractor fileDataExtractor;
    @Value("${keycloak.config.realm}")
    private String realm;

    @Value("${keycloak.config.client-id}")
    private String clientId;


    @Value("${keycloak.config.secret}")
    private String secret;

    @Value("${spring.security.oauth2.resourceserver.jwt.token-uri}")
    private String keyCloakApiUrl;


    @Override
    public void createUser(RequestUserDto dto) throws IOException {
            String userId="";
            String otpId="";
        Keycloak keycloak=null;

        UserRepresentation existingUser=null;
        keycloak=keycloackSecurityUtil.getKeycloakInstance();
        existingUser=keycloak.realm(realm).users().search(dto.getUsername()).stream()
                .findFirst().orElse(null);
        if (existingUser!=null) {
            Optional <User> byEmail=userRepo.findByUserName(existingUser.getEmail());
            if (byEmail.isEmpty()) {
                keycloak.realm(realm).users().delete(existingUser.getId());
            }else{
                throw new DuplicateEntryException("User With email"+dto.getUsername()+"already created");
            }

        }else {
            Optional<User> byEmail=userRepo.findByUserName(dto.getUsername());
            if (byEmail.isPresent()){
                Optional<Otp> bySystemUserId=otpRepo.findBySystemUserId(byEmail.get().getUserId());
            }
            userRepo.deleteById(byEmail.get().getUserId());
        }
        UserRepresentation userRep = mapUserRep(dto);
        Response res = keycloak.realm(realm).users().create(userRep);
        // Add the admin role to the newly created user
        if (res.getStatus() == Response.Status.CREATED.getStatusCode()) {
            RoleRepresentation userRole = keycloak.realm(realm).roles().get("user").toRepresentation();
            userId = res.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Arrays.asList(userRole));
            User createdSystemUser =User.builder()
                    .userId(userId)
                    .activeStatus(false)
                    .userName(dto.getUsername())
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .isAccountNonExpired(true)
                    .isEmailVerified(false)
                    .isAccountNonLocked(true)
                    .isEnabled(false)
                    .createdDate(new Date())
                    .build();
            User savedUser = UserRepo.save(createdSystemUser);
            Otp otp = Otp.builder()
                    .propertyId(UUID.randomUUID().toString())                    .code(otpGenerator.generateOtp(4))
                    .createdDate(new Date())
                    .isVerified(false)
                    .attempts(0)
                    .systemUser(savedUser)
                    .build();
            otpRepo.save(otp);
            emailService.sendUserSignupVerificationCode(dto.getUsername(),
                    "Verify Your Email Address for Quick-Cart Access", otp.getCode());
        }

    }

    @Override
    public Object userLogin(RequestUserLoginRequest request) {
        return null;
    }

    @Override
    public boolean verifyEmail(String otp, String email) {
        return false;
    }

    @Override
    public List<ResponseUserDto> findUsersPaginate(String searchText, int page, int size) {
        return List.of();
    }

    @Override
    public void resend(String email) {

    }

    @Override
    public void forgotPasswordSendVerificationCode(String email) {

    }

    @Override
    public boolean verifyReset(String email, String otp) {
        return false;
    }

    @Override
    public boolean passwordReset(RequestUserDto dto) {
        return false;
    }

    @Override
    public String getUserId(String email) {
        return "";
    }
    private UserRepresentation mapUserRep (RequestUserDto user) {
        UserRepresentation userRep = new UserRepresentation();
        userRep.setUsername(user.getUsername());
        userRep.setFirstName(user.getFirstName());
        userRep.setLastName(user.getLastName());
        userRep.setEmail(user.getUsername());
        userRep.setEnabled(false);
        userRep.setEmailVerified(false);
        List<CredentialRepresentation> creds = new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(false);
        cred.setValue(user.getPassword());
        creds.add(cred);
        userRep.setCredentials(creds);
        return userRep;

    }
}
