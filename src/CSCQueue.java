/**
 * Created by Andrew on 3/15/17.
 */

/**
 * This is our Queue class. Implemented with a circular array.
 * Enqueue and dequeue methods will throw an exception if the queue is full or the queue is empty, respectively.
 */
public class CSCQueue {
    /**
     * This is a simple custom exception class, we throw exceptions when we are peeking or popping on an empty
     * stack. This does not NEED to be here, but for shits and giggles let's add it.
     */
    class CSCQueueException extends Exception {

        /**
         * Only one constructor for this one, which takes a string argument, which represents the error message to convey
         * when we throw an exception
         *
         * @param message the message to give with our excpetion
         */
        public CSCQueueException(String message) {
            //simply call the parent constructor (Exception() class)
            super(message);
        }
    }

    /**
     * This is the front of our linked node(s). If this is null, then our queue is empty, i.e. we dequeue here
     */
    private int front;

    /**
     * This is the rear of our linked nodes; If this is null then our queue is empty, we enqueue here
     */
    private int rear;
    //call it the minivan, 2 in the front, 5 in the rear....God i want to make an easter egg of that

    /**
     * This is our inner array containing the values in our queue
     */
    private int[] innerArray;

    /**
     * The size of our queue, when enqueuing, increment by one, dequeuing, decrement by one
     */
    private int length;

    /**
     * The allocation size of the inner array we are creating
     */
    private int maxSize;

    /**
     * Just creating one constructor, basically this allows us to instanciate an object of type CSCQueue with a default max
     * size of 1000 elemets
     */
    public CSCQueue() {
        this(1000);
    }

    /**
     * This constructor allows us to allocate our inner array to a specified size. i.e. i need to hold 1000 items maximum
     * in my queue
     *
     * @param maxSize the maximum number of elements in our inner array
     */
    public CSCQueue(int maxSize) {
        this.setMaxSize(maxSize); //set max size
        this.setInnerArray(new int[this.getMaxSize()]); //create a new inner array of the specified size
        this.setLength(0);  //set the length =0
        this.setFront(0);//set the front to the first item in inner array
        this.setRear(maxSize - 1); //point the last index in the array maxSize-1

    }

    /**
     * Get the value at the front of the queue and remove the node at the front
     *
     * @return the value stored at the front of the queue
     * @throws CSCQueueException throws a CSCQueueException if we attempt to dequeue an empty queue
     */
    public Integer dequeue() throws CSCQueueException {
        //make sure that our queue is not empty, if it is, throw an exception, if not, then
        //get the value and dequeue
        if (!this.isEmpty()) {
            int value = this.getInnerArray()[front]; //get the value at the front
            this.setFront((front + 1) % maxSize);//set the front to the next element in the queue
            this.length--;//decrement size of queue
            return value;// return the value
        }
        //throw an exception if the queue is empty
        throw new CSCQueueException("The queue you are trying to dequeue from is empty");
    }

    /**
     * Add an item to our queue
     *
     * @param value the item to add to the queue
     * @throws CSCQueueException throw an exception if our queue is full
     */
    public void enqueue(int value) throws CSCQueueException {
        if (!isFull()) {
            this.setRear((rear + 1) % maxSize); //get the index of the new rear and update our rear index
            innerArray[rear] = value; //store the value in the rear
            this.length++; //increment the lenth of the quque

        } else {
            //throw exception if we are full
            throw new CSCQueueException("The queue you are trying to add to is full");
        }

    }

    /**
     * This method let's us look at the value at the front of the queue without removing it. It will throw an exception if
     * the queue we are handling is empty
     *
     * @return the value at the front of the queue
     * @throws CSCQueueException throws a CSCQueueException if the queue we are working with is empty
     */
    public Integer peek() throws CSCQueueException {
        //make sure this inner array is not empty
        if (!this.isEmpty())
            return this.getInnerArray()[this.getFront()]; //view the value stored at the front of our inner array

        //throw the excpetion if our queue is empty
        throw new CSCQueueException("The queue you are trying to peek is empty");

    }

    /**
     * This method allows us to determine whether the queue is empty or not
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.getLength() == 0);
    }

    /**
     * Determine whether our inner array has reached its capacity
     *
     * @return true if the inner array is full, false otherwise
     */
    public boolean isFull() {
        return (this.getLength() == this.getMaxSize());
    }

    /**
     * Get the front index of our inner array
     *
     * @return the index of our inner array
     */
    public int getFront() {
        return front;
    }

    /**
     * Set the front index of our inner array
     *
     * @param front the index of our inner array
     */
    public void setFront(int front) {
        this.front = front;
    }

    /**
     * Get the rear index of our inner array
     *
     * @return the index of our inner array
     */
    public int getRear() {
        return rear;
    }

    /**
     * Set the rear index of our inner array
     *
     * @param rear
     */
    public void setRear(int rear) {
        this.rear = rear;
    }


    /**
     * Get our inner array
     *
     * @return our inner array
     */
    public int[] getInnerArray() {
        return innerArray;
    }

    /**
     * Set our inner array
     *
     * @param innerArray the inner array to set
     */
    public void setInnerArray(int[] innerArray) {
        this.innerArray = innerArray;
    }

    /**
     * Get the length (number of items) in our inner array
     *
     * @return the number of items in our inner array
     */
    public int getLength() {
        return length;
    }

    /**
     * Set the lenght of our inner array
     *
     * @param length the new lenght of our inner array
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Get the size of the inner array we have allocated for
     *
     * @return
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Set the maximum size of the array we
     *
     * @param maxSize
     */
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

}
