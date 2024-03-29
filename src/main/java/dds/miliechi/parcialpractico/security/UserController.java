package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.apis.AdapterCalculadoraInternaBMI;
import dds.miliechi.parcialpractico.apis.CalculadoraBMI;
import dds.miliechi.parcialpractico.apis.CalculadoraExternaBMI;
import dds.miliechi.parcialpractico.dtos.CalcularBMIRequest;
import dds.miliechi.parcialpractico.dtos.CalcularBMIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("bmi")
    public ResponseEntity<CalcularBMIResponse> calcularBMI(@RequestBody CalcularBMIRequest request, Principal currentUserUsername) throws IOException {
        CalculadoraBMI calculadoraBMI;
        if (request.getMetodoCalculo().equals("INTERNO")) {
            calculadoraBMI = new AdapterCalculadoraInternaBMI();
        } else {
            calculadoraBMI = new CalculadoraExternaBMI();
        }
        long idUsuario = userService.findByUsername(currentUserUsername.getName()).get().getId();
        double bmi = userService.calcularBMI(idUsuario, calculadoraBMI);
        return ResponseEntity.ok(new CalcularBMIResponse(bmi));
    }

}
