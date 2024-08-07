package view;

import controller.LogInController;
import domain.enums.UserState;
import domain.model.User;

import javax.sound.midi.MetaMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginWindow extends JFrame {
    public LoginWindow() {
        setTitle("Login Form");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the size of the frame
        setSize(300, 200);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                MainWindow mainWindow = new MainWindow();
            }
        });
        // Initialize the login form
        initializeLoginForm();
    }

    // Method to initialize the login form
    private void initializeLoginForm() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel for the form components (FlowLayout by default)
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Labels and text fields
        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField(15);
        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField(15);

        // Add components to the form panel
        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passLabel);
        formPanel.add(passField);

        // Panel for the login button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton loginButton = new JButton("Login");
        buttonPanel.add(loginButton);

        // Registration section
        JLabel registerPrompt = new JLabel("Do not have an account? ");
        JButton registerButton = new JButton("Register here");

        // Panel for registration
        JPanel registerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        registerPanel.add(registerPrompt);
        registerPanel.add(registerButton);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(buttonPanel);
        buttonsPanel.add(registerPanel);
        // Add form panel to the center of the main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add button panel to the bottom of the main panel
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        // Add register panel below the button panel
       // mainPanel.add(registerPanel, BorderLayout.PAGE_END);

        // Add main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);

        // Action listener for the login button
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.");
            }
            else {
             //TO-DO: LOGIN FUNCTION
                LogInController controller = new LogInController();
                User user = controller.logIn(username,password);

                if (user == null) {
                    JOptionPane.showMessageDialog(this, "Credentials are incorrect.");
                }
                else {
                    if (user.getUserState() == UserState.MEMBER) {
                        MemberWindow memberWindow = new MemberWindow(user);
                    }
                    else if (user.getUserState() == UserState.VOLUNTEER) {
                        VolunteerWindow volunteerWindow = new VolunteerWindow(user);
                    }
                    else {
                        MainWindow mainWindow = new MainWindow();
                    }

                    this.dispose();
                }
            }
        });

        // Action listener for the register button
        registerButton.addActionListener(e -> {
            // In a real application, you might open a registration form
            RegisterWindow registerWindow = new RegisterWindow();
            this.dispose();
        });
    }
}
