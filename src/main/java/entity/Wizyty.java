package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@IdClass(WizytyPK.class)
public class Wizyty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "data")
    private Date data;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "godzina")
    private Time godzina;
    @Basic
    @Column(name = "pesel_pacjenci")
    private String peselPacjenci;
    @Basic
    @Column(name = "id_szczepienia")
    private Integer idSzczepienia;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getGodzina() {
        return godzina;
    }

    public void setGodzina(Time godzina) {
        this.godzina = godzina;
    }

    public String getPeselPacjenci() {
        return peselPacjenci;
    }

    public void setPeselPacjenci(String peselPacjenci) {
        this.peselPacjenci = peselPacjenci;
    }

    public Integer getIdSzczepienia() {
        return idSzczepienia;
    }

    public void setIdSzczepienia(Integer idSzczepienia) {
        this.idSzczepienia = idSzczepienia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wizyty wizyty = (Wizyty) o;
        return Objects.equals(data, wizyty.data) && Objects.equals(godzina, wizyty.godzina) && Objects.equals(peselPacjenci, wizyty.peselPacjenci) && Objects.equals(idSzczepienia, wizyty.idSzczepienia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, godzina, peselPacjenci, idSzczepienia);
    }
}
