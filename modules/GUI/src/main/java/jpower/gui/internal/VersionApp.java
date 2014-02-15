package jpower.gui.internal;

import jpower.core.JPower;
import jpower.gui.GuiApp;

import javax.swing.*;

public class VersionApp extends GuiApp {

    @Override
    public void build() {
        JLabel version = (JLabel) getFrame().add(new JLabel("JPower v" + JPower.getVersion()));
        version.setLabelFor(getFrame());
        version.setVisible(true);
        version.setVerticalAlignment(SwingConstants.CENTER);
        version.setHorizontalAlignment(SwingConstants.CENTER);
        getFrame().setTitle("JPower Version");
    }

    public static void main(String[] args) {
        new VersionApp().show();
    }
}
