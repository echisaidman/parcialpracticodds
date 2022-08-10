package dds.miliechi.parcialpractico.base;

import java.util.UUID;

public interface BaseRepository<TEntity extends BaseEntity> {
    TEntity findById(UUID id);

    void save(TEntity entity);

    TEntity merge(TEntity entity);
}
