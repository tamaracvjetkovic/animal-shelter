package view;

import dtos.PostDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PetGallery extends JFrame {

    public PetGallery(PostDTO post) {
        JFrame galleryFrame = new JFrame("Gallery");
        galleryFrame.setSize(253, 400);
        galleryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        galleryFrame.setLocationRelativeTo(null);

        JPanel galleryPanel = new JPanel();
        galleryPanel.setLayout(new BoxLayout(galleryPanel, BoxLayout.Y_AXIS));
        JScrollPane galleryScrollPane = new JScrollPane(galleryPanel);

        for (int i = 1; i <= 5; i++) { // Just a placeholder loop
            try {
                //ImageIcon galleryImage = new ImageIcon("src/images/pets/" + post.getName() + ".png");
                ImageIcon galleryImage = new ImageIcon("src/images/pets/mali_zje.png");
                Image img = galleryImage.getImage();
                Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel galleryImageLabel = new JLabel(new ImageIcon(scaledImg));
                galleryImageLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
                galleryPanel.add(galleryImageLabel);
            } catch (Exception ex) {
                galleryPanel.add(new JLabel("Picture not found"));
            }
        }

        galleryFrame.add(galleryScrollPane);
        galleryFrame.setVisible(true);
    }
}
