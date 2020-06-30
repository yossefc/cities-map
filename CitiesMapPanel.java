/*
 * Represents the GUI for the cities map
 */

package graph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author yossef cohen zardi 
 */
public class CitiesMapPanel extends JPanel {
    private CitiesMap map;
    private ArrayList<City> currentPath;
    private JButton cmdAddWay, cmdFindPath, cmdClearPath, cmdClearMap;
    private JLabel lblFrom, lblTo;
    private JTextField txtFrom, txtTo;

    public CitiesMapPanel()
    {
        map = new CitiesMap();
        cmdAddWay = new JButton("Add Way");
        cmdClearMap = new JButton("Clear Map");
        cmdClearPath =  new JButton("Clear Path");
        cmdFindPath =  new JButton("Find Path");
        lblFrom = new JLabel("From: ");
        lblTo = new JLabel("To: ");
        txtFrom = new JTextField(10);
        txtTo = new JTextField(10);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2, 4, 10, 10));
        controls.add(lblFrom);
        controls.add(txtFrom);
        controls.add(lblTo);
        controls.add(txtTo);
        controls.add(cmdAddWay);
        controls.add(cmdFindPath);
        controls.add(cmdClearMap);
        controls.add(cmdClearPath);

        this.setLayout(new BorderLayout());
        add(controls, BorderLayout.SOUTH);

        this.addMouseListener(new MListener());
        ControlsListener l = new ControlsListener();
        cmdAddWay.addActionListener(l);
        cmdFindPath.addActionListener(l);
        cmdClearMap.addActionListener(l);
        cmdClearPath.addActionListener(l);
        this.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ArrayList<City> cities = map.getCities();
        for(City c : cities)
        {
            g.drawOval(c.getCenterX(), c.getCenterY(), 3, 3);
            g.drawString(c.getCityName(), c.getCenterX(), c.getCenterY());
        }
        drawEdges(g, cities);
        if(currentPath != null)
            drawPath(g);
    }

    private void drawEdges(Graphics g, ArrayList<City> cities)
    {
        for(City from : cities)
        {
            ArrayList<City> targets = map.getWays(from);
            for(City target : targets)
                g.drawLine(from.getCenterX(), from.getCenterY(), target.getCenterX(), target.getCenterY());

        }
    }

    private void drawPath(Graphics g)
    {
        for(int i = 0; i < currentPath.size() - 1; i++)
        {
            City c1 = currentPath.get(i);
            City c2 = currentPath.get(i+1);
            g.setColor(Color.RED);
            g.drawLine(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
        }
    }

    public City findCity(String name)
    {
        ArrayList<City> cities = map.getCities();
        for(City c : cities)
            if(c.getCityName().equals(name))
                return c;
        return null;
    }

    private class MListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            String name = JOptionPane.showInputDialog(null, "Enter city name:");
            City c = new City(name, x, y);
            try {
                map.addCity(c);
                repaint();
            }
            catch(CityExistException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }

    private class ControlsListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == cmdAddWay)
            {
                if(!txtFrom.equals(null) && !txtTo.equals(null))
                {
                    City c1 = findCity(txtFrom.getText());
                    City c2 = findCity(txtTo.getText());
                    try {
                        map.addWay(c1, c2);
                        repaint();
                    } catch (CityNotExistException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid cities");
                    }
                }
            }
            else if(e.getSource() == cmdFindPath)
            {
                if(!txtFrom.equals(null) && !txtTo.equals(null))
                {
                    City c1 = findCity(txtFrom.getText());
                    City c2 = findCity(txtTo.getText());
                    try {
                        currentPath = map.findPath(c1, c2);
                        repaint();
                    }
                    catch(NoPathException ex)
                    {
                             JOptionPane.showMessageDialog(null, "No Path");
                    }
                }
            }
            else if(e.getSource() == cmdClearPath)
            {
                currentPath = null;
                repaint();
            }
            else if(e.getSource() == cmdClearMap)
            {
                map = new CitiesMap();
                currentPath = null;
                repaint();
            }
        }
    }

}
