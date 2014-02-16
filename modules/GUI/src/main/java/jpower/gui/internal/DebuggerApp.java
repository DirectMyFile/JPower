package jpower.gui.internal;

import jpower.core.utils.NetUtils;
import jpower.gui.GuiApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class DebuggerApp extends GuiApp {
    private final JPanel panel = new JPanel();

    @Override
    public void build() {
        getFrame().setExtendedState(getFrame().getExtendedState() | JFrame.MAXIMIZED_BOTH);
        panel.setLayout(new GridLayout());
        createButton("Download Test File", event -> {
            try {
                NetUtils.download("https://raw.github.com/git/git/master/Documentation/RelNotes/1.9.0.txt", new File("git-release-notes.txt"));
            } catch (IOException e) {
                showDialog("Error", "ERROR: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        });
        createButton("Delete Test File", event -> {
            boolean deleted = new File("git-release-notes.txt").delete();
            if (!deleted) {
                showDialog("Error", "Failed to delete file!", JOptionPane.ERROR_MESSAGE);
            }
        });
        getFrame().add(panel);
    }

    public JButton createButton(String name, Consumer<ActionEvent> consumer) {
        JButton button = new JButton(name);
        button.addActionListener(consumer::accept);
        button.setPreferredSize(new Dimension(50, 20));
        panel.add(button);
        return button;
    }

    public static void main(String[] args) {
        new DebuggerApp().show();
    }
}
