package dds.miliechi.parcialpractico;

import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import dds.miliechi.parcialpractico.security.AppRole;
import dds.miliechi.parcialpractico.security.AppUser;
import dds.miliechi.parcialpractico.security.RoleService;
import dds.miliechi.parcialpractico.security.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SpringCommandLineRunner implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    public SpringCommandLineRunner(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userService.existsUserWithAdminRole()) {
            // Si no existe ningun usuario admin, creo uno con los roles
            AppRole adminRole = new AppRole("ADMIN");
            AppRole userRole = new AppRole("USER");
            roleService.save(adminRole);
            roleService.save(userRole);

            RegisterRequest adminRegisterRequest = new RegisterRequest("admin", "admin", 180.0, 60.0);
            AppUser adminUser = userService.save(adminRegisterRequest);
            adminUser.addRole(adminRole);
            userService.save(adminUser);
        }
    }
}