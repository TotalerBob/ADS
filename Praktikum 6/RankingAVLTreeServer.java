package praktikum_6;

import base.CommandExecutor;
import praktikum_3.Competitor;
import praktikum_5.Tree;

public class RankingAVLTreeServer implements CommandExecutor {
    @Override
    public String execute(String command) throws Exception {
        // parse csv
        Tree<Competitor> competitors = parseCompetitorsFile(command);

        StringBuilder sb = new StringBuilder();
        // print ranked table
        competitors.traversal().inorder(obj ->
                sb.append(obj.toString()).append("\n")
        );
        sb.append("Treesize: ").append(competitors.height()).append("\n");

        return sb.toString();
    }


    public Tree<Competitor> parseCompetitorsFile(String fileContent){
        String[] entries = fileContent.split("\n");
        Tree<Competitor> competitors = new AVLSearchTree<>();

        for (String entry : entries) {
            String[] values = entry.split(";");

            competitors.add(new Competitor(Integer.valueOf(values[0]), values[1], Integer.valueOf(values[2]), values[3], values[4]));
        }

        return competitors;
    }
}
