package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "my_entities")
@Getter
@Setter
@NoArgsConstructor
public class PruebaMongoEntity extends BaseEntity {

    private String firstName;
    private String lastName;

    public PruebaMongoEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
