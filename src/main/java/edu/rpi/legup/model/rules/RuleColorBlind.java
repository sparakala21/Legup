package edu.rpi.legup.model.rules;

import edu.rpi.legup.app.LegupPreferences;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import edu.rpi.legup.model.rules.Rule;

public abstract class RuleColorBlind
    extends Rule{

    public RuleColorBlind(String ruleID, String ruleName, String description, String imageName)
    {
        super(ruleID, ruleName, description, imageName);
    }


    private void loadImage() {
        if (imageName != null) {
            LegupPreferences prefs = LegupPreferences.getInstance();
            if(imageName.contains("shorttruthtable") && prefs.getUserPref(LegupPreferences.COLOR_BLIND).equals("true")) {
                imageName = imageName.replace("ruleimages", "ruleimages_cb");
            }
            this.image = new ImageIcon(ClassLoader.getSystemClassLoader().getResource(imageName));
            //Resize images to be 100px wide
            Image image = this.image.getImage();
            if (this.image.getIconWidth() < 120) return;
            int height = (int) (100 * ((double) this.image.getIconHeight() / this.image.getIconWidth()));
            if (height == 0) {
                System.out.println("height is 0 error");
                System.out.println("height: " + this.image.getIconHeight());
                System.out.println("width:  " + this.image.getIconWidth());
                return;
            }
            BufferedImage bimage = new BufferedImage(100, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bimage.createGraphics();
            g.drawImage(image, 0, 0, 100, height, null);
            this.image = new ImageIcon(bimage);
        }
    }
}
