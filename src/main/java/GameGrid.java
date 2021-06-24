import java.util.ArrayList;
import java.util.Arrays;

public class GameGrid {
    private int height;
    private int width;
    private int[][] grid;
    private int[][] auxgrid;
    private int tickCounter;

    public GameGrid(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new int[height][width];
        this.auxgrid = new int[height][width];
        this.tickCounter=0;
    }

    // allow injection of 3X3 pattern at x,y coordinates in the grid
    public void injectPattern(int[][] pattern,int x, int y){
        // edge cases
        if(x<0 || y<0) {
            System.out.println("Invalid coordinates in grid.. injection failed");
        }else if(pattern.length==0 || pattern[0].length >= width) {
            System.out.println("Empty pattern.. injection failed");
        }else if((x+pattern.length) >= this.height || (y+pattern[0].length) >= width){
            System.out.println("Grid size insufficient for pattern injection.. injection failed");
        }else{
            for (int i = 0; i <pattern.length ; i++) {
                for (int j = 0; j < pattern[0].length; j++) {
                    this.grid[x+i][y+j] = pattern[i][j];
                }
            }
        }
        printGrid();
    }

    public void printGrid(){
        int[][] printableGrid;
        if(tickCounter%2!=0){
            printableGrid = auxgrid;
        }else{
            printableGrid = grid;
        }
        System.out.println("===================Grid================");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(printableGrid[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void nextGen(){
        // copy grid to aux grid
        int[][] source;
        int[][] target;
        if(tickCounter%2==0){
            source = grid;
            target = auxgrid;
        }else{
            source = auxgrid;
            target = grid;
        }
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source[0].length; j++) {
                // current state of cell
                int currentState = source[i][j];
                int neighbourCount = getAliveNeighbourCount(i,j, source);
                // curr state live and < 2 neighbours
                if(currentState == 1 && neighbourCount<2) {
                    target[i][j] = 0;
                }else if(currentState == 1 && (neighbourCount==2 || neighbourCount==3)) {
                    target[i][j] = 1;
                }else if(currentState == 1 && neighbourCount>3) {
                    target[i][j] = 0;
                }else if(currentState == 0 && neighbourCount==3) {
                    target[i][j] = 1;
                }else{
                    target[i][j] = source[i][j];
                }
            }
        }
        this.tickCounter++;
        printGrid();
    }

    private int getAliveNeighbourCount(int x, int y, int[][] source){
        int sum = 0;
        //x-1 , y-1 :
        sum += (x-1 < 0 || y-1 <0 ? 0 :source[x-1][y-1]);

        //x-1, y
        sum += (x-1 < 0 ? 0 :source[x-1][y]);

        //x-1 , y+1
        sum += (x-1 < 0 || y+1 > source[0].length-1? 0 :source[x-1][y+1]);

        //x, y-1
        sum += ( y-1 <0 ? 0 :source[x][y-1]);

        //x, y+1
        sum += (y+1 > source[0].length-1? 0 :source[x][y+1]);

        //x+1, y-1
        sum += (x+1 > source.length-1 || y-1 < 0? 0 :source[x+1][y-1]);

        //x+1,y
        sum += (x+1 > source.length-1 ? 0 :source[x+1][y]);

        //x+1,y+1
        sum += (x+1 > source.length-1 || y+1 > source[0].length-1? 0 :source[x+1][y+1]);
        return sum;
    }
}
