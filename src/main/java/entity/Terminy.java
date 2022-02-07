package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

@Entity
public class Terminy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "data")
    private Date data;
    @Basic
    @Column(name = "godzina")
    private Time godzina;

    public Terminy(int id, Date data, Time godzina) {
        this.id = id;
        this.data = data;
        this.godzina = godzina;
    }

    public Terminy() {
    }

    @Override
    public String toString() {
        return "Terminy{" +
                "id=" + id +
                ", data=" + data +
                ", godzina=" + godzina +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        Terminy terminy = (Terminy) o;
        return id == terminy.id && Objects.equals(data, terminy.data) && Objects.equals(godzina, terminy.godzina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data, godzina);
    }

    public static ArrayList<Terminy> getTerms(ArrayList<Integer> idList, ArrayList<Date> dataList, ArrayList<Time> godzinaList) {
        ArrayList<Terminy> terms = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++)
            terms.add(new Terminy(idList.get(i), dataList.get(i), godzinaList.get(i)));
        return terms;
    }
}
