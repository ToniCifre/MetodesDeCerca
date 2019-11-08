package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgorismesNoInformats extends NinePuzleCommons {


    ArrayList<NinePuzzle> BFS(NinePuzzle start, NinePuzzle goal){
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
                if (!isCycle(child, parent)) {
                    g.addChild(edge++, parent, child);
                    if (child.isSolution(goal)) {
                        new ShowGraph(g);
                        return getCamiSol(child);
                    } else {
                        LNO.add(child);
                    }
                }
            }
        }

        new ShowGraph(g);
        return getCamiSol(null);
    }


    ArrayList<NinePuzzle> DFS(NinePuzzle start, NinePuzzle goal){
        NinePuzzle.resetIds();
        int edge = 0;

        List<NinePuzzle> LNO = new ArrayList<>();
        LNO.add(start);

        g = new DelegateTree<>();
        g.addVertex(start);

        while (LNO.size() > 0) {

            NinePuzzle parent = LNO.remove(0);
            List<Dir> dirs = parent.validMoves();

            int pos = 0;
            for (Dir d : dirs) {
                NinePuzzle child = parent.clone();
                child.move(d);

                if ( !isCycle(child, parent)) {
                    g.addChild(edge++, parent, child);

                    if (child.isSolution(goal)) {
                        new ShowGraph(g);
                        return getCamiSol(child);
                    } else {
                        LNO.add(pos++, child);
                    }
                }
            }
        }

        new ShowGraph(g);
        return getCamiSol(null);
    }

}
