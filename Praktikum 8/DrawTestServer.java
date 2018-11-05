package praktikum_8;

import base.CommandExecutor;

import java.awt.*;

public class DrawTestServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        ServerGraphics sg = new ServerGraphics();

        // Triangle
        sg.setColor(Color.black);
        sg.drawLine(0.1, 0.2, 0.9, 0.2);
        sg.drawLine(0.1, 0.2, 0.5, 0.893);
        sg.drawLine(0.9, 0.2, 0.5, 0.893);

        // Square
        sg.setColor(Color.red);
        sg.drawRect(0.1, 0.1, 0.8, 0.8);

        return sg.getTrace();
    }
}
