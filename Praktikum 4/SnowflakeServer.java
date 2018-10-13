package praktikum_4;

import base.CommandExecutor;
import base.Turtle;

public class SnowflakeServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        String tmp = "";
        Turtle turtle = new Turtle();
        int stufe = Integer.parseInt(command.split(" ")[0]);
        //double strecke = Double.parseDouble(command.split(" ")[1]);

        turtle.reset(0.2d, 0.7d);
        schneeflocke(stufe, 0.6d, turtle);
        turtle.turn(-120);
        schneeflocke(stufe, 0.6, turtle);
        turtle.turn(-120);
        schneeflocke(stufe, 0.6, turtle);

        //schneeflocke(stufe , distance, turtle);
        return turtle.getTrace();
    }

    void schneeflocke(int stufe, double dist, Turtle turtle) {
        if (stufe == 0) {
            turtle.move(dist);
        } else {
            stufe--;
            dist = dist / 3;
            schneeflocke(stufe, dist, turtle);
            turtle.turn(60);
            schneeflocke(stufe, dist, turtle);
            turtle.turn(-120);
            schneeflocke(stufe, dist, turtle);
            turtle.turn(60);
            schneeflocke(stufe, dist, turtle);
        }
    }
}
