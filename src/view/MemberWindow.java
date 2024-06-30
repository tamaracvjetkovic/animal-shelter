package view;

import controller.FeedController;
import controller.RequestsController;
import domain.model.User;
import dtos.PostDTO;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MemberWindow extends JFrame {

    private User user;
    private FeedController feedController;
    private RequestsController requestsController;

    public MemberWindow(User user) {
        this.user = user;
        feedController = new FeedController();
        requestsController = new RequestsController();
        setWindowData();

        // top panel with login and register buttons
        JPanel topPanel = new JPanel(new BorderLayout());
        Color topPanelsColor = new Color(207, 198, 176, 98);
        topPanel.setBackground(topPanelsColor);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        Color buttonPanelColor = new Color(181, 171, 145);
        buttonPanel.setBackground(buttonPanelColor);

        // Left panel for logout button
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(buttonPanelColor);
        leftPanel.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(92, 92, 92));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setFocusable(false);
        logoutButton.setBorder(new EmptyBorder(5, 10, 5, 10));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        logoutButton.addActionListener(e -> {
            LoginWindow loginWindow = new LoginWindow();
            this.dispose();
        });

        leftPanel.add(logoutButton);

        // Right panel for profile and volunteer request buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(buttonPanelColor);

        // "view profile" button with icon
        JButton viewProfileButton = new JButton();
        try {
            ImageIcon profileIcon = new ImageIcon("src/images/icons/profile_view.png"); // Specify the path to your PNG file
            Image img = profileIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Adjust the size as needed
            viewProfileButton.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            viewProfileButton.setText("View Profile");
        }
        viewProfileButton.setBackground(buttonPanelColor);  // Set the background color
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setFocusable(false);
        viewProfileButton.setBorder(new EmptyBorder(0, 0, 0, 10));
        viewProfileButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        viewProfileButton.addActionListener(e -> {
            UserProfileDialog userProfileDialog = new UserProfileDialog(this, this.user);
            userProfileDialog.setVisible(true);
        });

        // "send volunteer request" button with icon
        JButton volunteerRequestButton = new JButton();
        try {
            ImageIcon profileIcon = new ImageIcon("src/images/icons/send_volunteer_request.png"); // Specify the path to your PNG file
            Image img = profileIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH); // Adjust the size as needed
            volunteerRequestButton.setIcon(new ImageIcon(scaledImg));
        } catch (Exception e) {
            volunteerRequestButton.setText("Send Volunteer Request");
        }

        volunteerRequestButton.setBackground(buttonPanelColor);  // Set the background color
        volunteerRequestButton.setFocusPainted(false);
        volunteerRequestButton.setBorder(new EmptyBorder(0, 0, 0, 16));
        volunteerRequestButton.setFocusable(false);
        volunteerRequestButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        volunteerRequestButton.addActionListener(e -> {
            VolunteeringRequestDialog volunteeringRequestDialog = new VolunteeringRequestDialog(this);
            volunteeringRequestDialog.setVisible(true);
            String reason = volunteeringRequestDialog.getText();

            if(reason != null) {
                // add text to a request?
                requestsController.requestToBeVolunteer(this.user, reason);
            }
        });

        rightPanel.add(viewProfileButton);
        rightPanel.add(volunteerRequestButton);

        // Adding left and right panels to the main button panel
        buttonPanel.add(leftPanel, BorderLayout.WEST);
        buttonPanel.add(rightPanel, BorderLayout.EAST);
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Add buttonPanel to the topPanel or the main container as required
        topPanel.add(buttonPanel, BorderLayout.NORTH);

        topPanel.add(buttonPanel, BorderLayout.NORTH);

        // title label
        JLabel titleLabel = new JLabel("Welcome, " + user.getName() + " " + user.getLastname() + "!", JLabel.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        titleLabel.setFont(new Font("Arial", Font.ITALIC, 14));

        topPanel.add(titleLabel, BorderLayout.CENTER);

        // create tabs
        JTabbedPane tabbedPane = new JTabbedPane();

        // first tab: Pet Panel
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        Color petPanelColor = new Color(207, 198, 176, 234);

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

        petPanel.add(searchPanel, BorderLayout.SOUTH);

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

            // "View" button
            JButton viewButton = new JButton("View");
            viewButton.setFocusable(false);
            viewButton.setBackground(new Color(163, 153, 131));  // Set the background color
            viewButton.setForeground(Color.WHITE);  // Set the text color
            viewButton.setFocusPainted(false);
            viewButton.setBorder(new EmptyBorder(5, 10, 5, 10));
            viewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            viewButton.addActionListener(e -> {
                PetPostWindow petPostWindow = new PetPostWindow(user, post);
                petPostWindow.setVisible(true);
            });

            // set constraints for the view button
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(15, 15, 15, 15); // Adjust as needed for padding

            petPostPanel.add(viewButton, gbc);

            // create a line separator - separates pets
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
        tabbedPane.addTab("Pets", scrollPane);

        // second tab: My posts
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));

        JPanel addPostPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addPostButton = new JButton("Add post");

        addPostButton.addActionListener(e -> {
            CreatePostDialog createPostDialog = new CreatePostDialog(this, user);
            createPostDialog.setVisible(true);
        });

        addPostPanel.add(addPostButton);
        postsPanel.add(addPostPanel, BorderLayout.SOUTH);

        for (PostDTO post : feedController.getAllPostsWithAnimalsAndBreeds(user)) {
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

            JLabel adopted = new JLabel("Status: " + post.getStatus());
            switch (post.getStatus()) {
                case "Adopted" -> adopted.setForeground(new Color(67, 177, 26));
                case "Not adopted" -> adopted.setForeground(new Color(214, 116, 3));
                case "In foster care" -> adopted.setForeground(new Color(9, 120, 188));
                case "Under treatment" -> adopted.setForeground(new Color(221, 9, 9));
            }
            petInfoPanel.add(adopted);

            gbc.gridx = 1;
            petPostPanel.add(petInfoPanel, gbc);

            // "Edit" button
            JButton editButton = new JButton("Edit");
            editButton.setFocusable(false);
            editButton.setBackground(new Color(163, 153, 131));  // Set the background color
            editButton.setForeground(Color.WHITE);  // Set the text color
            editButton.setFocusPainted(false);
            editButton.setBorder(new EmptyBorder(5, 10, 5, 10));
            editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            editButton.addActionListener(e -> {
                EditPostDialog editPostDialog = new EditPostDialog(this, user, feedController.getAnimalFromPost(post), post.getId());
                editPostDialog.setVisible(true);
            });

            // set constraints for the view button
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(15, 15, 15, 15); // Adjust as needed for padding

            petPostPanel.add(editButton, gbc);

            // create a line separator - separates pets
            JPanel lineSeparator = new JPanel();
            lineSeparator.setBackground(Color.GRAY);
            lineSeparator.setPreferredSize(new Dimension(0, 1)); // Height 2px, width 0 to be adjusted by layout
            gbc.gridy = 1;
            petPanel.add(lineSeparator, gbc);

            petPostPanel.setBorder(new EmptyBorder(7, 0, 7, 0));
            petPostPanel.setBackground(petPanelColor);

            postsPanel.add(petPostPanel);
        }

        tabbedPane.addTab("Posts", postsPanel);

        // third tab: Pets I adopted
        JPanel petsAdoptedPanel = new JPanel();

        for (PostDTO post : feedController.getAllPostsWithAnimalsAndBreeds(user)) {
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

            JLabel adopted = new JLabel("Status: " + post.getStatus());
            switch (post.getStatus()) {
                case "Adopted" -> adopted.setForeground(new Color(67, 177, 26));
                case "Not adopted" -> adopted.setForeground(new Color(214, 116, 3));
                case "In foster care" -> adopted.setForeground(new Color(9, 120, 188));
                case "Under treatment" -> adopted.setForeground(new Color(221, 9, 9));
            }
            petInfoPanel.add(adopted);

            gbc.gridx = 1;
            petPostPanel.add(petInfoPanel, gbc);

            // "Grade" button
            JButton gradeButton = new JButton("Grade");
            gradeButton.setFocusable(false);
            gradeButton.setBackground(new Color(163, 153, 131));  // Set the background color
            gradeButton.setForeground(Color.WHITE);  // Set the text color
            gradeButton.setFocusPainted(false);
            gradeButton.setBorder(new EmptyBorder(5, 10, 5, 10));
            gradeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            gradeButton.addActionListener(e -> {
                // to be added
            });

            // set constraints for the view button
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 0.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(15, 15, 15, 15); // Adjust as needed for padding

            petPostPanel.add(gradeButton, gbc);

            // create a line separator - separates pets
            JPanel lineSeparator = new JPanel();
            lineSeparator.setBackground(Color.GRAY);
            lineSeparator.setPreferredSize(new Dimension(0, 1)); // Height 2px, width 0 to be adjusted by layout
            gbc.gridy = 1;
            petPanel.add(lineSeparator, gbc);

            petPostPanel.setBorder(new EmptyBorder(7, 0, 7, 0));
            petPostPanel.setBackground(petPanelColor);

            petsAdoptedPanel.add(petPostPanel);
        }

        tabbedPane.addTab("My pets", petsAdoptedPanel);

        // fourth tab: Payments
        JPanel paymentsPanel = new JPanel();
        paymentsPanel.add(new JLabel("Payments will be displayed here."));
        tabbedPane.addTab("Payments", paymentsPanel);

        // fifth tab: Inbox
        JPanel inboxPanel = new JPanel();
        inboxPanel.add(new JLabel("Inbox will be displayed here."));
        tabbedPane.addTab("Inbox", inboxPanel);

        // add top panel and tabbed pane to the frame
        add(topPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        tabbedPane.setBackgroundAt(0, new Color(202, 191, 168));
        tabbedPane.setBackgroundAt(1, new Color(202, 191, 168));
        tabbedPane.setBackgroundAt(2, new Color(202, 191, 168));
        tabbedPane.setBackgroundAt(3, new Color(202, 191, 168));
        tabbedPane.setBackgroundAt(4, new Color(202, 191, 168));

        // "Animal Shelter ©" label at the bottom
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
