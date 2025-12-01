import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class returnBook extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_bookID;
    private JTextField textField_studentID;
    private JTextField textField_issueDate;
    private JTextField textField_dueDate;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                returnBook frame = new returnBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public returnBook() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 723, 514);
        setTitle("Return Book");
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 🏷️ Title Label
        JLabel lblTitle = new JLabel("Return Book");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 26));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(270, 40, 250, 40);
        contentPane.add(lblTitle);

        JLabel lblBookID = new JLabel("Book ID");
        lblBookID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookID.setBounds(145, 125, 100, 22);
        contentPane.add(lblBookID);

        JLabel lblStudentID = new JLabel("Student ID");
        lblStudentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStudentID.setBounds(145, 178, 100, 21);
        contentPane.add(lblStudentID);

        JLabel lblIssueDate = new JLabel("Issue Date");
        lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIssueDate.setBounds(146, 231, 100, 31);
        contentPane.add(lblIssueDate);

        JLabel lblDueDate = new JLabel("Due Date");
        lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDueDate.setBounds(146, 284, 100, 22);
        contentPane.add(lblDueDate);

        textField_bookID = new JTextField();
        textField_bookID.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_bookID.setBackground(new Color(255, 255, 128));
        textField_bookID.setBounds(260, 123, 200, 28);
        textField_bookID.setEditable(false);
        contentPane.add(textField_bookID);

        textField_studentID = new JTextField();
        textField_studentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_studentID.setBackground(new Color(255, 255, 128));
        textField_studentID.setBounds(260, 176, 200, 26);
        contentPane.add(textField_studentID);

        textField_issueDate = new JTextField();
        textField_issueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_issueDate.setBackground(new Color(255, 255, 128));
        textField_issueDate.setBounds(260, 233, 200, 28);
        textField_issueDate.setEditable(false);
        contentPane.add(textField_issueDate);

        textField_dueDate = new JTextField();
        textField_dueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_dueDate.setBackground(new Color(255, 255, 128));
        textField_dueDate.setBounds(260, 282, 200, 28);
        textField_dueDate.setEditable(false);
        contentPane.add(textField_dueDate);

        // 🔍 Search Button
        JButton btnSearch = new JButton("Search",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\search.png"));
        btnSearch.setBackground(new Color(192, 192, 192));
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSearch.setBounds(480, 170, 120, 30);
        btnSearch.addActionListener(e -> {
            String studentID = textField_studentID.getText().trim();

            if (studentID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Student ID");
                return;
            }

            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement pst = con.prepareStatement(
                        "SELECT issue.bookID, issue.issueDate, issue.dueDate " +
                                "FROM issue WHERE issue.studentID = ? AND issue.returnBook='No'");
                pst.setString(1, studentID);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    textField_bookID.setText(rs.getString("bookID"));
                    textField_issueDate.setText(rs.getString("issueDate"));
                    textField_dueDate.setText(rs.getString("dueDate"));
                } else {
                    JOptionPane.showMessageDialog(null, "No issued book found for this Student ID");
                }
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        contentPane.add(btnSearch);

        // ✅ Return Button
        JButton btnReturn = new JButton("Return",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\return book png.png"));
        btnReturn.addActionListener(e -> {
            String bookID = textField_bookID.getText();
            String studentID = textField_studentID.getText();

            if (bookID.isEmpty() || studentID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Student ID and search issued book first.");
                return;
            }

            try {
                Connection con = ConnectionProvider.getCon();
                PreparedStatement pst = con.prepareStatement(
                        "UPDATE issue SET returnBook='Yes' WHERE studentID=? AND bookID=?");
                pst.setString(1, studentID);
                pst.setString(2, bookID);
                int updated = pst.executeUpdate();

                if (updated > 0) {
                    JOptionPane.showMessageDialog(null, "Book successfully returned!");
                    textField_bookID.setText("");
                    textField_studentID.setText("");
                    textField_issueDate.setText("");
                    textField_dueDate.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Return failed! Please check IDs.");
                }

                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        btnReturn.setBackground(new Color(192, 192, 192));
        btnReturn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReturn.setBounds(206, 360, 120, 28);
        contentPane.add(btnReturn);

        // ❌ Close Button
        JButton btnClose = new JButton("Close",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\red-x-mark-transparent-background-3.png"));
        btnClose.addActionListener(e -> setVisible(false));
        btnClose.setBackground(new Color(192, 192, 192));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClose.setBounds(355, 360, 120, 28);
        contentPane.add(btnClose);

        // Background Image (added last so buttons stay visible)
        JLabel lblBg = new JLabel(
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png"));
        lblBg.setBounds(-13, -21, 729, 510);
        contentPane.add(lblBg);
    }
}
