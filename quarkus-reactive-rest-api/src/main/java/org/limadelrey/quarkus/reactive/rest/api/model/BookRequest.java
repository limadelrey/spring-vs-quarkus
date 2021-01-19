package org.limadelrey.quarkus.reactive.rest.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

import static org.limadelrey.quarkus.reactive.rest.api.model.BookRequest.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({AUTHOR_FIELD, TITLE_FIELD, PRICE_FIELD})
public class BookRequest {

    public static final String AUTHOR_FIELD = "author";
    public static final String TITLE_FIELD = "title";
    public static final String PRICE_FIELD = "price";

    @NotEmpty
    @JsonProperty(value = AUTHOR_FIELD)
    private String author;

    @NotEmpty
    @JsonProperty(value = TITLE_FIELD)
    private String title;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Positive
    @JsonProperty(value = PRICE_FIELD)
    private double price;

    // should be used in insert method
    public Book toBook(UUID id) {
        return Book.builder()
                .id(id)
                .author(author)
                .title(title)
                .price(price)
                .build();
    }

    // should be used in update method
    public Book toBook() {
        return Book.builder()
                .author(author)
                .title(title)
                .price(price)
                .build();
    }

}
