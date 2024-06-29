package view;

import controller.FeedController;
import controller.RequestsController;
import domain.enums.AnimalState;
import domain.enums.RequestState;
import domain.enums.RequestType;
import domain.serializeddata.UsersList;
import domain.model.Request;
import dtos.PostDTO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VolunteerWindow extends JFrame {
    private FeedController feedController;
    private RequestsController requestsController;
    public VolunteerWindow() {
        // Set the title of the frame
        setTitle("Volunteer");
        feedController = new FeedController();
        requestsController = new RequestsController();
        // Set default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        setSize(450, 580);
        JButton signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(e -> {
            // Handle sign out action (for example, show a confirmation dialog)
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to sign out?",
                                                         "Confirm Sign Out", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // Perform sign-out logic, e.g., close the application or log out the user
                MainWindow mainWindow = new MainWindow();
                dispose();
            }
        });

        // Create a panel for the "Sign Out" button
        JPanel signOutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        signOutPanel.add(signOutButton);

        // Add the sign out panel to the top of the frame
        add(signOutPanel, BorderLayout.NORTH);
        // Create the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        // Create tabs with panels
        JPanel pets = createTabPanel("Pets Feed");
        setUpPetsPanel(pets);

        JPanel requests = createTabPanel("Requests");
        JPanel panel3 = createTabPanel("This is the content of Tab 3");

        // Add panels to the tabbed pane
        tabbedPane.addTab("Animals", pets);
        tabbedPane.addTab("Requests", requests);
        tabbedPane.addTab("Tab 3", panel3);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create a panel for each tab
    private JPanel createTabPanel(String labelText) {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(labelText);
        panel.add(label);
        panel.setSize(450, 580);
        return panel;
    }
    private static void center(Component component) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = component.getSize().width;
        int h = component.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;

        // set the new location for the component
        component.setLocation(x, y);
    }

    private void setUpPetsPanel(JPanel pets){
        JPanel petPanel = new JPanel();
        petPanel.setLayout(new BoxLayout(petPanel, BoxLayout.Y_AXIS));
        Color petPanelColor = new Color(207, 198, 176, 234);

        for (PostDTO post : feedController.getAllPostsWithAnimalsAndBreeds()) {
            JPanel petPostPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.33;
            gbc.weighty = 1.0;

            // pet image
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
            petPostPanel.add(petImageLabel, gbc);

            // panel for pet info
            JPanel petInfoPanel = new JPanel();
            petInfoPanel.setLayout(new BoxLayout(petInfoPanel, BoxLayout.Y_AXIS));
            petInfoPanel.setBackground(petPanelColor);

            petInfoPanel.add(new JLabel(" "));
            petInfoPanel.add(new JLabel("Name: " + post.getName()));
            petInfoPanel.add(new JLabel("Breed: " + post.getBreed()));
            petInfoPanel.add(new JLabel("Color: " + post.getColor()));
            petInfoPanel.add(new JLabel("Date: " + post.getDate()));
            petInfoPanel.add(new JLabel(" "));
            petInfoPanel.add(new JLabel("Status: " + post.getStatus()));
            gbc.gridx = 1;
            petPostPanel.add(petInfoPanel, gbc);

            // Create a line separator
            JPanel lineSeparator = new JPanel();
            lineSeparator.setBackground(Color.GRAY);
            lineSeparator.setPreferredSize(new Dimension(0, 1)); // Height 2px, width 0 to be adjusted by layout
            gbc.gridy = 1;
            petPanel.add(lineSeparator, gbc);

            petPostPanel.setBorder(new EmptyBorder(7, 0, 7, 0));
            petPostPanel.setBackground(petPanelColor);

            petPanel.add(petPostPanel);
        }

        JScrollPane scrollPane = new JScrollPane(petPanel);

        // add the scrollable panel to the frame
        pets.setLayout(new BorderLayout());
        pets.add(scrollPane, BorderLayout.CENTER);
        center(pets);
        pets.setVisible(true);
    }
    private void setUpRequestsPanel(JPanel requests){
        JPanel requstsPanel = new JPanel();
        requstsPanel.setLayout(new BoxLayout(requstsPanel, BoxLayout.Y_AXIS));
        Color petPanelColor = new Color(207, 198, 176, 234);
        UsersList usersList = new UsersList();
        for (Request req : requestsController.getPendingRequests()) {
            JPanel requestPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 0.33;
            gbc.weighty = 1.0;
            gbc.gridx = 0;
            requstsPanel.add(createButtonPanel(req), gbc);
            // panel for pet info
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.setBackground(petPanelColor);

            infoPanel.add(new JLabel(" "));
            infoPanel.add(new JLabel("Type: " + req.getType()));
            infoPanel.add(new JLabel("User: "+  usersList.getById(req.getUserId()).toString()));
            switch (req.getType()) {
                case VOLUNTEERING:
                     break; // terminates the case
                case ADOPTION:
                case TEMPORARY_CARE:
                    PostDTO post = feedController.getById(req.getPostId());
                    infoPanel.add(new JLabel(" "));
                    infoPanel.add(new JLabel("Name: " + post.getName()));
                    infoPanel.add(new JLabel("Breed: " + post.getBreed()));
                    infoPanel.add(new JLabel("Color: " + post.getColor()));
                    infoPanel.add(new JLabel("Date: " + post.getDate()));
                    infoPanel.add(new JLabel(" "));
                    infoPanel.add(new JLabel("Status: " + post.getStatus()));
                    // code to be executed if expression equals value2
                    break;
                // you can have any number of case statements
                case ANIMAL_REGISTRATION:
                    // code to be executed if expression doesn't match any case
                     break;
                case POST_EDITING:
                     break;
                default:
                    break;
            }
            gbc.gridx = 1;
            requestPanel.add(infoPanel, gbc);

            // Create a line separator
            JPanel lineSeparator = new JPanel();
            lineSeparator.setBackground(Color.GRAY);
            lineSeparator.setPreferredSize(new Dimension(0, 1)); // Height 2px, width 0 to be adjusted by layout
            gbc.gridy = 1;
            requstsPanel.add(lineSeparator, gbc);

            requestPanel.setBorder(new EmptyBorder(7, 0, 7, 0));
            requestPanel.setBackground(petPanelColor);

            requstsPanel.add(requestPanel);
        }

        JScrollPane scrollPane = new JScrollPane(requstsPanel);

        // add the scrollable panel to the frame
        requests.setLayout(new BorderLayout());
        requests.add(scrollPane, BorderLayout.CENTER);
        center(requests);
        requests.setVisible(true);
    }
    private JPanel createButtonPanel(Request r) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical box layout

        // Create the "Approve" button
        JButton approveButton = new JButton("Approve");
        approveButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        approveButton.setToolTipText("Click to approve");

        // Add action listener to the "Approve" button
        approveButton.addActionListener(e -> {
            if(r.getType() == RequestType.VOLUNTEERING){
                r.increaseApproved();
            }else{
                r.setState(RequestState.APPROVED);
                if(r.getType() == RequestType.ADOPTION){
                    feedController.getById(r.getPostId()).setStatus(AnimalState.ADOPTED.toString());
                }else if(r.getType() == RequestType.TEMPORARY_CARE){
                    feedController.getById(r.getPostId()).setStatus(AnimalState.INFOSTERCARE.toString());
                }
            }
            JOptionPane.showMessageDialog(this, "You have approved!");
        });

        // Create the "Reject" button
        JButton rejectButton = new JButton("Reject");
        rejectButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the button
        rejectButton.setToolTipText("Click to reject");

        // Add action listener to the "Reject" button
        rejectButton.addActionListener(e -> {
            if(r.getType() == RequestType.VOLUNTEERING){
                r.increaseRejected();
            }else{
                r.setState(RequestState.REJECTED);
            }
            JOptionPane.showMessageDialog(this, "You have rejected!");
        });

        // Add spacing and buttons to the panel
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Top spacing
        panel.add(approveButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing between buttons
        panel.add(rejectButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Bottom spacing

        return panel;
    }
}