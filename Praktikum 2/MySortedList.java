package praktikum_2;

import java.util.Collections;
import java.util.List;

public class MySortedList extends MyList implements List {
    @Override
    public boolean add(Object o) {
        boolean success = super.add(o);
        Collections.sort(this);
        return success;
    }
}
