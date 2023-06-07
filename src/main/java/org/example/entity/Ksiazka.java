package org.example.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ksiazki")
public class Ksiazka {
    public Ksiazka() {
    }

    public Ksiazka(String nazwa, String wydawnictwo, float cena) {
        this.nazwa = nazwa;
        this.wydawnictwo = wydawnictwo;
        this.cena = cena;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nazwa")
    private String nazwa;

    @Column(name = "wydawnictwo")
    private String wydawnictwo;

    @Column(name = "cena")
    private float cena;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name = "ksiazka_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")

    )
    private Set<Autor> autorzy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAutor(Autor autor) {
        if (autorzy == null)
            autorzy = new HashSet<>();
        //autor.addKsiazka(this);
        autorzy.add(autor);
    }

    public void removeAutor(Autor autor) {
        if (autorzy == null)
            return;
        //autor.addKsiazka(this);
        autorzy.remove(autor);


    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

    public Set<Autor> getAutorzy() {
        return autorzy;
    }

    public void setAutorzy(Set<Autor> autorzy) {
        this.autorzy = autorzy;
    }

    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", wydawnictwo='" + wydawnictwo + '\'' +
                ", cena=" + cena +
                ", kategoria=" + kategoria +
                '}';
    }

    public List<Integer> getAuthorsIds() {
        List<Integer> authorsIds = new ArrayList<>();
        for (Autor a : this.autorzy) {
            authorsIds.add(a.getId());
        }
        return authorsIds;
    }
}
