package jpower.gui.internal;

import jpower.core.JPower;
import jpower.gui.GuiApp;

import javax.swing.*;

public class VersionApp extends GuiApp {

   public static void main(String[] args) {
      new VersionApp().show();
   }

   @Override
   public void build() {
      JLabel version = (JLabel) getFrame().add(new JLabel("JPower v" + JPower.getReleaseInfo().getVersion()));
      version.setVisible(true);
      version.setVerticalAlignment(SwingConstants.CENTER);
      version.setHorizontalAlignment(SwingConstants.CENTER);
      getFrame().setTitle("JPower Version");
   }
}
