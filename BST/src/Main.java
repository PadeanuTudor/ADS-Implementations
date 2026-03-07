import java.util.*;

void printOptions()
{
    IO.println(String.format(
            "What do you want to do?\n" +
                    "[1] Add Node\n" +
                    "[2] Remove Node\n" +
                    "[3] Search Number\n" +
                    "[4] Find Min\n" +
                    "[5] Find Max\n" +
                    "[0] Exit"));
}

void add(BST bst)
{
    Scanner input = new Scanner(System.in);
    IO.println("\nPlease enter the node you would like to add: ");
    try{
        int number = input.nextInt();
        bst.insert(number);
    }catch(InputMismatchException ex){
        add(bst);
    }
}

void remove(BST bst)
{
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the node you would like to remove: ");
    try{
        int number = input.nextInt();
        bst.delete(number);
    }catch(InputMismatchException ex){
        remove(bst);
    }
}

void find(BST bst){
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the node you would like to find: ");
    try{
        int number = input.nextInt();
        if(bst.search(number) == true){
            IO.println("The node is inside the tree!\n");
        } else{
            IO.println("The node is not inside the tree!\n");
        }
    }catch(InputMismatchException ex){
        find(bst);
    }
}

void menu(BST bst)
{
    Scanner input = new Scanner(System.in);
    while(true) {
        printOptions();
        try {
            int opt = input.nextInt();
            switch (opt) {
                case 1:
                    add(bst);
                    break;
                case 2:
                    remove(bst);
                    break;
                case 3:
                    find(bst);
                    break;
                case 4:
                    IO.println(String.format("The smallest number in the BST is: " + bst.min() + "\n"));
                    break;
                case 5:
                    IO.println(String.format("The largest number in the BST is: " + bst.max() + "\n"));
                    break;
                case 0:
                    IO.println(String.format("Bye!\n"));
                    System.exit(0);
                default:
                    menu(bst);
            }
        } catch (InputMismatchException ex) {
            IO.println(String.format("Enter 1 digit number please."));
            menu(bst);
        }
    }
}

void main() {
    IO.println(String.format("Welcome to my BST implementation!"));
    BST bst  = new BST();
    menu(bst);
}
