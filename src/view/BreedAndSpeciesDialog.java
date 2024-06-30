package view;

import domain.model.Breed;
import domain.model.Species;
import domain.serializeddata.BreedList;
import domain.serializeddata.SpeciesList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BreedAndSpeciesDialog extends JDialog {
    private JComboBox<String> speciesComboBox;
    private JTextField newSpeciesField;
    private JTextField breedField;
    private JButton okButton;
    private JButton cancelButton;
    private boolean submitted;

    // Mock method to simulate fetching species from the database
    private List<String> getSpeciesFromDatabase() {
        // In a real application, replace this with database query
        List<String> speciesList = new ArrayList<>();
        speciesList.add("Dog");
        speciesList.add("Cat");
        speciesList.add("Bird");
        return speciesList;
    }

    public BreedAndSpeciesDialog(Frame parent) {
        super(parent, "Species and Breed", true);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Label and ComboBox for Species
        JLabel speciesLabel = new JLabel("Species:");
        speciesComboBox = new JComboBox<>();
        speciesComboBox.addItem("Select Species");
        SpeciesList speciesList = new SpeciesList();
        for (Species species : speciesList.getInstance().getSpeciess()) {
            speciesComboBox.addItem(species.getName());
        }
        speciesComboBox.addItem("Other"); // Option to enter new species

        // Label and TextField for New Species
        JLabel newSpeciesLabel = new JLabel("New Species:");
        newSpeciesField = new JTextField(15);
        newSpeciesField.setEnabled(false); // Initially disabled

        // Enable New Species field only if "Other" is selected
        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (speciesComboBox.getSelectedItem().equals("Other")) {
                    newSpeciesField.setEnabled(true);
                } else {
                    newSpeciesField.setEnabled(false);
                    newSpeciesField.setText(""); // Clear the field if not used
                }
            }
        });

        // Label and TextField for Breed
        JLabel breedLabel = new JLabel("Breed:");
        breedField = new JTextField(15);

        // Buttons for OK and Cancel
        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSubmit();
            }
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // Layout setup
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(speciesLabel, gbc);

        gbc.gridx = 1;
        add(speciesComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(newSpeciesLabel, gbc);

        gbc.gridx = 1;
        add(newSpeciesField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(breedLabel, gbc);

        gbc.gridx = 1;
        add(breedField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(okButton, gbc);

        gbc.gridy = 4;
        add(cancelButton, gbc);

        pack();
        setLocationRelativeTo(parent);
    }

    private void onSubmit() {
        String species = speciesComboBox.getSelectedItem().toString();
        String newSpecies = newSpeciesField.getText().trim();
        String breed = breedField.getText().trim();

        if (species.equals("Select Species") && newSpecies.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a species or enter a new one.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (breed.isEmpty()) {
            breed = null;
        }

        if (species.equals("Other")) {
            species = newSpecies;
            SpeciesList speciesList = new SpeciesList();
            if (breed == null) {
                speciesList.getInstance().addSpecies(species, null);
            } else {
                Species s = speciesList.getInstance().addSpecies(species, null);
                BreedList breedList = new BreedList();
                Breed b = breedList.getInstance().addBreed(breed, s.getId());
                ArrayList<Integer> bIds = new ArrayList<>();
                bIds.add(b.getId());
                s.setBreeds(bIds);
            }
            submitted = true;
            JOptionPane.showMessageDialog(this, "Species: " + species + "\nBreed: " + breed);
            dispose();
        }
        SpeciesList speciesList = new SpeciesList();
        Species s = speciesList.getInstance().getByName(species);
        BreedList breedList = new BreedList();
        Breed b = breedList.getInstance().addBreed(breed, s.getId());
        ArrayList<Integer> bIds = s.getBreeds();
        bIds.add(b.getId());
        s.setBreeds(bIds);

        submitted = true;
        JOptionPane.showMessageDialog(this, "Species: " + species + "\nBreed: " + breed);
        dispose();
    }

    private void onCancel() {
        submitted = false;
        dispose();
    }

    public boolean isSubmitted() {
        return submitted;
    }
}

