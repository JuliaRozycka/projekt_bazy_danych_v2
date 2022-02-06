package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pracownicy {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "login")
    private String login;
    @Basic
    @Column(name = "haslo")
    private String haslo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pracownicy that = (Pracownicy) o;
        return id == that.id && Objects.equals(login, that.login) && Objects.equals(haslo, that.haslo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, haslo);
    }
}
