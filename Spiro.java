import java.awt.*;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Iterator;

public class Spiro extends JComponent {

    private ArrayList<Integer> xCoordinates;
    private ArrayList<Integer> yCoordinates;
    public static final int SAMPLES = 1000;

    public void paintComponent(Graphics g)
    {
        //w is x, and h is y (as in x/y values in a graph)
        int w = this.getWidth()/2;
        int h = this.getHeight()/2;

        Graphics2D g1 = (Graphics2D) g;
        g1.setStroke(new BasicStroke(2));
        g1.setColor(Color.black);



        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.red);
        //line1

        Polygon p = new Polygon();

        Iterator<Integer> xIterator = xCoordinates.iterator();
        Iterator<Integer> yIterator = yCoordinates.iterator();
        while(xIterator.hasNext()&&yIterator.hasNext()) {
            p.addPoint(w+xIterator.next(),h-yIterator.next());
        }

        g2.drawPolyline(p.xpoints, p.ypoints, p.npoints);
    }

    public void draw() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setTitle("Spirograph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        Spiro mySpirograph = new Spiro(150,105, 14);
        mySpirograph.draw();
    }

    public Spiro(double R, double r, double O) {
        xCoordinates = new ArrayList<Integer>();
        yCoordinates = new ArrayList<Integer>();

        double step = 2*Math.PI/SAMPLES;
        double scale = 250/(R-r+O);

        for(int t = 0; t<=10*SAMPLES; t++) {
            xCoordinates.add((int) Math.round(scale*((R-r)*Math.cos(t*step) + O*Math.cos(((R-r)/r)*t*step))));
            yCoordinates.add((int) Math.round(scale*((R-r)*Math.sin(t*step) - O*Math.sin(((R-r)/r)*t*step))));


        }
    }

}