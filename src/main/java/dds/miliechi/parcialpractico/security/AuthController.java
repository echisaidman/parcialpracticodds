package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.dtos.LoginRequest;
import dds.miliechi.parcialpractico.dtos.LoginResponse;
import dds.miliechi.parcialpractico.dtos.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(UserService userService, JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        userService.save(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/registerAdmin")
    @IsAdmin
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        if (userService.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        userService.saveAdmin(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<AppUser> appUserOptional = userService.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (!appUserOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        String accessToken = jwtUtils.generateToken(appUserOptional.get());
        LoginResponse response = new LoginResponse(accessToken);
        return ResponseEntity.ok(response);
    }

}
