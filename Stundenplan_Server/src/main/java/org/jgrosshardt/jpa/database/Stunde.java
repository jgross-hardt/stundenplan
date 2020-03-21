package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stunden")
public class Stunde {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`Stunden-ID`")
    private Integer id;

    @Column(name = "Tag", length = 3)
    private short tag;

    @Column(name = "Stunde", length = 3)
    private short stunde;

    @ManyToMany(mappedBy = "stunden")
    private Set<Kurs> kurse;

    public Stunde() {
    }

    public Stunde(short tag, short stunde) {
        this.tag = tag;
        this.stunde = stunde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getTag() {
        return tag;
    }

    public void setTag(short tag) {
        this.tag = tag;
    }

    public int getStunde() {
        return stunde;
    }

    public void setStunde(short stunde) {
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

    @Override
    public String toString() {
        return "" + tagFromInt(tag) + ", " + stunde + ". Stunde";
    }

    private static String tagFromInt(short tag) {
        switch (tag) {
        case 0:
            return "MO";
        case 1:
            return "DI";
        case 2:
            return "MI";
        case 3:
            return "DO";
        case 4:
            return "FR";
        case 5:
            return "SA";
        case 6:
            return "SO";
        default:
            return "##";
        }
    }
}
