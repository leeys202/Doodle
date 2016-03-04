// Palette Class:
// View of the palette

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

public class ViewPalette extends JPanel implements Observer{
    private Model model;

    // allows for 10 colours on the palette
    private Display display; // selected paint brush size/colour display
    private JButton[] palettes = new JButton[12];
    private JButton custom;
    //private JColorChooser mixer;
    private JSlider thickness; // thickness slider
    private JPanel palette; // palette view
    private JPanel strokes; // stroke view

    private class Display extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(model.getWidth()));
            g2.setColor(model.getColor());
            int height = (this.getSize().height - model.getWidth()) / 2;
            int width = (this.getSize().width - model.getWidth())/2;
            g2.drawOval(width,height,model.getWidth(), model.getWidth());
        }
    }

    public ViewPalette(Model m) {
        model = m;

        createPalette();
        createStrokes();

        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();

        // for debugging purpose
        palette.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));
        strokes.setBorder(BorderFactory.createCompoundBorder(raisedbevel,loweredbevel));

        // add the view into one view
        this.setLayout(new GridLayout(2,0));
        this.add(palette);
        this.add(strokes);

    }

    private void createPalette() {
        // initalizing JButtons of Palette
        for (int i = 0; i < 12 ; i++) {
            palettes[i] = new JButton();
        }
        palettes[0].setBackground(Color.WHITE);
        palettes[2].setBackground(Color.PINK);
        palettes[4].setBackground(Color.MAGENTA);
        palettes[6].setBackground(Color.RED);
        palettes[8].setBackground(Color.ORANGE);
        palettes[10].setBackground(Color.YELLOW);
        palettes[1].setBackground(Color.GREEN);
        palettes[3].setBackground(Color.CYAN);
        palettes[5].setBackground(Color.BLUE);
        palettes[7].setBackground(Color.BLACK);
        palettes[9].setBackground(Color.DARK_GRAY);
        palettes[11].setBackground(Color.GRAY);

        display = new Display();
        //display.setBorder(BorderFactory.createTitledBorder("preview"));

        // layout of palette
        palette = new JPanel();
        palette.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        c.weighty = 0.5;
        palette.add(display, c);
        c.gridwidth = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridy = GridBagConstraints.RELATIVE;
        for (int i =0; i < 12; i++) {
            c.gridx = (i%2)+1;
            palette.add(palettes[i], c);
            paletteListener(i);
            // display colour of palette
            palettes[i].setOpaque(true);
        }

        // create custom mixer
        custom = new JButton("Custom");
        custom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color initialBackground = custom.getBackground();
                Color background = JColorChooser.showDialog(null, "Change Button Background",
                        initialBackground);
                if (background != null) {
                    custom.setBackground(background);
                    model.changeColor(background);
                    display.repaint();
                }
            }
        });

        c.gridx = 0;
        c.gridwidth = 3;
        palette.add(custom, c);
        custom.setOpaque(true);

    }

    private void createStrokes() {
        // layout of stroke
        strokes = new JPanel(new GridLayout(0,1));

        thickness = new JSlider(JSlider.VERTICAL, 1, 11, 6);
        // creating label for thickness slider
        Hashtable<Integer,JLabel> labelTable = new Hashtable<Integer,JLabel>();
        labelTable.put(new Integer(1), new JLabel("thin"));
        labelTable.put(new Integer(6), new JLabel("medium"));
        labelTable.put(new Integer(11), new JLabel("thick"));
        thickness.setLabelTable(labelTable);
        //thickness.setMinorTickSpacing(1);
        //thickness.setMajorTickSpacing(5);
        //thickness.setPaintTicks(true);
        thickness.setSnapToTicks(true);
        thickness.setPaintLabels(true);

        // add listener to slider
        thickness.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                model.changeWidth(thickness.getValue());
                display.repaint();
            }
        });

        strokes.add(thickness);
    }

    private void paletteListener(int i) {
        palettes[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.changeColor(palettes[i].getBackground());
                display.repaint();
            }
        });
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }
}
