public class Maze
{
    private Cell[][] board;
    private final int DELAY = 200;
    boolean complete = false;
   

    public Maze(int rows, int cols, int[][] map){
        StdDraw.setXscale(0, cols);
        StdDraw.setYscale(0, rows);
        board = new Cell[rows][cols];
        //grab number of rows to invert grid system with StdDraw (lower-left, instead of top-left)
        int height = board.length - 1;
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                board[r][c] = map[r][c] == 1 ? new Cell(c , height - r, 0.5, false) : new Cell(c, height - r, 0.5, true);
            }
    }

    /*
    *   This method draws the current state of the maze to the canvas.
    *   Leave the method alone.
    */
    public void draw()
    {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++){
                Cell cell = board[r][c];
                StdDraw.setPenColor(cell.getColor());
                StdDraw.filledSquare(cell.getX(), cell.getY(), cell.getRadius());
            }
            StdDraw.show();
    }

    private boolean isValid(int row, int col)
    {
        if (row < 0 || row >= board.length || 
        col <0 || col >= board[0].length ||
        board[row][col].isWall() == true || 
        board[row][col].isVisited() == true )
        {
        return false;
       }
       else 
       {
        return true;
      }
    }

    private boolean isExit(int row, int col)
    {
        if (row == board.length-1 && col == board[0].length-1)
        {
            return true;
        }
        else
        {
        return false;
    }
    }

    public boolean findPath(int row, int col)
    {   
        if (isValid(row, col))
            {  
                board[row][col].visitCell();
                draw();
                StdDraw.pause(30);
                if (isExit(row, col))
                {
                    complete = true;
                }
                else if (!complete)
                {
                    if (isValid(row+1, col))
                    {
                      findPath(row+1, col);
                    }
                    if (isValid(row, col+1))
                    {
                      findPath(row, col+1);
                    }
                    if (isValid(row-1, col))
                    {
                      findPath(row-1, col);
                    }
                    if (isValid(row, col-1))
                    {
                      findPath(row, col-1);
                    }
                }
                if (complete)
                {
                    board[row][col].becomePath();
                    draw();
                    StdDraw.pause(30);
                }
            }  
        return complete;
    }
    private int[][] randomMaze(int rows, int cols)
    {
        int[][] randomMaze = new int[rows][cols];
        double p = .4;
            for (int i = 0; i < rows; i ++)
        {
            for (int j = 0; j < cols; j ++)
        {
            double randomNum = Math.random();
            if ( (i == 0 || i == rows || j == 0 || j == cols) && !(i == 0 && j ==0))
            {
                randomMaze[i][j] = 0;
            }
            else if (randomNum < p)
            {
                randomMaze[i][j] = 0;
            }
            else
            {
                randomMaze[i][j] = 1;
            }
        }
        }
        return randomMaze;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        int[][] maze = {{1,1,0,0,0,0,0,0,0,0},
                        {0,1,1,1,1,0,1,1,1,0},
                        {0,1,1,1,1,0,1,1,0,0},
                        {0,1,0,1,1,1,1,1,1,0},
                        {0,0,0,0,0,1,0,1,1,0},
                        {0,1,1,1,1,1,0,1,1,0},
                        {0,1,1,0,0,1,0,0,1,0},
                        {0,1,1,0,1,1,0,1,1,0},
                        {0,1,1,0,1,1,0,1,1,0},
                        {0,0,0,0,0,0,0,0,1,1}};
                        
         int[][] maze2 = {{1,1,0,0,0,0,0,0,0,0},
                        {0,1,1,1,1,0,1,1,1,0},
                        {0,1,1,1,1,0,1,1,0,0},
                        {0,1,0,1,1,1,1,1,1,0},
                        {0,0,0,0,0,0,0,1,1,0},
                        {0,1,1,1,1,1,0,1,1,0},
                        {0,1,1,0,0,1,0,0,1,0},
                        {0,1,1,0,1,1,0,1,1,0},
                        {0,1,1,0,1,1,0,1,1,0},
                        {0,0,0,0,0,0,0,0,1,1}};
        Maze geerid = new Maze(maze.length, maze[0].length, maze);
        geerid.draw();
        geerid.findPath(0, 0);
        geerid.draw();
     
    }
}
