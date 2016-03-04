
// Model class:
// applies the logic of the application

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Vector;

public class Model extends Observable {
    private Vector <ArrayList<Point>> lines;
    private Vector <BasicStroke> strokes;
    private Vector <Color> colors;
    private int paintindex;

    private Color selected_color;
    private int selected_width;

    // constructor
    public Model() {
        paintindex = 0;
        // set default color and width
        lines = new Vector<ArrayList<Point>>();
        strokes = new Vector<BasicStroke>();
        colors = new Vector<Color>();

        selected_color = Color.black;
        selected_width = 12;
        setChanged();
    }

    // public functions for others
    public void undo() {
        lines.setSize(paintindex);
        strokes.setSize(paintindex);
        colors.setSize(paintindex);
    }
    public int getIndex() {
        return paintindex;
    }
    public void addIndex() {
        paintindex++;
    }
    public void resetIndex() { paintindex = lines.size();}
    public void setIndex(int i) {
        paintindex = i;
    }
    public Vector<BasicStroke> getStrokes() {
        return strokes;
    }
    public void addStroke(BasicStroke s) {
        strokes.add(s);
    }
    public Vector<Color> getColors() {
        return colors;
    }
    public void addColor(Color c) {
        colors.add(c);
    }
    public int getWidth() {
        return selected_width;
    }
    public Color getColor() {
        return selected_color;
    }

    public void replace(ArrayList<Point> updated) {
        lines.remove(lines.size()-1);
        lines.add(updated);
        setChanged();
        notifyObservers();
    }

    public Vector<ArrayList<Point>> getLines() {
        return lines;
    }

    public void addLine(ArrayList<Point> line) {
        lines.add(line);
        setChanged();
        notifyObservers();
    }

    public void changeColor(Color newC) {
        selected_color = newC;
        setChanged();
        notifyObservers();
    }

    public void changeWidth(int newW) {
        selected_width = newW*2;
        setChanged();
        notifyObservers();
    }

}
