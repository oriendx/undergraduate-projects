public class UnionFind {

    private int[] parent;

    /*
     * Returns the parent of element p.
     * If p is the root of a tree, returns the negative size
     * of the tree for which p is the root.
     */
    public int parent(int p) {
        return parent[p];
    }

    /* Prints the parents of the elements, separated by a space */
    public void printParent() {
        for (int element : parent) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /*
     *****  DO NOT CHANGE ANY INSTANCE VARIABLES ABOVE *****
     *****  DO NOT ADD ANY INSTANCE VARIABLES **************
     *****  DO NOT ADD ANY LIBRARIES ******** **************
     *****  VIOLATION = 0 MARKS IN PART A ******************
     */


    /*
     ***** HELPER METHODS START *****
     */

    private void compression(int p){


        validate(p);
        int root =p;
        int index;
        while (parent[root]>-1){
            root=parent[root];
        }

        while (parent[p]>-1){
            index=parent[p];
            parent[p]=root;
            p=index;
        }


    }

    // get the max union size
    public int maxSize() {
        int max = 0;
        for (int i : parent) {
            if (i < 0 && -i > max) {
                max = -i;
            }
        }
        return max;
    }

    // copy constructor
    public UnionFind(UnionFind uf) {
        parent = new int[uf.parent.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = uf.parent[i];
        }
    }

    /*
     ***** HELPER METHODS END *****
     */


    // COURSEWORK 3 PART A.1 Union Find CONSTRUCTOR

    /**
     * Creates a Union Find data structure with N elements,
     * 0 through N-1.
     * Initially, each element is in its own set.
     * @param N the number of elements
     */
    public UnionFind(int N) {
        parent=new int[N];
        for (int i=0;i<N;i++){
            parent[i]=-1;
        }
    }


    // COURSEWORK 3 PART A.2 Union Find VALIDATE

    /**
     * Validates that p is a valid element/index.
     * @param p is an element
     * @throws IllegalArgumentException if p is not a valid index.
     */
    public void validate(int p) {
        if(  p<0 || p>parent.length){
            throw new IllegalArgumentException();
        }

    }


    // COURSEWORK 3 PART A.3 Union Find SIZE OF

    /**
     * Returns the size of the set element p belongs to.
     * @param p is a valid element
     * @return the size of the set containing p
     */
    public int sizeOf(int p) {

        int rootValue =parent(find(p));
        return rootValue*-1;

    }

