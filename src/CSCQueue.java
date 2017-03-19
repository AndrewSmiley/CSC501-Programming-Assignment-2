/**
 * Created by Andrew on 3/17/17.
 */

public class CSCQueue {
    /**
     * This is a simple custom exception class, we throw exceptions when we are peeking or popping on an empty
     * stack. This does not NEED to be here, but for shits and giggles let's add it.
     */
    class CSCQueueException extends Exception
    {

        /**
         * Only one constructor for this one, which takes a string argument, which represents the error message to convey
         * when we throw an exception
         * @param message the message to give with our excpetion
         */
        public CSCQueueException(String message)
        {
            //simply call the parent constructor (Exception() class)
            super(message);
        }
    }
    /**
     * This is the front of our linked node(s). If this is null, then our stack is empty, i.e. we dequeue here
     */
    private Node front;

    /**
     * This is the rear of our linked nodes; If this is null then our stack is empty, we enqueue here
     */
    private Node rear;
    //call it the minivan, 2 in the front, 5 in the rear....God i want to make an easter egg of that

    /**
     * Just creating one constructor, basically this allows us to instanciate an object of type CSCQueue
     *
     */
    public CSCQueue() {
    }

    /**
     * Get the value at the front of the queue and remove the node at the front
     * @return the value stored at the front of the queue
     * @throws CSCQueueException throws a CSCQueueException if we attempt to dequeue an empty queue
     */
    public Integer dequeue() throws CSCQueueException {
        //make sure that our queue is empty, if it is, throw an exception, if not, then
        //get the value and dequeue
        if(!this.isEmpty()){
            Integer value = getFront().getValue(); //get the value stored at the front of the queue
            //set current front to the node stored behind it
            this.setFront(this.getFront().getNode());
            //if the front is now null, make sure we set the rear node to null
            if(this.getFront() == null)
                this.setRear(null);
            return value; //return the value stored at what was once our front node

        }
        //throw an exception if the queue is empty
        throw new CSCQueueException("The queue you are trying to dequeue from is empty");
    }

    /**
     * Add an item to our queue
     * @param value the item to add to the queue
     */
    public void enqueue(int value) {
        //check to see if our current queue is empty, if it is, we need to
        if (this.isEmpty()) {
            this.setFront(new Node(value));
            this.setRear(front);
        } else {
            //little confused as to what we're doing here..
            Node newNode = new Node(value);
            this.rear.setNode(newNode);
            this.setRear(newNode);

        }
    }


    /**
     * This method allows us to determine whether the queue is empty or not
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty(){
        return (this.front == null);
    }

    /**
     * This method allows us to clear the queue
     */
    public void clear(){
        //set the front equal to the rear, which is equal to null
        this.front = this.rear = null;

    }

    /**
     * Get the number of items in our queue
     * @return the number of items in the queue
     */
    public int getSize() {
        return size;
    }

    /**
     * Set the size of our queue, this one is private as we only want the internal class to be able to access it
     * @param size the size of our queue to set
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Get the front item in our queue, use during dequeue method
     * @return the front node in our queue
     */
    public Node getFront() {
        return front;
    }

    /**
     * Set the front item in our queue, only use this when enqueuing
     * @param front the item to set as our queue item
     */
    private void setFront(Node front) {
        this.front = front;
    }

    /**
     * Get the rear... No seriously, get the rear item in our queue.
     * @return the rear item in our queue
     */
    public Node getRear() {
        return rear;
    }

    /**
     * Set the rear item in our queue
     * @param rear the rear item to set in our queue
     */
    private void setRear(Node rear) {
        this.rear = rear;
    }

    /**
     * The size of our queue, when enqueuing, increment by one, dequeuing, decrement by one
     */
    private int size;

}
