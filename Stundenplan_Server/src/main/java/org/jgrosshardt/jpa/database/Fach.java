package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "faecher")
public class Fach {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`Fach-ID`")
    private Integer id;

    @Column(name = "Fach", length = 255)
    private String fach;

    @Column(name = "Kuerzel")
    private String shorthand;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "`fach-lehrer`",
            joinColumns = @JoinColumn(name = "`Lehrer-ID`"),
            inverseJoinColumns = @JoinColumn(name = "`Fach-ID`")
    )
    private Set<Lehrer> lehrer;

    public Fach() {
    }

    public Fach(String fach, String shorthand) {
        this.fach = fach;
        this.shorthand = shorthand;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public Set<Lehrer> getLehrer() {
        return lehrer;
    }

    public void setLehrer(Set<Lehrer> lehrerSet) {
        this.lehrer = lehrerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fach fach1 = (Fach) o;
        return Objects.equals(id, fach1.id) &&
                Objects.equals(fach, fach1.fach) &&
                Objects.equals(lehrer, fach1.lehrer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fach, lehrer);
    }

    @Override
    public String toString() {
        return fach;
    }
}
