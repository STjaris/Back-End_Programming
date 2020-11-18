package com.example.BackEnd_Programming.textimport;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("textimport")
public class textimportTests {

    private static final String content = "isLangerDan7";

    @Test
    @DisplayName("Is longer than 7")
    public void longerThan(){
        String text = "kjdbfdsabfbsd";
        boolean isLongerThan = text.length() > 7;
        assertTrue(isLongerThan);
    }

    @Test
    @DisplayName("Is not longer than 7")
    public void notLongerThan(){
        String text = "kjdbfdsabfbsd";
        boolean isLongerThan = text.length() <= 7;
        assertTrue(isLongerThan);
    }

    @Test
    @DisplayName("Is not smaller than 5")
    public void notSmallerThan(){
        String text = "kjdb";
        boolean isSmallerThan = text.length() >= 5;
        assertTrue(isSmallerThan);
    }



}
