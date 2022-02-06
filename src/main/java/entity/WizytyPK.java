package entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class WizytyPK implements Serializable {
    @Column(name = "data")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Date data;
    @Column(name = "godzina")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Time godzina;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WizytyPK wizytyPK = (WizytyPK) o;
        return Objects.equals(data, wizytyPK.data) && Objects.equals(godzina, wizytyPK.godzina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, godzina);
    }
}
