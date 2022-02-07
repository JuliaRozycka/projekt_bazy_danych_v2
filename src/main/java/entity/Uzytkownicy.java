package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Uzytkownicy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public static int id;
    @Basic
    @Column(name = "login")
    public static String login;
    @Basic
    @Column(name = "haslo")
    public static String haslo;

    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public static String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzytkownicy that = (Uzytkownicy) o;
        return id == that.id && Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, haslo);
    }
}
