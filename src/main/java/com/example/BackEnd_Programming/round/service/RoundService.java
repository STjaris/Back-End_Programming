package com.example.BackEnd_Programming.round.service;

import com.example.BackEnd_Programming.fileImport.TextImportInterface;
import com.example.BackEnd_Programming.word.service.WordServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Scanner;

@Service
public class RoundService implements RoundServiceInterface{

    private TextImportInterface textImportInterface;
    private WordServiceInterface wordServiceInterface;

//    @Autowired
//    public RoundService(WordServiceInterface wordServiceInterface, TextImportInterface textImportInterface ) {
//        this.textImportInterface = textImportInterface;
//        this.wordServiceInterface = wordServiceInterface;
//    }

    public RoundService() {
    }

    public void startNewRound(){

    }


}
