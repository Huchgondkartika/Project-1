import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.*;

public class feedback extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField nameField, emailField;
    private JTextArea feedbackArea;

    public feedback() {
        setTitle("Feedback");
        setSize(701, 472);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 245));

        // 🔷 Title
        JLabel lblTitle = new JLabel("Feedback Form", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\feedback.png"), JLabel.LEFT);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 22));
        lblTitle.setBounds(230, 20, 300, 40);
        getContentPane().add(lblTitle);

        // 🔹 Name Label
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setIcon(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\name.png"));
        lblName.setBounds(100, 80, 150, 25);
        getContentPane().add(lblName);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.BOLD, 14));
        nameField.setBackground(new Color(255, 255, 128));
        nameField.setBounds(250, 80, 280, 25);
        getContentPane().add(nameField);

        // 🔹 Email Label
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblEmail.setIcon(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\email.png"));
        lblEmail.setBounds(100, 120, 150, 25);
        getContentPane().add(lblEmail);

        emailField = new JTextField();
        emailField.setFont(new Font("Tahoma", Font.BOLD, 14));
        emailField.setBackground(new Color(255, 255, 128));
        emailField.setBounds(250, 120, 280, 25);
        getContentPane().add(emailField);

        // 🔹 Feedback Label
        JLabel lblFeedback = new JLabel("Feedback:");
        lblFeedback.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblFeedback.setIcon(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\comment.png"));
        lblFeedback.setBounds(100, 160, 150, 25);
        getContentPane().add(lblFeedback);

        feedbackArea = new JTextArea();
        feedbackArea.setBackground(new Color(255, 255, 128));
        feedbackArea.setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scroll = new JScrollPane(feedbackArea);
        scroll.setBounds(250, 160, 280, 150);
        getContentPane().add(scroll);

        // 🔘 Submit Button with icon
        JButton btnSubmit = new JButton("Submit", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\submit.png"));
        btnSubmit.setBackground(new Color(192, 192, 192));
        btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSubmit.setBounds(200, 330, 130, 35);
        btnSubmit.addActionListener(e -> validateAndSubmit());
        getContentPane().add(btnSubmit);

        // 🔘 Clear Button with icon
        JButton btnClear = new JButton("Clear", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\clear.png"));
        btnClear.setBackground(new Color(192, 192, 192));
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClear.setBounds(350, 330, 130, 35);
        btnClear.addActionListener(e -> clearFields());
        getContentPane().add(btnClear);

        // 🌄 Background Image
        JLabel lblBackground = new JLabel(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png"));
        lblBackground.setBounds(10, -23, 677, 486);
        getContentPane().add(lblBackground);
    }

    // ✅ Validate all inputs before submitting
    private void validateAndSubmit() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String feedbackMsg = feedbackArea.getText().trim();

        // Name Validation — not empty, not only digits, can contain letters + digits
        if (name.isEmpty()) {
            showPopup("⚠️ Name cannot be empty.", Color.ORANGE);
            return;
        } else if (name.matches("\\d+")) {
            showPopup("⚠️ Name cannot contain only digits.", Color.ORANGE);
            return;
        }

        // Email Validation
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            showPopup("⚠️ Enter a valid email address.", Color.ORANGE);
            return;
        }

        // Feedback Validation
        if (feedbackMsg.isEmpty()) {
            showPopup("⚠️ Feedback cannot be empty.", Color.ORANGE);
            return;
        }

        // All validations passed — save feedback
        submitFeedback(name, email, feedbackMsg);
    }

    // ✅ Save feedback to database
    private void submitFeedback(String name, String email, String feedbackMsg) {
        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "INSERT INTO feedback (name, email, message) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, feedbackMsg);
            pst.executeUpdate();
            con.close();

            clearFields();
            showPopup("✅ Feedback submitted successfully!", new Color(50, 205, 50));
        } catch (Exception ex) {
            showPopup("❌ Error: " + ex.getMessage(), Color.RED);
        }
    }

    // ✅ Toast-style popup
    private void showPopup(String message, Color bgColor) {
        JWindow popup = new JWindow();
        popup.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JLabel label = new JLabel(message);
        label.setFont(new Font("Tahoma", Font.BOLD, 14));
        label.setForeground(Color.WHITE);
        panel.add(label);

        popup.add(panel);
        popup.pack();

        Dimension frameSize = getSize();
        Point location = getLocationOnScreen();
        int x = location.x + (frameSize.width - popup.getWidth()) / 2;
        int y = location.y + 20;
        popup.setLocation(x, y);

        popup.setVisible(true);
        new Timer(2000, e -> popup.dispose()).start();
    }

    private void clearFields() {
        nameField.setText("");
        emailField.setText("");
        feedbackArea.setText("");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new feedback().setVisible(true));
    }
}
