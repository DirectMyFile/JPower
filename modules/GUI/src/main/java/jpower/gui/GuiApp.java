package jpower.gui;

import jpower.event.EventBus;

import javax.swing.*;

public abstract class GuiApp {
    private final JFrame frame = new JFrame();
    private final EventBus eventBus = new EventBus();

    public GuiApp() {
        setDefaults();
        build();
    }

    private void setDefaults() {
        frame.setLocationRelativeTo(null); // Centers the Frame
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public abstract void build();

    public JFrame getFrame() {
        return frame;
    }

    public void show() {
        frame.setVisible(true);
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
