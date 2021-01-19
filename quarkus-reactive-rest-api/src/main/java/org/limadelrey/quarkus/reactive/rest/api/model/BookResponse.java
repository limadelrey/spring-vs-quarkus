package org.limadelrey.quarkus.reactive.rest.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.UUID;

import static org.limadelrey.quarkus.reactive.rest.api.model.BookResponse.*;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
@JsonPropertyOrder({ID_FIELD, AUTHOR_FIELD, TITLE_FIELD, PRICE_FIELD})
public class BookResponse {

    public static final String ID_FIELD = "id";
    public static final String AUTHOR_FIELD = "author";
    public static final String TITLE_FIELD = "title";
    public static final String PRICE_FIELD = "price";

    @JsonProperty(value = ID_FIELD)
    private UUID id;

    @JsonProperty(value = AUTHOR_FIELD)
    private String author;

    @JsonProperty(value = TITLE_FIELD)
    private String title;

    @JsonProperty(value = PRICE_FIELD)
    private double price;

    public static BookResponse of(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .price(book.getPrice())
                .build();
    }

}
