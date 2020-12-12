package nl.hu.vkbep.lingo.privilege.data;

import nl.hu.vkbep.lingo.privilege.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
