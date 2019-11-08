package com.ToniC;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowGraph {

    public ShowGraph(DelegateTree<NinePuzzle, Integer> g) {
        showGraph(g);
    }

    private void showGraph(DelegateTree<NinePuzzle, Integer> g) {
        /*
             System.out.println("https://dreampuf.github.io/GraphvizOnline");
             System.out.println("http://graphviz.it/");
             GraphExporter<NinePuzzle, DefaultEdge> exporter =
             new DOTExporter<>(vertexIdProvider, vertexLabelProvider, null);
             Writer writer = new StringWriter();
             exporter.exportGraph(g, writer);
             System.out.println(writer.toString());*/
        Layout<Integer, String> layout = new TreeLayout(g, 70, 120);
        // layout.setSize(new Dimension(300,300)); // sets the initial size of the space     // The BasicVisualizationServer<V,E> is parameterized by the edge types
        VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout, new Dimension(500, 400));
        //vv.setPreferredSize(); //Sets the viewing area size
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final GraphZoomScrollPane scrollPane = new GraphZoomScrollPane(vv);
        //JScrollPane scrollPane = new JScrollPane(vv);
        frame.getContentPane().add(scrollPane);

        final ScalingControl scaler = new CrossoverScalingControl();

        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1.1f, vv.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(vv, 1 / 1.1f, vv.getCenter());
            }
        });

        JButton reset = new JButton("reset");
        reset.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
            }
        });

        JPanel controls = new JPanel();
        controls.add(plus);
        controls.add(minus);
        controls.add(reset);
        frame.getContentPane().add(controls, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

}
