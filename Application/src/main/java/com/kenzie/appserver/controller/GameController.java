package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CreateGameRequest;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    GameController(GameService gameService){
        this.gameService = gameService;
    }


    @PostMapping
    public ResponseEntity<GameResponse> addNewGame(@RequestBody CreateGameRequest createGameRequest) {
        GameResponse gameResponse = gameService.addNewGame(createGameRequest);
        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping("/listOfGames")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.findAllGames();
        return ResponseEntity.ok(games);
    }

}
