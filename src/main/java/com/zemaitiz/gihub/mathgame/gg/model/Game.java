package com.zemaitiz.gihub.mathgame.gg.model;

import com.zemaitiz.gihub.mathgame.gg.App;

import java.time.LocalDateTime;

public class Game {

    private LocalDateTime dateTime;
    int difficultyLvl;
    int score;


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getDifficultyLvl() {
        return difficultyLvl;
    }

    public void setDifficultyLvl(int difficultyLvl) {
        this.difficultyLvl = difficultyLvl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void start() {
       App app = new App();
   };



}
