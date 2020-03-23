public class Fach {
    private Integer id;
    private String fach;
    private String shorthand;

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

    @Override
    public String toString() {
        return fach;
    }
}