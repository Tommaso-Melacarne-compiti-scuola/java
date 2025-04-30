package online.polp.battleship.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class BattleshipController {
    @GetMapping("/")
    public String index() {
        return "Greetings from Battleship!";
    }
}
