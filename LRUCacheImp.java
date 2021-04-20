import java.util.*;
 
class LRUCacheImp {
 
    Set<Integer> cache;
    int capacity;
 
    public LRUCacheImp(int capacity)
    {
        this.cache = new LinkedHashSet<Integer>(capacity);
        this.capacity = capacity;
    }
    
    public boolean get(int key)
    {
        if (!cache.contains(key))
            return false;
        cache.remove(key);
        cache.add(key);
        return true;
    }
 
    public void addToCache(int key)
    {        
        if (get(key) == false)
           put(key);
    }
 
    public void display()
    {
      LinkedList<Integer> list = new LinkedList<>(cache);
      Iterator<Integer> itr = list.descendingIterator(); 
       
      while (itr.hasNext())
            System.out.print(itr.next() + " ");
    }
     
    public void put(int key)
    {
         
      if (cache.size() == capacity) {
            int firstKey = cache.iterator().next();
            cache.remove(firstKey);
        }
        cache.add(key);
    }
     
    public static void main(String[] args)
    {
        LRUCacheImp ca = new LRUCacheImp(4);
        ca.addToCache(6);
        ca.addToCache(4);
        ca.addToCache(3);
        ca.get(6)
        ca.addToCache(7);
        ca.addToCache(2);
        ca.addToCache(5);
        ca.display();
    }
}
