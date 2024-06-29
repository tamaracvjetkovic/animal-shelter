package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class MainWindow extends JFrame {

    public MainWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 580);
        setLayout(new BorderLayout());
        setTitle("Homepage");

        // top panel with login and register buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        Color topPanelsColor = new Color(207, 198, 176, 98);
        topPanel.setBackground(topPanelsColor);

        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        Color loginPanelColor = new Color(181, 171, 145, 186);
        loginPanel.setBackground(loginPanelColor);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(114, 189, 28, 237));  // Set the background color
        loginButton.setForeground(Color.WHITE); // Set the text color
        loginButton.setFocusPainted(false);

        // register button
        JButton registerButton = new JButton("Sign Up");
        registerButton.setBackground(new Color(223, 146, 27));  // Set the background color
        registerButton.setForeground(Color.WHITE);  // Set the text color
        registerButton.setFocusPainted(false);

        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        loginPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        topPanel.add(loginPanel, BorderLayout.NORTH);

        // title label
        JLabel titleLabel = new JLabel("Welcome to 'Pets Feed'!", JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        // scrollable panel for pet posts
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        Color petPanelColor = new Color(207, 198, 176, 234);

        for (int i = 0; i < 15; i++) {
            JPanel petPostPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.33;
            gbc.weighty = 1.0;

            // pet image
            JLabel petImageLabel;
            try {
                ImageIcon petImage = new ImageIcon("src/images/mali_zje.png");
                Image img = petImage.getImage();

                Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                petImageLabel = new JLabel(new ImageIcon(scaledImg));
            } catch (Exception e) {
                // image not found -> placeholder text
                petImageLabel = new JLabel("Picture not found");
                petImageLabel.setPreferredSize(new Dimension(150, 150));
            }

            petImageLabel.setPreferredSize(new Dimension(150, 150));
            petImageLabel.setHorizontalAlignment(JLabel.CENTER);
            petImageLabel.setVerticalAlignment(JLabel.CENTER);
            gbc.gridx = 0;
            petPostPanel.add(petImageLabel, gbc);

            // panel for pet info
            JPanel petInfoPanel = new JPanel();
            petInfoPanel.setLayout(new BoxLayout(petInfoPanel, BoxLayout.Y_AXIS));
            petInfoPanel.setBackground(petPanelColor);

            petInfoPanel.add(new JLabel(" "));
            petInfoPanel.add(new JLabel("Name: Zic zic"));
            petInfoPanel.add(new JLabel("Breed: Zje (ali mali)"));
            petInfoPanel.add(new JLabel("Color: Brown"));
            petInfoPanel.add(new JLabel("Date: 24.12.2022."));
            petInfoPanel.add(new JLabel(" "));
            petInfoPanel.add(new JLabel("Status: Available"));
            gbc.gridx = 1;
            petPostPanel.add(petInfoPanel, gbc);

            // Create a line separator
            JPanel lineSeparator = new JPanel();
            lineSeparator.setBackground(Color.GRAY);
            lineSeparator.setPreferredSize(new Dimension(0, 1)); // Height 2px, width 0 to be adjusted by layout
            gbc.gridy = 1;
            petPanel.add(lineSeparator, gbc);

            petPostPanel.setBorder(new EmptyBorder(7, 0, 7, 0));
            petPostPanel.setBackground(petPanelColor);

            petPanel.add(petPostPanel);
        }

        JScrollPane scrollPane = new JScrollPane(petPanel);

        // add the scrollable panel to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // "Pet Ambulance" label
        JLabel bottomLabel = new JLabel("Pet Ambulance", JLabel.CENTER);
        bottomLabel.setBorder(new EmptyBorder(5, 0, 8, 0));
        add(bottomLabel, BorderLayout.SOUTH);

        center(this);
        setVisible(true);
    }

    private static void center(Component component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = component.getSize().width;
        int h = component.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // set the new location for the component
        component.setLocation(x, y);
    }
}
