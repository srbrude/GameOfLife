public class GameOfLifeRunner {
    public static void main(String[] args) {

        // init a grid of 10x10 size
        GameGrid grid = new GameGrid(10,10);
        // init a grid of 3x3 size
        int[][] gliderPattern = new int[][]{{0,1,0},
                                            {0,0,1},
                                            {1,1,1}};
        int[][] stablePattern = new int[][]{{0,0,0},
                                            {1,1,1},
                                            {0,0,0}};
        int[][] stablePattern2 = new int[][]{{0,0,0,0},
                                            {0,1,1,0},
                                            {0,1,1,0},
                                            {0,0,0,0}};
        // inject at specified position
//        grid.injectPattern(stablePattern, 3,3);
//        grid.injectPattern(stablePattern2, 3,3);
        grid.injectPattern(gliderPattern, 3,3);

        // create next generation from current one
        for (int i = 0; i < 4; i++) {
            System.out.println("======================Gen "+(i+1));
            grid.nextGen();
        }

    }
}
