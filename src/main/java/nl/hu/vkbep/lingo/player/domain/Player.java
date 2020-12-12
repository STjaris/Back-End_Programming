package nl.hu.vkbep.lingo.player.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private boolean enabled;

    private boolean tokenExpired;


    public Player(String name, String password, String email, boolean enabled, boolean tokenExpired) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.tokenExpired = tokenExpired;

    }

    public Player() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }

}
