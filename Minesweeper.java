/*
GROUP 3 - Sec 02
Cory Suzuki - 025749631
Jeannie Gonzalez - 027717857
10 February 2022
Description:
This program generates a Minesweeper game board based on user-provided inputs. Users
can input integer values from 5-10 when specifying rows, columns, and mines to generate.
Assignments: Jeannie fixed errors in the code and wrote the main string and displayGrid()
method. Cory also fixed errors in the code and wrote the placeMines() and fillGrid()
method. Both authors contributed to syntax reformatting and Java Documentation.
*/
import java.util.Scanner;
class Main {
  /**
   * This is the main method that contains the variables for the number of rows, columns,
   * and mines for the array
   * @param args No parameters as arguments taken into the main method.
   **/
    public static void main(String[] args) {
        CheckInput user_input = new CheckInput();
        System.out.println("Minesweeper Maker");
        System.out.printf("Enter number of Rows (5-10) : ");
        int rows = user_input.getIntRange(5,10);
        System.out.printf("Enter number of Columns (5-10) : ");
        int cols = user_input.getIntRange(5,10);
        System.out.printf("Enter number of Mines (5-10) : ");
        int num_mines = user_input.getIntRange(5,10);
        int [][] board = new int [rows][cols];
        placeMines(board, num_mines);
        fillGrid(board);
        displayGrid(board);
    }

    /**
     * This is the placeMines method which passes the board and number of mines variables
     * as integer-based values.
     * @param grid The grid variable is passed in as a parameter that holds the 2D array
     * stored in the board variable from the main method.
     * @param mines The mines variable is passed in as a parameter that holds the number
     * of mines stored in the num_mines variable from the method.
     * @return The grid variable that contains the modified 2D Array is returned to be
     * used in other methods.
     **/

    public static void placeMines(int [][] grid, int mines) {
        int mine_limit = 0;
        int mine_place = 9;
        while (mine_limit < mines) {
            int row = (int) (Math.random() * grid.length - 1) + 0;
            int cols = (int) (Math.random() * (grid[0].length - 1)) + 0;
            if (grid[row][cols] != mine_place) {
                grid[row][cols] = mine_place;    //places mine where one is not present
                mine_limit++;
            }
        }
    }

    /**
     * This is the fillGrid method which takes in the grid variable as a parameter and counts
     * the number of mines adjacent to (left and right) and directly normal (above and below)
     * about each mine placed.
     * @param grid The grid variable is passed in to this method as a parameter to be used for
     * the modification of the Minesweeper board. It holds a 2D array that contains mines.
     * @return The integer-based 2D array is returned and will be used in the following method
     * for display purposes.
     **/
    public static void fillGrid(int [][] grid) {
        for(int i = 0; i < grid.length; i++) { //goes through all elements in rows
            for(int j = 0; j < grid[i].length; j++) { //goes through all elements in columns
                if(grid[i][j] == 9) {
                    continue; //skips the loop so nine isn't overwritten
                }
                int count = 0;
                //m and n checks all 8 spaces around middle
                int m = i - 1;
                int n = j - 2;
                while(count < 8) {

                    if(count <= 2) {
                        n += 1;
                    }
                    else if(count <= 4) {
                        m += 1;
                    }
                    else if(count <= 6) {
                        n -= 1;
                    }
                    else {
                        m -= 1;
                    }

                    if((m >= 0 && m < grid.length) && (n >= 0 && n < grid[i].length)) { // checks if spot on grid is valid
                        if(grid[m][n] == 9) {
                            grid[i][j] += 1; //zero increments by one if nine is adjacent
                        }
                    }
                    count += 1;
                }
            }
        }
    }

    /**
     * This method uses the passed variable grid which contains the 2D Array that has mines
     * and numerical hints as to where mines are located.
     * @param grid This is once again the parametric representation for the 2D Array that we
     * pass into the method.
     */
    public static void displayGrid (int [][] grid) {
        for (int i=0; i<grid.length; i++) {       // A nested for loop that processes all elements in the array
            for (int j=0; j<grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println(); // Prints the array
        }
    }
}