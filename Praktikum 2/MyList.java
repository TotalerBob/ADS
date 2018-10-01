package praktikum_2;

import java.util.AbstractList;
import java.util.List;

public class MyList extends AbstractList implements List {

    protected ListNode header = new ListNode(null);

    @Override
    public int size() {
        int counter = 0;
        ListNode currentNode = header;
        while(currentNode.next != null && currentNode.next != header){
            counter++;
            currentNode = currentNode.next;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean add(Object o) {
        ListNode newNode = new ListNode(o);

        if(this.size() > 0){
            // Set references so the new object is between header and latest object
            newNode.previous = header.previous;
            header.previous.next = newNode;
            header.previous = newNode;
            newNode.next = header;
        }else{
            // Reset
            header = new ListNode(null);

            // Add object after header and set references
            header.next = newNode;
            header.previous = newNode;
            newNode.next = header;
            newNode.previous = header;
        }

        return true;
    }

    @Override
    public void clear() {
        header = new ListNode(null);
    }

    @Override
    public Object get(int index) {
        ListNode currentNode = header;
        while(index >= 0){
            currentNode = currentNode.next;
            index--;
        }

        return currentNode.value;
    }

    @Override
    public boolean remove(Object o) {
        ListNode currentNode = header;
        while(currentNode.value != o){
            if(currentNode.next == null || currentNode.next == header)
                return false;

            currentNode = currentNode.next;
        }

        currentNode.next.previous = currentNode.previous;
        currentNode.previous.next = currentNode.next;

        return true;
    }

    @Override
    public Object set(int index, Object element) {
        ListNode currentNode = header;
        while(index >= 0){
            currentNode = currentNode.next;
            index--;
        }

        currentNode.value = element;
        return currentNode.value;
    }
}
