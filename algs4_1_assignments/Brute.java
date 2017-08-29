import java.util.Arrays;
public class Brute {

 /**
  * @param args
  */
 public static void main(String[] args) {
   In in = new In(args [0]);
  //In in = new In("/Users/GuoFang/Downloads/collinear/input8.txt");
  int size = in.readInt();
  Point point[] = new Point[size];
          StdDraw.setXscale(0, 40000);
        StdDraw.setYscale(0, 40000);
  for(int i = 0; i < size; i++) {
   point[i] = new Point(in.readInt(), in.readInt());
   point[i].draw();
  }
  Arrays.sort(point);
  double slp2, slp3, slp4;
  for(int p1 = 0; p1 < size-3; p1++){
   for(int p2 = p1 + 1; p2 < size-2; p2++) {
    for(int p3 = p2 + 1; p3 < size-1; p3++) {
     for(int p4 = p3 + 1; p4 < size; p4++) {
      slp2 = point[p1].slopeTo(point[p2]);
      slp3 = point[p1].slopeTo(point[p3]);
      slp4 = point[p1].slopeTo(point[p4]);
      if(slp2 == slp3&&slp2 == slp4){
       Point newP[] = {point[p1],point[p2],point[p3],point[p4]};
        newP[0].drawTo(newP[3]);
       for(int m = 0; m<3; m++){
        StdOut.print(newP[m].toString()+" -> ");
       }
       StdOut.println(newP[3].toString());
      }
     }
    }
   }
  }
  StdDraw.show(0);
 }

}