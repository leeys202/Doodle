// Playback Class
// View that shows the playback options

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class ViewPlayback extends JPanel implements Observer {
    private Model model;
    private ViewCanvas canvas;

    private JPanel view;

    private JButton play;
    private JButton rewind;
    private JButton fastforward;
    private JSlider animation;

    public ViewPlayback(Model m, ViewCanvas cv) {
        canvas = cv;
        model = m;

        play = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("pictures/play.png"));
            img = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH );
            play.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.resetIndex();
                animation.setValue(animation.getMinimum());
                canvas.repaint();
            }
        });
        rewind = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("pictures/rewind.png"));
            img = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH );
            rewind.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        rewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animation.setValue(animation.getMinimum());
            }
        });

        fastforward = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("pictures/fastforward.png"));
            img = img.getScaledInstance( 30, 20,  java.awt.Image.SCALE_SMOOTH );
            fastforward.setIcon(new ImageIcon(img));
        } catch (IOException ex) {
        }
        fastforward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animation.setValue(animation.getMaximum());
            }
        });

        animation = new JSlider(0,1,1);
        animation.setPaintTicks(true);
        animation.setMajorTickSpacing(5);
        animation.setMinorTickSpacing(1);

        // layout

        view = new JPanel();
        view.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.NONE;
        view.add(rewind, c);
        c.gridx = 1;
        view.add(play, c);
        c.gridx = 2;
        view.add(fastforward,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.weightx = 0.5;

        this.setLayout(new GridLayout(0,1));
        view.add(animation,c);

        this.add(view);

        animation.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                model.setIndex(animation.getValue());
                canvas.repaint();
            }
        });

    }

    @Override
    public void update(Observable arg0, Object arg1) {
        animation.setMaximum(model.getLines().size());
        animation.setValue(animation.getMaximum());
    }
}
