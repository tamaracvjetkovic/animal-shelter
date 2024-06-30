package view;

import dtos.PostDTO;

import javax.swing.*;
import java.awt.*;

public class PostDialog extends JDialog {
    public PostDialog(Frame parent, PostDTO post) {
        super(parent, "Animal", false);
        setSize(600, 200);
        setLocationRelativeTo(parent);
        Color petPanelColor = new Color(207, 198, 176, 234);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 0.33;
        gbc.weighty = 1.0;

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
        add(petImageLabel, gbc);

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
        add(petInfoPanel, gbc);
        getContentPane().setBackground(petPanelColor);
        setVisible(true);
    }
}
