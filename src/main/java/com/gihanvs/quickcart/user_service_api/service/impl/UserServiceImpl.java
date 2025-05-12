package com.gihanvs.quickcart.user_service_api.service.impl;

import com.gihanvs.quickcart.user_service_api.config.KeycloackSecurityUtil;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserDto;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserLoginRequest;
import com.gihanvs.quickcart.user_service_api.dto.response.ResponseUserDto;
import com.gihanvs.quickcart.user_service_api.repo.UserRepo;
import com.gihanvs.quickcart.user_service_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final KeycloackSecurityUtil keycloackSecurityUtil;
    private final UserRepo userRepo;

    @Override
    public void createUser(RequestUserDto dto) throws IOException {

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
}
