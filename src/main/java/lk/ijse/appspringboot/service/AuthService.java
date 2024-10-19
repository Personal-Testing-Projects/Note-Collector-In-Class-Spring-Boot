package lk.ijse.appspringboot.service;

import lk.ijse.appspringboot.dto.impl.UserDTO;
import lk.ijse.appspringboot.secure.JWTAuthResponse;
import lk.ijse.appspringboot.secure.SignIn;

public interface AuthService {
    JWTAuthResponse signIn(SignIn signIn);
    JWTAuthResponse signUp(UserDTO userDTO);
    JWTAuthResponse refreshToken(String accessToken);
}
