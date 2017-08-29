public class PointSET {
    private SET<Point2D> set;
    public PointSET() {
     set = new SET<Point2D>();// construct an empty set of points
    }
    public boolean isEmpty() {
     return set.size() == 0;// is the set empty?
    }
    public int size() {
     return set.size();// number of points in the set
    }
    public void insert(Point2D p) {
     if(!set.contains(p)) set.add(p);;// add the point p to the set (if it is not already in the set)
    }
    public boolean contains(Point2D p) {
     return set.contains(p);// does the set contain the point p?
    }
    public void draw() {
     for(Point2D p : set) StdDraw.point(p.x(), p.y());// draw all of the points to standard draw
    }
    public Iterable<Point2D> range(RectHV rect) {
    Queue<Point2D> q = new Queue<Point2D>();
    for(Point2D p:set) if(rect.contains(p)) q.enqueue(p);
    return q;// all points in the set that are inside the rectangle
    }
    public Point2D nearest(Point2D p) {
    Point2D near=set.max();
    for(Point2D p1:set) if(p1.distanceSquaredTo(p)<near.distanceSquaredTo(p)) near = p1;
    return near;// a nearest neighbor in the set to p; null if set is empty
    }
}
