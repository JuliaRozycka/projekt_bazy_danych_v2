package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Szczepienia {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "nazwa_szczepienia")
    private String nazwaSzczepienia;
    @Basic
    @Column(name = "producent")
    private String producent;
    @Basic
    @Column(name = "status")
    private Object status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwaSzczepienia() {
        return nazwaSzczepienia;
    }

    public void setNazwaSzczepienia(String nazwaSzczepienia) {
        this.nazwaSzczepienia = nazwaSzczepienia;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Szczepienia that = (Szczepienia) o;
        return id == that.id && Objects.equals(nazwaSzczepienia, that.nazwaSzczepienia) && Objects.equals(producent, that.producent) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazwaSzczepienia, producent, status);
    }
}
