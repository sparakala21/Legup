package ui;

import controller.Controller;

import java.lang.Double;

import java.util.TreeSet;
import java.util.logging.Logger;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;

import javafx.scene.control.ScrollPane;

public abstract class DynamicViewer extends ScrollPane
{
    private final static Logger LOGGER = Logger.getLogger(DynamicViewer.class.getName());

    private static final double minScale = 0.25;
    private static final double maxScale = 4.0;
    private static final double levels[] = {0.25, 1.0 / 3.0, 0.50, 2.0 / 3.0, 1.0, 2.0, 3.0, 4.0};

    private Dimension viewSize;
    private Dimension zoomSize;
    private TreeSet<Double> zoomLevels;
    private double scale;

    private Controller controller;
    private ZoomablePane canvas;
    private ZoomWidget widget;

    /**
     * DynamicViewer Constructor - creates a DynamicViewer object using
     * the controller handle the ui events
     *
     * @param controller controller that handles the ui events
     */
    public DynamicViewer(Controller controller) {
        super();
        setHbarPolicy(ScrollBarPolicy.ALWAYS);
        setVbarPolicy(ScrollBarPolicy.ALWAYS);

        viewSize = new Dimension();
        zoomSize = new Dimension();
        scale = 1.0;

        this.canvas = new ZoomablePane(this);
        setContent(canvas);

        zoomLevels = new TreeSet<>();
        for (Double level : levels) {
            zoomLevels.add(level);
        }

        widget = new ZoomWidget(this);
//        setCorner(JScrollPane.LOWER_RIGHT_CORNER, widget);
//
//        setWheelScrollingEnabled(false);
//
//        this.controller = controller;
//        controller.setViewer(this);
//        canvas.addMouseMotionListener(controller);
//        canvas.addMouseListener(controller);
//        viewport.addMouseWheelListener(controller);
//    }
    }
}
