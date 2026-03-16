import java.util.*;

void printOptions()
{
    IO.println(String.format(
            "What do you want to do?\n" +
                    "[1] Add Node\n" +
                    "[2] Remove Node\n" +
                    "[3] Show Median\n" +
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
    if(rbt.getRoot() == rbt.getNully())
    {
        IO.println("The tree is empty");
        return;
    }
    ArrayList<Integer> list = new ArrayList<>();
    rbt.inorder(rbt.getRoot(), list);
    int median;
    if(list.size() % 2 == 0)
        median = list.get((list.size()/2)-1);
    else
        median = list.get((list.size()/2));
    IO.println("The median is: " + median);
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
    IO.println(String.format("Dynamic Median"));
    RBTree rbt  = new RBTree();
    menu(rbt);
}
