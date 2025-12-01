import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;

public class newStudent extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtStudentID, txtName, txtFatherName, txtContact, txtEmail;
    private JComboBox<String> comboCourse, comboBranch;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                newStudent frame = new newStudent();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public newStudent() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New Student Entry");
        setResizable(false);
        setSize(700, 560);
        setLocationRelativeTo(null);

        // ✅ Background Panel
        contentPane = new JPanel() {
            private Image bgImage = new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ✅ Header Label
        JLabel lblTitle = new JLabel("Add New Student");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(230, 25, 300, 40);
        contentPane.add(lblTitle);

        // === Labels ===
        JLabel lblStudentID = new JLabel("Student ID");
        lblStudentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStudentID.setBounds(150, 90, 120, 25);
        lblStudentID.setForeground(Color.black);
        contentPane.add(lblStudentID);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(150, 130, 120, 25);
        lblName.setForeground(Color.black);
        contentPane.add(lblName);

        JLabel lblFatherName = new JLabel("Father Name");
        lblFatherName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblFatherName.setBounds(150, 170, 120, 25);
        lblFatherName.setForeground(Color.black);
        contentPane.add(lblFatherName);

        JLabel lblCourse = new JLabel("Course Name");
        lblCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCourse.setBounds(150, 210, 120, 25);
        lblCourse.setForeground(Color.black);
        contentPane.add(lblCourse);

        JLabel lblBranch = new JLabel("Branch Name");
        lblBranch.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBranch.setBounds(150, 250, 120, 25);
        lblBranch.setForeground(Color.black);
        contentPane.add(lblBranch);

        JLabel lblContact = new JLabel("Contact No");
        lblContact.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblContact.setBounds(150, 290, 120, 25);
        lblContact.setForeground(Color.black);
        contentPane.add(lblContact);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblEmail.setBounds(150, 330, 120, 25);
        lblEmail.setForeground(Color.black);
        contentPane.add(lblEmail);

        // === Text Fields ===
        txtStudentID = new JTextField();
        txtStudentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtStudentID.setBounds(300, 90, 220, 30);
        txtStudentID.setBackground(Color.WHITE);
        contentPane.add(txtStudentID);

        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtName.setBounds(300, 130, 220, 30);
        txtName.setBackground(Color.WHITE);
        contentPane.add(txtName);

        txtFatherName = new JTextField();
        txtFatherName.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtFatherName.setBounds(300, 170, 220, 30);
        txtFatherName.setBackground(Color.WHITE);
        contentPane.add(txtFatherName);

        comboCourse = new JComboBox<>(new String[]{"B.Tech"});
        comboCourse.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "TY", "SY", "FY"}));
        comboCourse.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboCourse.setBackground(Color.LIGHT_GRAY);
        comboCourse.setBounds(300, 210, 220, 30);
        contentPane.add(comboCourse);

        comboBranch = new JComboBox<>(new String[]{"CSE", "IT", "Civil", "AIDS"});
        comboBranch.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBranch.setBackground(Color.LIGHT_GRAY);
        comboBranch.setBounds(300, 250, 220, 30);
        contentPane.add(comboBranch);

        txtContact = new JTextField();
        txtContact.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtContact.setBounds(300, 290, 220, 30);
        txtContact.setBackground(Color.WHITE);
        contentPane.add(txtContact);

        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtEmail.setBounds(300, 330, 220, 30);
        txtEmail.setBackground(Color.WHITE);
        contentPane.add(txtEmail);

        // === Buttons ===
        JButton btnSave = new JButton("Save",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\save-icon--1.png"));
        btnSave.setBackground(Color.WHITE);
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSave.setBounds(160, 410, 120, 35);
        contentPane.add(btnSave);

        JButton btnClose = new JButton("Close",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\red-x-mark-transparent-background-3.png"));
        btnClose.setBackground(Color.WHITE);
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClose.setBounds(400, 410, 120, 35);
        contentPane.add(btnClose);

        // === Save Button Action ===
        btnSave.addActionListener(e -> {
            String id = txtStudentID.getText().trim();
            String name = txtName.getText().trim();
            String father = txtFatherName.getText().trim();
            String course = comboCourse.getSelectedItem().toString();
            String branch = comboBranch.getSelectedItem().toString();
            String contact = txtContact.getText().trim();
            String email = txtEmail.getText().trim();

            // ====== VALIDATIONS ======
            if (id.isEmpty() || name.isEmpty() || father.isEmpty() || contact.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "⚠️ Please fill all fields!");
                return;
            }

            // Name must contain at least one letter
            if (!name.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "⚠️ Name must contain letters (not only digits)!");
                return;
            }

            // Father name must contain letters
            if (!father.matches(".*[a-zA-Z].*")) {
                JOptionPane.showMessageDialog(null, "⚠️ Father Name must contain letters (not only digits)!");
                return;
            }

            // Contact must be 10-digit number
            if (!contact.matches("\\d{10}")) {
                JOptionPane.showMessageDialog(null, "⚠️ Enter a valid 10-digit contact number!");
                return;
            }

            // Email validation
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
                JOptionPane.showMessageDialog(null, "⚠️ Enter a valid email address!");
                return;
            }
            // ==========================

            try {
                Connection con = ConnectionProvider.getCon();

                PreparedStatement checkStmt = con.prepareStatement(
                        "SELECT studentID FROM student WHERE studentID = ?");
                checkStmt.setString(1, id);
                ResultSet rs = checkStmt.executeQuery();

                if (!rs.next()) {
                    PreparedStatement pst = con.prepareStatement(
                            "INSERT INTO student (studentID, name, fatherName, courseName, branchName, contactNo, email) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    pst.setString(1, id);
                    pst.setString(2, name);
                    pst.setString(3, father);
                    pst.setString(4, course);
                    pst.setString(5, branch);
                    pst.setString(6, contact);
                    pst.setString(7, email);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "✅ Student data saved successfully!");
                    con.close();
                } else {
                    JOptionPane.showMessageDialog(null, "⚠️ Student ID already exists!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "❌ Error while saving data: " + ex.getMessage());
            }
        });

        // === Close Button ===
        btnClose.addActionListener(e -> setVisible(false));
    }
}
