package ua.moshchuk.aifilms.models.entityes;

import lombok.*;

import java.io.Serializable;
import java.text.DecimalFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Film implements Serializable {
    private String name;
    private int year;
    private float rating;
    private String urlImage;

    public String getStringedNameOfFilm() {
        return String.format("%s (%d)", name, year);
    }

    public String getIMDBRating() {
        return String.format("Rating IMDB: %s", new DecimalFormat("#0.0").format(rating));
    }
}
