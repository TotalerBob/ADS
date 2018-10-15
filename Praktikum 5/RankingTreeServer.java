package praktikum_5;

import base.CommandExecutor;
import praktikum_3.Competitor;

public class RankingTreeServer implements CommandExecutor {
    SortedBinaryTree<Competitor> competitors = new SortedBinaryTree<>();

    @Override
    public String execute(String command) throws Exception {
        // Tools
        StringBuilder builder = new StringBuilder();
        var charArray = command.toCharArray();
        int fieldCounter = 0;

        // Fields
        int startNr = 0;
        String name = "";
        int jg = 0;
        String country = "";
        String time = "";

        // Loop
        for(char c : charArray){
            if(c == ';'){
                switch(fieldCounter){
                    case 0:
                        startNr = Integer.parseInt(builder.toString());
                        fieldCounter++;
                        builder.setLength(0);
                        break;
                    case 1:
                        name = builder.toString();
                        fieldCounter++;
                        builder.setLength(0);
                        break;
                    case 2:
                        jg = Integer.parseInt(builder.toString());
                        fieldCounter++;
                        builder.setLength(0);
                        break;
                    case 3:
                        country = builder.toString();
                        fieldCounter++;
                        builder.setLength(0);
                        break;
                    case 4:
                        time = builder.toString();
                        builder.setLength(0);
                }
            }
            else if(c == '\n'){
                // Reset field counter
                fieldCounter = 0;

                // Create new competitor with saved data
                Competitor competitor = new Competitor(startNr, name, jg, country, time);

                // Add competitor
                competitors.add(competitor);
            }
            else{
                builder.append(c);
            }
        }

        RankingVisitor visitor = new RankingVisitor();
        competitors.traversal().inorder(visitor);

        return visitor.getVisitorData();
    }
}
