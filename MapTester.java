

package graph;

import javax.swing.JFrame;

/**
 *
 * @author yossef cohen zardi 
 */
public class MapTester {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Cities Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CitiesMapPanel p = new CitiesMapPanel();
        frame.add(p);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
