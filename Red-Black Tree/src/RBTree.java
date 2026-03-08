public class RBTree {
    private Node root;
    private final Node nully; ///sentinel node

    public RBTree() {
        nully = new Node(0);
        nully.color = false;
        root = nully;
    }

    private void leftRotate(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        if(temp.left != nully)
            temp.left.parent = node;
        temp.parent = node.parent;
        if(node.parent == null)
            root = node;
        else if(node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        temp.left = node;
        node.parent = temp;
    }

    private void rightRotate(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        if(temp.right != nully)
            temp.right.parent = node;
        temp.parent = node.parent;
        if(node.parent == null)
            root = temp;
        else if(node == node.parent.left)
            node.parent.left = temp;
        else
            node.parent.right = temp;
        temp.right = node;
        node.parent = temp;
    }

    public void insert(int nr)
    {
        Node node = new Node(nr);
        node.left = nully;
        node.right = nully;

        Node y = null; ///parent
        Node x = root; ///guide

        while(x != nully)
        {
            y = x;
            if(node.nr < x.nr)
                x = x.left;
            else
                x = x.right;
        }
        node.parent = y;
        if(y == null)
            root = node;
        else if(node.nr < y.nr)
            y.left = node;
        else
            y.right = node;

        node.color = true;
        fixInsert(node);
    }

    private void fixInsert(Node node)
    {
        while(node.parent != null && node.parent.color == true)
        {
            if(node.parent == node.parent.parent.left)
            {
                Node uncle =  node.parent.parent.right;
                if(uncle.color == true) /// Red Uncle
                {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                }
                else
                {
                    if(node == node.parent.right)/// triangle
                    {
                        node = node.parent;
                        leftRotate(node);
                    }
                    /// now it's a line
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rightRotate(node.parent.parent);
                }
            }
            else /// mirror case
            {
                Node uncle = node.parent.parent.right;
                if(uncle.color == true)
                {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                }
                else
                {
                    if(node == node.parent.left)
                    {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = false;
    }

    public boolean search(int nr)
    {
        Node current  = root;
        while(current != nully)
        {
            if(nr == current.nr)
                return true;
            if(nr < current.nr)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }
    public Node searchy(int nr) /// search but gives me the node itself
    {
        Node current  = root;
        while(current != nully)
        {
            if(nr == current.nr)
                return current;
            if(nr < current.nr)
                current = current.left;
            else
                current = current.right;
        }
        return nully;
    }

    public int findMin()
    {
        if(root == nully)
            throw new IllegalArgumentException("Tree's empty");
        return min(root).nr;
    }

    private Node min(Node node)
    {
        while(node.left != nully)
            node = node.left;
        return node;
    }

    public int findMax()
    {
        if (root == nully)
            throw new IllegalArgumentException("Tree's empty");
        Node current = root;
        while (current.right != nully)
            current = current.right;
        return current.nr;
    }

    public void delete(int nr) {
        Node z = nully;
        Node x;
        Node y;
        Node current = root;

        while (current != nully)
        {
            if (current.nr == nr)
            {
                z = current;
                break;
            }
            if(nr < current.nr)
                current = current.left;
            else
                current = current.right;
        }

        if (z == nully)
            return;

        y = z;
        boolean yOriginalColor = y.color;
        if (z.left == nully)
        {
            x = z.right;
            replace(z, z.right);
        }
        else if (z.right == nully)
        {
            x = z.left;
            replace(z, z.left);
        }
        else
        {
            y = min(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z)
            {
                x.parent = y;
            }
            else
            {
                replace(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            replace(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (yOriginalColor == false)
        {
            fixDelete(x);
        }
    }

    private void replace(Node x, Node y)
    {
        if(x.parent == null)
            root = y;
        else if(x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.parent = x.parent;
    }

    private void fixDelete(Node x)
    {
        while(x != root && x.color == false)
        {
            if(x == x.parent.left)
            {
                Node sibling = x.parent.right;
                if(sibling.color == true)
                {
                    sibling.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    sibling = x.parent.right;
                }
                if(sibling.left.color == false && sibling.right.color == false)
                {
                    sibling.color = true;
                    x = x.parent;
                }
                else
                {
                    if(sibling.right.color == false)
                    {
                        sibling.left.color = false;
                        sibling.color = true;
                        rightRotate(sibling);
                        sibling = x.parent.right;
                    }
                    sibling.color = x.parent.color;
                    x.parent.color = false;
                    sibling.right.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            }
            else /// mirror case
            {
                Node sibling = x.parent.left;
                if(sibling.color == true)
                {
                    sibling.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    sibling = x.parent.left;
                }
                if(sibling.left.color == false && sibling.right.color == false)
                {
                    sibling.color = true;
                    x = x.parent;
                }
                else
                {
                    if(sibling.left.color == false)
                    {
                        sibling.right.color = false;
                        sibling.color = true;
                        rightRotate(sibling);
                        sibling = x.parent.left;
                    }
                    sibling.color = x.parent.color;
                    x.parent.color = false;
                    sibling.left.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }
}
