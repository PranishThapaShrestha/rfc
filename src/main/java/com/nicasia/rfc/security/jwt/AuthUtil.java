
package com.nicasia.rfc.security.jwt;


import com.nicasia.rfc.shared.exception.UnAuthorizedException;
import com.nicasia.rfc.core.usermanagement.user.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ali on 5/19/20
 */
public class AuthUtil {
    public static User getCurrentUser() {
        try {
            Userprincipal principal = (Userprincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return principal.getUser();
        } catch (Exception e) {
            throw new UnAuthorizedException("Please, login and continue.");
        }
    }
}

