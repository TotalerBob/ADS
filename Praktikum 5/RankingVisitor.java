package praktikum_5;

import praktikum_3.Competitor;

public class RankingVisitor implements Visitor<Competitor> {
    public StringBuilder sb = new StringBuilder();
    int currentRank = 1;

    @Override
    public void visit(Competitor obj) {
        obj.setRank(currentRank++);
        sb.append(obj.toString() + "\n");
    }

    public String getVisitorData(){
        return sb.toString();
    }
}
