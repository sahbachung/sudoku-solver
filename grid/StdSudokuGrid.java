/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import java.io.*;
import java.util.Scanner;


/**
 * Class implementing the grid for standard Sudoku.
 * Extends SudokuGrid (hence implements all abstract methods in that abstract
 * class).
 * You will need to complete the implementation for this for task A and
 * subsequently use it to complete the other classes.
 * See the comments in SudokuGrid to understand what each overriden method is
 * aiming to do (and hence what you should aim for in your implementation).
 */
public class StdSudokuGrid extends SudokuGrid
{
    // TODO: Add your own attributes

    public StdSudokuGrid() {
        super();

        // TODO: any necessary initialisation at the constructor
    } // end of StdSudokuGrid()


    /* ********************************************************* */


    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        if(!input.hasNext()){
            throw new IOException();
        }
        int currLine = 0; // counter for what line of the input file is being read
        while(input.hasNext()){
            int lineNumber = ++currLine;
            String data = input.nextLine();
            if(lineNumber==1) {
                this.size = Integer.parseInt(data);
                if(!this.checkSize(this.size)){
                    throw new IOException();
                }
                this.grid = new int[this.size][this.size];
                for(int row=0; row<this.size; row++){
                    for(int col=0; col<this.size; col++){
                        this.grid[row][col] = 0;
                    }
                }
            }
            else if(lineNumber==2){
                String[] tmp = data.split(" ");
                this.symbols = new int[tmp.length];
                for (int i=0;i<tmp.length;i++){
                    this.symbols[i] = Integer.parseInt(tmp[i]);
                }
            }
            else{
                int[] coords = {
                        Integer.parseInt(data.substring(0,1)),
                        Integer.parseInt(data.substring(2,3))
                };
                int value = Integer.parseInt(data.substring(4));
                this.grid[coords[0]][coords[1]] = value;
            }
        }
    } // end of initBoard()


    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
        File file = new File(filename);
        PrintWriter output = new PrintWriter(file);
        output.write(this.toString());
        output.close();
    } // end of outputBoard()


    @Override
    public String toString() {
        StringBuilder self = new StringBuilder("Current state:"); //Build a representation of self
        for(int[] row: this.grid){
            for(int cell: row){
                self.append(cell);
                self.append(",");
            }
            self.deleteCharAt(self.length() - 1);
            self.append("\n");
        }
        return self.toString();
    } // end of toString()


    @Override
    public boolean validate() {
        // TODO

        // placeholder
        return false;
    } // end of validate()

    @Override
    public boolean setCell(int x, int y, int value) {
        if(this.size-1<x||x<0||this.size-1<y||y<0||!this.checkSymbol(value)){
            return false;
        }
        this.grid[x][y] = value;
        return true;
    }

    @Override
    public int getCell(int x, int y) {
        if(this.size-1<x || x<0 || this.size-1<y || y<0){
            return -1;
        }
        return this.grid[x][y];
    }

    public static void main(String[] args) throws IOException {
        StdSudokuGrid grid = new StdSudokuGrid();
        grid.initGrid("sampleGames/easy-std-99-01.in");
        System.out.println(grid);
    }
} // end of class StdSudokuGrid
