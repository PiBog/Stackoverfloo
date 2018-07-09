package ua.pp.sola.stackoverfloo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pp.sola.stackoverfloo.entities.UserEntity;
import ua.pp.sola.stackoverfloo.entities.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserAuthService implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserAuthService.class.getName());

    private static final List<GrantedAuthority> ADMIN_AUTH
            = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
            new SimpleGrantedAuthority("ROLE_MODERATOR"), new SimpleGrantedAuthority("ROLE_USER"));
    private static final List<GrantedAuthority> MODER_AUTH
            = Arrays.asList(new SimpleGrantedAuthority("ROLE_MODERATOR"), new SimpleGrantedAuthority("ROLE_USER"));

    private static final List<GrantedAuthority> USER_AUTH = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));


    @Autowired
    UserService userService;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param userName the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        LOGGER.info("Starting build user");
        UserEntity userEntity = userService.getUserByName(userName);
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole().toString().toUpperCase());
        UserDetails userDetails = new User(userEntity.getUserName(), userEntity.getUserPas(), getAuthorities(userEntity));
        LOGGER.info("Got user [" + userDetails.getUsername() + ":" + userDetails.getPassword() + "]");
        return userDetails;

    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity) {
        String role = userEntity.getRole().toString().toUpperCase();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (role.equals("ADMIN")) authorities = ADMIN_AUTH;
        else if (role.equals("MODERATOR")) authorities = MODER_AUTH;
        else authorities = USER_AUTH;
        return authorities;
    }


}
