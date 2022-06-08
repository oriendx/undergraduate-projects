import java.util.ArrayList;       // optional

public class ConnectCoins {

    private final UnionFind uf1;
    private final UnionFind uf2;  // optional
    private final boolean[][] ccMatrix;   // optional
    private final int row;        // optional
    private final int column;     // optional


    /*
     *****  DO NOT CHANGE ANY INSTANCE VARIABLES ABOVE *****
     *****  DO NOT ADD ANY INSTANCE VARIABLES **************
     *****  DO NOT ADD ANY LIBRARIES ******** **************
     *****  VIOLATION = 0 MARKS IN PART B ******************
     */

    /*
     ***** HELPER METHODS START *****
     */

    // Add your own helper methods here
    // INCLUDE your helper methods in your submission !



    /*
     ***** HELPER METHODS END *****
     */


    // COURSEWORK 3 PART B.1 Connect Coin CONSTRUCTOR

    /**
     * Initializes the instance variable including a UnionFind data structure.
     * @param ccMatrix is s a 2-D boolean array of true (T) and false (F) values
     *                 to represent the 2-D space where A T in a coordinate indicates that there is a coin
     *                 at that position in the 2-D space, while an F indicates an empty space
     */
    public ConnectCoins(boolean[][] ccMatrix) {
        row = ccMatrix.length;
        column = ccMatrix[0].length;
        uf1 = new UnionFind(row*column);
        uf2 = new UnionFind(row*column);
        this.ccMatrix = new boolean[row][column];

        // construct the union tree
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                this.ccMatrix[i][j] = ccMatrix[i][j];
                if (ccMatrix[i][j] == true) {
                    int p = i*column+j;
                    int q = 0;
                    // do union for connected coins
                    if (i+1 < row && ccMatrix[i+1][j] == true) {
                        q = (i+1)*column + j;
                        uf1.union(p, q);
                    }

                    // do union for connected coins
                    if (j+1 < column && ccMatrix[i][j+1] == true) {
                        q = i*column + j + 1;
                        uf1.union(p, q);
                    }
                }
            }
        }

        //uf1.printParent(); // for out
    }

    // COURSEWORK 3 PART B.2 Connect Coins PLACE MAX CONNECTED COINS

    /**
     * @return a 2-element integer array that represents the coordinate in [row, column],
     * so that a coin that is placed in that coordinate will give the maximum number of newly connected coins.
     * If there are multiple possible such placements, return the left-and-topmost coordinate.
     */

    public int[] placeMaxConnCoins() {

        int max_size = 0;
        int[] ret = new int[2];

        // four direction
        int dir_x[] = {-1, 0, 1, 0};
        int dir_y[] = {0, -1, 0, 1};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                //System.out.println("i=" + i + ",j = " + j);

                if (ccMatrix[i][j] == false) {
                    // find a empty place
                    UnionFind uf2 = new UnionFind(uf1);
                    int p =  i*column + j;

                    for (int k = 0; k < dir_x.length; k++) {
                        int nx = i + dir_x[k];
                        int ny = j + dir_y[k];

                        // do union for connected coins
                        if (nx >= 0 && nx < row 
                                && ny >= 0 && ny < column
                                && ccMatrix[nx][ny] == true) {
                            int q = nx*column + ny;
                            //System.out.println("nx ="+nx+", ny="+ny);
                            uf2.union(p, q);
                        }
                    }
                    int msize = uf2.maxSize();
                    //System.out.println(i + ", " + j + " = " + msize);
                    if (msize > max_size) {
                        // find one solution
                        max_size = msize;
                        ret[0] = i;
                        ret[1] = j;
                    }
                }
            }
        }

        return ret;
    }


    // COURSEWORK 3 PART B.3 Connect Coins MAX CONNECTED COINS

    /**
     * @return the maximum number of newly connected coins after placing a new coin.
     */

    public int maxConnCoins() {
        // get max connected coins place
        int[] place = placeMaxConnCoins();
        UnionFind uf2 = new UnionFind(uf1);

        // change position to one dimension
        int p = place[0]*column + place[1];

        // four direction
        int dir_x[] = {-1, 0, 1, 0};
        int dir_y[] = {0, -1, 0, 1};

        for (int k = 0; k < dir_x.length; k++) {
            int nx = place[0] + dir_x[k];
            int ny = place[1] + dir_y[k];

            // do union for connected coins
            if (nx >= 0 && nx < row && ny >= 0 && ny < column
                    && ccMatrix[nx][ny] == true) {
                int q = nx*column + ny;
                uf2.union(p, q);
            }
        }
        //uf2.printParent();
        //System.out.println(uf2.maxSize());

        return uf2.maxSize();
    }

}
