# GameOfLife

## Classes
Since Game of life is a 0 player game, we do not need to set up entities for players. So here I have set up a class for the game grid itself.
### GameGrid
The game of life has an infinite grid but for the purpose of this implementation I have taken a 2D grid of fixed dimensions. This grid is modeled as a class called GameGrid which has following member variables & methods :
#### Member variables
- **height** of the 2D grid.
- **width** of the 2D grid.
- **grid** : the 2D array of integers which holds the grid.
- **auxgrid** : the second 2D array. More on this with the tick/nextgen method.
- **tickCounter** : the counter to keep track of current generation.

#### Member methods
- **constructor** : initializes the two grids with given height & width; tickCounter as 0.
  - input : height & width
- **injectPattern** : injects a smaller 2D grid pattern of dimensions <= game grid's dimension (this is how I inject a pattern like **Glider**)
  - input : a pattern of any dimension (method validates whether it is within the dimension of game grid), x,y coordinates of where the top left corner of thsi has to be placed withing the gamegrid.
- **nextGen** : this method responsible for progressing the game grid from current to the next generation. Our grid is initialized from 0th generation. Since the next generation is entirely based on the previous generation, we need to keep the information from previous generation while we create the next one. For this I have kept two grids in the class. One for the current state / source generation, another for next/target generation. After a single tick/next generation is processed we simply switch the roles of the grids as the storage for source/target generations. The tickCounter is used to determine which role are these two grids playing at any point of time. Prints the grid after every tick.
- **printGrid** : prints the grid which is playing the role of target generation because that contains the latest state/ generation of the game grid. 
- **getAliveNeighbourCount** : this is the private member which is called by the nextGen method to find the number of alive neighbours for any given x,y coordinates in the game grid
  - input : x,y coordinates of the cell for which we want to find the neighbour count, source generation grid's 2D array
  - output : number of alive neighbours for this cell
  
### Main
The runner class for this game application. Here I have initialized a grid with fixed height & width. I have set up three patterns : one glider & two stable patterns which do not glide across the grid.

I have injected the pattern picked & ran 4 generations on the game grid instance.
