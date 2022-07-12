package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.entities.AppUser;
import dds.miliechi.parcialpractico.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional = userRepository.findByUsername(username);
        if (!appUserOptional.isPresent())
            throw new UsernameNotFoundException("User " + username + " not found.");

        AppUser appUser = appUserOptional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

}
