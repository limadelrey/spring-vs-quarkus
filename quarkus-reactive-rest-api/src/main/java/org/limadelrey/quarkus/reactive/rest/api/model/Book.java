package org.limadelrey.quarkus.reactive.rest.api.model;

import io.vertx.reactivex.sqlclient.Row;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Book {

    private UUID id;
    private String author;
    private String title;
    private double price;

    public static Book of(Row row) {
        return Book.builder()
                .id(row.getUUID("id"))
                .author(row.getString("author"))
                .title(row.getString("title"))
                .price(row.getDouble("price"))
                .build();
    }

}
