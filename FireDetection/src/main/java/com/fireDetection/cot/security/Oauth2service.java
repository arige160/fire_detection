package com.fireDetection.cot.security;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import com.fireDetection.cot.entities.user;
import com.fireDetection.cot.Exceptions.UserNotAuthorizedException;
import com.fireDetection.cot.repositories.UserTokenRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.time.Duration;
import java.util.Optional;
import java.util.Set;

@ApplicationScoped
public class Oauth2service {
    private static final Config config = ConfigProvider.getConfig();

    static final int EXPIRE_IN = config.getValue("jwt.lifetime.duration",Integer.class);
    static final Duration EXPIRES = Duration.ofSeconds(EXPIRE_IN);
    @Inject
    private UserTokenRepository userTokenRepository ;

    @Inject
    private SecurityService securityService;

    @Inject
    private Validator validator;

    public Oauth2response token(Oauth2request request) {

        final user user = securityService.findBy(request.getEmail(), request.getPassword());
        System.out.println(user.toString());
        Optional<UserToken> optionalUserToken = userTokenRepository.findByEmail(request.getEmail()) ;
        UserToken userToken ;
        if(optionalUserToken.isPresent()){
            userToken=optionalUserToken.get() ;
        }else{
            userToken=new UserToken(request.getEmail()) ;
        }

        final Token token= Token.generate() ;
        final String jwt = UserJWT.createToken(user, token, EXPIRES);
        AccessToken accessToken = new AccessToken(token.get(), jwt, EXPIRES);
        RefreshToken refreshToken = new RefreshToken(Token.generate(), accessToken);
        userToken.add(refreshToken);
        userTokenRepository.save(userToken);
        final Oauth2response response = Oauth2response.of(accessToken, refreshToken, EXPIRE_IN, user.getEmail());
        return response;
    }

    public Oauth2response refreshToken(Oauth2request request) {
        System.out.println("Activation of refresh mode");
        final Set<ConstraintViolation<Oauth2request>> violations = validator.validate(request, Oauth2request.RefreshToken.class);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        System.out.println(userTokenRepository.findByRefreshToken(request.getRefreshToken()));
        final UserToken userToken = userTokenRepository.findByRefreshToken(request.getRefreshToken())
                .orElseThrow(() -> new UserNotAuthorizedException("Unauthorized"));

        final user user = securityService.findBy(userToken.getEmail());
        final String employeeId = user.getEmail();
        final Token token = Token.generate();
        final String jwt = UserJWT.createToken(user, token, EXPIRES);
        AccessToken accessToken = new AccessToken(token.get(), jwt, EXPIRES);
        RefreshToken refreshToken = userToken.update(accessToken, request.getRefreshToken(), userTokenRepository);
        final Oauth2response response = Oauth2response.of(accessToken, refreshToken, EXPIRE_IN, employeeId);
        return response;
    }

}