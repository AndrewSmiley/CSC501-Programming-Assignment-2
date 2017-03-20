/**
 * Created by Andrew on 3/15/17.
 */

/**
 * This class represents our Stack ADT, which is an implementation of a standard stack
 * Methods are: push, pop & peek
 * Two members: size and top
 *
 * For details on the members or methods, please see the inline comments
 */
public class CSCStack {

    /**
     * This is the top of our linked node(s). If this is null, then our stack is empty
     */
    private Node top;
    /**
     * The size of our stack, when pusing, increment by one
     */

    private int size;

    /**
     * Default, no argument constructor
     */
    public CSCStack() {
        this.size=0; //just set the size =0
    }


    /**
     * Add a value to the stack by adding a node to the stack
     * @param value The value to add ot the stack
     */
    public void push(int value){
        //create a temp node with the value and the top as the next node
        Node temp = new Node(value, top);
        //change the top node from the current top to our new node (LIFO)
        this.top = temp;
        this.size++;

    }

    /**
     * Get the top item from the stack.
     * @throws Exception this method will throw an exception if you attempt to pop when it is empty
     * @return the value from the top item in the stack
     */
    public Integer pop() throws Exception {
        //check if the stack is empty
        if(!this.isEmpty()){
            //get the value of the top of the node
            Integer val = top.getValue();
            //remove the top node and go to the next
            top = top.node();
            //decrement size
            size--;
            //return the value of the node we just deleted
            return val;
        }
        //if it is, go ahead and throw the exception
        throw new Exception("The stack you are trying to pop() is empty.");
    }

    /**
     * Method to get the value stored at the top of our stack but retain its position, vs pop which will return the value and
     * remove the top item from the stack
     * @throws Exception this method will throw an exception if you attempt to peek when it is empty
     * @return the value of the top item in the stack
     */
    public Integer peek() throws Exception {
        //make sure that our stack is not empty
        if(!this.isEmpty())
            return top.getValue(); //return the top value from our stack but not delete it
        //otherwise throw the exception
        throw new Exception("The stack you are trying to pop() is empty.");
    }
    /**
     * Method to determine if our stack is empty or not
     * @return boolean true if the stack is empty, false if the stack is not empty
     */
    public boolean isEmpty(){
        /**Check both the size and the top node. if the size is 0 and the top node is not null (or visa versa),
        then something is fubar in our stack */
        return (this.size ==0  && this.top == null);
    }

}
