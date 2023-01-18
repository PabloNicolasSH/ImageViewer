package imageviewer;

import imageviewer.control.Command;
import imageviewer.ui.ImageDisplay;
import imageviewer.ui.swing.SwingImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {

    private final Map<String, Command> commandMap = new HashMap<>();
    private ImageDisplay imageDisplay;

    public MainFrame(){
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(image());
        this.add(toolbar(), BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void add(Command command){
        commandMap.put(command.name(), command);
    }

    private Component image() {
        SwingImageDisplay display = new SwingImageDisplay();
        imageDisplay = display;
        return display;
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(button("prev"));
        panel.add(button("next"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(execute(name));
        return button;
    }

    private ActionListener execute(String name) {
        return e -> commandMap.get(name).execute();
    }
}
