/**
 * @author Qingguo Chen
 * Date June 17th 2014
 * Assignment1 for AlgorithmPart1
 * to estimate the value of the percolation threshold via Monte Carlo simulation
 */
public class Percolation {
 private boolean [][] grid;
 private int N;
 private WeightedQuickUnionUF wqu, wqu2;
 public Percolation(int N) {
     if (N <= 0) 
          throw new IllegalArgumentException("size index N out of bounds");
  this.N = N;
  grid = new boolean[N + 1][N + 1];
  for (int i = 0; i < N + 1; i++) {
   for (int j = 0; j < N + 1; j++) {
    grid[i][j] = false;
   }
  }
  wqu = new WeightedQuickUnionUF(N * N + 2);
  wqu2 = new WeightedQuickUnionUF(N * N + 2);
 };
 public void open(int i, int j) {
     if (i <= 0 || i > N) 
         throw new IndexOutOfBoundsException("row index i out of bounds");
     if (j <= 0 || j > N) 
         throw new IndexOutOfBoundsException("column index j out of bounds");
  if (!grid[i][j]) {
   grid[i][j] = true;
   if (i == 1) {
    wqu.union((i - 1) * N + j, 0);
    wqu2.union((i - 1) * N + j, 0);
   } else if (grid[i - 1][j]) {
    wqu.union((i - 1) * N + j, (i - 2) * N + j);
    wqu2.union((i - 1) * N + j, (i - 2) * N + j);
   }
   if (j != 0 && grid[i][j - 1]) {
     if (!wqu.connected((i - 1) * N + j, (i - 1) * N + j - 1)) {
            wqu.union((i - 1) * N + j, (i - 1) * N + j - 1);
            wqu2.union((i - 1) * N + j, (i - 1) * N + j - 1);
    }
   }
   if ((j != N) && (grid[i][j + 1])) {
       if ((!wqu.connected((i - 1) * N + j, (i - 1) * N + j + 1))) {
           wqu.union((i - 1) * N + j, (i - 1) * N + j + 1);
           wqu2.union((i - 1) * N + j, (i - 1) * N + j + 1);
    }
   }
   if (i != N) {
    if (grid[i + 1][j]) {
     if (!wqu.connected((i - 1) * N + j, i * N + j)) {
              wqu.union((i - 1) * N + j, i * N + j);
              wqu2.union((i - 1) * N + j, i * N + j);
             }
          }
   } else {
    wqu2.union((i - 1) * N + j, N * N + 1);
    if (isFull(i, j)) {
            wqu.union((i - 1) * N + j, N * N + 1);
           }
   }
  }
 };
 public boolean isOpen(int i, int j) {
     if (i <= 0 || i > N) 
         throw new IndexOutOfBoundsException("row index i out of bounds");
     if (j <= 0 || j > N) 
         throw new IndexOutOfBoundsException("column index j out of bounds");
     return grid[i][j];
 };
 public boolean isFull(int i, int j) { 
     if (i <= 0 || i > N) 
         throw new IndexOutOfBoundsException("row index i out of bounds");
     if (j <= 0 || j > N) 
         throw new IndexOutOfBoundsException("column index j out of bounds");
     return wqu.connected((i - 1) * N + j, 0);
 };
 public boolean percolates() {  
  return wqu2.connected(0, N * N + 1);
 };
}