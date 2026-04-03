package challenge2;

import java.util.List;

public class Main {

    public static void main(String[] args) {

    }
}

abstract class ListItem {
    protected ListItem rightLink;
    protected ListItem leftLink;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }

    abstract ListItem next();

    abstract ListItem setNext(ListItem list);

    abstract ListItem previous();

    abstract ListItem setPrevious(ListItem list);

    abstract int compareTo(ListItem list);

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

class Node extends ListItem {

    public Node(Object value) {
        super(value);
    }

    @Override
    ListItem next() {
        return this.rightLink;
    }

    ListItem setNext(ListItem list) {
        this.rightLink = list;
        return this.rightLink;
    }

    @Override
    ListItem previous() {
        return this.leftLink;
    }

    @Override
    ListItem setPrevious(ListItem list) {
        this.leftLink = list;
        return this.leftLink;
    }

    @Override
    int compareTo(ListItem list) {
        return ((Comparable) this.value).compareTo(list.getValue());
    }
}

class MyLinkedList implements NodeList {
    private ListItem root;

    public MyLinkedList(ListItem root) {
        this.root = root;
    }

    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem newItem) {
        if (root == null) {
            root = newItem;
            return true;
        }

        ListItem current = root;
        while (true) {
            int cmp = current.compareTo(newItem);
            if (cmp < 0) { // newItem suurem → liigu edasi
                if (current.next() != null) current = current.next();
                else {
                    current.setNext(newItem);
                    newItem.setPrevious(current);
                    return true;
                }
            } else if (cmp > 0) { // newItem väiksem → lisa enne
                ListItem prev = current.previous();
                newItem.setNext(current);
                current.setPrevious(newItem);
                if (prev != null) {
                    prev.setNext(newItem);
                    newItem.setPrevious(prev);
                } else root = newItem; // uus root
                return true;
            } else return false; // duplicate
        }
    }

    public boolean removeItem(ListItem item) {
        if (root == null) return false;

        ListItem current = root;
        while (current != null) {
            if (current.compareTo(item) == 0) {
                ListItem prev = current.previous();
                ListItem next = current.next();
                if (prev != null) prev.setNext(next);
                else root = next;
                if (next != null) next.setPrevious(prev);
                return true;
            }
            current = current.next();
        }
        return false;
    }

    public void traverse(ListItem root) {
        if (root == null) {
            System.out.println("The list is empty");
            return;
        }
        ListItem current = root;
        while (current != null) {
            System.out.println(current.getValue());
            current = current.next();
        }
    }
}

interface NodeList {
    ListItem getRoot();

    boolean addItem(ListItem item);

    boolean removeItem(ListItem item);

    void traverse(ListItem root);
}

class SearchTree implements NodeList {
    private ListItem root;

    public SearchTree(ListItem root) {
        this.root = root;
    }

    @Override
    public ListItem getRoot() {
        return root;
    }

    public boolean addItem(ListItem newItem) {
        if (root == null) {
            root = newItem;
            return true;
        }

        ListItem current = root;
        while (true) {
            int cmp = current.compareTo(newItem);

            if (cmp < 0) { // newItem suurem → liigu parem
                if (current.next() != null) current = current.next();
                else {
                    current.setNext(newItem);
                    newItem.setPrevious(current);
                    return true;
                }
            } else if (cmp > 0) { // newItem väiksem → liigu vasak
                if (current.previous() != null) current = current.previous();
                else {
                    current.setPrevious(newItem);
                    newItem.setNext(current);
                    return true;
                }
            } else return false; // duplicate
        }
    }

    public boolean removeItem(ListItem item) {
        ListItem parent = root;
        ListItem current = root;

        while (current != null) {
            int cmp = current.compareTo(item);
            if (cmp == 0) {
                performRemoval(current, parent);
                return true;
            }
            parent = current;
            if (cmp < 0) current = current.next();
            else current = current.previous();
        }
        return false;
    }

    private void performRemoval(ListItem removed, ListItem parent) {
        ListItem prev = removed.previous();
        ListItem next = removed.next();

        if (prev != null) prev.setNext(next);
        else root = next;

        if (next != null) next.setPrevious(prev);
    }

    public void traverse(ListItem root) {
        if (root != null) {
            traverse(root.previous());          // vasak
            System.out.println(root.getValue()); // parent
            traverse(root.next());              // parem
        }
    }


}
