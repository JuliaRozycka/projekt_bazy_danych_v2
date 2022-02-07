package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Objects;

@Entity
@Table(name = "widok_uzytkownika", schema = "punkt_szczepien", catalog = "")
public class WidokUzytkownika {
    @Id
    private static Long id;

    @Basic
    @Column(name = "id_login")
    private Integer idLogin;
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
    @Basic
    @Column(name = "status")
    private Object status;


    public WidokUzytkownika(Integer idLogin, String nazwaSzczepienia, String producent, Date data, Time godzina, Object status) {
        this.idLogin = idLogin;
        this.nazwaSzczepienia = nazwaSzczepienia;
        this.producent = producent;
        this.data = data;
        this.godzina = godzina;
        this.status = status;
    }

    public WidokUzytkownika(Integer integer, String s, String s1, String s2, String s3, Date date, Time time) {
    }

    public WidokUzytkownika() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
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
        WidokUzytkownika that = (WidokUzytkownika) o;
        return Objects.equals(idLogin, that.idLogin) && Objects.equals(nazwaSzczepienia, that.nazwaSzczepienia) && Objects.equals(producent, that.producent) && Objects.equals(data, that.data) && Objects.equals(godzina, that.godzina) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogin, nazwaSzczepienia, producent, data, godzina, status);
    }

    public static ArrayList<WidokUzytkownika> getWidok(ArrayList<Integer> idList, ArrayList<String> nazwaSzczepieniaList, ArrayList<String> producentList,
                                               ArrayList<Date> dataList, ArrayList<Time> godzinaList, ArrayList<Object> statusList) {
        ArrayList<WidokUzytkownika> widok = new ArrayList<>();
        for (int i = 0; i < idList.size(); i++)
            widok.add(new WidokUzytkownika(idList.get(i),
                    nazwaSzczepieniaList.get(i), producentList.get(i), dataList.get(i), godzinaList.get(i),statusList.get(i) ));
        return widok;
    }
}
