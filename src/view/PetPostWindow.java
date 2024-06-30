package view;

import controller.FeedController;
import domain.model.User;
import dtos.PostDTO;
import util.UIUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PetPostWindow extends JFrame {
    private FeedController feedController;
    int likes;

    public PetPostWindow(User user, PostDTO post) {
        feedController = new FeedController();
        setWindowData(post);

        // Top panel for pet name and breed
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(post.getName() + " (" + post.getBreed() + ")", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Adding separator after top panel
        topPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        topPanel.setBackground(new Color(207, 198, 176, 98));

        // Main panel for pet details, comments and buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground( new Color(172, 164, 146));

        // Pet view panel
        JPanel petViewPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // 1st column: pet picture
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel picturePanel = new JPanel(new BorderLayout());
        picturePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        try {
            ImageIcon petImage = new ImageIcon("src/images/pets/mali_zje.png");
            Image img = petImage.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            JLabel petImageLabel = new JLabel(new ImageIcon(scaledImg));
            picturePanel.add(petImageLabel, BorderLayout.CENTER);
            picturePanel.setBackground(new Color(213, 202, 179));
        } catch (Exception e) {
            JLabel petImageLabel = new JLabel("Picture not found");
            petImageLabel.setPreferredSize(new Dimension(150, 150));
            picturePanel.add(petImageLabel, BorderLayout.CENTER);
        }

        petViewPanel.add(picturePanel, gbc);

        // 2nd column: pet details
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.anchor = GridBagConstraints.NORTH;

        JPanel petInfoPanel = new JPanel();
        petInfoPanel.setLayout(new BoxLayout(petInfoPanel, BoxLayout.Y_AXIS));
        petInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        petInfoPanel.setBackground(new Color(213, 202, 179));

        petInfoPanel.add(new JLabel("Pet name: " + post.getName()));
        petInfoPanel.add(new JLabel("Breed: " + post.getBreed()));
        petInfoPanel.add(new JLabel("Color: " + post.getColor()));
        petInfoPanel.add(new JLabel("Date: " + post.getDate()));

        String address = "Adresaaa";
        petInfoPanel.add(new JLabel("Address: " + address));

        String species = "Ultra mali jez";
        petInfoPanel.add(new JLabel("Species: " + species));

        petInfoPanel.add(new JLabel(" "));
        JLabel status = new JLabel("Status: " + post.getStatus());
        switch (post.getStatus()) {
            case "Adopted" -> status.setForeground(new Color(67, 177, 26));
            case "Not adopted" -> status.setForeground(new Color(214, 116, 3));
            case "In foster care" -> status.setForeground(new Color(9, 120, 188));
            case "Under treatment" -> status.setForeground(new Color(221, 9, 9));
        }
        petInfoPanel.add(status);

        petViewPanel.add(petInfoPanel, gbc);

        // 3rd column: gallery and like button
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.33;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setBackground(new Color(213, 202, 179));

        JButton galleryButton = new JButton();
        try {
            ImageIcon likeIcon = new ImageIcon("src/images/icons/open_gallery.png");
            Image img = likeIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            galleryButton.setIcon(new ImageIcon(scaledImg));
            galleryButton.setFocusable(false);
            galleryButton.setBackground(new Color(213, 202, 179));  // Set the background color
            galleryButton.setForeground(Color.WHITE);  // Set the text color
            galleryButton.setFocusPainted(false);
            galleryButton.setBorder(new EmptyBorder(5, 10, 5, 10));
            galleryButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            galleryButton.setText("Open Gallery");
        }
        galleryButton.addActionListener(e -> {
            PetGallery petGallery = new PetGallery(post);
        });

        buttonsPanel.add(galleryButton, BorderLayout.NORTH);

        JButton likeButton = new JButton();
        try {
            ImageIcon likeIcon = new ImageIcon("src/images/icons/like_icon.png");
            Image img = likeIcon.getImage();
            Image scaledImg = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            likeButton.setIcon(new ImageIcon(scaledImg));
            likeButton.setFocusable(false);
            likeButton.setBackground(new Color(213, 202, 179));  // Set the background color
            likeButton.setForeground(Color.WHITE);  // Set the text color
            likeButton.setFocusPainted(false);
            likeButton.setBorder(new EmptyBorder(5, 0, 5, 0));
            likeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } catch (Exception e) {
            likeButton.setText("Like");
        }

        JLabel likesLabel = new JLabel(": " + likes);

        JPanel likePanel = new JPanel();
        likePanel.add(likeButton);
        likePanel.add(likesLabel);
        likePanel.setBackground(new Color(213, 202, 179));

        likeButton.addActionListener(e -> {
            feedController.likePost(post);
            likes++;
            likesLabel.setText(": " + likes);
        });

        buttonsPanel.add(likePanel, BorderLayout.SOUTH);
        petViewPanel.add(buttonsPanel, gbc);

        mainPanel.add(petViewPanel);

        // Adding separator after pet view panel
        mainPanel.add(new JSeparator(JSeparator.HORIZONTAL));

        // Comments panel
        JPanel commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        //commentsPanel.setBackground(new Color(243, 230, 197));

        // Placeholder comments
        for (int i = 1; i <= 20; i++) { // Increased to 20 to better demonstrate scrolling
            JLabel commentLabel = new JLabel("Comment " + i);
            commentLabel.setBorder(new EmptyBorder(5, 5, 0, 5));
            if (i == 20) {
                commentLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            commentsPanel.add(commentLabel);
        }

        // Scrollable pane for comments
        JScrollPane commentsScrollPane = new JScrollPane(commentsPanel);
        commentsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        commentsScrollPane.setPreferredSize(new Dimension(400, 250)); // Set preferred size for demonstration
        commentsScrollPane.setBorder(new EmptyBorder(10, 5, 5, 5));
        //commentsScrollPane.setBackground(new Color(243, 230, 197));

        mainPanel.add(commentsScrollPane);

        // Buttons panel for liking, commenting and adopting
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        bottomPanel.setBackground(new Color(213, 202, 179));

        JPanel leftBottomPanel = new JPanel();
        leftBottomPanel.setBackground(new Color(213, 202, 179));
        JButton addCommentButton = new JButton("Add Comment");
        addCommentButton.setFocusable(false);
        addCommentButton.setBackground(new Color(142, 119, 87));  // Set the background color
        addCommentButton.setForeground(Color.WHITE); // Set the text color
        addCommentButton.setFocusPainted(false);
        addCommentButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        addCommentButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addCommentButton.addActionListener(e -> {
            // Open a new dialog/form to add a comment
        });
        leftBottomPanel.add(addCommentButton);

        JPanel rightBottomPanel = new JPanel();
        rightBottomPanel.setBackground(new Color(213, 202, 179));
        JButton adoptButton = new JButton("Adopt");
        adoptButton.setFocusable(false);
        adoptButton.setBackground(new Color(21, 179, 10));  // Set the background color
        adoptButton.setForeground(Color.WHITE); // Set the text color
        adoptButton.setFocusPainted(false);
        adoptButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        adoptButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adoptButton.addActionListener(e -> {
            ConfirmAdoptionWindow confirmAdoptionWindow = new ConfirmAdoptionWindow(user, post);
        });
        if (!post.getStatus().equalsIgnoreCase("Not adopted")) {
            adoptButton.setEnabled(false);
            adoptButton.setBackground(new Color(122, 193, 117));
            adoptButton.setText("<html><font color = white>Adopt</font></html>");
        } else {
            adoptButton.setEnabled(true);
        }
        rightBottomPanel.add(adoptButton);

        bottomPanel.add(leftBottomPanel, BorderLayout.WEST);
        bottomPanel.add(rightBottomPanel, BorderLayout.EAST);

        mainPanel.add(bottomPanel);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setWindowData(PostDTO post) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLayout(new BorderLayout());
        setTitle("Pet View");
        UIUtils.center(this);
        likes = feedController.getById(post.getId()).getLikes();
    }
}
