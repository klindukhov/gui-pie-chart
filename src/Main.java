import javax.swing.*;
import java.awt.*;

class Slice {
    double value;
    Color color;

    Slice(double value, Color color) {
        this.value = value;
        this.color = color;
    }
}

class MyComponent extends Button {
    private Slice[] slices = {
            new Slice(24, Color.black), new Slice(17, Color.green), new Slice(53, Color.yellow), new Slice(36, Color.red)
    };
    private Graphics g;

    public void paint(Graphics g) {
        super.paint(g);
        drawPie((Graphics2D) g, getBounds(), slices);
        this.g = g;
    }

    private void drawPie(Graphics2D g, Rectangle area, Slice[] slices) {
        double total = 0;

        for (Slice slice : slices) {
            total += slice.value;
        }

        int startAngle = 90;

        for(int i = 0; i < slices.length; i++) {
            double arcAngle =  (slices[i].value * 360 / total);
            if(i == slices.length-1) arcAngle = 360 - startAngle + 90;
            g.setColor(slices[i].color);
            g.fillArc(area.x + area.width/6,
                    area.y + area.height/6,
                    area.width*2/3,
                    area.height*2/3,
                    startAngle,
                    (int)(arcAngle));
            startAngle += (int)arcAngle;
        }




    }

    void rotate(){
        Slice[] newSlices = new Slice[4];
        newSlices[3] = slices[0];
        System.arraycopy(slices, 1, newSlices, 0, 3);
        System.arraycopy(newSlices,0, slices, 0, 4);
        paint(g);
    }
}
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Graph");
        frame.setLayout(new BorderLayout());
        Container c = frame.getContentPane();
        MyComponent com = new MyComponent();
        c.add(com);
        com.addActionListener(e -> com.rotate());



        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}