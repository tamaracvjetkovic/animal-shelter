package view;

import controller.FeedController;
import controller.RequestsController;
import domain.enums.AnimalState;
import domain.model.Animal;
import domain.model.User;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class CreatePostDialog extends JDialog {
    private JTextField nameField;
    private JTextField colorField;
    private JTextField pictureField;
    private JComboBox<String> breedPicker;
    private JComboBox<String> speciesPicker;
    private JTextField birthDateField;
    private FeedController feedController;
    private RequestsController requestsController;

    public CreatePostDialog(JFrame parent, User user) {
        super(parent, "Post Information", true);
        feedController = new FeedController();
        requestsController = new RequestsController();
        setSize(600, 500);  // Adjusted size for larger text fields
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel, gbc);
        gbc.gridx++;
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel colorLabel = new JLabel("Color:");
        panel.add(colorLabel, gbc);
        gbc.gridx++;
        colorField = new JTextField();
        colorField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(colorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel pictureLabel = new JLabel("Picture URL:");
        panel.add(pictureLabel, gbc);
        gbc.gridx++;
        pictureField = new JTextField();
        pictureField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(pictureField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel picker1Label = new JLabel("Breed picker:");
        panel.add(picker1Label, gbc);
        gbc.gridx++;
        breedPicker = new JComboBox<>(feedController.getBreedsForPicker());
        breedPicker.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(breedPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel picker2Label = new JLabel("Species picker:");
        panel.add(picker2Label, gbc);
        gbc.gridx++;
        speciesPicker = new JComboBox<>(feedController.getSpeciesForPicker());
        speciesPicker.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(speciesPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel birthDateLabel = new JLabel("Birth Date (yyyy-MM-dd):");
        panel.add(birthDateLabel, gbc);
        gbc.gridx++;
        birthDateField = new JTextField();
        birthDateField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(birthDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Create post");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String color = colorField.getText();
            String pictureUrl = pictureField.getText();
            String selectedPicker1 = (String) breedPicker.getSelectedItem();
            String selectedPicker2 = (String) speciesPicker.getSelectedItem();
            String birthDateText = birthDateField.getText();
            Date birthDate = null;

            // Parse date string to Date object
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                birthDate = dateFormat.parse(birthDateText);
            } catch (ParseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Invalid date format. Please enter yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(name.isEmpty() || color.isEmpty() || pictureUrl.isEmpty() || selectedPicker1.isEmpty() || selectedPicker2.isEmpty() || birthDateText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                // Process the data here (e.g., save to database or display)
                requestsController.requestPostRegistration(user, new Animal(0, name, color, birthDate, 0,
                        AnimalState.NOTADOPTED, new ArrayList<>(Arrays.asList(pictureUrl)),
                        feedController.getBreedId(selectedPicker1), feedController.getSpeciesId(selectedPicker2)));

                // Close the dialog
                dispose();
            }
        });
        panel.add(saveButton, gbc);

        getContentPane().add(panel, BorderLayout.CENTER);
    }
}
