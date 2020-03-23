package org.jgrosshardt.jpa.database;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "unbestaetigter_nutzer")
public class Unbestaetigt {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;
    private String bestaetigungs_schluessel;

    public Unbestaetigt(String vorname, String nachname, String benutzername, String passwort, String bestaetigungs_schluessel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.bestaetigungs_schluessel = bestaetigungs_schluessel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getBestaetigungs_schluessel() {
        return bestaetigungs_schluessel;
    }

    public void setBestaetigungs_schluessel(String bestaetigungs_schluessel) {
        this.bestaetigungs_schluessel = bestaetigungs_schluessel;
    }

    @Override
    public String toString() {
        return benutzername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unbestaetigt that = (Unbestaetigt) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(vorname, that.vorname) &&
                Objects.equals(nachname, that.nachname) &&
                Objects.equals(benutzername, that.benutzername) &&
                Objects.equals(passwort, that.passwort) &&
                Objects.equals(bestaetigungs_schluessel, that.bestaetigungs_schluessel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vorname, nachname, benutzername, passwort, bestaetigungs_schluessel);
    }
}
