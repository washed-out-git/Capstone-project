package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.CreateGameRequest;
import com.kenzie.appserver.controller.model.GameResponse;
import com.kenzie.appserver.repositories.GameRepository;
import com.kenzie.appserver.repositories.model.GameRecord;
import com.kenzie.appserver.service.model.Game;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
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
        gameRecord.setAvailable(true);


        if(!createGameRequest.getBorrower().isEmpty()){
            //Lambda function call here
            gameRecord.setBorrower(createGameRequest.getBorrower());
            gameRecord.setAvailable(false);
        }

        gameRepository.save(gameRecord);
        return createGameResponse(gameRecord);
    }

    public List<Game> findAllGames(){
        List<Game> listOfGames = new ArrayList<>();

        Iterable<GameRecord> gameIterator = gameRepository.findAll();

        if(gameIterator.equals("")){
            return new ArrayList<>();
        }

        for(GameRecord record : gameIterator) {
            listOfGames.add(new Game(record.getTitle(),
                    record.getLoaner(),
                    record.getBorrower()));
        }
        return listOfGames;
    }


    private GameResponse createGameResponse(GameRecord gameRecord){
        GameResponse gameResponse = new GameResponse();
        gameResponse.setTitle(gameRecord.getTitle());
        gameResponse.setLoaner(gameRecord.getLoaner());
        gameResponse.setBorrower(gameRecord.getBorrower());
        gameResponse.setAvailable(gameRecord.isAvailable());
        return gameResponse;
    }
}
