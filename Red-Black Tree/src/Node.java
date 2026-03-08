public class Node {
    int nr;
    Node left;
    Node right;
    Node parent;
    boolean color; /// true = red && false = black

    public Node(int nr) {
        this.nr = nr;
        this.color = true;
    }

}


