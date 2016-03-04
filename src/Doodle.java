import javax.swing.*;
import java.awt.*;

// Doodle Class:
// main method class:
//      - create instance of model
//      - create instances of view/controllers, pass them reference to model
//      - display the views in the frame

public class Doodle {

    public static void main(String[] args) {
        // makes sure application looks the same throughout all platforms
       try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Doodle");

        MenuBar menu = new MenuBar();
        frame.setJMenuBar(menu);


        // create Model and Controller
        Model model = new Model();

        // creating 3 panel layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        ViewPalette palette = new ViewPalette(model);
        ViewCanvas canvas = new ViewCanvas(model);
        ViewPlayback playback = new ViewPlayback(model, canvas);

        // attach View
        model.addObserver(palette);
        model.addObserver(canvas);
        model.addObserver(playback);

        // to check if rendered properly. can delete after
        //palette.setBorder(BorderFactory.createTitledBorder("palette"));
        //canvas.setBorder(BorderFactory.createTitledBorder("canvas"));
        //playback.setBorder(BorderFactory.createTitledBorder("playback"));

        panel.add(palette, BorderLayout.WEST);
        panel.add(canvas, BorderLayout.CENTER);
        panel.add(playback, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setMinimumSize(new Dimension(300,500));
        frame.setSize(new Dimension(600,600));
        frame.setVisible(true);
    }
}
