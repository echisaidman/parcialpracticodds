package dds.miliechi.parcialpractico;

import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import dds.miliechi.parcialpractico.entities.AppRole;
import dds.miliechi.parcialpractico.entities.AppUser;
import dds.miliechi.parcialpractico.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandLineRunner implements CommandLineRunner {
    private final UserService userService;

    public SpringCommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userService.existsUserWithAdminRole()) {
            // Si no existe ningun usuario admin, creo uno con los roles
            AppRole adminRole = new AppRole("ADMIN");
            AppRole userRole = new AppRole("USER");

            RegisterRequest adminRegisterRequest = new RegisterRequest("admin", "admin", 180.0, 60.0);
            AppUser adminUser = userService.save(adminRegisterRequest);
            adminUser.addRole(adminRole);
            adminUser.addRole(userRole);
            userService.save(adminUser);
        }
    }
}