package nl.hu.vkbep.lingo.privilege.domain;

import nl.hu.vkbep.lingo.role.domain.Role;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="privilege")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public void setName(String name) {
        this.name = name;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
}
