package cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.controllers;

import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.EntityDTOSMapper;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.domain.Player;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.interfaces.AuthService;
import cat.itacademy.barcelonactiva.salasderamo.ezequiel.s05.t02.n01.model.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private EntityDTOSMapper mapper;

    @PostMapping
    public PlayerDTO login(@RequestParam("user") String username, @RequestParam("password") String pwd) {

        String token = authService.authenticate(username, pwd);
        Player player = playerService.getByUser(username);
        PlayerDTO playerDTO = mapper.entityToDTO(player, null);
        playerDTO.setToken(token);

        return playerDTO;

    }

}
