/**
 * @author Qingguo Chen
 * Date June 17th 2014
 * Assignment1 for AlgorithmPart1
 * to estimate the value of the percolation threshold via Monte Carlo simulation
 */
public class PercolationStats {
  private int T;
  private double percolateNum[];
  public PercolationStats(int N, int T) {
      if (N <= 0) 
          throw new IllegalArgumentException("size index N out of bounds");
      if (T <= 0) 
          throw new IllegalArgumentException("times index T out of bounds");
      this.T = T;
      int total = N * N;
      percolateNum = new double[T];
      int i = 1;
      int j = 1;
      int count = 0;
      for (int k = 0; k < T; k++) {
          count = 0;
          Percolation pr = new Percolation(N);
          while (!pr.percolates()) {
            i = StdRandom.uniform(N) + 1;
            j = StdRandom.uniform(N) + 1;
            if (!pr.isOpen(i, j)) {
               pr.open(i, j);
               count++;
            }
          }
       percolateNum[k] = (1.0 * count / total);
      }
  }
    public double mean() {
     return StdStats.mean(percolateNum);
    }
    public double stddev() {
     return StdStats.stddev(percolateNum);
    }
    public double confidenceLo() {
     return (mean() - 1.96 * stddev() / Math.sqrt(T));
    }
    public double confidenceHi() {
     return (mean() + 1.96 * stddev() / Math.sqrt(T));
    }
 
 public static void main(String[] args) {
        Stopwatch sw = new Stopwatch();
        PercolationStats  ps = new PercolationStats(1000, 10);
        StdOut.println("mean:" + ps.mean());
        StdOut.println("stddev:" + ps.stddev());
        StdOut.println("95% confidence:" + ps.confidenceLo() + ", " + ps.confidenceHi());
        StdOut.println("elapsedTime:" + sw.elapsedTime() + "s");
 }
}
