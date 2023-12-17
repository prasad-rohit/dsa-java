public class Linkedlist {
    Node next;

    public Linkedlist(int i) {
    }

    static class Node{
        int data;
        Node next;

        Node(int d){
            data = d;
            next = null;
        }
    }
}
