package praktikum_4;

import base.CommandExecutor;

public class HanoiServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        return hanoi(Integer.parseInt(command), "A", "B", "C");
    }

    public String hanoi(int groesse, String from, String to, String help){
        String tmp = "";

        if(groesse > 0){

            // Bewege von from auf help
            tmp += hanoi(groesse-1, from, help, to);

            // Print command
            tmp += "move " + from + " to " + to + "\n";

            // Bewege help auf to
            tmp += hanoi(groesse-1, help, to, from);
        }

        return tmp;
    }
}
