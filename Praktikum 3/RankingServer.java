package praktikum_3;

import base.CommandExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RankingServer implements CommandExecutor {

    private List<Competitor> competitors = new ArrayList<Competitor>();
    private StringBuilder returnBuilder = new StringBuilder();

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

        // Assign the ranks
        assignRanks();

        // Output by time
        outputByTime();

        // Output by name
        returnBuilder.append('\n');
        outputByName();

        return returnBuilder.toString();
    }

    private void assignRanks(){
        Collections.sort(competitors);
        int counter = 1;
        for(var competitor: competitors){
            competitor.setRank(counter++);
        }
    }

    private void outputByTime(){
        for(var competitor: competitors){
            returnBuilder.append(competitor.toString());
            returnBuilder.append('\n');
        }
    }

    private void outputByName(){
        var comp = new Comparator<Competitor>() {
            @Override
            public int compare(Competitor o1, Competitor o2) {
                int compareValue = o1.getName().compareTo(o2.getName());
                if(compareValue == 0){
                    return o1.getJg() - o2.getJg();
                }

                return compareValue;
            }
        };

        Collections.sort(competitors, comp);
        for(var competitor: competitors){
            returnBuilder.append(competitor.toString());
            returnBuilder.append('\n');
        }
    }
}
