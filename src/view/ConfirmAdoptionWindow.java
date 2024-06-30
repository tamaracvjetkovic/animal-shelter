package view;

import controller.FeedController;
import controller.RequestsController;
import domain.enums.UserState;
import domain.model.Post;
import domain.model.User;
import dtos.PostDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;


public class ConfirmAdoptionWindow extends JFrame {
    private RequestsController requestsController;
    private FeedController feedController;

    public ConfirmAdoptionWindow(User user, PostDTO post) {
        requestsController = new RequestsController();
        feedController = new FeedController();
        setWindowData();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(227, 215, 191));

        // First panel with two columns
        JPanel firstPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        JRadioButton adoptRadioButton = new JRadioButton("Adopt");
        JRadioButton tempCareRadioButton = new JRadioButton("Temporary care");
        adoptRadioButton.setBackground(new Color(227, 215, 191));
        tempCareRadioButton.setBackground(new Color(227, 215, 191));

        // Button group to ensure only one radio button can be selected at a time
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(adoptRadioButton);
        radioButtonGroup.add(tempCareRadioButton);

        // By default, select the adopt radio button
        adoptRadioButton.setSelected(true);

        firstPanel.add(adoptRadioButton);
        firstPanel.add(tempCareRadioButton);
        firstPanel.setBorder(new EmptyBorder(20, 50, 10, 30));
        firstPanel.setBackground(new Color(227, 215, 191));

        // Second panel with label and text field
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout(5, 5));
        secondPanel.setBorder(new EmptyBorder(0, 10, 5, 10));
        JLabel reasonLabel = new JLabel("  Why would you like to take care of this animal?");

        JTextArea textArea = new JTextArea(10, 30);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        //textArea.setBackground(new Color(244, 241, 232));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        secondPanel.add(reasonLabel, BorderLayout.NORTH);
        secondPanel.add(textArea, BorderLayout.CENTER);
        secondPanel.setBackground(new Color(227, 215, 191));

        // Third panel with confirm button
        JPanel thirdPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton confirmButton = new JButton("Confirm");
        confirmButton.setFocusable(false);
        confirmButton.setBackground(new Color(27, 186, 46));  // Set the background color
        confirmButton.setForeground(Color.WHITE); // Set the text color
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Post currentPost = feedController.getById(post.getId());
                String reason = textArea.getText();

                if (adoptRadioButton.isSelected()) {
                    if(user.getUserState() == UserState.MEMBER){
                        requestsController.requestAnimalAdoption(user, currentPost, reason);
                    } else if(user.getUserState() == UserState.VOLUNTEER){
                        requestsController.adopt(user, currentPost);
                    }
                    showMessageDialog(null, "Request for adoption is sent!\nVolunteers will review your request as soon as possible!\n\nThank you for considering this little buddy! :)");
                    dispose();
                }
                else {
                    if(user.getUserState() == UserState.MEMBER){
                        requestsController.requestAnimalTemporaryCare(user, currentPost, reason);
                    } else if(user.getUserState() == UserState.VOLUNTEER){
                        requestsController.fosterCare(user, currentPost);
                    }
                    showMessageDialog(null, "Request for temporary care is sent!\nVolunteers will review your request as soon as possible!\n\nThank you for considering this little buddy! :)");
                    dispose();
                }

            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setBackground(new Color(239, 145, 0));  // Set the background color
        cancelButton.setForeground(Color.WHITE); // Set the text color
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new EmptyBorder(6, 11, 6, 11));
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        thirdPanel.add(confirmButton);
        thirdPanel.add(new JLabel("  "));
        thirdPanel.add(cancelButton);
        thirdPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        thirdPanel.setBackground(new Color(227, 215, 191));

        // Add panels to main panel
        mainPanel.add(firstPanel);
        mainPanel.add(new JLabel(" "));
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private void setWindowData() {
        setTitle("Confirm Adoption");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
