package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgorismesInformats extends NinePuzleCommons {

    ArrayList<NinePuzzle> HillClimbing(NinePuzzle start, NinePuzzle goal, int max_depth){
        NinePuzzle.resetIds();
        int edge = 0;

        List<NinePuzzle> LNO = new ArrayList<>();
        LNO.add(start);

        g = new DelegateTree<>();
        g.addVertex(start);

        while (LNO.size() > 0) {
            NinePuzzle parent = LNO.remove(0);
            List<Dir> dirs = parent.validMoves();
            for (Dir d : dirs) {

                NinePuzzle child = parent.clone();
                child.move(d);
                if ( child.getDepth() < max_depth && !isCycle(child, parent)) {
                    g.addChild(edge++, parent, child);
                    if (child.isSolution(goal)) {
                        new ShowGraph(g);
                        return getCamiSol(child);
                    } else {
                        LNO.add(child);
                    }
                }
            }
            LNO.sort(Comparator.comparingInt(r -> manhattan(r, goal)));
        }

        new ShowGraph(g);
        return getCamiSol(null);
    }

}
