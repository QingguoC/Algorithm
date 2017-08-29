import java.util.*;



public class Solver {

  public static void main(String[] args) {
    In in = new In(args[0]);
       int N = in.readInt();
       int[][] blocks = new int[N][N];
       for (int i = 0; i < N; i++)
           for (int j = 0; j < N; j++)
               blocks[i][j] = in.readInt();
       Board initial = new Board(blocks);
       // solve the puzzle
       Solver solver = new Solver(initial);

       // print solution to standard output
       if (!solver.isSolvable())
           StdOut.println("No solution possible");
       else {
           StdOut.println("Minimum number of moves = " + solver.moves());
          for (Board board : solver.solution())
           StdOut.println(board.toString());
       }
        
   }
   private boolean isSolve;
   private searchNode goal;
   private int moves=0;
   //int tMove=0;
   public Solver (Board initial) {
       int N = initial.dimension();
       int[][] goalBoard = new int[N][N];
       for(int i = 0; i<N; i++){
        for(int j = 0; j<N;j++){
          goalBoard[i][j] = i*N+j+1;
        }
       }
       goalBoard[N-1][N-1] = 0;
          this.goal= new searchNode(new Board(goalBoard),-1,null);
       this.isSolve = false;
   searchNode start = new searchNode(initial,0,null);
   searchNode startTwin = new searchNode(initial.twin(),0,null);
       MinPQ<searchNode> mPQ = new MinPQ<searchNode>();
       MinPQ<searchNode> mPQtwin = new MinPQ<searchNode>();
       mPQ.insert(start);
       mPQtwin.insert(startTwin);
       searchNode del, delTwin;
       while(true){
        del = mPQ.delMin();
        delTwin = mPQtwin.delMin();
        if(del.board.isGoal()){
         isSolve = true;
         goal = del;
         break;
        }
        if(delTwin.board.isGoal()){
         isSolve = false;
         goal.numOfMoves = -1;
         break;
        }
        for(Board nei : del.board.neighbors()){
         if(del.numOfMoves==0){
          mPQ.insert(new searchNode(nei,1,del));
         }
         else if(!nei.equals(del.prev.board)){
        moves= del.numOfMoves+1;  
          mPQ.insert(new searchNode(nei,moves,del));
          }
        }
        for(Board nei1 : delTwin.board.neighbors()){
         if(delTwin.numOfMoves==0){
          mPQtwin.insert(new searchNode(nei1,1,delTwin));
         }
         else if(!nei1.equals(delTwin.prev.board)){
          moves=delTwin.numOfMoves+1;
          mPQtwin.insert(new searchNode(nei1,moves,delTwin));
          }
        }
        
       }
   }
      public boolean isSolvable() {
       return isSolve;
      }
      public int moves() {
       if(!isSolvable()) return -1;
       return goal.numOfMoves;// min number of moves to solve initial board; -1 if no solution
      }
      public Iterable<Board> solution() {
          searchNode temp = goal;
          if(!isSolvable()) return null;
       Stack<Board> stack = new Stack<Board>();
       while(goal!=null){
        stack.push(goal.board);
        goal=goal.prev;
        
       }
       goal = temp;
       return stack;
      }
     private class searchNode implements Comparable<searchNode> {
            private Board board;
            private int numOfMoves;
            private searchNode prev;
            private int manhan;
            public searchNode(Board board, int numOfMoves, searchNode prev) {
             this.board = board;
             this.numOfMoves = numOfMoves;
             this.prev = prev;
             this.manhan= board.manhattan()+numOfMoves;
            }
   public int compareTo(searchNode s) {
    if(this.manhan>s.manhan) return 1;
    else if(this.manhan<s.manhan) return -1;
    else return 0;
   }
      
     }
}
