package view;

import domain.model.Animal;
import domain.model.Breed;
import domain.serializeddata.BreedList;
import dtos.PostDTO;

import javax.swing.*;
import java.awt.*;

public class AnimalDialog extends javax.swing.JDialog {
    public AnimalDialog(Frame parent, Animal animal, PostDTO post) {
        super(parent, "Animal", false);
        if (post == null) {
            setSize(600, 200);
            setTitle("Animal in registration process");
        } else {
            setSize(600, 400);
            setTitle("Edited animal");
        }
        setLocationRelativeTo(parent);
        Color petPanelColor = new Color(207, 198, 176, 234);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5; // Adjusted weighty to split space evenly

        // Create the original content panel
        JPanel originalContentPanel = createContentPanel(animal, petPanelColor);
        gbc.gridx = 0;
        gbc.gridy = 0; // First row
        add(originalContentPanel, gbc);

        // Create the duplicate content panel

        if (post != null) {
            JPanel duplicateContentPanel = createOriginalContentPanel(post, petPanelColor);
            gbc.gridy = 1; // Second row
            add(duplicateContentPanel, gbc);
        }

        getContentPane().setBackground(petPanelColor);
        setVisible(true);
    }

    private JPanel createContentPanel(Animal animal, Color petPanelColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.33;
        gbc.weighty = 1.0;

        JLabel petImageLabel;
        try {
            ImageIcon petImage = new ImageIcon(animal.getMultimedia().get(0));
            Image img = petImage.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            petImageLabel = new JLabel(new ImageIcon(scaledImg));
        } catch (Exception e) {
            // Image not found -> placeholder text
            petImageLabel = new JLabel("Picture not found");
            petImageLabel.setPreferredSize(new Dimension(150, 150));
        }
        petImageLabel.setPreferredSize(new Dimension(150, 150));
        petImageLabel.setHorizontalAlignment(JLabel.CENTER);
        petImageLabel.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        panel.add(petImageLabel, gbc);

        JPanel petInfoPanel = new JPanel();
        petInfoPanel.setLayout(new BoxLayout(petInfoPanel, BoxLayout.Y_AXIS));
        petInfoPanel.setBackground(petPanelColor);

        petInfoPanel.add(new JLabel(" "));
        petInfoPanel.add(new JLabel("Name: " + animal.getName()));
        BreedList breedList = new BreedList();
        Breed breed = breedList.getInstance().getById(animal.getBreedId());
        petInfoPanel.add(new JLabel("Breed: " + breed));
        petInfoPanel.add(new JLabel("Color: " + animal.getColour()));
        petInfoPanel.add(new JLabel("Date of birth: " + animal.getBorn().toString()));
        petInfoPanel.add(new JLabel("State: " +animal.getState()));
        petInfoPanel.add(new JLabel(" "));

        gbc.gridx = 1;
        panel.add(petInfoPanel, gbc);
        panel.setBackground(petPanelColor);

        return panel;
    }

    private JPanel createOriginalContentPanel(PostDTO post, Color petPanelColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.33;
        gbc.weighty = 1.0;

        panel.setBackground(petPanelColor);

        // Add the top label
        JLabel topLabel = new JLabel("ORIGINAL POST");
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topLabel.setOpaque(true);
        topLabel.setBackground(new Color(240, 230, 140)); // Example background color for label
        gbc.gridx = 0;
        gbc.gridy = 0; // Positioned at the top
        gbc.gridwidth = 2; // Span across two columns
        gbc.weighty = 0.1; // Less height for the label
        panel.add(topLabel, gbc);

        // Reset constraints for the next components
        gbc.gridwidth = 1; // Reset to default
        gbc.weighty = 1.0; // Default weighty
        gbc.insets = new Insets(10, 0, 10, 0); // Add some padding

        // Create the image label
        JLabel petImageLabel;
        try {
            ImageIcon petImage = new ImageIcon(post.getPicture());
            Image img = petImage.getImage();
            Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            petImageLabel = new JLabel(new ImageIcon(scaledImg));
        } catch (Exception e) {
            // Image not found -> placeholder text
            petImageLabel = new JLabel("Picture not found");
            petImageLabel.setPreferredSize(new Dimension(150, 150));
        }
        petImageLabel.setPreferredSize(new Dimension(150, 150));
        petImageLabel.setHorizontalAlignment(JLabel.CENTER);
        petImageLabel.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1; // Positioned below the label
        panel.add(petImageLabel, gbc);

        // Create the information panel
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
        gbc.gridy = 1; // Positioned next to the image label
        panel.add(petInfoPanel, gbc);

        return panel;
    }
}
