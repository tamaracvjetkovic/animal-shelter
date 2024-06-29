package view;

import controller.LogInController;
import domain.enums.UserState;
import domain.model.User;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterWindow extends JFrame {
    public RegisterWindow() {
        setTitle("Registration Form");

        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel with BoxLayout for vertical stacking
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Username panel
        JPanel usernamePanel = createTextPanel("Username:");

        // Password panel
        JPanel passwordPanel = createPasswordPanel("Password:");

        // Name panel
        JPanel namePanel = createTextPanel("Name:");

        // Last name panel
        JPanel lastNamePanel = createTextPanel("Last Name:");

        // Email panel
        JPanel emailPanel = createTextPanel("Email:");

        // Birthdate panel
        JPanel birthdatePanel = createTextPanel("Birthdate (yyyy-MM-dd):");

        // Submit button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton submitButton = new JButton("Register");
        buttonPanel.add(submitButton);

        // Add all panels to the main panel
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(namePanel);
        mainPanel.add(lastNamePanel);
        mainPanel.add(emailPanel);
        mainPanel.add(birthdatePanel);
        mainPanel.add(buttonPanel);

        // Add main panel to the frame
        add(mainPanel);

        // Adjust frame size based on contents
        pack();

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Set frame visible
        setVisible(true);

        // Action listener for the submit button
        submitButton.addActionListener(e -> {
            // Get values from text fields
            String username = getUsernameFromPanel(usernamePanel);
            String password = getPasswordFromPanel(passwordPanel);
            String name = getTextFromPanel(namePanel);
            String lastName = getTextFromPanel(lastNamePanel);
            String email = getTextFromPanel(emailPanel);
            Date birthdate = getDateFromPanel(birthdatePanel);

            // Validate and process registration (for example, print values)
            if (validateInputs(username, password, name, lastName, email, birthdate)) {
                LogInController controller = new LogInController();
                User user = controller.signUp(name,lastName,email,birthdate,username,password);
                if(user == null){
                    JOptionPane.showMessageDialog(this, "Choose another username.");
                }else{
                    if(user.getUserState() == UserState.MEMBER){

                    }else if(user.getUserState() == UserState.VOLUNTEER){

                    }else{
                        MainWindow mainWindow = new MainWindow();
                    }
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill out all fields correctly.");
            }
        });
    }

    // Method to create a text panel with a labeled text field
    private JPanel createTextPanel(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(20); // Adjust size as needed
        panel.add(label);
        panel.add(textField);
        return panel;
    }

    // Method to create a password panel with a labeled password field
    private JPanel createPasswordPanel(String labelText) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText);
        JPasswordField passwordField = new JPasswordField(20); // Adjust size as needed
        panel.add(label);
        panel.add(passwordField);
        return panel;
    }

    // Method to get text from a text panel
    private String getTextFromPanel(JPanel panel) {
        Component[] components = panel.getComponents();
        if (components.length == 2 && components[1] instanceof JTextField) {
            JTextField textField = (JTextField) components[1];
            return textField.getText();
        }
        return null; // Handle error case
    }

    // Method to get password from a password panel
    private String getPasswordFromPanel(JPanel panel) {
        Component[] components = panel.getComponents();
        if (components.length == 2 && components[1] instanceof JPasswordField) {
            JPasswordField passwordField = (JPasswordField) components[1];
            return new String(passwordField.getPassword());
        }
        return null; // Handle error case
    }

    // Method to get username from a username panel
    private String getUsernameFromPanel(JPanel panel) {
        return getTextFromPanel(panel);
    }

    // Method to get date from a date panel
    private Date getDateFromPanel(JPanel panel) {
        String dateString = getTextFromPanel(panel);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception
        }
        return null; // Handle error case
    }

    // Method to validate inputs (basic validation)
    private boolean validateInputs(String username, String password, String name,
                                   String lastName, String email, Date birthdate) {
        return username != null && !username.isEmpty()
                && password != null && !password.isEmpty()
                && name != null && !name.isEmpty()
                && lastName != null && !lastName.isEmpty()
                && email != null && !email.isEmpty()
                && birthdate != null;
    }
}
