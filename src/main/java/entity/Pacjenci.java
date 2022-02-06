package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pacjenci {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "pesel")
    private static String pesel;
    @Basic
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic
    @Column(name = "imie")
    private String imie;
    @Basic
    @Column(name = "nazwisko")
    private String nazwisko;
    @Basic
    @Column(name = "numer_telefonu")
    private String numerTelefonu;

    public static String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
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

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public void setNumerTelefonu(String numerTelefonu) {
        this.numerTelefonu = numerTelefonu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacjenci pacjenci = (Pacjenci) o;
        return Objects.equals(pesel, pacjenci.pesel) && Objects.equals(idLogin, pacjenci.idLogin) && Objects.equals(imie, pacjenci.imie) && Objects.equals(nazwisko, pacjenci.nazwisko) && Objects.equals(numerTelefonu, pacjenci.numerTelefonu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel, idLogin, imie, nazwisko, numerTelefonu);
    }
}
