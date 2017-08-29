import java.util.Arrays;
/**
 * @author GuoFang
 *
 */
public class Fast {
 public static void main(String[] args) {
  //In in = new In(args [0]);
  In in = new In("/Users/GuoFang/Downloads/collinear/input200.txt");
  int size = in.readInt();
    StdDraw.setXscale(0, 32768);
  StdDraw.setYscale(0, 32768);
  Point point0[] = new Point[size];
  for(int i = 0; i < size; i++) {
   point0[i] = new Point(in.readInt(), in.readInt());
   point0[i].draw();
  }
  Arrays.sort(point0);
 int same;
  for(int j = 0; j<size-3;j++){
   Point point[] = new Point[size];
  for(int i = 0; i < size; i++) {
   point[i] = point0[i];
  }
   same=1;
   Arrays.sort(point, 0, size,point0[j].SLOPE_ORDER);
   for(int m=1;m<size-1;m++){
    if(point0[j].slopeTo(point[m]) == point0[j].slopeTo(point[m+1])){
     same++; //StdOut.print(same+"-"+point[j].slopeTo(point[m])+" m="+m+" ||"); 
     if(m == (size - 2) && same>2){
        Point temp[] = new Point[same+1];
         temp[0] = point0[j];
         for(int i = 1; i<same+1;i++){
             temp[i] = point[size-same+i-1];
         }
         Arrays.sort(temp);
         if(temp[0].compareTo(point0[j])==0){
         temp[0].drawTo(temp[same]);
          StdOut.print(temp[0].toString());
         for(int k=1;k<same+1;k++){
       StdOut.print(" -> "+temp[k].toString());
      }
         StdOut.println();}
     }
    }else {if(same>2){
         Point temp[] = new Point[same+1];
         temp[0] = point0[j];
         for(int i = 1; i<same+1;i++){
             temp[i] = point[m-same+i];
         }
         Arrays.sort(temp);
         if(temp[0].compareTo(point0[j])==0){
         temp[0].drawTo(temp[same]);
         StdOut.print(temp[0].toString());
         for(int k=1;k<same+1;k++){
       StdOut.print(" -> "+temp[k].toString());
      }
         StdOut.println();}
     }
    same = 1;}
    }  
  }
 }
}