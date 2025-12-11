public  class SingLLFile {
    public static void main(String[] args) {
        LL list = new LL();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertLast(90);
        list.insertFirst(30);
        list.Display();        // 30->20->10->90
        list.deleteAtIndex(1); // removes value 20
        list.display();        // 30->10->90->END
    }
}

class LL {
    Node head;
    Node tail;
    int size;

    public LL() {
        this.size = 0;
    }

    public void insertFirst(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void insertLast(int value) {
        if (tail == null) {
            insertFirst(value);
            return;
        }
        Node node = new Node(value);
        tail.next = node;
        tail = node;
        size++;
    }

    public void insert(int val, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Invalid index");
        if (index == 0) {
            insertFirst(val);
            return;
        }
        if (index == size) {
            insertLast(val);
            return;
        }
        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        Node node = new Node(val, temp.next);
        temp.next = node;
        size++;
    }

    public int deleteAtIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");

        // delete first
        if (index == 0) {
            int val = head.value;
            head = head.next;
            if (head == null) tail = null;
            size--;
            return val;
        }

        // general case: get previous node
        Node prev = get(index - 1);
        int val = prev.next.value;
        prev.next = prev.next.next;

        // if we removed last node, update tail
        if (prev.next == null) tail = prev;

        size--;
        return val;
    }

    public Node get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Invalid index");
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + "->");
            temp = temp.next;
        }
        System.out.println("END");
    }
}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

