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

    public int manhattan(NinePuzzle child, NinePuzzle goal) {
        int count = 0;
        int expected = 0;

        for (int row = 0; row < child.puzzle.length; row++) {
            for (int col = 0; col < child.puzzle.length; col++) {
                int value = child.puzzle[row][col];
                expected++;
                if (value != 0 && value != expected) {
                    count += Math.abs(row
                            - getRow(goal.puzzle, value))
                            + Math.abs(col
                            - getCol(goal.puzzle, value));
                }
            }
        }
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
}
