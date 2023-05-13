//@author Shreyan Wankavala
//112634232
//Recitation 01

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

public class FXGuiMaker {

    public static void main(String[]args) throws FileNotFoundException {

        System.out.print("\nWelcome to counterfeit SceneBuilder.\n");

        Scanner scan = new Scanner(System.in);
        FXComponentTree tree = new FXComponentTree();


        boolean check = true;

        while(check) {
            System.out.print("\nMenu:\n" +
                    "L- Load from file\n" +
                    "P- Print\n" +
                    "C- Cursor to child (index number)\n" +
                    "A- Add child (index, type, prompt for text)\n" +
                    "U- Cursor up (to parent)\n" +
                    "E- Edit Text\n" +
                    "D- Delete child (index number)\n" +
                    "R- Cursor to root\n" +
                    "S- Save to Text File\n" +
                    "Q - Quit\n");

            System.out.print("\nPlease select an option:");
            String choice = scan.nextLine();

            choice = choice.toUpperCase();

            switch(choice){
                case "L":
                    System.out.print("Please enter filename:");
                    String filename = scan.nextLine();
                    tree = FXComponentTree.readFromFile(filename);

                    // /Users/shreyanwankavala/Desktop/CStext.txt

                    break;
                case "P":
                    System.out.println();
                    tree.traverseTree(tree.getRoot());
                    break;
                case "C":
                    System.out.print("Please enter number of child (starting with 1):");
                    tree.cursorToChild(Integer.parseInt(scan.nextLine()));
                    break;
                case "A":
                    FXTreeNode newNode = new FXTreeNode();
                    System.out.print("Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label):");
                    String type = scan.nextLine();
                    type = type.toUpperCase();
                    switch(type){
                        case "B": newNode.setType(ComponentType.Button);
                            break;
                        case "L":  newNode.setType(ComponentType.Label);
                            break;
                        case "T":  newNode.setType(ComponentType.TextArea);
                            break;
                        case "H":  newNode.setType(ComponentType.HBox);
                            break;
                        case "V":  newNode.setType(ComponentType.VBox);
                            break;
                    }
                    if(newNode.getType().equals(ComponentType.Button) || newNode.getType().equals(ComponentType.Label) || newNode.getType().equals(ComponentType.TextArea)){
                        System.out.print("Please enter text: ");
                        String text = scan.nextLine();
                        newNode.setText(text);
                        System.out.print("Please enter an index: ");
                        int index = scan.nextInt();
                        tree.addChild(index,newNode);
                    }
                    else{
                        System.out.print("Please enter an index: ");
                        int index = scan.nextInt();
                        tree.addChild(index,newNode);
                    }

                    break;
                case "U":
                    if(tree.getCursor().getParent() != null) {
                        tree.cursorToParent();
                        System.out.println("Cursor moved to " + tree.getCursor().getType());
                    }
                    else{
                        System.out.println("\nThe node has no parent!\n");
                    }
                    break;
                case "E":
                    if(tree.getCursor().getType().equals(ComponentType.Button) || tree.getCursor().getType().equals(ComponentType.Label) || tree.getCursor().getType().equals(ComponentType.TextArea)) {
                        System.out.print("Please enter new text: ");
                        tree.setTextAtCursor(scan.nextLine());
                        System.out.print("Text Edited.\n");
                    }else{
                        System.out.println("\n" + tree.getCursor().getType() + " cannot have text!");
                    }
                    break;
                case "D":
                    break;
                case "R":
                    tree.cursorToRoot();
                    System.out.println("\nCursor is at Root. ");
                    break;
                case "S":
                    System.out.print("Please enter a filename: ");
                    String fileName = scan.nextLine();
                    FXComponentTree.writeToFile(tree,fileName);
                    System.out.print("File saved.\n");
                    break;
                case "Q":
                    check = false;
                    break;
                default:
                    System.out.print("\nInvalid input. Try Again.\n");
                    break;
            }
        }

        System.out.print("\nMake like a tree and leave!\n");
    }
}
