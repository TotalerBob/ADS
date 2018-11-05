package praktikum_7;

import base.CommandExecutor;

import java.util.PriorityQueue;
import java.util.Queue;

public class RouteServer implements CommandExecutor {

    @Override
    public String execute(String command) throws Exception {
        Graph graph = new AdjListGraph<DijkstraNode, Edge>(DijkstraNode.class, Edge.class);
        String[] entries = command.split("\n");

        for(String entry : entries){
           String[] entryData = entry.split(" ");
           String src = entryData[0];
           String dest = entryData[1];
           double weight = Double.parseDouble(entryData[2]);

           try{
               graph.addNode(src);
               graph.addNode(dest);
               graph.addEdge(src, dest, weight);
               graph.addEdge(dest, src, weight);
           }
           catch(Throwable t){
                return "Input in invalid format!";
           }
        }


        for(DijkstraNode node : (Iterable<DijkstraNode>) graph.getNodes()){
            node.dist = 9999999d;
            node.prev = null;
            node.mark = false;
        }

        // Prepare
        DijkstraNode startNode = (DijkstraNode) graph.findNode("Winterthur");
        DijkstraNode goalNode = (DijkstraNode) graph.findNode("Lugano");

        /// Calculate
        DijkstraNode result = Dijkstra(graph, startNode, goalNode);
        DijkstraNode current = result;

        // Output
        StringBuilder sb = new StringBuilder();
        while(current != null){
            sb.append(current.name);
            current = current.prev;
            if(current != null) {
                sb.append(" -> ");
            }
        }
        sb.append(" | Total: " + result.dist + "Km");

        return sb.toString();
    }

    public DijkstraNode Dijkstra(Graph g, DijkstraNode start, DijkstraNode goal){
        // Initialize vars
        DijkstraNode currentNode = null;
        PriorityQueue q = new PriorityQueue<DijkstraNode>();
        start.dist = 0;

        // Start Dijkstra
        q.add(start);
        while(!q.isEmpty()){
            // Remove from queue and mark as visited
            currentNode = (DijkstraNode) q.poll();
            currentNode.mark = true;

            if(currentNode.equals(goal)){
                return currentNode;
            }

            // Iterate childs
            for(Edge edge : (Iterable<Edge>) currentNode.getEdges()){
                // Get child
                DijkstraNode childNode = (DijkstraNode) edge.dest;

                // Only not yet visited childs
                if(!childNode.mark){
                    // Set new distace
                    double dist = edge.weight + currentNode.dist;
                    // If the child was not yet visited and the calculated distance is smaller than the current
                    if((childNode.prev == null) && (dist < childNode.dist)){
                        // Add as possible path
                        childNode.dist = dist;
                        childNode.prev = currentNode;
                        q.add(childNode);
                    }
                }
            }
        }

        return null;
    }
}
