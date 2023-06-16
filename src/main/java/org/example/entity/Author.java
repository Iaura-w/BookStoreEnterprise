package org.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "autorzy")
public class Author {
    public Author() {
    }

    public Author(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "imie")
    private String name;

    @Column(name = "nazwisko")
    private String lastName;


    @ManyToMany
    @JoinTable(
            name = "autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "ksiazka_id")

    )
    private Set<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String imie) {
        this.name = imie;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String nazwisko) {
        this.lastName = nazwisko;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> ksiazki) {
        this.books = ksiazki;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
