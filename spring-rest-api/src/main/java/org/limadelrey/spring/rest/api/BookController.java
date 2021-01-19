package org.limadelrey.spring.rest.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity readAll() {
        return ResponseEntity.ok(bookService.readAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity readOne(@PathVariable UUID id) {
        return ResponseEntity.ok(bookService.readOne(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Book book) {
        return ResponseEntity.status(201).body(bookService.insert(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Book book, @PathVariable UUID id) {
        bookService.update(book, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
