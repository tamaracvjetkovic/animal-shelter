package view;

import controller.FeedController;
import controller.RequestsController;
import domain.model.Comment;
import domain.model.User;
import dtos.PostDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;


public class AddCommentWindow extends JFrame {
    private RequestsController requestsController;
    private FeedController feedController;

    public AddCommentWindow(PetPostWindow currentPetPostWindow, User user, PostDTO post) {
        requestsController = new RequestsController();
        feedController = new FeedController();
        setWindowData();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(227, 215, 191));

        // Second panel with label and text field
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new BorderLayout(5, 5));
        secondPanel.setBorder(new EmptyBorder(0, 10, 5, 10));
        JLabel reasonLabel = new JLabel("  Enter comment:");

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
                String comment = textArea.getText();
                feedController.addComment(post, comment);
                dispose();
                showMessageDialog(null, "Comment has been added!");
                currentPetPostWindow.setComments(post);
                currentPetPostWindow.revalidate();
                currentPetPostWindow.repaint();
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
        mainPanel.add(new JLabel("  "));
        mainPanel.add(secondPanel);
        mainPanel.add(thirdPanel);

        // Add main panel to frame
        add(mainPanel);

        setVisible(true);
    }

    private void setWindowData() {
        setTitle("Add Comment");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
