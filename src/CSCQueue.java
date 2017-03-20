/**
 * Created by Andrew on 3/15/17.
 */

/**
 * This is our Queue class. Implemented with a circular array.
 * Enqueue and dequeue methods will throw an exception if the queue is full or the queue is empty, respectively.
 */
public class CSCQueue {
    /**
     * This is the front of our linked node(s). If this is null, then our queue is empty, i.e. we dequeue here
     */
    private int front;

    /**
     * This is the rear of our linked nodes; If this is null then our queue is empty, we enqueue here
     */
    private int rear;

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
        this.maxSize = maxSize; //set max size
        this.innerArray = new int[maxSize]; //create a new inner array of the specified size
        this.length= 0;  //set the length =0
        this.front = 0;//set the front to the first item in inner array
        this.rear = maxSize - 1; //point the last index in the array maxSize-1

    }

    /**
     * Get the value at the front of the queue and remove the node at the front
     *
     * @return the value stored at the front of the queue
     * @throws Exception throws a Exception if we attempt to dequeue an empty queue
     */
    public Integer dequeue() throws Exception {
        //make sure that our queue is not empty, if it is, throw an exception, if not, then
        //get the value and dequeue
        if (!this.isEmpty()) {
            int value = this.innerArray[front]; //get the value at the front
            this.front = (front + 1) % maxSize;//set the front to the next element in the queue
            this.length--;//decrement size of queue
            return value;// return the value
        }
        //throw an exception if the queue is empty
        throw new Exception("The queue you are trying to dequeue from is empty");
    }

    /**
     * Add an item to our queue
     *
     * @param value the item to add to the queue
     * @throws Exception throw an exception if our queue is full
     */
    public void enqueue(int value) throws Exception {
        if (!isFull()) {
            this.rear = (rear + 1) % this.maxSize; //get the index of the new rear and update our rear index
            this.innerArray[this.rear] = value; //store the value in the rear
            this.length++; //increment the lenth of the quque

        } else {
            //throw exception if we are full
            throw new Exception("The queue you are trying to add to is full");
        }

    }

    /**
     * This method let's us look at the value at the front of the queue without removing it. It will throw an exception if
     * the queue we are handling is empty
     *
     * @return the value at the front of the queue
     * @throws Exception throws a Exception if the queue we are working with is empty
     */
    public Integer peek() throws Exception {
        //make sure this inner array is not empty
        if (!this.isEmpty())
            return this.innerArray[this.front]; //view the value stored at the front of our inner array

        //throw the exception if our queue is empty
        throw new Exception("The queue you are trying to peek is empty");

    }

    /**
     * This method allows us to determine whether the queue is empty or not
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return (this.length == 0);
    }

    /**
     * Determine whether our inner array has reached its capacity
     *
     * @return true if the inner array is full, false otherwise
     */
    public boolean isFull() {
        return (this.length == this.maxSize);
    }



}
