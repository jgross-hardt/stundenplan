package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lehrer")
public class Lehrer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Lehrer-ID")
    private Long id;

    @Column(name = "Nachname")
    private String lastname;

    @Column(name = "Geschlecht")
    private char gender;

    @Column(name = "Kuerzel")
    private String shorthand;

    @ManyToMany(mappedBy = "lehrer")
    private Set<Fach> faecher;

    @OneToMany(mappedBy = "lehrer")
    private Set<Kurs> kurse;

    public Lehrer() {

    }

    public Lehrer(String lastname, char gender, String shorthand) {
        this.lastname = lastname;
        this.gender = gender;
        this.shorthand = shorthand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public Set<Fach> getFaecher() {
        return faecher;
    }

    public void setFaecher(Set<Fach> faecher) {
        this.faecher = faecher;
    }

    public Set<Kurs> getKurse() {
        return kurse;
    }

    public void setKurse(Set<Kurs> kurse) {
        this.kurse = kurse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lehrer lehrer = (Lehrer) o;
        return id == lehrer.id &&
                gender == lehrer.gender &&
                Objects.equals(lastname, lehrer.lastname) &&
                Objects.equals(shorthand, lehrer.shorthand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, gender, shorthand);
    }
}
