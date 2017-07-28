/**
 * Created by William Madgwick on 7/28/2017.
 * I am providing my own linked list implementation
 * That will store block objects
 */
public class LinkedList{

    private Node head;
    private Node tail;

    //Will add the block to the tail of the list
    public void appendBlock(Block theBlock){

        Node newNode = new Node(theBlock, head);

        if(this.isEmpty())
            head = newNode;
        else
            tail.next = newNode;

        tail = newNode;
    }

    //Will add the block to the head of the list
    public void prependBlock(Block theBlock){

        Node newNode = new Node(theBlock, head);

        if(this.isEmpty())
            tail = newNode;

        head = newNode;

    }

    //If the head is null, the list must be empty
    public boolean isEmpty(){
        return head == null;
    }

    //Get the block from the head node
    public Block getFirst(){
        return head.getBlock();
    }

    public Block getLast(){
        return tail.getBlock();
    }

    //Simply clears the linked list by removing any references
    public void clear(){
        head = null;
        tail = null;
    }

    //I want only the LinkedList class to access the Node class, hench all private
    private class Node{
        private Block block;
        private Node next;

        private Node(Block block, Node next){
            this.block = block;
            this.next = next;
        }

        private Block getBlock(){
            return block;
        }

        private Node getNext(){
            return next;
        }
    }
}
