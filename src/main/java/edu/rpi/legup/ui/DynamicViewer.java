package edu.rpi.legup.ui;

import edu.rpi.legup.controller.Controller;

import java.lang.Double;

import java.util.TreeSet;
import java.util.logging.Logger;

import javafx.geometry.Dimension2D;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public abstract class DynamicViewer extends Pane
{
    private final static Logger LOGGER = Logger.getLogger(DynamicViewer.class.getName());

    private static final double minScale = 0.25;
    private static final double maxScale = 4.0;
    private static final double levels[] = {0.25, 1.0 / 3.0, 0.50, 2.0 / 3.0, 1.0, 2.0, 3.0, 4.0};

    private Dimension2D viewSize;
    private Dimension2D zoomSize;
    private TreeSet<Double> zoomLevels;
    private double scale;

    private ScrollPane canvas;
    private Controller controller;

    /**
     * DynamicViewer Constructor - creates a DynamicViewer object using
     * the edu.rpi.legup.controller handle the edu.rpi.legup.ui events
     *
     * @param controller edu.rpi.legup.controller that handles the edu.rpi.legup.ui events
     */
    public DynamicViewer(Controller controller) {
        super();

        canvas = new ScrollPane();
        canvas.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        canvas.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        viewSize = new Dimension2D(0,0);
        zoomSize = new Dimension2D(0,0);
        scale = 1.0;

        zoomLevels = new TreeSet<>();
        for (Double level : levels) {
            zoomLevels.add(level);
        }

        canvas.setPrefSize(400, 400);

        getChildren().add(canvas);
//    }
    }
}