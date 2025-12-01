import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField passwordField;
    private JTextField textField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login frame = new Login();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Login() {
        // ✅ Set simple page title
        setTitle("Library Management System - Login Page");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1249, 723);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Background image
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\Login background.PNG"));
        background.setBounds(0, 0, 1249, 723);
        contentPane.add(background);

        // Username label
        JLabel lblUser = new JLabel("UserName");
        lblUser.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblUser.setBounds(609, 299, 111, 30);
        background.add(lblUser);

        // Password label
        JLabel lblPass = new JLabel("Password");
        lblPass.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPass.setBounds(615, 367, 91, 30);
        background.add(lblPass);

        // Username text field
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField.setBackground(new Color(255, 255, 128));
        textField.setBounds(751, 293, 200, 36);
        background.add(textField);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.BOLD, 14));
        passwordField.setBackground(new Color(255, 255, 128));
        passwordField.setBounds(751, 366, 200, 33);
        background.add(passwordField);

        // Login button
        JButton btnLogin = new JButton("Login", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\login.png"));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogin.setBounds(628, 429, 120, 35);
        background.add(btnLogin);

        btnLogin.addActionListener(e -> {
            String username = textField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (username.equals("admin") && password.equals("admin")) {
                JOptionPane.showMessageDialog(null, "✅ Login successful!");
                setVisible(false);
                new Home().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "❌ Incorrect Username or Password");
            }
        });

        // Close button
        JButton btnClose = new JButton("Close", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\red-x-mark-transparent-background-3.png"));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClose.setBounds(815, 429, 120, 35);
        btnClose.addActionListener(e -> System.exit(0));
        background.add(btnClose);
    }
}
