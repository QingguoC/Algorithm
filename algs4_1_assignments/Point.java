import java.util.Comparator;

/**
 * @author GuoFang
 *
 */
public class Point implements Comparable<Point>{

 /**
  * @param args
  */
 private final int x, y;
 public static void main(String[] args) {
  
        int a[][]={{2,0},{2,3},{2,4}};
        Point p1 = new Point(2,0);
        Point p2 = new Point(2,3);
        Point p3 = new Point(2,4);
        StdOut.print(p1.toString());
        StdDraw.setXscale(-1, 10);
        StdDraw.setYscale(-1, 10);
        p1.draw();
        p2.draw();
        p1.drawTo(p2);
        StdDraw.show(0);
 }
 public final Comparator<Point> SLOPE_ORDER = new SL();
 private class SL implements Comparator<Point> {
  public int compare(Point o1, Point o2) {
   if(slopeTo(o1) > slopeTo(o2)) return 1;
   if(slopeTo(o1) < slopeTo(o2)) return -1;
   else return 0;
  }
  
 }

 public Point(int x, int y){
  this.x = x;
  this.y = y;
 }

 public void draw() {
  StdDraw.point(x, y);
 }
 public void drawTo(Point that) {
  StdDraw.line(this.x, this.y, that.x, that.y);
 }
 public String toString() {
  return "("+x+", "+y+")";
 }

 public int compareTo(Point that) {
  if(y > that.y) return 1;
  if (y < that.y) return -1;
  if (x > that.x) return 1;
  if (x < that.x) return -1;
  return 0;
 }
 public double slopeTo(Point that) {
      if(compareTo(that) == 0) return Double.NEGATIVE_INFINITY;
  if(y == that.y) return 0d;
  if(x == that.x) return Double.POSITIVE_INFINITY;
 
  else return (that.y-y)*1.0/((that.x-x)*1.0);
 }

}
