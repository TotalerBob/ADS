package praktikum_1;

import base.CommandExecutor;

public class KgvServer implements CommandExecutor {
    public String execute(String s) {
        String[] numbers = s.split(" ");
        int a = Integer.parseInt(numbers[0]);
        int b = Integer.parseInt(numbers[1]);
        return Integer.toString(kgv(a,b));
    }

    public int kgv(int a, int b){
        return Math.abs(a * b) / ggt(a,b);
    }

    public int ggt(int a, int b){
        if (a > b) return ggt(a-b,b);
        else if (a < b) return ggt(a,b-a);
        else return a;
    }
}
