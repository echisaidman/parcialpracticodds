package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdTextPair {
    private long id;
    private String text;

    public IdTextPair(long id, String text) {
        this.id = id;
        this.text = text;
    }
}
