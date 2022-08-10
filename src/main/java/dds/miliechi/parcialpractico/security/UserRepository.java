package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class UserRepository implements BaseRepository<AppUser> {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<AppUser> findByUsername(String username) {
        String sql = "select u from AppUser u where u.username = :username";
        return entityManager.createQuery(sql, AppUser.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public AppUser findById(long id) {
        String sql = "select u from AppUser u where u.id = :id";
        return entityManager.createQuery(sql, AppUser.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(AppUser appUser) {
        entityManager.persist(appUser);
    }

    @Override
    public AppUser merge(AppUser entity) {
        return entityManager.merge(entity);
    }

    public boolean existsUserWithAdminRole() {
        String sql = "select user " +
                "from AppUser as user " +
                "join user.roles as role " +
                "where role.nombre = 'ADMIN'";
        return !entityManager.createQuery(sql, AppUser.class)
                .getResultList()
                .isEmpty();
    }
}
