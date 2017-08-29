
public class RectHV {
 private double xmin, ymin, xmax, ymax;
 public RectHV(double xmin, double ymin,         // construct the rectangle [xmin, xmax] x [ymin, ymax]
             double xmax, double ymax){
  if (xmin > xmax||ymin > ymax) 
   throw new java.lang.IllegalArgumentException(); 
  this.xmax = xmax;
  this.xmin = xmin;
  this.ymax = ymax;
  this.ymin = ymin;
  }
    public double xmin() {
     return xmin;// minimum x-coordinate of rectangle
    }
    public double ymin() {
     return ymin;// minimum y-coordinate of rectangle
    }
    public double xmax() {
     return xmax;// maximum x-coordinate of rectangle
    }
    public double ymax() {
     return ymax;// maximum y-coordinate of rectangle
    }
    public boolean contains(Point2D p) {
       if(p.x()<=xmax&&p.x()>=xmin&&p.y()>=ymin&&p.y()<=ymax)
        return true;
       return false;// does this rectangle contain the point p (either inside or on boundary)?
    }
    public boolean intersects(RectHV that) {
     if(that == null) return false;
    if(xmax < that.xmin||xmin> that.xmax) return false;
    if(ymax < that.ymin||ymin> that.ymax) return false;
    return true;// does this rectangle intersect that rectangle (at one or more points)?
    }
    public double distanceTo(Point2D p) {
   double minDX, minDY;
   double  DX1,DX2,DY1,DY2;
   DX1 = p.x()-xmin;
   DX2 = p.x()-xmax;
   DY1 = p.y()-ymin;
   DY2 = p.y()-ymax;
   if(DX2>0&&DY2>0) return Math.sqrt(DX2*DX2+DY2*DY2);
   if(DX2>0&&DY1<0) return Math.sqrt(DX2*DX2+DY1*DY1);
   if(DX1<0&&DY2>0) return Math.sqrt(DX1*DX1+DY2*DY2);
   if(DX1<0&&DY1<0) return Math.sqrt(DX1*DX1+DY1*DY1);
   if(contains(p)){
  if(DX1<=-DX2) minDX = DX1;
  else minDX = -DX2;
  if(DY1<=-DY2) minDY = DY1;
  else minDY = -DY2;
  if(minDX<=minDY) return minDX;
  else return minDY;
   }

   if(DX1<0) return -DX1; 
   if(DX2>0) return DX2;
   if(DY1<0) return -DY1;
      return DY2;
 // Euclidean distance from point p to the closest point in rectangle
   }
    public double distanceSquaredTo(Point2D p) {
     double dis = distanceTo(p);
     return dis*dis;// square of Euclidean distance from point p to closest point in rectangle
    }
    public boolean equals(Object that) {
     if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        RectHV that1 = (RectHV) that;
        return that1.xmax()==xmax&&that1.xmin()==xmin&&that1.ymax() == ymax&&that1.ymin()==ymin;// does this rectangle equal that?
    }
    public void draw() {
     StdDraw.rectangle((xmin+xmax)/2, (ymin+ymax)/2, (xmax-xmin)/2, (ymax-ymin)/2);// draw to standard draw
    }
    public String toString() {
     return "((" + xmin + ", " +xmax+"), ("+ ymin +", "+ymax+ "))";
     // string representation
    }
}
