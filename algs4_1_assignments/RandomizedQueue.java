import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author GuoFang
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] rQ;
    private int size;
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  RandomizedQueue<String> rdQ = new RandomizedQueue<String>();
  rdQ.enqueue("a");
  rdQ.dequeue();
  rdQ.enqueue("b");
  rdQ.enqueue("c");
  rdQ.enqueue("d");

  StdOut.println(rdQ.size());
  StdOut.println(rdQ.sample());
  Iterator<String> di = rdQ.iterator();
        while (di.hasNext()) {
         String s = di.next();
         StdOut.print(s);
        }
        StdOut.println();
    Iterator<String> di1 = rdQ.iterator();
        while (di1.hasNext()) {
         String s = di1.next();
         StdOut.print(s);
        }
  
 }
 public RandomizedQueue() {
  rQ = (Item[]) new Object[1];
  size = 0;
 }
 public boolean isEmpty() {
  return size == 0;
 }
 public int size() {
  return size;
 }
 public void enqueue(Item item) {
  if (item == null) throw new NullPointerException("nothing is added!");
  if(size == rQ.length) {
   Item[] temp = (Item[]) new Object[size*2];
   for(int i = 0; i < size; i++){
    temp[i] = rQ[i];
   }
   rQ = (Item[]) new Object[size * 2];
   rQ = temp;
  }
  rQ[size++] = item;
  
 }
 public Item dequeue() {
  if(isEmpty()) throw new NoSuchElementException("not working");
  int forDe = StdRandom.uniform(size);
  Item item = rQ[forDe];
  rQ[forDe] = rQ[--size];
  rQ[size] = null;
  if(size > 0 && size == rQ.length/4) {
   Item[] temp = (Item[]) new Object[rQ.length/2];
   for(int i=0;i<size;i++){
    temp[i] = rQ[i];
   }
   rQ = (Item[]) new Object[rQ.length/2];
   rQ = temp;
  }
  return item;
 }
 public Item sample() {
  if(isEmpty()) throw new NoSuchElementException("empty quene");
  int forSample = StdRandom.uniform(size);
  return rQ[forSample];
 }

 public Iterator<Item> iterator() {
  // TODO Auto-generated method stub
  return new randomIterator();
 }
 private class randomIterator implements Iterator<Item> {
        int iter = size;
        int current;
        Item[] rQ1=(Item[]) new Object[size];
        public randomIterator(){
         for(int i=0;i<size;i++){
          rQ1[i]=rQ[i];
         }
        }
        
  public boolean hasNext() {
   // TODO Auto-generated method stub
   return iter>0;
  }

  public Item next() {
   // TODO Auto-generated method stub
   if(!hasNext()) throw new NoSuchElementException("no any more!");
   current = StdRandom.uniform(iter);
   Item item = rQ1[current];
   rQ1[current] = rQ1[--iter];
   rQ1[iter]=null;
   return item;
  }
  public void remove(){
   throw new UnsupportedOperationException("no this function");
  }
  
 }

}
