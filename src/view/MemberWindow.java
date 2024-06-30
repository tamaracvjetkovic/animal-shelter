package view;

import controller.FeedController;
import domain.model.User;
import dtos.PostDTO;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MemberWindow extends JFrame {

    private FeedController feedController;

    public MemberWindow(User user) {
        feedController = new FeedController();
        setWindowData();

        // top panel with login and register buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        Color topPanelsColor = new Color(207, 198, 176, 98);
        topPanel.setBackground(topPanelsColor);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        Color buttonPanelColor = new Color(181, 171, 145, 186);
        buttonPanel.setBackground(buttonPanelColor);

        // view profile button with icon
        JButton viewProfileButton = new JButton();
        try {
            ImageIcon profileIcon = new ImageIcon("src/images/icons/profile_view.png"); // Specify the path to your PNG file
            Image img = profileIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Adjust the size as needed
            viewProfileButton.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            viewProfileButton.setText("View Profile");
        }
        viewProfileButton.setBackground(new Color(223, 146, 27, 0));  // Set the background color
        viewProfileButton.setForeground(Color.WHITE);  // Set the text color
        viewProfileButton.setBorder(null);
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setBorder(new EmptyBorder(0, 0, 0, 10));

        // send volunteer request
        JButton volunteerRequestButton = new JButton();

        try {
            ImageIcon profileIcon = new ImageIcon("src/images/icons/send_volunteer_request.png"); // Specify the path to your PNG file
            Image img = profileIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Adjust the size as needed
            volunteerRequestButton.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            volunteerRequestButton.setText("Send Volunteer Request");
        }
        volunteerRequestButton.setBackground(new Color(223, 146, 27, 0));  // Set the background color
        volunteerRequestButton.setForeground(Color.WHITE);  // Set the text color
        volunteerRequestButton.setBorder(null);
        volunteerRequestButton.setFocusPainted(false);
        volunteerRequestButton.setBorder(new EmptyBorder(0, 0, 0, 16));

        buttonPanel.add(viewProfileButton);
        buttonPanel.add(volunteerRequestButton);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        topPanel.add(buttonPanel, BorderLayout.NORTH);

        // title label
        JLabel titleLabel = new JLabel("Welcome, " + user.getName() + " " + user.getLastname() + "!", JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        // scrollable panel for pet posts
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        Color petPanelColor = new Color(207, 198, 176, 234);

        for (PostDTO post : feedController.getAllPostsWithAnimalsAndBreeds()) {
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
            petInfoPanel.add(new JLabel("Status: " + post.getStatus()));
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
        JLabel bottomLabel = new JLabel("Animal Shelter ©", JLabel.CENTER);
        bottomLabel.setBorder(new EmptyBorder(5, 0, 8, 0));
        add(bottomLabel, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void setWindowData() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new BorderLayout());
        setTitle("Member Homepage");
        UIUtils.center(this);
    }
}
