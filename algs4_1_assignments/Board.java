
public class Board {
 private int[][] blocks;
   private int zeroI=0,zeroJ=0;
   private int N;
   public Board(int[][] blocks) {
    // construct a board from an N-by-N array of blocks
       N = blocks.length;
    this.blocks = new int[N][N];
    for(int i = 0; i<dimension(); i++){
        for(int j = 0; j<dimension();j++){
          this.blocks[i][j] = blocks[i][j];
          if(this.blocks[i][j] == 0){
          zeroI = i;
          zeroJ = j;
         }
         
        }
       }
   }
      
      public int dimension() {
       return N;
      }
      public int hamming() {
       int wrong = 0;
       for(int i = 0; i<N; i++){
        for(int j = 0; j<N;j++){
         if(blocks[i][j] != (i*N+j+1)&&blocks[i][j]!=0){
          wrong++;
         }
        }
       }
       return wrong;
      }
      public int manhattan() {
       int sum = 0;
       int m,n;
       for(int i = 0; i<N; i++){
        for(int j = 0; j<N;j++){
         if(blocks[i][j]!=0&&blocks[i][j]!=(i*N+j+1)){
          n=(blocks[i][j]-1)%N;
          m=(blocks[i][j]-1)/N;
          sum+=Math.abs(m-i)+Math.abs(n-j); 
            }
        }
       }
       return sum;
      }
      public boolean isGoal() {
       return hamming() == 0;
      }
      public Board twin() {
       int[][] twinBlocks = new int[N][N];
       int temp;
       for(int i = 0; i<N; i++){
        for(int j = 0; j<N;j++){
         twinBlocks[i][j] = blocks[i][j];
        }
       }
       if((zeroI+1)!=N){
        temp = twinBlocks[zeroI+1][0];
        twinBlocks[zeroI+1][0] = twinBlocks[zeroI+1][1];
        twinBlocks[zeroI+1][1] = temp;
       }
       else {
        temp = twinBlocks[zeroI-1][0];
        twinBlocks[zeroI-1][0] = twinBlocks[zeroI-1][1];
        twinBlocks[zeroI-1][1] = temp;
       }
       return new Board(twinBlocks);
      }
      public boolean equals(Object y) {
       if (y == this) return true;
          if (y == null) return false;
          if (y.getClass() != this.getClass()) return false;
          Board that = (Board) y;
          if(that.dimension()!=dimension()) return false;
          for(int i = 0; i<dimension(); i++){
        for(int j = 0; j<dimension();j++){
         if(that.blocks[i][j] != blocks[i][j]) return false;
        }
          }
          return true;
      }
      public Iterable<Board> neighbors() {
       Queue<Board> qNei = new Queue<Board>();
       int[][] neiBlocks1 = new int[dimension()][dimension()];
       int[][] neiBlocks2 = new int[dimension()][dimension()];
       int[][] neiBlocks3 = new int[dimension()][dimension()];
       int[][] neiBlocks4 = new int[dimension()][dimension()];
       for(int i = 0; i<dimension(); i++){
        for(int j = 0; j<dimension();j++){
         neiBlocks1[i][j] = blocks[i][j];
         neiBlocks2[i][j] = blocks[i][j];
         neiBlocks3[i][j] = blocks[i][j];
         neiBlocks4[i][j] = blocks[i][j];
        }
       }
       if(zeroI!=0){
        neiBlocks1[zeroI][zeroJ] = neiBlocks1[zeroI-1][zeroJ];
        neiBlocks1[zeroI-1][zeroJ] = 0;
        qNei.enqueue(new Board(neiBlocks1));
       }
       if(zeroI!=dimension()-1){
        neiBlocks2[zeroI][zeroJ] = neiBlocks2[zeroI+1][zeroJ];
        neiBlocks2[zeroI+1][zeroJ] = 0;
        qNei.enqueue(new Board(neiBlocks2));
       }
       if(zeroJ!=0){
        neiBlocks3[zeroI][zeroJ] = neiBlocks3[zeroI][zeroJ-1];
        neiBlocks3[zeroI][zeroJ-1] = 0;
        qNei.enqueue(new Board(neiBlocks3));
       }
       if(zeroJ!=dimension()-1){
        neiBlocks4[zeroI][zeroJ] = neiBlocks4[zeroI][zeroJ+1];
        neiBlocks4[zeroI][zeroJ+1] = 0;
        qNei.enqueue(new Board(neiBlocks4));
       }
       return qNei;

      }
      public String toString() {
        StringBuilder s = new StringBuilder();
           s.append(dimension() + "\n");
           for (int i = 0; i < dimension(); i++) {
               for (int j = 0; j < dimension(); j++) {
                   s.append(String.format("%2d ", blocks[i][j]));
               }
               s.append("\n");
           }
           return s.toString();
      }
      
}
