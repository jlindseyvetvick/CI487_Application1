import java.util.Random;
import java.util.ArrayList;
public class SinglyLinkedList<E> {

    class ListNode<E>{

        E data;
        ListNode<E> next;

        public ListNode(E data){
            this.data = data;
            this.next = null;
        }

    }

    private ListNode<E> head;
    private int size;

    public SinglyLinkedList(){
        head = null;
        size = 0;
    }

    public ListNode<E> getNodeAtPosition(int pos) throws IndexOutOfBoundsException{

        if(pos > size || pos < 0) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<E> tmp = head;
        for(int i = 0; i < pos; i++){
            tmp = tmp.next;
        }

        return tmp;
    }

    /* Case 1: we have to add the thing to the head */
    public void addToFront(E data){
        ListNode<E> node = new ListNode<E>(data);
        if(head != null){
            node.next = head;
        }
        head = node;
        size++;
    }

    /* Case 2: we have to add the thing to the end */
    public void addToEnd(E data){
        ListNode<E> node = new ListNode<E>(data);
        if(head == null){
            head = node;
        } else{
            ListNode<E> tmp = head;
            while(tmp.next != null) tmp = tmp.next;
            tmp.next = node;
        }
        size++;
    }

    /* Create a function that adds new nodes to the singly linked list */
    public void addNodeAtPos(int pos, E data) throws IndexOutOfBoundsException{

        if(pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("Index is " + pos +
                    " for list of length " + this.size);
        }

        if(pos == 0){
            addToFront(data);
        } else if(pos == size - 1){
            addToEnd(data);
        } else {
            /* Case 3: adding the node somewhere in between */
            ListNode<E> newNode = new ListNode<E>(data);
            ListNode<E> nodeAtPos = getNodeAtPosition(pos - 1);
            newNode.next = nodeAtPos.next;
            nodeAtPos.next = newNode;
            size++;
        }
    }

    /* Case 1: we have to remove the thing to the head */
    public ListNode<E> removeFromFront() throws IndexOutOfBoundsException{
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Unable to remove from front of " +
                    "empty list.");
        } else{
            ListNode<E> tmp = head;
            ListNode<E> removalNode = head;
            head = head.next;
            tmp.next = null;
            size--;
            return removalNode; //use in Shuffle & Game Method
        }
    }

    /* Case 2: we have to add the thing to the end*/
    public ListNode<E> removeFromEnd() throws IndexOutOfBoundsException{
        if(this.isEmpty()){
            throw new IndexOutOfBoundsException("Unable to remove from" +
                    "end of empty list");
        } else{
            ListNode<E> tmp = this.getNodeAtPosition(this.size - 2);
            ListNode<E> removalNode = this.getNodeAtPosition(this.size - 1);
            tmp.next = null;
            size--;
            return removalNode;
        }
    }


    /* Create a function that removes specific nodes from the singly linked list */
    public ListNode<E> removeNodeAtPos(int pos) throws IndexOutOfBoundsException{

        if(pos > size || pos < 0) {
            throw new IndexOutOfBoundsException("Index is " + pos +
                    " for list of length " + this.size);
        }

        if (pos == 0){
            return this.removeFromFront();
        } else if (pos == size - 1){
            return this.removeFromEnd();
        } else {
            ListNode<E> prev = this.getNodeAtPosition(pos - 1);
            ListNode<E> tmp = prev.next;
            prev.next = prev.next.next;
            tmp.next = null;
            size--;
            return tmp;
        }

    }

    /**/
    public boolean isEmpty(){
       return size == 0;
    }

    /**/
    public int size(){
        return size;
    }



    /*
    Creates a function that prints the contents of a linked list
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();

        ListNode<E> t = head;
        while(t != null){
            s.append(t.data.toString() + "-");
            t = t.next;
        }
        s.append("null");

        return s.toString();
    }

}
