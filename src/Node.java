/**
 * Created by Andrew on 3/15/17.
 */

/**
 * This is our generic node class.
 * Grabbed this from CSC 501 programming assingment one
 */
public class Node {

    /**
     *This is the value of the node (using object so we can use different types of variables with our linked lists
     */
    private Integer value;



    /**
     *This field refers to the node node in the linked list
     */

    private Node node;

    /**
     * Empty constructor just in case
     */
    public Node() {
    }

    /**
     * Two argument constructor that allows us to pass in the related node as wellas the value
     * @param value the value of the node
     * @param node the node we are giving a relationship to
     */
    public Node(Integer value, Node node) {
        this.value = value;
        this.node = node;
    }

    /**
     * Method to get the node associated with the current node
     * @return the node stored in the current node's node member
     */
    public Node getNode() {
        return node;
    }

    /**
     * One constructor for now, we want to create a node with a predetermined value
     * @param value the value of the node
     */
    public Node(Integer value) {

        this.value = value;
    }

    /**
     * Get the node node
     * @return the node node
     */
    public Node node() {
        return node;
    }

    /**
     * Set the node node
     * @param node the node node in the linked list
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Get the value stored in this node
     * @return the value stored in this node
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Set the value of this node
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Determine if this node has a node or not
     * @return True if there is a related node, false otherwise
     */
    public Boolean hasNode(){
        return this.node != null;
    }


}