package dds.miliechi.parcialpractico.base;

public interface BaseRepository<TEntity extends BaseEntity> {
    TEntity findById(long id);

    void save(TEntity entity);

    TEntity merge(TEntity entity);
}
