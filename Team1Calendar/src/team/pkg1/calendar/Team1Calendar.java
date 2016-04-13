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
    
    @Override
    public void run() {
        
        current_day = 0;
        
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
        
        JMenuItem button_modify_event = new JMenuItem("Modify Event");
        button_modify_event.setMnemonic(KeyEvent.VK_M);
        button_modify_event.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
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
        Giraffix graphics = new Giraffix();
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
        if(xpos < 168 && ypos < 144){
            System.out.println("Day selected: April " + day);
            current_day = day;
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    private class Giraffix extends JComponent {
        public void paint(Graphics g){
            // Shape drawLine = new Line2D.Float(20, 90, 55, 250);
            Graphics2D giraffix = (Graphics2D)g;
            giraffix.setPaint(Color.ORANGE);
            giraffix.setFont(new Font("Arial", Font.BOLD, 14)); 
            giraffix.fill(new Rectangle2D.Float(8, 56, 168, 144));
            giraffix.setPaint(Color.BLACK);
            int i;
            for(i = 0; i < 8; i ++){
                giraffix.draw(new Line2D.Float(i * 24 + 8, 56, i * 24 + 8, 200));
            }
            for(i = 0; i < 7; i ++){
                giraffix.draw(new Line2D.Float(8, i * 24 + 56, 176, i * 24 + 56));
            }
            String day;
            for(i = -4; i <= 37; i ++){
                day = "" + i;
                if(i < 1){
                    day = "" + (31 + i);
                }
                if(i > 30){
                    day = "" + (i - 30);
                }
                //giraffix.drawString(day, ((i + 4) % 7) * (640 / 7) + 10, (int)((i + 4) / 7) * (480 / 6) + 20);
                giraffix.drawString(day, ((i + 4) % 7) * 24 + 13, (int)((i + 4) / 7) * 24 + 73);
            }
            giraffix.drawString("April 2016", 8, 24);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        // test menu actions
        JMenuItem source = (JMenuItem)(ae.getSource());
        System.out.println(source.getText());
        if(source.getText().equals("Add Event")){
            if(current_day == 0){
                JOptionPane.showMessageDialog(frame, "Please select a day first", "Add Event", JOptionPane.WARNING_MESSAGE);
            } else {
                String event = (String)JOptionPane.showInputDialog(frame, "Enter the event's time in military time,\nfollowed by a description. Example:\n1200 Eat pills", "Add Event", JOptionPane.QUESTION_MESSAGE, null, null, "");
                System.out.println(event.split(" ")[0]);
            }
        }
    }
    
    public static void main(String[] args) {
        Team1Calendar calendar = new Team1Calendar();
        calendar.run();
    }
    
}