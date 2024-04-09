package sudoku.computationlogic;

import java.util.ArrayList;
import java.util.Collections;

import JavaDesktopSudokuDemo.src.sudoku.constants.GameState;
import JavaDesktopSudokuDemo.src.sudoku.problemdomain.SudokuGame;

public class GameLogic {
    public static SudokuGame getNewGame(){
        return new SudokuGame(
            GameState.NEW,
            GameGenerator.getNewGameGrid()
        );
    }
    public static GameState checkForCompletion(int[][] grid){
        if(sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if(tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid){
        if(rowsAreInvalid(grid)) return true;
        if(columnsAreInvalid(grid)) return true;
        if(squaresAreInvalid(grid)) return true;
        else return false;
    }
    private static boolean rowsAreInvalid(int[][] grid){
        for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
            List<Integer> row = new ArrayList<>();
            for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
                row.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(row)) return true;
        }
        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid){
        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            List<Integer> row = new ArrayList<>();
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                row.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(row)) return true;
        }
        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid){
        if(rowsOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        if(rowsOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        if(rowsOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowsOfSquaresIsInvalid(Rows top, int[][] grid){
        switch (value) {
            case TOP:
                if(squareIsInValid(0, 0, grid)) return true;
                if(squareIsInValid(0, 3, grid)) return true;
                if(squareIsInValid(0, 6, grid)) return true;
                return false;

            case MIDDLE:
                if(squareIsInValid(3, 0, grid)) return true;
                if(squareIsInValid(3, 3, grid)) return true;
                if(squareIsInValid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if(squareIsInValid(6, 0, grid)) return true;
                if(squareIsInValid(6, 3, grid)) return true;
                if(squareIsInValid(6, 6, grid)) return true;
                return false;
            default:


        }
    }
    private static boolean squareIsInValid(int xIndex, int yIndex, int[][] grid){
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while(yIndex < yIndexEnd){
            while (xIndex < xIndexEnd) {
                square.add(grid[xIndex][yIndex]);

                xIndex++;
                
            }
            xIndex -= 3;

            yIndex++;
        }
        if(collectionHasRepeats(square)) return true;
        return false;

    }
    public static boolean collectionHasRepeats(List<Integer> collection){
        for(int index = 1; index <= GRID_BOUNDARY; index++){
            if(Collections.frequency(collection, index) > 1) return true;
        }
        return false;
    }
    public static boolean tilesAreNotFilled(int[][] grid){
        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                if(grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }
}
