package praktikum_8;

import base.CommandExecutor;
import praktikum_7.AdjListGraph;
import praktikum_7.Edge;
import praktikum_7.Graph;

import java.awt.*;

public class LabyrinthServer implements CommandExecutor {
    final double SCALE = 10;

    @Override
    public String execute(String command) throws Exception {
        // Read Input data
        String[] paths = command.split("\n");

        // Add to graph
        AdjListGraph<LabyrinthNode, Edge> graph = new AdjListGraph(LabyrinthNode.class, Edge.class);
        for (String path : paths) {
            String from = path.split(" ")[0];
            String to = path.split(" ")[1];

            try {
                graph.addNode(from);
                graph.addNode(to);
                graph.addEdge(from, to, 1);
                graph.addEdge(to, from, 1);
            } catch (Throwable t) {
                // Suppress errors as we know the input data
            }
        }

        // Drawing
        ServerGraphics sg = new ServerGraphics();

        // Background
        sg.setColor(Color.black);
        sg.fillRect(0, 0, 1, 1);

        // Paths
        sg.setColor(Color.white);
        for (LabyrinthNode node : graph.getNodes()) {
            for (Edge edge : node.getEdges()) {
                drawPath(sg, node.getName(), ((LabyrinthNode) edge.getDest()).getName(), false);
            }
        }

        if(search(graph.findNode("0-6"), graph.findNode("3-0"))){
            LabyrinthNode node = graph.findNode("3-0");

            // Iterate over found path
            sg.setColor(Color.red);
            while(node.prev != null){
                drawPath(sg, node.getName(), node.prev.getName(), true);
                node = node.prev;
            }
        }

        // Return drawn labyrinth
        return sg.getTrace();

    }

    private boolean search(LabyrinthNode start, LabyrinthNode goal){
        start.marked = true;
        if(start.equals(goal)){
            return true;
        }
        else{
            for(Edge edge : start.getEdges()){
                LabyrinthNode adjNode = (LabyrinthNode) edge.getDest();
                if(!adjNode.marked){
                    adjNode.prev = start;
                    if(search(adjNode, goal)){
                        return true;
                    }
                }
            }
        }

        start.marked = false;
        return false;
    }

    private void drawPath(ServerGraphics g, String from, String to, boolean mouse) {
        double xh0 = from.charAt(0) - '0';
        double yh0 = from.charAt(2) - '0';
        double xh1 = to.charAt(0) - '0';
        double yh1 = to.charAt(2) - '0';

        double x0 = Math.min(xh0, xh1) / SCALE;
        double y0 = Math.min(yh0, yh1) / SCALE;
        double x1 = Math.max(xh0, xh1) / SCALE;
        double y1 = Math.max(yh0, yh1) / SCALE;

        double w = 1 / SCALE;

        if (mouse) {
            g.drawLine(x0 + w / 2, y0 + w / 2, x1 + w / 2, y1 + w / 2);
        } else {
            if (y0 == y1) {
                g.fillRect(x0, y0, x1 - x0 + w, w);
            } else {
                g.fillRect(x0, y0, w, y1 - y0 + w);
            }
        }
    }
}
