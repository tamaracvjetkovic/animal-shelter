package view;

import controller.FeedController;
import dtos.PostDTO;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;


public class MainWindow extends JFrame {

    private FeedController feedController;
    private ArrayList<PostDTO> posts;

    public MainWindow() {
        feedController = new FeedController();
        posts = feedController.getAllPostsWithAnimalsAndBreeds();
        setWindowData();

        // top panel with login and register buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        Color topPanelsColor = new Color(207, 198, 176, 98);
        topPanel.setBackground(topPanelsColor);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        Color buttonsPanelColor = new Color(181, 171, 145, 186);
        buttonsPanel.setBackground(buttonsPanelColor);

        // change all buttons' background after focusing/clicking on them
        UIManager.put("Button.select", buttonsPanelColor);

        // login button
        JButton loginButton = new JButton("Login");
        loginButton.setFocusable(false);
        loginButton.setBackground(new Color(87, 186, 21));  // Set the background color
        loginButton.setForeground(Color.WHITE); // Set the text color
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonsPanel.add(loginButton);

        // register button
        JButton registerButton = new JButton("Sign Up");
        registerButton.setFocusable(false);
        registerButton.setBackground(new Color(223, 146, 27));  // Set the background color
        registerButton.setForeground(Color.WHITE);  // Set the text color
        registerButton.setFocusPainted(false);
        registerButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonsPanel.add(registerButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        buttonsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        topPanel.add(buttonsPanel, BorderLayout.NORTH);

        // title label
        JLabel titleLabel = new JLabel("Welcome to 'Pets Feed'!", JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        // search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBackground(new Color(207, 198, 176, 98));

        JTextField searchField = new JTextField("Search...", 15);
        JButton searchButton = new JButton("Search");
        searchButton.setFocusable(false);
        searchButton.setBackground(new Color(156, 148, 131, 255));  // Set the background color
        searchButton.setForeground(Color.WHITE);  // Set the text color
        searchButton.setFocusPainted(false);
        searchButton.setBorder(new EmptyBorder(2, 10, 5, 10));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // scrollable panel for pet posts
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));

        // add pets to the scrollable panel
        setPets(petPanel);
        JScrollPane scrollPane = new JScrollPane(petPanel);

        // add the scrollable panel to the frame
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // association name label
        JLabel bottomLabel = new JLabel("Animal Shelter ©", JLabel.CENTER);
        bottomLabel.setBorder(new EmptyBorder(5, 0, 8, 0));
        add(bottomLabel, BorderLayout.SOUTH);

        setVisible(true);

        registerButton.addActionListener(e -> {
            this.dispose();
            RegisterWindow registerWindow = new RegisterWindow();
        });

        loginButton.addActionListener(e->{
            this.dispose();
            LoginWindow loginWindow = new LoginWindow();
        });

        searchButton.addActionListener(e -> {
            posts.clear();
            petPanel.removeAll();

            String[] tokens = searchField.getText().split(" ");

            for(String token : tokens) {
                posts.addAll(feedController.getFilteredPosts(token, token, token, token));
            }

            setPets(petPanel);
        });
    }

    private void setPets(JPanel petPanel) {
        Color petPanelColor = new Color(207, 198, 176, 234);

        for (PostDTO post : posts) {
            JPanel petPostPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.33;
            gbc.weighty = 1.0;

            // pet image
            JLabel petImageLabel;
            try {
                ImageIcon petImage = new ImageIcon(post.getPicture());
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
            petInfoPanel.add(new JLabel("Name: " + post.getName()));
            petInfoPanel.add(new JLabel("Breed: " + post.getBreed()));
            petInfoPanel.add(new JLabel("Color: " + post.getColor()));
            petInfoPanel.add(new JLabel("Date: " + post.getDate()));
            petInfoPanel.add(new JLabel(" "));

            JLabel status = new JLabel("Status: " + post.getStatus());
            switch (post.getStatus()) {
                case "Adopted" -> status.setForeground(new Color(67, 177, 26));
                case "Not adopted" -> status.setForeground(new Color(214, 116, 3));
                case "In foster care" -> status.setForeground(new Color(9, 120, 188));
                case "Under treatment" -> status.setForeground(new Color(221, 9, 9));
            }
            petInfoPanel.add(status);

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
    }

    private void setWindowData() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 580);
        setLayout(new BorderLayout());
        setTitle("Homepage");
        UIUtils.center(this);
    }
}