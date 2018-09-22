/*
 * Mountain Paths - Greedy Algorithm
 * Mr. Muir
 * 2018.03.26 - v1.0
 */
package edu.hdsb.gwss.yulia.ics4u.u3;

import java.io.File;
import java.io.FileNotFoundException;
import java.awt.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MountainPaths {

    /**
     * Mount Paths
     */
    public static void main(String[] args) throws Exception {

        // ***********************************
        // TASK 1:  read data into a 2D Array
        // 
        System.out.println("TASK 1: READ DATA");
        int[][] data = read("Colorado.844x480.dat");

        // ***********************************
        // Construct DrawingPanel, and get its Graphics context
        //
        DrawingPanel panel = new DrawingPanel(data[0].length, data.length);
        Graphics g = panel.getGraphics();

        // ***********************************
        // TASK 2:  find HIGHEST & LOWEST elevation; for GRAY SCALE
        //
        System.out.println("TASK 2: HIGHEST / LOWEST ELEVATION");
        int min = findMinValue(data);
        System.out.println("\tMin: " + min);

        int max = findMaxValue(data);
        System.out.println("\tMax: " + max);

        // ***********************************
        // TASK 3:  Draw The Map
        //
        System.out.println("TASK 3: DRAW MAP");
        drawMap(g, data);
//
//      // ***********************************
//      // TASK 4A:  implement indexOfMinInCol
        System.out.println("TASK 4A: INDEX OF MIN IN COL 0");
        int minRow = indexOfMinInCol(data, 0);
        System.out.println("\tRow with lowest Col 0 Value: " + minRow);

//        // ***********************************
//        // TASK 4B:  use minRow as starting point to draw path
//        //
        System.out.println("TASK 4B: PATH from LOWEST STARTING ELEVATION");
        g.setColor(Color.RED);
        int totalChange = drawLowestElevPath(g, data, minRow, 0); //
        System.out.println("\tLowest-Elevation-Change Path starting at row " + minRow + " gives total change of: " + totalChange);
//
//        // ***********************************
        //TASK 5:  determine the BEST path
        g.setColor(Color.RED);
        int bestRow = indexOfLowestElevPath(g, data);
//
//        // ***********************************
//        // TASK 6:  draw the best path
//        //
        System.out.println("TASK 6: DRAW BEST PATH");
//        drawMap.drawMap(g); //use this to get rid of all red lines
        g.setColor(Color.GREEN); //set brush to green for drawing best path
        totalChange = drawLowestElevPath(g, data, bestRow, 0);
        System.out.println("\tThe Lowest-Elevation-Change Path starts at row: " + bestRow + " and gives a total change of: " + totalChange);
    }

    /**
     * This method reads a 2D data set from the specified file. The Graphics'
     * industry standard is width by height (width x height), while programmers
     * use rows x cols / (height x width).
     *
     * @param fileName the name of the file
     * @return a 2D array (rows x cols) of the data from the file read
     */
    public static int[][] read(String fileName) throws Exception {
        File map = new File(".\\data\\mountain.paths\\" + fileName);
       // File map = new File(".\\" + fileName);
        Scanner input = new Scanner(map);
        StringTokenizer st = new StringTokenizer(input.nextLine());
        int row = 1;
        //?? here?
        int col = 0;
        int r = 0;
        int c = 0;

        // COUNT ROWS AND COLLUMNS TO INITIALIZE ARRAY SIZE
        col = st.countTokens();

        //System.out.println("columns=" + col);
        while (input.hasNextLine()) {
            input.nextLine();
            row++;
        }
        //System.out.println("rows=" + row);

        int[][] data = new int[row][col];

        //CREATE A NEW SCANNER TO READ FROM THE TOP OF THE FILE
        Scanner inputTwo = new Scanner(map);

        StringTokenizer st2;
        for (r = 0; r < data.length; r++) {
            st2 = new StringTokenizer(inputTwo.nextLine());
            for (c = 0; c < data[r].length; c++) {
                data[r][c] = Integer.parseInt(st2.nextToken());

            }
        }

        return data;
    }

    /**
     * @param grid a 2D array from which you want to find the smallest value
     * @return the smallest value in the given 2D array
     */
    public static int findMinValue(int[][] grid) {

        int min = grid[0][0];
        System.out.println(min);
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (min > grid[r][c]) {
                    min = grid[r][c];
                }
            }
        }
        // TODO
        return min;

    }

    /**
     * @param grid a 2D array from which you want to find the largest value
     * @return the largest value in the given 2D array
     */
    public static int findMaxValue(int[][] grid) {
        int max = grid[0][0];

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (max < grid[r][c]) {
                    max = grid[r][c];
                }
            }
        }
        // TODO
        return max;

    }

    /**
     * Given a 2D array of elevation data create a image of size rows x cols,
     * drawing a 1x1 rectangle for each value in the array whose color is set to
     * a a scaled gray value (0-255). Note: to scale the values in the array to
     * 0-255 you must find the min and max values in the original data first.
     *
     * @param g a Graphics context to use
     * @param grid a 2D array of the data
     */
    public static void drawMap(Graphics g, int[][] data) {
        // TODO

        int min = findMinValue(data);
        int range = findMaxValue(data) - min;
        int oneGray = range / 255;
        int multiplier;
        int colour;

        for (int r = 0; r <= data.length - 1; r++) {
            for (int c = 0; c <= data[0].length - 1; c++) {

                multiplier = (data[r][c] - min) / oneGray;

                //colour = oneGray * multiplier;
                g.setColor(new Color(multiplier, multiplier, multiplier));
                g.fillRect(c, r, 1, 1);

            }
        }

    }

    /**
     * Scan a single column of a 2D array and return the index of the row that
     * contains the smallest value
     *
     * @param grid a 2D array
     * @col the column in the 2D array to process
     * @return the index of smallest value from grid at the given col
     */
    public static int indexOfMinInCol(int[][] grid, int col) {

        // TODO
        int minRow = grid[0][col];
        int rowIndex = 0;

        for (int r = 0; r < grid.length; r++) {

            if (minRow > grid[r][col]) {
                minRow = grid[r][col];
                rowIndex = r;

            }
        }

        return rowIndex;
    }

    /**
     * Find the minimum elevation-change route from West-to-East in the given
     * grid, from the given starting row, and draw it using the given graphics
     * context
     *
     * @param g - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @param row - the starting row for traversing to find the min path
     * @return total elevation of the route
     */
    public static int drawLowestElevPath(Graphics g, int[][] data, int row, int col) {
        //keep from going out of bounds
        //keep track of elevation change

        //how to keep track without setting back to zero
        //int elevationChange;
        // WE ARE HERE!
        g.fillRect(col, row, 1, 1);
        
        // BASE CASE
        if (col == data[0].length - 1) {
            return 0;
        }

        // UP
        int differenceUp = Integer.MAX_VALUE;
        if (row > 0) {
            differenceUp = Math.abs(data[row][col] - data[row - 1][col + 1]);
        }

        // FORWARD
        int differenceForward = Math.abs(data[row][col] - data[row][col + 1]);

        // DOWN
        int differenceDown = Integer.MAX_VALUE;
        if (row < data.length - 1) {
            differenceDown = Math.abs(data[row][col] - data[row + 1][col + 1]);
        }

        // MOVE FORWARD
        if (differenceForward <= differenceDown && differenceForward <= differenceUp) {
            return differenceForward + drawLowestElevPath(g, data, row, col + 1);
        }

        // MOVE UP
        if (differenceUp < differenceDown) {
            return differenceUp + drawLowestElevPath(g, data, row - 1, col + 1);
        }
        
        // MOVE DOWN
        if (differenceDown < differenceUp) {
            return differenceDown + drawLowestElevPath(g, data, row + 1, col + 1);
        }
        
        if ( (int) (Math.random() * 2) == 1) {
            return differenceUp + drawLowestElevPath(g, data, row - 1, col + 1);
        }
        else {
            return differenceDown + drawLowestElevPath(g, data, row + 1, col + 1);
        }
        
    }

    /**
     * Generate all west-to-east paths, find the one with the lowest total
     * elevation change, and return the index of the row that path starts on.
     *
     * @param g - the graphics context to use
     * @param grid - the 2D array of elevation values
     * @return the index of the row where the lowest elevation-change path
     * starts.
     */
    public static int indexOfLowestElevPath(Graphics g, int[][] data) {

        // SORT ALL ELEVATION CHANGES FROM EACH PIXEL INTO 1D ARRAY
        int[] elevationArray = new int[data.length];
        int min;
        int minRowIndex = 0;
        for (int r = 0; r < data.length; r++) {

            elevationArray[r] = drawLowestElevPath(g, data, r, 0);
        }

        // FIND MIN VALUE IN THE ARRAY AND ITS ROW INDEX, COLOUR IT GREEN 
        min = elevationArray[0];
        for (int i = 0; i < data.length; i++) {
            if (min > elevationArray[i]) {
                min = elevationArray[i];
                //THE INDEX OF THE ROW WITH THE OVERALL SMALLEST ELEVATION CHANGE 
                minRowIndex = i;
            }

        }

        return minRowIndex;
    }

}
