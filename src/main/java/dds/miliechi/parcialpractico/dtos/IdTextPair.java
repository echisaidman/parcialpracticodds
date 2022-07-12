package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class IdTextPair {
    private UUID id;
    private String text;

    public IdTextPair(UUID id, String text) {
        this.id = id;
        this.text = text;
    }
}
