package ru.alsem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by SMertNIK on 25.06.2017.
 */
@Entity
public class Book {
    @Id
    @SequenceGenerator(name="book_id_seq",
            sequenceName="book_id_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="book_id_seq")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name="\"id\"", unique=true)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column (unique = true, nullable = false)
    private Long id;

    @NotNull
    private String title;

    private Float price;

    @Size(min = 10, max = 2000)
    private String description;

    private String isbn;


    public Book() {}
    public Book(String title, String description, Float price,  String isbn) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
