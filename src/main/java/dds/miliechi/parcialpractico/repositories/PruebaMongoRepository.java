package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.PruebaMongoEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PruebaMongoRepository {

    private final MongoTemplate mongoTemplate;

    public PruebaMongoRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<PruebaMongoEntity> list() {
        return mongoTemplate.findAll(PruebaMongoEntity.class);
    }

    public void save(PruebaMongoEntity entity) {
        mongoTemplate.insert(entity);
    }

}
