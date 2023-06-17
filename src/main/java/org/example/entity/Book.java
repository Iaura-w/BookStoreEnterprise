package org.example.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ksiazki")
public class Book {
    public Book() {
    }

    public Book(String name, String publisher, float price) {
        this.name = name;
        this.publisher = publisher;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nazwa")
    private String name;

    @Column(name = "wydawnictwo")
    private String publisher;

    @Column(name = "cena")
    private float price;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kategoria_id")
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name = "ksiazka_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Author> authors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String wydawnictwo) {
        this.publisher = wydawnictwo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float cena) {
        this.price = cena;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> autorzy) {
        this.authors = autorzy;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public List<Integer> getAuthorsIds() {
        List<Integer> authorsIds = new ArrayList<>();
        for (Author a : this.authors) {
            authorsIds.add(a.getId());
        }
        return authorsIds;
    }
}
