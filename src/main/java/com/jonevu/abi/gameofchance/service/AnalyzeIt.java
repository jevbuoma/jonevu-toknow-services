package com.jonevu.abi.gameofchance.service;

import java.util.Objects;

public class AnalyzeIt {
    private boolean isNumberMatched;
    private String winningNumbers;
    private String yourPicks;
    private String matchedNumbers;
    private String powerBallNumber;
    private String gameType;
    private String winningNumbersDate;

    public boolean isNumberMatched() {
        return isNumberMatched;
    }

    public void setNumberMatched(boolean numberMatched) {
        isNumberMatched = numberMatched;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(String winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public String getYourPicks() {
        return yourPicks;
    }

    public void setYourPicks(String yourPicks) {
        this.yourPicks = yourPicks;
    }

    public String getMatchedNumbers() {
        return matchedNumbers;
    }

    public void setMatchedNumbers(String matchedNumbers) {
        this.matchedNumbers = matchedNumbers;
    }

    public String getPowerBallNumber() {
        return powerBallNumber;
    }

    public void setPowerBallNumber(String powerBallNumber) {
        this.powerBallNumber = powerBallNumber;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getWinningNumbersDate() {
        return winningNumbersDate;
    }

    public void setWinningNumbersDate(String winningNumbersDate) {
        this.winningNumbersDate = winningNumbersDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalyzeIt)) return false;
        AnalyzeIt analyzeIt = (AnalyzeIt) o;
        return isNumberMatched == analyzeIt.isNumberMatched &&
                winningNumbers.equals(analyzeIt.winningNumbers) &&
                yourPicks.equals(analyzeIt.yourPicks) &&
                matchedNumbers.equals(analyzeIt.matchedNumbers) &&
                powerBallNumber.equals(analyzeIt.powerBallNumber) &&
                gameType.equals(analyzeIt.gameType) &&
                winningNumbersDate.equals(analyzeIt.winningNumbersDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isNumberMatched, winningNumbers, yourPicks, matchedNumbers, powerBallNumber, gameType, winningNumbersDate);
    }

    @Override
    public String toString() {
        return "AnalyzeIt{" +
                "isNumberMatched=" + isNumberMatched +
                ", winningNumbers='" + winningNumbers + '\'' +
                ", yourPicks='" + yourPicks + '\'' +
                ", matchedNumbers='" + matchedNumbers + '\'' +
                ", powerBallNumber='" + powerBallNumber + '\'' +
                ", gameType='" + gameType + '\'' +
                ", winningNumbersDate='" + winningNumbersDate + '\'' +
                '}';
    }
}
