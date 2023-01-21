package com.fireDetection.cot.security;
import com.fireDetection.cot.Exceptions.*;
import com.fireDetection.cot.entities.user;
import com.fireDetection.cot.entities.Role;
import com.fireDetection.cot.repositories.UserRepository;
import com.fireDetection.cot.utils.Argon2Utils;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SecurityService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private Argon2Utils argon2Utils;
    @Inject
    private SecurityContext securityContext;

    private boolean isForbidden(String  email, SecurityContext context, Principal principal) {
        return !(context.isUserInRole(Role.ADMIN.name()));

    }

    public user findBy(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotAuthorizedException("Unauthorized"));
    }

    public user findBy(String email, String password) {
        System.out.println(userRepository.findById(email).toString());
        final user user = userRepository.findById(email)
                .orElseThrow(() -> new UserNotAuthorizedException("Unauthorized"));
        System.out.println(argon2Utils.check(user.getPassword() ,password.toCharArray()));
        if (argon2Utils.check(user.getPassword() ,password.toCharArray() )) {

            return user;
        }
        throw new UserNotAuthorizedException("Unauthorized");

    }


}