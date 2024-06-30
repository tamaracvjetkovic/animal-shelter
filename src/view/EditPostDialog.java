package view;

import controller.FeedController;
import controller.RequestsController;
import domain.enums.AnimalState;
import domain.model.Address;
import domain.model.Animal;
import domain.model.User;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EditPostDialog extends JDialog {
    private JTextField nameField;
    private JTextField colorField;
    private JTextField pictureField;
    private JTextField cityField;
    private JTextField streetField;
    private JTextField numberField;
    private JComboBox<String> breedPicker;
    private JComboBox<String> speciesPicker;
    private JTextField birthDateField;
    private FeedController feedController;
    private RequestsController requestsController;

    public EditPostDialog(JFrame parent, User user, Animal animal, int postId) {
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
        nameField = new JTextField(animal.getName());
        nameField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel colorLabel = new JLabel("Color:");
        panel.add(colorLabel, gbc);
        gbc.gridx++;
        colorField = new JTextField(animal.getColour());
        colorField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(colorField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel pictureLabel = new JLabel("Picture URL:");
        panel.add(pictureLabel, gbc);
        gbc.gridx++;
        pictureField = new JTextField(animal.getMultimedia().get(0));
        pictureField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(pictureField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel picker1Label = new JLabel("Breed picker:");
        panel.add(picker1Label, gbc);
        gbc.gridx++;
        breedPicker = new JComboBox<>(feedController.getBreedsForPicker());
        breedPicker.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        breedPicker.setSelectedItem("Jez"); // for now hard coded
        panel.add(breedPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel picker2Label = new JLabel("Species picker:");
        panel.add(picker2Label, gbc);
        gbc.gridx++;
        speciesPicker = new JComboBox<>(feedController.getSpeciesForPicker());
        speciesPicker.setSelectedItem("Jezic Zje"); // for now hard coded
        speciesPicker.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(speciesPicker, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel birthDateLabel = new JLabel("Birth Date (yyyy-MM-dd):");
        panel.add(birthDateLabel, gbc);
        gbc.gridx++;
        birthDateField = new JTextField(animal.getBorn().toString());
        birthDateField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(birthDateField, gbc);

        Address address = feedController.getAddress(animal.getAddressId());

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cityLabel = new JLabel("City:");
        panel.add(cityLabel, gbc);
        gbc.gridx++;
        cityField = new JTextField(address.getCity());
        cityField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(cityField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel streetLabel = new JLabel("Street:");
        panel.add(streetLabel, gbc);
        gbc.gridx++;
        streetField = new JTextField(address.getStreet());
        streetField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(streetField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel numberLabel = new JLabel("Street Number:");
        panel.add(numberLabel, gbc);
        gbc.gridx++;
        numberField = new JTextField(address.getNumber());
        numberField.setPreferredSize(new Dimension(300, 25)); // Set preferred size to 300 pixels width and 25 pixels height
        panel.add(numberField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("Edit post");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String color = colorField.getText();
            String pictureUrl = pictureField.getText();
            String selectedPicker1 = (String) breedPicker.getSelectedItem();
            String selectedPicker2 = (String) speciesPicker.getSelectedItem();
            String birthDateText = birthDateField.getText();
            String city = cityField.getText();
            String street = streetField.getText();
            String number = numberField.getText();
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

            if(name.isEmpty() || color.isEmpty() || pictureUrl.isEmpty() || selectedPicker1.isEmpty() ||
                    selectedPicker2.isEmpty() || birthDateText.isEmpty() || city.isEmpty() ||
                    street.isEmpty() || number.isEmpty() || !number.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields with right values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                // Process the data here (e.g., save to database or display)
                int addressId = -1;

                if(city.equals(address.getCity()) && street.equals(address.getStreet()) &&
                    number.equals(String.valueOf(address.getNumber()))) {
                    addressId = animal.getAddressId();
                }
                else {
                    addressId = feedController.createAddress(city, street, number).getId();
                }

                requestsController.requestPostUpdate(user, postId, new Animal(0, name, color, birthDate, addressId,
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
