package nl.hu.vkbep.lingo.role.data;

import nl.hu.vkbep.lingo.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
