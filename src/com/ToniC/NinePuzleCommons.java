package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;

class NinePuzleCommons {

    DelegateTree<NinePuzzle, Integer> g = null;

    ArrayList<NinePuzzle> getCamiSol(NinePuzzle solution){
        if (solution == null) {
            System.out.println("Sense solució.");
            return new ArrayList<>();
        } else {
            NinePuzzle node = solution;
            ArrayList<NinePuzzle> camiSolucio = new ArrayList<>();

            while (node != null) {
                camiSolucio.add(0, node);
                node = g.getParent(node);
            }

            System.out.println("------------- Solució -------------\n");
            System.out.println("Mida: " + camiSolucio.size());
            System.out.println("Id: " + camiSolucio.size());

            for (NinePuzzle cami : camiSolucio) {
                cami.print();
            }

            System.out.println("\n----------------------------------");
            return camiSolucio;
        }
    }

    int getEuristic(NinePuzzle child, NinePuzzle goal){
        int euristic = 0;
        for (int i = 0; i<child.puzzle.length; i++) {
            for (int j = 0; j<child.puzzle[i].length; j++) {
                if(child.puzzle[i][j] != goal.puzzle[i][j] && child.puzzle[i][j] != 0){
                    euristic++;
                }
            }
        }
        return euristic;
    }

    boolean isCycle(NinePuzzle child, NinePuzzle parent) {
        while (parent != null) {
            if (child.isSolution(parent)) {
                return true;
            }
            parent = g.getParent(parent);
        }
        return false;
    }
}
