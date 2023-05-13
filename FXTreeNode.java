//@author Shreyan Wankavala
//112634232
//Recitation 01

public class FXTreeNode {

    private String text;
    private ComponentType type;
    private FXTreeNode parent;
    private FXTreeNode[] children;
    private int maxChildren = 10;

    /** A constructor for FXTreeNode
     * initializes a new array of children for a node
     */
    public FXTreeNode(){
        children = new FXTreeNode[maxChildren];
    }


    /** A method which returns the next of a node
     *
     * @return the text of a node
     */
    public String getText(){
        return this.text;
    }

    /** A method which returns the component type of a node.
     *
     * @return the component type of a node.
     */
    public ComponentType getType(){
        return this.type;
    }

    /** A method which returns the parent of a given node.
     *
     * @return the parent of a node.
     */
    public FXTreeNode getParent(){
        return this.parent;
    }

    /** A method which returns the array of children given a node.
     *
     * @return the array of children given a node.
     */
    public FXTreeNode[] getChildren(){
        return this.children;
    }

    /** A method which sets the text of a node.
     *
     * @param text = the text that a node has.
     */
    public void setText(String text){
        this.text = text;
    }

    /** A method which allows you to set the type for a node.
     *
     * @param type = the type of a node from the enum.
     */
    public void setType(ComponentType type){
        this.type = type;
    }

    /** A method which allows you to set the parent of a node.
     *
     * @param parent = the parent of the node.
     */
    public void setParent(FXTreeNode parent){
        this.parent = parent;
    }

    /** A method which allows you to set the children of a node.
     *
     * @param children = the array of children in a node. Takes in an array as a parameter.
     */
    public void setChildren(FXTreeNode[] children){
        this.children = children;
    }

    /** A toString method for a node.
     *
     * @return the type and text that the node contains.
     */
    public String toString(){
        if(getText() != null){
            return type + ": " + text;
        }
        else{
            return type + "";
        }
    }

}
