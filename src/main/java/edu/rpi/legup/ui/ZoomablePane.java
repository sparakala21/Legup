package edu.rpi.legup.ui;

import javafx.scene.layout.Pane;

public class ZoomablePane extends Pane
{
    private DynamicViewer viewer;

    /**
     * ZoomablePane Constructor - creates scalable JComponent
     *
     * @param viewer dynamic viewer
     */
    public ZoomablePane(DynamicViewer viewer)
    {
        this.viewer = viewer;
    }

}
