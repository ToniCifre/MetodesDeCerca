package com.ToniC;

import edu.uci.ics.jung.graph.DelegateTree;

import java.util.ArrayList;

/**
 * Fincions que utilitzen els algorismes de cerca
 */
class NinePuzleTree {

    DelegateTree<NinePuzzle, Integer> g = null;

    /**
     * retorna e imprimeix el cami a segir per trobar la solució
     * @param solution node final igual a la solució
     * @return L'arrai amb els nodes del cami de la solució
     */
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

    /**
     * funció euristica basada en el nombre de caselles desordenades
     * @param child puzele actual
     * @param goal puzzle objectiu
     * @return nombre euristic
     */
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

    /**
     * Funció euristica vasada amb la distancia de manhatan
     * @param child puzele actual
     * @param goal puzele actual
     * @return distancia de manhatan
     */
    int manhattan(NinePuzzle child, NinePuzzle goal) {
        int count = 0;

        for (int row = 0; row < child.puzzle.length; row++) {
            for (int col = 0; col < child.puzzle[row].length; col++) {
                int value = child.puzzle[row][col];
                if (value != 0) {
                    count += Math.abs(row
                            - getRow(goal.puzzle, value))
                            + Math.abs(col
                            - getCol(goal.puzzle, value));
                }
            }
        }

        if(count>=22) return 99999;
        return count;
    }

    private int getCol(int[][] a, int value) {
        for (int[] ints : a) {
            for (int col = 0; col < ints.length; col++) {
                if (ints[col] == value) {
                    return col;
                }
            }
        }
        return -1;
    }

    private int getRow(int[][] a, int value) {
        for (int row = 0; row < a.length; row++) {
            for (int col = 0; col < a[row].length; col++) {
                if (a[row][col] == value) {
                    return row;
                }
            }
        }
        return -1;
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

    void drawTree(){
        new ShowGraph(g);
    }
}
