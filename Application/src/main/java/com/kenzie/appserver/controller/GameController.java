package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.CreateGameRequest;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.service.GameService;
import com.kenzie.appserver.service.model.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @PostMapping
    public ResponseEntity<GameResponse> addNewGame(@RequestBody CreateGameRequest createGameRequest) {
        GameResponse gameResponse = gameService.addNewGame(createGameRequest);
        return ResponseEntity.ok(gameResponse);
    }
}
