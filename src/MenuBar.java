import javafx.stage.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

// MenuBar class
// Creates a Menu for the app which allows for save/load functions

public class MenuBar extends JMenuBar {
    private JMenu file;
    private JMenu view;
    private JFileChooser fc;

    public MenuBar() {
        createFileMenu();
        createViewMenu();
        fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)","txt"));
        fc.setFileFilter(new FileNameExtensionFilter("Binary Files (*.bin)", "txt"));
        this.add(file);
        this.add(view);
    }

    private void createFileMenu() {
        file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem menuItem = new JMenuItem("Create new Doodle");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        file.add(menuItem);

        menuItem = new JMenuItem("Save Doodle");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.setDialogTitle("Saving Doodle to...");
                int value = fc.showSaveDialog(file.getParent());
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    // need to do something with file
                } else {
                    System.out.print("cannot open the extension");
                }

            }
        });
        file.add(menuItem);

        menuItem = new JMenuItem("Load Doodle");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fc.setDialogTitle("Loading Doodle from...");
                int value = fc.showOpenDialog(file.getParent());
                if (value == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    // need to do something with file
                } else {
                    System.out.print("cannot open the extension");
                }
            }
        });
        file.add(menuItem);

        file.addSeparator();
        menuItem = new JMenuItem("Quit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(menuItem);

    }

    private void createViewMenu() {
        view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JMenuItem menuItem = new JMenuItem("Fullsize");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.ALT_MASK));
        view.add(menuItem);

        menuItem = new JMenuItem("Fit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        view.add(menuItem);

    }
}
