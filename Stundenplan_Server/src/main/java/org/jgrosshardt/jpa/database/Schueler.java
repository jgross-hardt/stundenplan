package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "schueler")
public class Schueler {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`Schueler-ID`")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "`Stufen-ID`")
    private Stufe stufe;

    @Column(name = "Benutzername")
    private String username;

    @Column(name = "Passwort")
    private String password;

    @Column(name = "Vorname")
    private String firstname;

    @Column(name = "Nachname")
    private String lastname;

    @ManyToMany
    @JoinTable(
            name = "`schueler-kurs`",
            joinColumns = @JoinColumn(name = "`Schueler-ID`"),
            inverseJoinColumns = @JoinColumn(name = "`Kurs-ID`")
    )
    private Set<Kurs> kurse;

    public Schueler() {
    }

    public Schueler(Stufe stufe, String username, String password, String firstname, String lastname) {
        this.stufe = stufe;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stufe getStufe() {
        return stufe;
    }

    public void setStufe(Stufe stufe) {
        this.stufe = stufe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
        Schueler schueler = (Schueler) o;
        return Objects.equals(id, schueler.id) &&
                Objects.equals(stufe, schueler.stufe) &&
                Objects.equals(username, schueler.username) &&
                Objects.equals(password, schueler.password) &&
                Objects.equals(firstname, schueler.firstname) &&
                Objects.equals(lastname, schueler.lastname) &&
                Objects.equals(kurse, schueler.kurse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stufe, username, password, firstname, lastname, kurse);
    }
}
