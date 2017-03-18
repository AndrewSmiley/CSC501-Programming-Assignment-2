/**
 * Created by Andrew on 3/17/17.
 */
public class CSCStack {
    class CSCStackException extends Exception
    {
        //Parameterless Constructor
        public CSCStackException() {}

        //Constructor that accepts a message
        public CSCStackException(String message)
        {
            super(message);
        }
    }
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
        this.size=0;
    }

    /**
     * Constructor which allows us to pass in a node as a starting object
     * If this is called, we must set the size to 1 because there is one item in our stack
     * @param top
     */
    public CSCStack(Node top) {

        this.top = top;
        this.size=1;
    }

    /**
     * Add a value to the stack by adding a node to the stack
     * @param value The value to add ot the stack
     */
    public void push(int value){
        //create a temp node with the value and the top as the next node
        Node temp = new Node(value, top);
        //change the top node from the current top to our new node (LIFO)
        this.setTop(temp);

    }

    /**
     * Get the top item from the stack.
     * @throws CSCStack.CSCStackException this method will throw an exception if you attempt to pop when it is empty
     * @return the value from the top item in the stack
     */
    public Integer pop() throws CSCStackException {
        if(!this.isEmpty()){
            //get the value of the top of the node
            Integer val = top.getValue();
            //remove the top node and go to the next
            top = top.node();
            //return the value of the node we just deleted
            return val;
        }
        throw new CSCStackException("The stack you are trying to pop() is empty.");
    }

    /**
     * Method to get the value stored at the top of our stack but retain its position, vs pop which will return the value and
     * remove the top item from the stack
     * @return the value of the top item in the stack
     */
    public Integer peek() throws CSCStackException {
        if(!this.isEmpty())
            return top.getValue(); //return the top value from our stack but not delete it
        throw new CSCStackException("The stack you are trying to pop() is empty.");
    }
    /**
     * Method to determine if our stack is empty or not
     * @return boolean true if the stack is empty, false if the stack is not empty
     */
    public boolean isEmpty(){
        /**Check both the size and the top node. if the size is 0 and the top node is not null (or visa versa),
        then something is fubar in our stack */
        return (this.size ==0  && this.getTop() == null);
    }
    /**
     * Get the size of our stack, i.e. the number of items on it
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the bottom node in our stack. This should never be called
     * @return
     */
    public Node getTop() {
        return top;
    }

    /**
     * Set the bottom node of our stack. This should only be called when pushing to an empty stack
     * This method is private so it can only be used internally by our stack class
     * @param top
     */
    private void setTop(Node top) {
        this.top = top;
    }

    /**
     * Simple method to validate our stack
     * @return
     */
    public boolean validate(){
        int _size=0;
        Node temp = top;
        while(temp != null){
            //count2 the node
            _size++;
            //move to the next
            temp = temp.node();
        }
        return (this.getSize() == _size);
    }

}
