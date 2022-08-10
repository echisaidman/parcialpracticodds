package dds.miliechi.parcialpractico.security;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Optional<AppRole> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Transactional
    public AppRole save(AppRole role) {
        if (roleRepository.findById(role.getId()) == null) {
            roleRepository.save(role);
        } else {
            role = roleRepository.merge(role);
        }
        return role;
    }
}
