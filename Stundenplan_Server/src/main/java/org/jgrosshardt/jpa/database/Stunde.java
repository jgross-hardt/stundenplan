package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stunden")
public class Stunde {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Stunden-ID")
    private Long id;

    @Column(name = "Tag", length = 3)
    private int tag;

    @Column(name = "Stunde", length = 3)
    private int stunde;

    @ManyToMany(mappedBy = "kurse")
    private Set<Kurs> kurse;

    public Stunde() {
    }

    public Stunde(Long id, int tag, int stunde) {
        this.id = id;
        this.tag = tag;
        this.stunde = stunde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getStunde() {
        return stunde;
    }

    public void setStunde(int stunde) {
        this.stunde = stunde;
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
        Stunde stunde1 = (Stunde) o;
        return tag == stunde1.tag &&
                stunde == stunde1.stunde &&
                Objects.equals(id, stunde1.id) &&
                Objects.equals(kurse, stunde1.kurse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tag, stunde, kurse);
    }
}
