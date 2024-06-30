package view;

import controller.LogInController;
import domain.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileDialog extends JDialog {
    private LogInController logInController;
    private User user;

    public UserProfileDialog(Frame parent, User user) {
        super(parent, "User Profile", true);
        logInController = new LogInController();
        this.user = user;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel usernameLabel = new JLabel("Username:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel lastNameLabel = new JLabel("Last Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel birthDateLabel = new JLabel("Birth Date:");
        JLabel profileTypeLabel = new JLabel("Profile Type:");

        JLabel usernameValue = new JLabel(user.getName());
        JLabel nameValue = new JLabel(user.getName());
        JLabel lastNameValue = new JLabel(user.getLastname());
        JLabel emailValue = new JLabel(user.getEmail());
        JLabel birthDateValue = new JLabel(user.getBirthDate().toString());
        JLabel profileTypeValue = new JLabel(user.getUserState().toString());

        JButton updatePasswordButton = new JButton("Update Password");
        updatePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePassword();
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        add(usernameValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        add(lastNameValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(birthDateLabel, gbc);
        gbc.gridx = 1;
        add(birthDateValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(profileTypeLabel, gbc);
        gbc.gridx = 1;
        add(profileTypeValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(updatePasswordButton, gbc);

        pack();
        setLocationRelativeTo(parent);
    }

    private void updatePassword() {
        JPasswordField passwordField = new JPasswordField(10);
        JPasswordField confirmPasswordField = new JPasswordField(10);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("New Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Update Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String newPassword = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
                logInController.changePassword(this.user, newPassword);
                JOptionPane.showMessageDialog(this, "Password updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Passwords do not match. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}