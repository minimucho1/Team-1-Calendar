package team.pkg1.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Team1Calendar implements Runnable, ActionListener, MouseListener {
    
    private JFrame frame;
    private int current_day;
    Giraffix graphics;
    
    @Override
    public void run() {
        
        current_day = 14;
        
        frame = new JFrame("Very Cool Calendar");
        frame.addMouseListener(this);
        JMenuBar menu_bar = new JMenuBar();
        
        JMenu edit_menu = new JMenu("Edit");
        edit_menu.setMnemonic(KeyEvent.VK_E);
        menu_bar.add(edit_menu);
        
        JMenuItem button_add_event = new JMenuItem("Add Event");
        button_add_event.setMnemonic(KeyEvent.VK_A);
        button_add_event.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
        button_add_event.addActionListener(this);
        edit_menu.add(button_add_event);
        
        JMenuItem button_delete_event = new JMenuItem("Delete Event");
        button_delete_event.setMnemonic(KeyEvent.VK_D);
        button_delete_event.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK));
        button_delete_event.addActionListener(this);
        edit_menu.add(button_delete_event);
        
        JMenuItem button_modify_event = new JMenuItem("Edit Event");
        button_modify_event.setMnemonic(KeyEvent.VK_E);
        button_modify_event.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        button_modify_event.addActionListener(this);
        edit_menu.add(button_modify_event);
        
        JMenu view_menu = new JMenu("View");
        view_menu.setMnemonic(KeyEvent.VK_V);
        menu_bar.add(view_menu);
        
        ButtonGroup view_modes = new ButtonGroup();
        
        JRadioButtonMenuItem button_month_view = new JRadioButtonMenuItem("View by Month");
        button_month_view.setMnemonic(KeyEvent.VK_M);
        button_month_view.setSelected(true);
        button_month_view.addActionListener(this);
        view_modes.add(button_month_view);
        view_menu.add(button_month_view);
        
        JRadioButtonMenuItem button_week_view = new JRadioButtonMenuItem("View by Week");
        button_week_view.setMnemonic(KeyEvent.VK_W);
        button_week_view.addActionListener(this);
        view_modes.add(button_week_view);
        view_menu.add(button_week_view);
        
        JRadioButtonMenuItem button_day_view = new JRadioButtonMenuItem("View by Day");
        button_day_view.setMnemonic(KeyEvent.VK_D);
        button_day_view.addActionListener(this);
        view_modes.add(button_day_view);
        view_menu.add(button_day_view);
        
        view_menu.addSeparator();
        
        JMenuItem button_go_to_day = new JMenuItem("Go to Day...");
        button_go_to_day.setMnemonic(KeyEvent.VK_G);
        button_go_to_day.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        button_go_to_day.addActionListener(this);
        view_menu.add(button_go_to_day);
        
        JMenuItem button_go_to_today = new JMenuItem("Go to Current Day");
        button_go_to_today.setMnemonic(KeyEvent.VK_C);
        button_go_to_today.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        button_go_to_today.addActionListener(this);
        view_menu.add(button_go_to_today);
        
        /*
        JMenuItem button_quit = new JMenuItem("Quit");
        button_quit.setMnemonic(KeyEvent.VK_Q);
        button_quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        button_quit.getAccessibleContext().setAccessibleDescription("Exit the application");
        button_quit.addActionListener(this);
        edit_menu.add(button_quit);
        */
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setJMenuBar(menu_bar);
        graphics = new Giraffix();
        frame.add(graphics, BorderLayout.CENTER);
        frame.setVisible(true);
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // width = 168, height = 144
        int xpos = me.getX() - 16;
        int ypos = me.getY() - 109;
        int day = (int)(xpos / 24) + (int)(ypos / 24) * 7 - 4;
        if(xpos < 168 && ypos < 144 && xpos >= 0 && ypos >= 0){
            System.out.println("Day selected: April " + day);
            current_day = day;
        }
        graphics.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    private class Giraffix extends JComponent {
        private Graphics2D giraffix;
        public void paint(Graphics g){
            // Shape drawLine = new Line2D.Float(20, 90, 55, 250);
            giraffix = (Graphics2D)g;
            giraffix.setPaint(Color.BLUE);
            giraffix.fill(new Rectangle2D.Float(0, 0, 184, 208));
            giraffix.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
            giraffix.drawString("April " + current_day + " 2016", 200, 32);
            giraffix.setPaint(Color.WHITE);
            giraffix.setFont(new Font("Arial", Font.BOLD, 14));
            giraffix.fill(new Rectangle2D.Float(8, 56, 168, 144));
            giraffix.drawString("April 2016", 8, 24);
            giraffix.setPaint(new Color(100, 255, 100, 255));
            giraffix.fill(new Rectangle2D.Float(104, 104, 24, 24)); // current day
            if(current_day != 14){
                giraffix.setPaint(new Color(255, 200, 0, 255));
                giraffix.fill(new Rectangle2D.Float(((current_day + 4) % 7) * 24 + 8, (int)((current_day + 4) / 7) * 24 + 56, 24, 24)); // current selected day
            }
            giraffix.setPaint(Color.BLACK);
            int i;
            // numbers
            String day;
            for(i = -4; i <= 37; i ++){
                day = "" + i;
                if(i < 1){
                    day = "" + (31 + i);
                }
                if(i > 30){
                    day = "" + (i - 30);
                }
                if(i < 1 || i > 30){
                    giraffix.setColor(new Color(180, 180, 180, 255));
                    giraffix.fill(new Rectangle2D.Float(((i + 4) % 7) * 24 + 8, (int)((i + 4) / 7) * 24 + 56, 24, 24));
                    giraffix.setColor(Color.BLACK);
                }
                //giraffix.drawString(day, ((i + 4) % 7) * (640 / 7) + 10, (int)((i + 4) / 7) * (480 / 6) + 20);
                giraffix.drawString(day, ((i + 4) % 7) * 24 + 13, (int)((i + 4) / 7) * 24 + 73);
            }
            // grid
            for(i = 0; i < 8; i ++){
                giraffix.draw(new Line2D.Float(i * 24 + 8, 56, i * 24 + 8, 200));
            }
            for(i = 0; i < 7; i ++){
                giraffix.draw(new Line2D.Float(8, i * 24 + 56, 176, i * 24 + 56));
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        // test menu actions
        JMenuItem source = (JMenuItem)(ae.getSource());
        System.out.println(source.getText());
        switch(source.getText()){
            case "Add Event":
                if(current_day == 0){
                    JOptionPane.showMessageDialog(frame, "Please select a day first", "Add Event", JOptionPane.WARNING_MESSAGE);
                } else {
                    String event = (String)JOptionPane.showInputDialog(frame, "Enter the event's time in military time,\nfollowed by a description. Example:\n1200 Eat pills", "Add Event", JOptionPane.QUESTION_MESSAGE, null, null, "");
                    System.out.println(event.split(" ")[0]);
                }
                break;
            case "Delete Event":
                if(current_day == 0){
                    JOptionPane.showMessageDialog(frame, "Please select a day first", "Delete Event", JOptionPane.WARNING_MESSAGE);
                } else {
                    int response = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the event?", "Delete Event", JOptionPane.YES_NO_OPTION);
                    System.out.println(response);
                    if(response == 0){
                        System.out.println("Event at April " + current_day + " would be deleted");
                        JOptionPane.showMessageDialog(frame, "The event has been deleted", "Delete Event", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            case "Edit Event":
                if(current_day == 0){
                    JOptionPane.showMessageDialog(frame, "Please select a day first", "Edit Event", JOptionPane.WARNING_MESSAGE);
                } else {
                    String event = (String)JOptionPane.showInputDialog(frame, "Enter the event's new time in military time,\nfollowed by a description. Example:\n1200 Eat pills", "Edit Event", JOptionPane.QUESTION_MESSAGE, null, null, "???? old event data here");
                    System.out.println(event.split(" ")[0]);
                }
                break;
            default:
                break;
        }
    }
    
    public static void main(String[] args) {
        Team1Calendar calendar = new Team1Calendar();
        calendar.run();
    }
    
}