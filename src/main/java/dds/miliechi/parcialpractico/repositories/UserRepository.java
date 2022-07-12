package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.AppUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UserRepository {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<AppUser> findByUsername(String username) {
        String sql = "from AppUser u where u.username = :username";
        return entityManager.createQuery(sql, AppUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    public void save(AppUser appUser) {
        entityManager.persist(appUser);
    }

}
