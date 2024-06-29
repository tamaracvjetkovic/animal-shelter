package view;

import javax.swing.*;

public class MainWindow extends JFrame {

    private JPanel panel1;
    private JLabel testLabel;

    public MainWindow() {
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
    }
}
