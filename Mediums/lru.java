/*TO find the least recently used and to remove we will put all the new entries in a hashmap as well as in a doubly linked
* list and when any new entry is added then it is added to the front of the doubly linked list 
* 
* When we get any element then it will be recently used then we fetch the node then remove it from its current position 
* and then add it to the end of linked list 
* 
* Also when we have to put any new entry then if we find if it already exists if it does then we remove it then we make a new
* node and then add it to the hashmap as well as to the linkedlist
* In case when it exceeds capacity we just remove the node next to the head*/


class Linkedlst{
    int val;
    int key;
    Linkedlst prev;
    Linkedlst next;

    public Linkedlst(int key, int val){
        this.key = key;
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer, Linkedlst> mp;
    Linkedlst head;
    Linkedlst tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        mp = new HashMap<>();
        head = new Linkedlst(-1,-1);
        tail = new Linkedlst(-1,-1);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(!mp.containsKey(key)){
            return -1;
        }
        Linkedlst data = mp.get(key);
        remove(data);
        add(data);
        return data.val;
    }

    public void put(int key, int value) {
        if(mp.containsKey(key)){
            Linkedlst oldnode = mp.get(key);
            remove(oldnode);
        }

        Linkedlst node = new Linkedlst(key, value);
        mp.put(key, node);
        add(node);

        if(mp.size() > capacity){
            Linkedlst nodetoremove = head.next;
            remove(nodetoremove);
            mp.remove(nodetoremove.key);
        }
    }

    public void add(Linkedlst node){
        Linkedlst tailprev = tail.prev;
        tailprev.next = node;
        node.next = tail;
        tail.prev = node;
        node.prev = tailprev;
    }

    public void remove(Linkedlst node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
