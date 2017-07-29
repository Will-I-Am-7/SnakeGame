/**
 * Created by William Madgwick on 7/28/2017.
 * I am providing my own double linked list implementation
 * That will store block objects
 */
public class LinkedList{

    private Node head;
    private Node tail;
    private int countNodes = 0;

    //Will add the block to the tail of the list
    public void appendBlock(Block theBlock){

        Node newNode = new Node(theBlock, null, tail);

        if(tail != null)
            tail.next = newNode;

        tail = newNode;

        if(head == null)
            head = newNode;

        countNodes++;
    }

    //Will add the block to the head of the list
    public void prependBlock(Block theBlock){

        Node newNode = new Node(theBlock, head, null);

        if(head != null)
            head.previous = newNode;

        head = newNode;

        if(tail == null)
            tail = newNode;

        countNodes++;
    }

    public void removeLast(){

        if(countNodes != 0){
            Node temp = tail;
            tail = tail.previous;
            tail.next = null;
            countNodes--;
        }

    }

    public void removeFirst(){

        if(countNodes != 0){
            Node temp = head;
            head = head.next;
            head.previous = null;
            countNodes--;
        }

    }

    //If the head is null, the list must be empty
    public boolean isEmpty(){
        return countNodes == 0;
    }

    //Get the block from the head node
    public Block getFirst(){
        return head.block;
    }

    public Block getLast(){
        return tail.block;
    }

    //Simply clears the linked list by removing any references
    public void clear(){
        head = null;
        tail = null;
        countNodes = 0;
    }

    public Node getHead(){
        return head;
    }

    public Node getTail(){
        return tail;
    }

    public int getNumNodes(){
        return countNodes;
    }

    //Node inner class
    class Node{

        private Block block;
        private Node next;
        private Node previous;

        Node(Block block, Node next, Node previous){
            this.block = block;
            this.next = next;
            this.previous = previous;
        }

        Block getBlock(){
            return block;
        }

        Node getNext(){
            return next;
        }

        Node getPrevious(){
            return previous;
        }
    }
}
