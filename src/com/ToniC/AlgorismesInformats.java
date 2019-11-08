package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AlgorismesInformats extends NinePuzleCommons {

    ArrayList<NinePuzzle> HillClimbing(NinePuzzle start, NinePuzzle goal){
        NinePuzzle.resetIds();
        int edge = 0;


        List<NinePuzzle> LNO = new ArrayList<>();
        LNO.add(start);

        g = new DelegateTree<>();
        g.addVertex(start);

        while (LNO.size() > 0) {
            List<NinePuzzle> LF = new ArrayList<>();
            NinePuzzle parent = LNO.remove(0);

            List<Dir> dirs = parent.validMoves();
            for (Dir d : dirs) {

                NinePuzzle child = parent.clone();
                child.move(d);

                if ( !isCycle(child, parent)) {
                    g.addChild(edge++, parent, child);
                    if (child.isSolution(goal)) {
                        new ShowGraph(g);
                        return getCamiSol(child);
                    } else {
                        LF.add(child);
                    }
                }
            }
            LF.sort(Comparator.comparingInt(r -> manhattan(r, goal)));
            if(LF.size()!=0) LNO.add(LF.get(0));
        }

        new ShowGraph(g);
        return getCamiSol(null);
    }

    ArrayList<NinePuzzle> BestFirst(NinePuzzle start, NinePuzzle goal){
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

                if ( !isCycle(child, parent)) {
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


    ArrayList<NinePuzzle> N_Profunditat(NinePuzzle start, NinePuzzle goal, int listSize){
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

                if ( !isCycle(child, parent)) {
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
            LNO = LNO.subList(0, Math.min(LNO.size(), listSize));
        }

        new ShowGraph(g);
        return getCamiSol(null);
    }

}
