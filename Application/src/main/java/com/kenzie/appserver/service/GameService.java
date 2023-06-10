package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.CreateGameRequest;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.repositories.ExampleRepository;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.model.ExampleRecord;
import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.service.model.Example;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.ExampleData;

public class GameService {

    private GameRepository gameRepository;
    private LambdaServiceClient lambdaServiceClient;

    public GameService(GameRepository gameRepository, LambdaServiceClient lambdaServiceClient) {
        this.gameRepository = gameRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public GameResponse addNewGame(CreateGameRequest createGameRequest) {

        GameRecord gameRecord = new GameRecord();
        gameRecord.setTitle(createGameRequest.getTitle());
        gameRecord.setLoaner(createGameRequest.getLoaner());
        gameRecord.setAvailable(createGameRequest.isAvailable());
        gameRepository.save(gameRecord);

        return createGameResponse(gameRecord);
    }

    private GameResponse createGameResponse(GameRecord gameRecord){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setTitle(gameRecord.getTitle());
        gameResponse.setLoaner(gameRecord.getLoaner());
        gameResponse.setAvailable(gameRecord.isAvailable());
        return gameResponse;
    }
}
