package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.apis.CalculadoraBMI;
import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import dds.miliechi.parcialpractico.entities.AppUser;
import dds.miliechi.parcialpractico.repositories.RoleRepository;
import dds.miliechi.parcialpractico.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
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
        appUser.setAltura(request.getAltura());
        appUser.setPeso(request.getPeso());
        appUser.addRole(roleRepository.findByRoleName("USER").get());
        return save(appUser);
    }

    @Transactional
    public AppUser save(AppUser user) {
        if (userRepository.findById(user.getId()) == null) {
            userRepository.save(user);
        } else {
            user = userRepository.merge(user);
        }
        return user;
    }

    @Transactional
    public double calcularBMI(UUID id, CalculadoraBMI calculadoraBMI) throws IOException {
        AppUser appUser = userRepository.findById(id);
        double alturaEnMetros = appUser.getAltura() / 100;
        return calculadoraBMI.calcular(alturaEnMetros, appUser.getPeso());
    }

    @Transactional
    public boolean existsUserWithAdminRole() {
        return userRepository.existsUserWithAdminRole();
    }
}
