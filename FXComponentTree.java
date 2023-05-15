//@author Shreyan Wankavala

import java.io.File;
import java.util.Scanner;

public class FXComponentTree {

    private FXTreeNode root;
    private FXTreeNode cursor;

    /** A constructor for FXComponentTree. Initializes a new node which serves as the root. Sets the root and cursor to that node. Sets the type to AnchorPane.
     *
     */
    public FXComponentTree(){
        FXTreeNode node = new FXTreeNode();
        node.setType(ComponentType.AnchorPane);
        node.setParent(null);
        node.setText(null);
        cursor = node;
        root = node;
    }

    /** Moves the cursor of the tree to the root which is the AnchorPane.
     *
     */
    public void cursorToRoot(){
        cursor = root;
    }

    /** Deletes the child given an index.
     *
     * @param index = the index of the child.
     */
    public void deleteChild(int index){

    }

    /** a method which allows you to add a child given the index and node that the child has.
     *
     * @param index = the int index of the child.
     * @param node = the node object of the child which contains its type and text.
     */
    public void addChild(int index, FXTreeNode node){
        int count = 0;
        FXTreeNode[] array = node.getParent().getChildren();
        for(int i = 0; i < array.length; i++){
            if(array[i] != null){
                count++;
            }
        }

        if(count < 10){
            int t = count;
            for(int i = count - index  + 1; i > 0; i--){
                node.getParent().getChildren()[t] = node.getParent().getChildren()[t-1];
                t--;
            }
        }
        // 1 2 3 4 5 0 0 0
        else{
            System.out.println("\nThis node has the max number of children!");
        }
        node.getParent().getChildren()[index] = node;
    }

    /** A method which allows you to change the text at the given cursor.
     *
     * @param text = the text you would like to input.
     */
    public void setTextAtCursor(String text){
        cursor.setText(text);
    }

    /** A method to move the cursor to a given child.
     *
     * @param index = the index of the child.
     */
    public void cursorToChild(int index){
        if(cursor.getChildren()[index - 1] != null) {
            cursor = cursor.getChildren()[index - 1];
            System.out.println("Cursor moved to " + cursor.toString());
        }
        else{
            System.out.println("\nThere is no node at this location! Try again.");
        }
    }

    /** A method to move the cursor to its parent.
     *
     */
    public void cursorToParent(){
        cursor = cursor.getParent();
    }

    /** A method to take in the original file that has been formatted for this program.
     *
     * @param filename = the name of the file being read.
     * @return the FXComponentTree from the file.
     */
    public static FXComponentTree readFromFile(String filename){
        FXComponentTree tree = new FXComponentTree();


        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            sc.nextLine();

            while (sc.hasNextLine()) {
                FXTreeNode[] currentArray = tree.root.getChildren();

                FXTreeNode parent = tree.root;

                String[] indices = sc.next().split("-");

                for (int i = 1; i < indices.length; i++) {
                    FXTreeNode currentNode = currentArray[Integer.parseInt(indices[i])];
                    if (currentNode == null){
                        FXTreeNode newNode = new FXTreeNode();
                        newNode.setParent(parent);
                        currentArray[Integer.parseInt(indices[i])] = newNode;
                        currentArray = newNode.getChildren();
                        parent = newNode;
                    }
                    else {
                        currentArray = currentNode.getChildren();
                        parent = currentNode;
                    }
                }

                // at this point, parent will be the leaf node, set type here to "parent"
               String type = sc.next();

                switch(type){
                    case "Button": parent.setType(ComponentType.Button);
                        break;
                    case "Label":  parent.setType(ComponentType.Label);
                        break;
                    case "TextArea":  parent.setType(ComponentType.TextArea);
                        break;
                    case "HBox":  parent.setType(ComponentType.HBox);
                        break;
                    case "VBox":  parent.setType(ComponentType.VBox);
                        break;
                }
                if(parent.getType().equals(ComponentType.Button) || parent.getType().equals(ComponentType.Label) || parent.getType().equals(ComponentType.TextArea)){
                    parent.setText(sc.nextLine());
                }
            }

            /*
            Using next() is the easiest way to get the space separated position in the tree and component type,
            and then nextLine() will extract the text for the component.
             */

            System.out.print(filename + " loaded.\n");
        }
        catch(Exception e){
            System.out.print(filename + " not found.\n");
        }
        return tree;
    }

    /** A Method to write a file and save it to your computer.
     *
     * @param tree = the tree that needs to be converted.
     * @param filename = the new filename of the file inputted by the user.
     */
    public static void writeToFile(FXComponentTree tree, String filename){

    }

    /** Returns the root of the tree.
     *
     * @return the root of the tree.
     */
    public FXTreeNode getRoot(){
        return this.root;
    }

    /** Returns the node at the cursor at any given time.
     *
     * @return the node at the cursor.
     */
    public FXTreeNode getCursor(){
        return this.cursor;
    }

    /** A method to traverse and print out the tree.
     *
     * @param node = the node that is used as the starting position for traversal.
     */
    public void traverseTree(FXTreeNode node){
        FXTreeNode[] array = node.getChildren();

        if(node.getType().equals(ComponentType.AnchorPane)){
            if(cursor.equals(node)){
                System.out.print("==>");
            }
            System.out.println(node.toString());
        }
        for(int i = 0;i < array.length; i++){
            if(array[i] != null){
                if(cursor.equals(array[i])){
                    System.out.print("==>");
                }
                System.out.println(array[i].toString());
                traverseTree(array[i]);
            }else{
                break;
            }
        }
    }
}
