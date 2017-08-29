
public class KdTree {
    private int size;
    private Node root;
    private RectHV CONTAINER;
    public KdTree() {
     size = 0;
     root = null;
     CONTAINER = new RectHV(0,0,1,1);// construct an empty set of points
    }
    public boolean isEmpty() {
     return size == 0;
    }
    public int size() {
     return size;// number of points in the set
    }
    public void insert(Point2D p) {
  root = insert(root, p, true);
     // add the point p to the set (if it is not already in the set)
    }
    private Node insert(Node node,Point2D p, boolean isVertical){
   
      if(node == null) {
       size++;
       return new Node(p, null, null, isVertical);
      }
      if(node.p.equals(p)) return node;
      if(isVertical && p.x() < node.p.x() || !isVertical && p.y() < node.p.y()) {
       node.lb = insert(node.lb,p,!isVertical);
      } else node.rt = insert(node.rt,p,!isVertical);

     return node;
    }
    private RectHV lbRect(Node node, RectHV rect) {
     if(node.isVertical) return new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
     else return new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
    }
    private RectHV rtRect(Node node, RectHV rect) {
     if(node.isVertical) return new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
     else return new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
    }
    public boolean contains(Point2D p) {
     return contains(root, p);
    }
    private boolean contains(Node node, Point2D p){
     if(node == null) return false;
     if(node.p.equals(p)) return true;
     if((node.isVertical&&p.x()<node.p.x())||(!node.isVertical&&p.y()<node.p.y())) {
      return contains(node.lb,p);
     } else return contains(node.rt,p);
    }
    public void draw() {
     CONTAINER.draw();
     draw(root,CONTAINER);// draw all of the points to standard draw
    }
    private void draw(Node node, RectHV rect) {
     if(node == null) return;
     StdDraw.setPenColor(StdDraw.BLACK);
     StdDraw.setPenRadius(.01);
     node.p.draw();
     Point2D min,max;
     if(node.isVertical) {
    min = new Point2D(node.p.x(),rect.ymin());
    max = new Point2D(node.p.x(),rect.ymax());
    StdDraw.setPenColor(StdDraw.RED);
   
     } else {
      min = new Point2D(rect.xmin(),node.p.y());
      max = new Point2D(rect.xmax(),node.p.y());
      StdDraw.setPenColor(StdDraw.BLUE);
     }
     StdDraw.setPenRadius();
     min.drawTo(max);
     draw(node.lb,lbRect(node,rect));
     draw(node.rt,rtRect(node,rect));
    }
    private Queue<Point2D> q;
    public Iterable<Point2D> range(RectHV rect) {
      q = new Queue<Point2D>();
      Search(root, CONTAINER, rect);
      return q;// all points in the set that are inside the rectangle
    }
    private void Search(Node node, RectHV rectP, RectHV rect) {
     if(node == null) return;
     if(rect.contains(node.p)) {
      Search(node.lb,lbRect(node, rectP), rect);
     Search(node.rt,rtRect(node, rectP), rect);
      q.enqueue(node.p);;
     }  else{
      if(node.lb!=null&&rect.intersects(lbRect(node, rectP))) Search(node.lb,lbRect(node, rectP),rect);
         if(node.rt!=null&&rect.intersects(rtRect(node, rectP))) Search(node.rt,rtRect(node, rectP),rect);
         }
    }
    
    public Point2D nearest(Point2D p) {
     if(size !=0) {
     return nearest(root,CONTAINER,p,root.p);}
     return null;
    // a nearest neighbor in the set to p; null if set is empty
    }
    private Point2D nearest(Node node,RectHV rect,Point2D p, Point2D nearest) {
     if(node == null) return nearest;
     double tempPP = p.distanceSquaredTo(nearest);
     if(rect.contains(p)) {
     if(node.p.distanceSquaredTo(p)< tempPP) 
      nearest = node.p;
     if(node.isVertical&&p.x()<node.p.x() ||!node.isVertical&& p.y() < node.p.y()) {
      nearest = nearest(node.lb,lbRect(node,rect), p, nearest);
      nearest = nearest(node.rt, rtRect(node,rect), p, nearest);
     } else {
      nearest = nearest(node.rt,rtRect(node,rect), p, nearest);
      nearest = nearest(node.lb, lbRect(node,rect), p, nearest);
     }
     } else if(tempPP > rect.distanceSquaredTo(p)) {
      if(node.p.distanceSquaredTo(p)< tempPP) 
       nearest = node.p;
      if(node.isVertical&&p.x()<node.p.x() ||!node.isVertical&& p.y() < node.p.y()) {
       nearest = nearest(node.lb,lbRect(node,rect), p, nearest);
       nearest = nearest(node.rt, rtRect(node,rect), p, nearest);
      } else {
       nearest = nearest(node.rt,rtRect(node,rect), p, nearest);
       nearest = nearest(node.lb, lbRect(node,rect), p, nearest);
      }
      }
     return nearest;
    }
    private static class Node {
     private final Point2D p;      // the point
                     // the axis-aligned rectangle corresponding to this node
     private Node lb;        // the left/bottom subtree
     private Node rt;
     private final boolean isVertical;
     public Node(Point2D p, Node lb, Node rt, boolean isVertical){
      this.p = p;
      this.lb = lb;
      this.rt = rt;
      this.isVertical = isVertical;
     }
    }
    
}
