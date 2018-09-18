package praktikum_1;

public class ListStack implements Stack {

    private Object[] stack = new Object[100];
    private int position = 0;

    @Override
    public void push(Object o) {
        if (position < stack.length) {
            stack[position] = o;
            position++;
        }
    }

    @Override
    public Object pop() {
        if (position > 0) {
            position--;
            return stack[position];
        } else {
            return null;
        }
    }

    @Override
    public Object peek() {
        if (position != 0) {
            return stack[position - 1];
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        return position == 0;
    }

    @Override
    public boolean isFull() {
        return stack.length == position;
    }
}
