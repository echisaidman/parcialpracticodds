package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import dds.miliechi.parcialpractico.entities.AppUser;
import dds.miliechi.parcialpractico.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Optional<AppUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public Optional<AppUser> findByUsernameAndPassword(String username, String password) {
        Optional<AppUser> appUserOptional = userRepository.findByUsername(username);
        if (appUserOptional.isPresent() && passwordEncoder.matches(password, appUserOptional.get().getPassword()))
            return appUserOptional;
        return Optional.empty();
    }

    @Transactional
    public AppUser save(RegisterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(appUser);
        return appUser;
    }

}
