package view;

import domain.model.Address;
import domain.model.Animal;
import domain.model.Post;
import domain.model.Species;
import domain.serializeddata.AddressList;
import domain.serializeddata.AnimalList;
import domain.serializeddata.PostList;
import domain.serializeddata.SpeciesList;
import dtos.PostDTO;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        PostList postList = new PostList();
        Post p = postList.getInstance().getById(post.getId());
        AnimalList animalList = new AnimalList();
        Animal a = animalList.getInstance().getAnimal(p.getAnimalId());

        petInfoPanel.add(new JLabel("Pet name: " + post.getName()));
        SpeciesList sList = new SpeciesList();
        Species s = sList.getInstance().getSpecies(a.getSpeciesId());
        petInfoPanel.add(new JLabel("Species: " + s.getName()));
        petInfoPanel.add(new JLabel("Breed: " + post.getBreed()));
        petInfoPanel.add(new JLabel("Color: " + post.getColor()));
        petInfoPanel.add(new JLabel("Date of birth: " + post.getDate()));

        AddressList addressList = new AddressList();
        Address ads = addressList.getInstance().getAddress(a.getAddressId());
        petInfoPanel.add(new JLabel("Address: " + ads.getCity() + " " + ads.getStreet() + " " + ads.getNumber()));
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
