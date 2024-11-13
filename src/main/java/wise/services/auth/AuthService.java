package wise.services.auth;

import lombok.RequiredArgsConstructor;
import wise.entities.UserEntity;
import wise.models.request.auth.SigninRequest;
import wise.models.request.auth.SigninRequestKiosk;
import wise.repositories.user_management.UserRepo;
import wise.common.CustomException;
import wise.common.RequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String Signin(SigninRequest signinRequest) {
        String username = signinRequest.getUsername();
        String password = signinRequest.getPassword();
        
        UserEntity user = userRepo.findByUserName(username)
        .orElseThrow(() -> new CustomException("authentication.userNameNotFound", HttpStatus.NOT_FOUND));
        
        String userPassword = user.getUserPassword();
        if (!password.equals(userPassword)) {
            throw new CustomException("authentication.passwordError", HttpStatus.NOT_FOUND);
        }

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        return token;
    }

    public String SigninKiosk(SigninRequestKiosk signinRequestKiosk) {
        logger.info("Sigin from the kiosk");
        String username = signinRequestKiosk.getUsername();
        userRepo.findByUserName(username)
                .orElseThrow(() -> new CustomException("authentication.userNameNotFound", HttpStatus.NOT_FOUND));

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
        return token;
    }
}
