package nl.hu.vkbep.lingo.player.application;

import nl.hu.vkbep.lingo.player.data.PlayerRepository;
import nl.hu.vkbep.lingo.player.domain.Player;
import nl.hu.vkbep.lingo.role.data.RoleRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PlayerService implements PlayerServiceInterface, UserDetailsService {


    private final PlayerRepository playerRepository;
    private final RoleRepository roleRepository;

    public PlayerService(PlayerRepository playerRepository, RoleRepository roleRepository) {
        this.playerRepository = playerRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Player player = playerRepository.getUserByName(username);

        return new User(
                player.getEmail(), player.getPassword(), player.isEnabled(), true, true,
                true, new ArrayList<>());
    }
}
