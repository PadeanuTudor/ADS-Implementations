public class Node {
     private int nr;
     Node left;
     Node right;

    public Node(int i) {
        this.nr = i;
        this.left = null;
        this.right = null;
    }

    public int getNr() {
        return this.nr;
    }

    public void setNr(int i) {
        this.nr = i;
    }

    public Node getLeft() {
        return this.left;
    }

    public Node getRight() {
        return this.right;
    }
}
