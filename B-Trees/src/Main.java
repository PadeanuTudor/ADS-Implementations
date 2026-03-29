import java.util.*;

void printOptions()
{
    IO.println(String.format(
            "What do you want to do?\n" +
                    "[1] Add Node\n" +
                    "[2] Remove Node\n" +
                    "[3] Traverse\n" +
                    "[0] Exit"));
}

void add(BTree bt)
{
    Scanner input = new Scanner(System.in);
    IO.println("\nPlease enter the node you would like to add: ");
    try{
        int number = input.nextInt();
        bt.insert(number);
    }catch(InputMismatchException ex){
        add(bt);
    }
}

void remove(BTree bt)
{
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the node you would like to remove: ");
    try{
        int number = input.nextInt();
        bt.remove(number);
    }catch(InputMismatchException ex){
        remove(bt);
    }
}

void traverse(BTree bt)
{
    if(bt.root == null) {
        IO.println("The tree is empty!");
        return;
    }
    IO.println("The traversed tree is:");
    bt.traverse();
}

void menu(BTree bt)
{
    Scanner input = new Scanner(System.in);
    while(true) {
        printOptions();
        try {
            int opt = input.nextInt();
            switch (opt) {
                case 1:
                    add(bt);
                    break;
                case 2:
                    remove(bt);
                    break;
                case 3:
                    traverse(bt);
                    break;
                case 0:
                    IO.println(String.format("Bye!\n"));
                    System.exit(0);
                default:
                    menu(bt);
            }
        } catch (InputMismatchException ex) {
            IO.println(String.format("Enter 1 digit number please."));
            menu(bt);
        }
    }
}

int getDegree()
{
    IO.println("Choose the degree of the BTree:");
    try{
        int deg;
        Scanner input = new Scanner(System.in);
        deg = input.nextInt();
        return deg;
    }
    catch (InputMismatchException e)
    {
        getDegree();
        return -1;
    }
}

void main() {
    IO.println(String.format("B-Tree Implementation\n"));
    int deg = getDegree();
    BTree bt  = new BTree(deg);
    menu(bt);
    return;
}