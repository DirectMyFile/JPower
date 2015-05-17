package jpower.gui;

import jpower.event.EventBus;

import javax.swing.*;

public abstract class GuiApp {
   private final JFrame frame = new JFrame();
   private final EventBus eventBus = new EventBus();
   private boolean built;

   public GuiApp() {
      setDefaults();
   }

   private void setDefaults() {
      frame.setLocationRelativeTo(null); // Centers the Frame
      frame.setSize(500, 200);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.setTitle("GUI");
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
      }
   }

   public abstract void build();

   public JFrame getFrame() {
      return frame;
   }

   public void show() {
      if (!built) {
         built = true;
         build();
      }
      frame.setVisible(true);
   }

   public EventBus getEventBus() {
      return eventBus;
   }

   public void showDialog(String title, String message, int type) {
      JOptionPane.showMessageDialog(frame, message, title, type);
   }
}
