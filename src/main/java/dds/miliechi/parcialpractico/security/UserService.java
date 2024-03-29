package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.apis.CalculadoraBMI;
import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

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
        AppUser appUser = buildBaseAppUserFromRequest(request);
        return save(appUser);
    }

    @Transactional
    public AppUser saveAdmin(RegisterRequest request) {
        AppUser appUser = buildBaseAppUserFromRequest(request);
        appUser.addRole(roleRepository.findByRoleName("ADMIN").get());
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
    public double calcularBMI(long idUsuario, CalculadoraBMI calculadoraBMI) throws IOException {
        AppUser appUser = userRepository.findById(idUsuario);
        double alturaEnMetros = appUser.getAltura() / 100;
        return calculadoraBMI.calcular(alturaEnMetros, appUser.getPeso());
    }

    @Transactional
    public boolean existsUserWithAdminRole() {
        return userRepository.existsUserWithAdminRole();
    }

    private AppUser buildBaseAppUserFromRequest(RegisterRequest request) {
        AppUser appUser = new AppUser();
        appUser.setUsername(request.getUsername());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setAltura(request.getAltura());
        appUser.setPeso(request.getPeso());
        appUser.addRole(roleRepository.findByRoleName("USER").get());
        return appUser;
    }
}
