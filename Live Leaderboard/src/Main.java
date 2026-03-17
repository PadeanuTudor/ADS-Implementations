import java.sql.Array;
import java.util.*;

void printOptions()
{
    IO.println(String.format(
            "What do you want to do?\n" +
                    "[1] Top x players\n" +
                    "[2] Add Player\n" +
                    "[3] Remove Player\n" +
                    "[4] Update Player\n" +
                    "[0] Exit"));
}

void top(RBTree rbt) {
    if (rbt.getRoot() == rbt.getNully())
    {
        IO.println("Tree's empty");
        return;
    }
    Scanner input =  new Scanner(System.in);
    IO.println("Please enter the number of top players you wanna see: ");
    try{
        int nr = input.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<String> listNames = new ArrayList<>();
        rbt.inorder(rbt.getRoot(),list,listNames);
        if(nr > list.size())
            nr = list.size();
        for(int i = list.size()-1;i>list.size()-nr-1;i--) {
            IO.println("Player: " +
                    listNames.get(i) +
                    "\n" +
                    "Score: " +
                    list.get(i));
        }

    }catch(InputMismatchException e){
        return;
    }

}

void add(RBTree rbt)
{
    Scanner input = new Scanner(System.in);
    IO.println("\nPlease enter the player's name: ");
    try{
        String name = input.nextLine();
        if(rbt.check(rbt.getRoot(),name)) {
            IO.println("That player already exists!");
            return;
        }
        IO.println("Please enter their score: ");
        int nr = input.nextInt();
        rbt.insert(nr,name);
    }catch(InputMismatchException ex){
        return;
    }
}

void update(RBTree rbt)
{
    if (rbt.getRoot() == rbt.getNully())
    {
        IO.println("Tree's empty");
        return;
    }
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the player's name that you want to update the score of: ");
    try{
        String name = input.nextLine();
        if(rbt.check(rbt.getRoot(),name) == false) {
            IO.println("That player doesn't exist!");
            return;
        }
        IO.println("Please enter the extra score (negative number if you want to subtract): ");
        int nr = input.nextInt();
        int score = rbt.checky(rbt.getRoot(),name).nr;
        rbt.delete(score);
        rbt.insert(nr+score,name);

    }catch(InputMismatchException ex){
        return;
    }
}

void remove(RBTree rbt)
{
    if (rbt.getRoot() == rbt.getNully())
    {
        IO.println("Tree's empty");
        return;
    }
    Scanner input = new Scanner(System.in);
    IO.println("Please enter the player's name that you want to remove: ");
    try{
        String name =  input.nextLine();
        if(rbt.checky(rbt.getRoot(),name) == null) {
            IO.println("That player does not exist!");
            return;
        }
        rbt.delete(rbt.checky(rbt.getRoot(),name).nr);
    }catch(InputMismatchException ex){
        return;
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
                    top(rbt);
                    break;
                case 2:
                    add(rbt);
                    break;
                case 3:
                    remove(rbt);
                    break;
                case 4:
                    update(rbt);
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
    IO.println(String.format("Leaderboard"));
    RBTree rbt  = new RBTree();
    menu(rbt);
}
