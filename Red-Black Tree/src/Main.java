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

void add(RBTree rbt)
{
    Scanner input = new Scanner(System.in);
    IO.println("\nPlease enter the node you would like to add: ");
    try{
        int number = input.nextInt();
        rbt.insert(number);
    }catch(InputMismatchException ex){
        add(rbt);
    }
}

void remove(RBTree rbt)
{
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the node you would like to remove: ");
    try{
        int number = input.nextInt();
        rbt.delete(number);
    }catch(InputMismatchException ex){
        remove(rbt);
    }
}

void find(RBTree rbt){
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the node you would like to find: ");
    try{
        int number = input.nextInt();
        Node node = rbt.searchy(number);
        if(rbt.search(number) == true){
            if(node.color == true)
                IO.println("The node is inside the tree and it's color is Red!\n");
            else
                IO.println("The node is inside the tree and it's color is Black!\n");
        } else{
                IO.println("The node is not inside the tree!\n");
        }
    }catch(InputMismatchException ex){
        find(rbt);
    }
}

void menu(RBTree rbt)
{
    Scanner input = new Scanner(System.in);
    while(true) {
        printOptions();
        try {
            int opt = input.nextInt();
            switch (opt) {
                case 1:
                    add(rbt);
                    break;
                case 2:
                    remove(rbt);
                    break;
                case 3:
                    find(rbt);
                    break;
                case 4:
                    Node min = rbt.searchy(rbt.findMin());
                    if(min.color == true)
                        IO.println(String.format("The smallest number in the Red-Black Tree is: " + rbt.findMin() + " and it's color is Red" + "\n"));
                    else
                        IO.println(String.format("The smallest number in the Red-Black Tree is: " + rbt.findMin() + " and it's color is Black" + "\n"));
                    break;
                case 5:
                    Node max =  rbt.searchy(rbt.findMax());
                    if(max.color == true)
                        IO.println(String.format("The largest number in the Red-Black Tree is: " + rbt.findMax() + " and it's color is Red" + "\n"));
                    else
                        IO.println(String.format("The largest number in the Red-Black Tree is: " + rbt.findMax() + " and it's color is Black" + "\n"));
                    break;
                case 0:
                    IO.println(String.format("Bye!\n"));
                    System.exit(0);
                default:
                    menu(rbt);
            }
        } catch (InputMismatchException ex) {
            IO.println(String.format("Enter 1 digit number please."));
            menu(rbt);
        }
    }
}

void main() {
    IO.println(String.format("Welcome to my Red-Black Tree implementation!"));
    RBTree rbt  = new RBTree();
    menu(rbt);
}
