package com.ToniC;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Usuari
 */
public class Main {

    public static void main(String[] args) {
        // puzzle amb solució de 5 moviments
//        NinePuzzle start = new NinePuzzle(new int[]{1, 2, 3, 4, 5, 0, 6, 7, 8});
//        NinePuzzle goal = new NinePuzzle(new int[]{1, 3, 5, 4, 2, 8, 6, 7, 0});

        // puzzle amb solució de 31 moviments
        NinePuzzle start = new NinePuzzle(new int[]{8, 6, 7, 2, 5, 4, 3, 0, 1});
        NinePuzzle goal = new NinePuzzle(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});

//        new AlgorismesNoInformats().BFS(start,goal,32);
//        new AlgorismesNoInformats().DFS(start,goal,32);
        new AlgorismesInformats().HillClimbing(start,goal,32);
    }

}
