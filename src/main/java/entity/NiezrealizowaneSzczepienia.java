package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "niezrealizowane_szczepienia", schema = "punkt_szczepien")
public class NiezrealizowaneSzczepienia {
    @Id
    private Long id;

    @Basic
    @Column(name = "pesel")
    private static String pesel;
    @Basic
    @Column(name = "imie")
    private String imie;
    @Basic
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic
    @Column(name = "nazwa_szczepienia")
    private String nazwaSzczepienia;
    @Basic
    @Column(name = "producent")
    private String producent;
    @Basic
    @Column(name = "data")
    private Date data;
    @Basic
    @Column(name = "godzina")
    private Time godzina;

    public NiezrealizowaneSzczepienia(String pesel, String imie, String nazwisko, String nazwaSzczepienia, String producent, Date data, Time godzina) {
        this.pesel = pesel;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nazwaSzczepienia = nazwaSzczepienia;
        this.producent = producent;
        this.data = data;
        this.godzina = godzina;
    }

    public NiezrealizowaneSzczepienia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
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
        NiezrealizowaneSzczepienia that = (NiezrealizowaneSzczepienia) o;
        return Objects.equals(pesel, that.pesel) && Objects.equals(imie, that.imie) && Objects.equals(nazwisko, that.nazwisko) && Objects.equals(nazwaSzczepienia, that.nazwaSzczepienia) && Objects.equals(producent, that.producent) && Objects.equals(data, that.data) && Objects.equals(godzina, that.godzina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, imie, nazwisko, nazwaSzczepienia, producent, data, godzina);
    }

    public static ArrayList<NiezrealizowaneSzczepienia> getNiezreal(ArrayList<String> peselList, ArrayList<String> ImieList, ArrayList<String>
            NazwiskoList, ArrayList<String> nazwaSzczepieniaList, ArrayList<String> producentList,
                                                                    ArrayList<Date> dataList, ArrayList<Time> godzinaList) {
        ArrayList<NiezrealizowaneSzczepienia> niezreal = new ArrayList<>();
        for (int i = 0; i < peselList.size(); i++)
            niezreal.add(new NiezrealizowaneSzczepienia(peselList.get(i), ImieList.get(i), NazwiskoList.get(i),
                    nazwaSzczepieniaList.get(i), producentList.get(i), dataList.get(i), godzinaList.get(i)));
        return niezreal;
    }
}
