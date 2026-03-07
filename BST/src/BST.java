public class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(int nr)
    {
        root = inserter(root,nr);

    }
    private Node inserter(Node root,int nr)
    {
        if(root == null)
        {
            return new Node(nr);
        }

        if(nr < root.getNr())
        {
            root.left = inserter(root.getLeft(),nr);
        }
        else if (nr > root.getNr())
        {
            root.right = inserter(root.getRight(), nr);
        }

        return root;
    }

    public boolean search(int nr)
    {
        return searcher(root,nr);
    }

    private boolean searcher(Node root,int nr)
    {
        if (root == null)
        {
            return false;
        }
        else if (nr == root.getNr())
        {
            return true;
        }
        else if(nr < root.getNr())
        {
            return searcher(root.getLeft(),nr);
        }
        else
        {
            return searcher(root.getRight(),nr);
        }
    }

    public int min()
    {
        if(root  == null)
        {
            throw new IllegalStateException("Tree's empty");
        }
        int min = root.getNr();
        Node aux = root;
        while(aux.getLeft() != null)
        {
            min = aux.getLeft().getNr();
            aux = aux.getLeft();
        }
        return min;
    }

    public int max()
    {
        if(root  == null)
        {
            throw new IllegalStateException("Tree's empty");
        }
        int max = root.getNr();
        Node aux = root;
        while(aux.getRight() != null)
        {
            max = aux.getRight().getNr();
            aux = aux.getRight();
        }
        return max;
    }

    public void delete(int nr)
    {
       root = deleter(root,nr);
    }

    private Node deleter(Node root,int nr)
    {
        if(root == null)
        {
            return null;
        }

        if(nr < root.getNr())
        {
            root.left = deleter(root.getLeft(),nr);
        }
        else if(nr > root.getNr())
        {
            root.right = deleter(root.getRight(),nr);
        }
        else {
            /// For no children or 1 child
            if(root.getLeft() == null)
            {
                return root.getRight();
            }
            else if(root.getRight() == null)
            {
                return root.getLeft();
            }
            /// For 2 children, find successor and replace(smallest higher number)(1 right then left as much as possible)(or the other way around works top)
            Node  aux = root.getRight();
            while(aux.getLeft() != null)
            {
                aux = aux.getLeft();
            }
            root.setNr(aux.getNr());
            ///Delete node
            root.right = deleter(root.getRight(),aux.getNr());
        }
        return root;
    }

}
