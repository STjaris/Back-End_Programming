package nl.hu.vkbep.lingo.player.domain;

import com.sun.istack.NotNull;
import nl.hu.vkbep.lingo.role.domain.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name= "player")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "player_roles",
            joinColumns = @JoinColumn(
                    name = "playerid", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "roleid", referencedColumnName = "id"))

    private Collection<Role> roles;

    public Player(String name, String password, String email, boolean enabled, boolean tokenExpired, Collection<Role> roles) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.tokenExpired = tokenExpired;
        this.roles = roles;
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
    public Collection<Role> getRoles() {
        return roles;
    }
}
