package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface Repository<TEntity extends BaseEntity> {
    TEntity findById(UUID id);

    List<TEntity> list();

    void save(TEntity entity);

    TEntity merge(TEntity entity);
}
