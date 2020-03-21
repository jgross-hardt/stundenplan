package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "kurse")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "`Kurs-ID`")
    private Long id;

    @Column(name = "Kursbezeichnung")
    private String kursbezeichnung;

    @ManyToOne
    @JoinColumn(name = "`Fach-ID`")
    private Fach fach;

    @ManyToOne
    @JoinColumn(name = "`Stufen-ID`")
    private Stufe stufe;

    @ManyToOne
    @JoinColumn(name = "`Lehrer-ID`")
    private Lehrer lehrer;

    @ManyToMany(mappedBy = "kurse")
    private Set<Schueler> schueler;

    @ManyToMany
    @JoinTable(
            name = "`kurs-stunde`",
            joinColumns = @JoinColumn(name = "`Kurs-ID`"),
            inverseJoinColumns = @JoinColumn(name = "`Stunden-ID`")
    )
    private Set<Stunde> stunden;

    public Kurs() {
    }

    public Kurs(String kursbezeichnung, Fach fach, Stufe stufe, Lehrer lehrer) {
        this.kursbezeichnung = kursbezeichnung;
        this.fach = fach;
        this.stufe = stufe;
        this.lehrer = lehrer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKursbezeichnung() {
        return kursbezeichnung;
    }

    public void setKursbezeichnung(String kursbezeichnung) {
        this.kursbezeichnung = kursbezeichnung;
    }

    public Fach getFach() {
        return fach;
    }

    public void setFach(Fach fach) {
        this.fach = fach;
    }

    public Stufe getStufe() {
        return stufe;
    }

    public void setStufe(Stufe stufe) {
        this.stufe = stufe;
    }

    public void setLehrer(Lehrer lehrer) {
        this.lehrer = lehrer;
    }

    public Set<Schueler> getSchueler() {
        return schueler;
    }

    public void setSchueler(Set<Schueler> schueler) {
        this.schueler = schueler;
    }

    public Set<Stunde> getStunden() {
        return stunden;
    }

    public void setStunden(Set<Stunde> stunden) {
        this.stunden = stunden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kurs kurs = (Kurs) o;
        return fach.equals(kurs.fach) &&
                stufe.equals(stufe) &&
                lehrer.equals(lehrer) &&
                Objects.equals(id, kurs.id) &&
                Objects.equals(kursbezeichnung, kurs.kursbezeichnung) &&
                Objects.equals(schueler, kurs.schueler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, kursbezeichnung, fach, stufe, lehrer, schueler);
    }
}
