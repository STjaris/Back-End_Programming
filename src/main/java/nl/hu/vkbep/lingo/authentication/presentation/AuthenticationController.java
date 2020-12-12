package nl.hu.vkbep.lingo.authentication.presentation;

import nl.hu.vkbep.lingo.authentication.application.JwtService;
import nl.hu.vkbep.lingo.models.domain.AuthenticationRequest;
import nl.hu.vkbep.lingo.models.domain.AuthenticationResponse;
import nl.hu.vkbep.lingo.player.application.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {


    private AuthenticationManager authenticationManager;
    private PlayerService playerService;
    private JwtService jwtService;

    public AuthenticationController(AuthenticationManager authenticationManager, PlayerService playerService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.playerService = playerService;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("INCORRECT USERNAME OR PASSWORD", e);
        }

        UserDetails userDetails = playerService.loadUserByUsername(request.getUsername());

        String jwt = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
