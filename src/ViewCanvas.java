import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// CanvasArea Class
// View for the canvas where user can draw
//      - renders the drawn images
public class ViewCanvas extends JPanel implements Observer {
    ArrayList<Point> line;

    private Model model;
    Timer timer;
    int fps = 100;

    public ViewCanvas(Model m) {

        this.setBackground(Color.WHITE);
        line = new ArrayList<Point>();
        model = m;
        timer = new Timer(fps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        this.addMouseListener(new MouseInputAdapter() {
           @Override
            public void mousePressed(MouseEvent e) {
               line.add(e.getPoint());
               if (model.getIndex() == model.getStrokes().size()) {
                   model.addIndex();
               } else {
                   model.undo();
               }
               model.addStroke(new BasicStroke(model.getWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
               model.addColor(model.getColor());
               model.addLine(line);
           }
            public void mouseReleased(MouseEvent e) {
                line.add(e.getPoint());
                model.replace(line);
                line = new ArrayList<Point>();
                repaint();

            }
        });
        this.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                line.add(e.getPoint());
                model.replace(line);
                repaint();
            }
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int size = model.getIndex();
        for (int i = 0; i < size; i++) {
            g2.setStroke(model.getStrokes().get(i));
            g2.setColor(model.getColors().get(i));
            ArrayList<Point> topaint = model.getLines().get(i);
            int len = topaint.size()-2;
            for (int j = 0 ; j < len; j++) {
                Point p1 = topaint.get(j);
                Point p2 = topaint.get(j+1);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {

    }
}
