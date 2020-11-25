package com.example.BackEnd_Programming.game.service;

import com.example.BackEnd_Programming.game.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService implements GameServiceInterface{

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void getAllGames() {

    }
}
