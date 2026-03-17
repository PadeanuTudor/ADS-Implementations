public class Node {
    int nr;
    String name;
    Node left;
    Node right;
    Node parent;
    boolean color; /// true = red && false = black

    public Node(int nr) {
        this.nr = nr;
        this.color = true;
    }
    public Node(int nr, String name) {
        this.name = name;
        this.nr = nr;
        this.color = true;
    }

}


