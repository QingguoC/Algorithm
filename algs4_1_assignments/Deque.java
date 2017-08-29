import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author GuoFang
 *
 */
public class Deque<Item> implements Iterable<Item> {

 /**
  * @param args
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
       Deque<String> deq = new Deque<String>();
       deq.addFirst("a");
       deq.addFirst("b");
       deq.addFirst("c");
       deq.removeFirst();
       deq.removeLast();
       StdOut.println(deq.removeLast());
       deq.addLast("d");
       deq.addFirst("e");
       Iterator<String> di = deq.iterator();
       while(di.hasNext()){
        String s = di.next();
        StdOut.print(s);
       }
       
 }
 private class Node {
  Item item;
  Node next;
  Node prev;
 }
 private Node first, last;
 private int size;
 public Deque() {
  size = 0;
 }
 public boolean isEmpty(){
  return size == 0;
 }
 public int size() {
  return size;
 }
 public void addFirst(Item item) {
  if(item == null) throw new NullPointerException ("No content added!");
  Node oldfirst = first;
  first = new Node();
  first.item = item;
  first.next = oldfirst;
  first.prev = null;
  if(isEmpty()) last = first;
  else oldfirst.prev = first;
  size++;
 }
 public void addLast(Item item) {
  if(item == null) throw new NullPointerException ("No content added!");
  Node oldlast = last;
  last = new Node();
  last.item = item;
  last.next = null;
  last.prev = oldlast;
  if(isEmpty()) first = last;
  else oldlast.next = last;
  size++;
 }
 public Item removeFirst() {
  if (size == 0) throw new NoSuchElementException("Empty deque!");
  Item item = first.item;
  size--;
  if (isEmpty()) {
   last = null;
   first = null;
   }
  else {
  first = first.next;
  first.prev = null;}
  return item;
 }
 public Item removeLast() {
  if (size == 0) throw new NoSuchElementException("Empty deque!");
  Item item = last.item;
  if (size == 1) {
   first = null;
   last = null;
  } else {
   last = last.prev;
   last.next = null;
  }
  size--;
  return item;
 }
 public Iterator<Item> iterator() {
  return new ListIterator();
 }
    private class ListIterator implements Iterator<Item> {
     Node Current = first;
     public boolean hasNext() {
      return Current != null;
     }
     public Item next() {
      if(!hasNext()) throw new NoSuchElementException("No any more!");
      Item item = Current.item;
      Current = Current.next;
      return item;
     }
     public void remove(){
      throw new UnsupportedOperationException("remove is not available!");
     }
    }
}
