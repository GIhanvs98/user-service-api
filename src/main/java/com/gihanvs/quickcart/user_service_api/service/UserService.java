package com.gihanvs.quickcart.user_service_api.service;

import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserDto;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserLoginRequest;
import com.gihanvs.quickcart.user_service_api.dto.request.RequestUserPasswordResetDto;
import com.gihanvs.quickcart.user_service_api.dto.response.ResponseUserDto;

import java.io.IOException;
import java.util.List;

public interface UserService {
    public void createUser(RequestUserDto dto) throws IOException;
    public  Object userLogin(RequestUserLoginRequest request);
    public  boolean verifyEmail(String otp,String email);
    public List<ResponseUserDto> findUsersPaginate(String searchText, int page, int size);
    public void resend(String email);
    public void forgotPasswordSendVerificationCode(String email);
    public boolean verifyReset(String email, String otp);
    public boolean passwordReset(RequestUserPasswordResetDto dto);
    String getUserId(String email);


}
