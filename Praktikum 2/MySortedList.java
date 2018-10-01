package praktikum_2;

import java.util.Collections;
import java.util.List;

public class MySortedList extends MyList implements List {
    @Override
    public boolean add(Object o) {
        ListNode currentNode = header.next;
        if(this.size() < 1){
            return super.add(o);
        }

        while(currentNode != header){
            int compared = ((Comparable)currentNode.value).compareTo((Comparable) o);
            if(compared >= 0){
                ListNode newNode = new ListNode(o);
                currentNode.previous.next = newNode;
                newNode.previous = currentNode.previous;
                currentNode.previous = newNode;
                newNode.next = currentNode;

                return true;
            }
            currentNode = currentNode.next;
        }

        ListNode newNode = new ListNode(o);
        newNode.previous = currentNode.previous;
        newNode.next = header;
        header.previous.next = newNode;
        header.previous = newNode;

        return true;
    }
}
