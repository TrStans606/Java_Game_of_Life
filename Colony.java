import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Colony {
   private int horizontal;
   private int verticle;
   private int[][] grid; //{{0,0,0,0,0},{0,0,0,0,0},{0,1,1,1,0},{0,0,0,0,0},{0,0,0,0,0}};

   public Colony(String filename) throws FileNotFoundException {
      Scanner input = new Scanner(new File(filename));

      verticle = input.nextInt();
      horizontal = input.nextInt();
      grid = new int[verticle][horizontal];

      for (int i = 0; i < grid[i].length; i++) {
         String line = input.next();
         for (int j = 0; j < grid[i].length; j++) {
            if (line.charAt(j) == 'L') {
               grid[i][j] = 1;
            } else {
               grid[i][j] = 0;
            }
         }
      }
   }

   public Colony() {
      Scanner stdin = new Scanner(System.in);
      verticle = stdin.nextInt();
      horizontal = stdin.nextInt();
      grid = new int[verticle][horizontal];
      for (int i = 0; i < grid.length; i++) {
         String line = stdin.next();
         for (int j = 0; j < grid[i].length; j++) {
            if (line.charAt(j) == 'L') {
               grid[i][j] = 1;
            } else {
               grid[i][j] = 0;
            }
         }
      }
   }

   public void next() {
      //temp holder for the original grid
      int[][] temp = new int[grid.length][grid[0].length];

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            temp[i][j] = grid[i][j];
         }
      }

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            int numOfLiving = 0;
            //calculates space to the left and right
            if (j == 0) {
               numOfLiving += grid[i][grid[i].length - 1];
               numOfLiving += grid[i][j + 1];
            } else if (j == grid[i].length - 1) {
               numOfLiving += grid[i][0];
               numOfLiving += grid[i][j - 1];
            } else {
               numOfLiving += grid[i][j + 1];
               numOfLiving += grid[i][j - 1];
            }
            //calculates space above and below
            if (i == 0) {
               numOfLiving += grid[grid.length - 1][j];
               numOfLiving += grid[i + 1][j];
            } else if (i == grid.length - 1) {
               numOfLiving += grid[0][j];
               numOfLiving += grid[i - 1][j];
            } else {
               numOfLiving += grid[i + 1][j];
               numOfLiving += grid[i - 1][j];
            }
            //calculates the diagonal values
            if (j == 0 && i != 0 && i != grid.length - 1) {
               numOfLiving += grid[i + 1][grid[i].length - 1];
               numOfLiving += grid[i - 1][grid[i].length - 1];
               numOfLiving += grid[i + 1][j + 1];
               numOfLiving += grid[i - 1][j + 1];
            } else if (j == grid[i].length - 1 && i != 0 && i != grid.length - 1) {
               numOfLiving += grid[i + 1][0];
               numOfLiving += grid[i - 1][0];
               numOfLiving += grid[i + 1][j - 1];
               numOfLiving += grid[i - 1][j - 1];
            } else if (i == 0 && j != 0 && j != grid[i].length - 1) {
               numOfLiving += grid[grid.length - 1][j + 1];
               numOfLiving += grid[grid.length - 1][j - 1];
               numOfLiving += grid[i + 1][j + 1];
               numOfLiving += grid[i + 1][j - 1];
            } else if (i == grid.length - 1 && j != 0 && j != grid[i].length - 1) {
               numOfLiving += grid[0][j + 1];
               numOfLiving += grid[0][j - 1];
               numOfLiving += grid[i - 1][j + 1];
               numOfLiving += grid[i - 1][j - 1];
            } else if (i == 0 && j == 0) {
               numOfLiving += grid[i + 1][j + 1];
               numOfLiving += grid[grid.length - 1][j + 1];
               numOfLiving += grid[grid.length - 1][grid[i].length - 1];
               numOfLiving += grid[i + 1][grid[i].length - 1];
            } else if (i == grid.length - 1 && j == grid[i].length - 1) {
               numOfLiving += grid[i - 1][j - 1];
               numOfLiving += grid[i - 1][0];
               numOfLiving += grid[0][0];
               numOfLiving += grid[0][j - 1];
            } else if (i == 0 && j == grid[i].length - 1) {
               numOfLiving += grid[i + 1][j - 1];
               numOfLiving += grid[i + 1][0];
               numOfLiving += grid[grid.length - 1][j - 1];
               numOfLiving += grid[grid.length - 1][0];
            } else if (i == grid.length - 1 && j == 0) {
               numOfLiving += grid[i - 1][j + 1];
               numOfLiving += grid[i - 1][grid[i].length - 1];
               numOfLiving += grid[0][j + 1];
               numOfLiving += grid[0][grid[i].length - 1];
            } else {
               numOfLiving += grid[i + 1][j + 1];
               numOfLiving += grid[i + 1][j - 1];
               numOfLiving += grid[i - 1][j + 1];
               numOfLiving += grid[i - 1][j - 1];
            }
            //makes dead cell live if it meets right conditions
            if (grid[i][j] == 0 && numOfLiving == 3) {
               temp[i][j] = 1;
            }
            //makes live cells live or die
            if (numOfLiving == 2 || numOfLiving == 3) {
               temp[i][j] = grid[i][j];
            } else {
               temp[i][j] = 0;
            } 
         }
      }
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = temp[i][j];
         }
      }
   }

   public void next(int n) {

      for (int a = 0; a < n; a++) {
         //temp holder for the original grid
         int[][] temp = new int[grid.length][grid[0].length];

         for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
               temp[i][j] = grid[i][j];
            }
         }
         for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
               int numOfLiving = 0;
               //calculates space to the left and right
               if (j == 0) {
                  numOfLiving += grid[i][grid[i].length - 1];
                  numOfLiving += grid[i][j + 1];
               } else if (j == grid[i].length - 1) {
                  numOfLiving += grid[i][0];
                  numOfLiving += grid[i][j - 1];
               } else {
                  numOfLiving += grid[i][j + 1];
                  numOfLiving += grid[i][j - 1];
               }
               //calculates space above and below
               if (i == 0) {
                  numOfLiving += grid[grid.length - 1][j];
                  numOfLiving += grid[i + 1][j];
               } else if (i == grid.length - 1) {
                  numOfLiving += grid[0][j];
                  numOfLiving += grid[i - 1][j];
               } else {
                  numOfLiving += grid[i + 1][j];
                  numOfLiving += grid[i - 1][j];
               }
               //calculates the diagonal values
               if (j == 0 && i != 0 && i != grid.length - 1) {
                  numOfLiving += grid[i + 1][grid[i].length - 1];
                  numOfLiving += grid[i - 1][grid[i].length - 1];
                  numOfLiving += grid[i + 1][j + 1];
                  numOfLiving += grid[i - 1][j + 1];
               } else if (j == grid[i].length - 1 && i != 0 && i != grid.length - 1) {
                  numOfLiving += grid[i + 1][0];
                  numOfLiving += grid[i - 1][0];
                  numOfLiving += grid[i + 1][j - 1];
                  numOfLiving += grid[i - 1][j - 1];
               } else if (i == 0 && j != 0 && j != grid[i].length - 1) {
                  numOfLiving += grid[grid.length - 1][j + 1];
                  numOfLiving += grid[grid.length - 1][j - 1];
                  numOfLiving += grid[i + 1][j + 1];
                  numOfLiving += grid[i + 1][j - 1];
               } else if (i == grid.length - 1 && j != 0 && j != grid[i].length - 1) {
                  numOfLiving += grid[0][j + 1];
                  numOfLiving += grid[0][j - 1];
                  numOfLiving += grid[i - 1][j + 1];
                  numOfLiving += grid[i - 1][j - 1];
               } else if (i == 0 && j == 0) {
                  numOfLiving += grid[i + 1][j + 1];
                  numOfLiving += grid[grid.length - 1][j + 1];
                  numOfLiving += grid[grid.length - 1][grid[i].length - 1];
                  numOfLiving += grid[i + 1][grid[i].length - 1];
               } else if (i == grid.length - 1 && j == grid[i].length - 1) {
                  numOfLiving += grid[i - 1][j - 1];
                  numOfLiving += grid[i - 1][0];
                  numOfLiving += grid[0][0];
                  numOfLiving += grid[0][j - 1];
               } else if (i == 0 && j == grid[i].length - 1) {
                  numOfLiving += grid[i + 1][j - 1];
                  numOfLiving += grid[i + 1][0];
                  numOfLiving += grid[grid.length - 1][j - 1];
                  numOfLiving += grid[grid.length - 1][0];
               } else if (i == grid.length - 1 && j == 0) {
                  numOfLiving += grid[i - 1][j + 1];
                  numOfLiving += grid[i - 1][grid[i].length - 1];
                  numOfLiving += grid[0][j + 1];
                  numOfLiving += grid[0][grid[i].length - 1];
               } else {
                  numOfLiving += grid[i + 1][j + 1];
                  numOfLiving += grid[i + 1][j - 1];
                  numOfLiving += grid[i - 1][j + 1];
                  numOfLiving += grid[i - 1][j - 1];
               }
               //makes dead cell live if it meets right conditions
               if (grid[i][j] == 0 && numOfLiving == 3) {
                  temp[i][j] = 1;
               }
               //makes live cells live or die
               if (numOfLiving == 2 || numOfLiving == 3) {
                  temp[i][j] = grid[i][j];
               } else {
                  temp[i][j] = 0;
               }
            }
         }

         for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
               grid[i][j] = temp[i][j];
            }
         }
      }
   }

   public void display() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == 1) {
               System.out.print("L");
            } else {
               System.out.print("D");
            }
         }
         System.out.println("");
      }
   }

   public int numOfLiving() {
      int numOfLiving = 0;

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            numOfLiving += grid[i][j];
         }
      }
      return numOfLiving;
   }

   public int getHorizontal() {
      return horizontal;
   }

   public int getVerticle() {
      return verticle;
   }

}