import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.text.SimpleDateFormat;

public class issueBook extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField_bookID;
    private JTextField textField_studentID;

    // 🔹 Constructor Overload: To receive BookID from search page
    public issueBook(String bookID) {
        this();
        textField_bookID.setText(bookID);
        textField_bookID.setEditable(false); // lock it to prevent user editing
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                issueBook frame = new issueBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public issueBook() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 715, 483);
        setTitle("Issue Book");
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Issue Book");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(280, 40, 200, 40);
        contentPane.add(lblTitle);

        JLabel lblBookID = new JLabel("Book ID");
        lblBookID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookID.setBounds(205, 122, 78, 23);
        contentPane.add(lblBookID);

        JLabel lblStudentID = new JLabel("Student ID");
        lblStudentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStudentID.setBounds(205, 173, 88, 23);
        contentPane.add(lblStudentID);

        JLabel lblIssueDate = new JLabel("Issue Date");
        lblIssueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblIssueDate.setBounds(205, 221, 78, 23);
        contentPane.add(lblIssueDate);

        JLabel lblDueDate = new JLabel("Due Date");
        lblDueDate.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDueDate.setBounds(205, 265, 78, 23);
        contentPane.add(lblDueDate);

        textField_bookID = new JTextField();
        textField_bookID.setBackground(new Color(255, 255, 128));
        textField_bookID.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_bookID.setBounds(321, 121, 199, 28);
        contentPane.add(textField_bookID);

        textField_studentID = new JTextField();
        textField_studentID.setBackground(new Color(255, 255, 128));
        textField_studentID.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_studentID.setBounds(321, 171, 199, 28);
        contentPane.add(textField_studentID);

        JDateChooser issueDateChooser = new JDateChooser();
        issueDateChooser.setBounds(321, 221, 199, 28);
        contentPane.add(issueDateChooser);

        JDateChooser dueDateChooser = new JDateChooser();
        dueDateChooser.setBounds(320, 260, 200, 28);
        contentPane.add(dueDateChooser);

        JButton btnIssue = new JButton("Issue",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\issue book.png"));
        btnIssue.setBackground(new Color(192, 192, 192));
        btnIssue.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIssue.setBounds(292, 314, 103, 28);
        contentPane.add(btnIssue);

        JButton btnClose = new JButton("Close",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\red-x-mark-transparent-background-3.png"));
        btnClose.setBackground(new Color(192, 192, 192));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClose.setBounds(417, 314, 103, 28);
        contentPane.add(btnClose);

        btnIssue.addActionListener(e -> {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            String bookID = textField_bookID.getText().trim();
            String studentID = textField_studentID.getText().trim();

            if (bookID.isEmpty() || studentID.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both Book ID and Student ID!");
                return;
            }

            if (issueDateChooser.getDate() == null || dueDateChooser.getDate() == null) {
                JOptionPane.showMessageDialog(null, "Please select both issue and due dates!");
                return;
            }

            String issueDate = dFormat.format(issueDateChooser.getDate());
            String dueDate = dFormat.format(dueDateChooser.getDate());
            String returnBook = "No";

            try {
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();

                ResultSet rsBook = st.executeQuery("SELECT * FROM book1 WHERE bookID='" + bookID + "'");
                if (rsBook.next()) {
                    ResultSet rsStudent = st.executeQuery("SELECT * FROM student WHERE studentID='" + studentID + "'");
                    if (rsStudent.next()) {
                        st.executeUpdate("INSERT INTO issue (bookID, studentID, issueDate, dueDate, returnBook) VALUES ('" +
                                bookID + "', '" + studentID + "', '" + issueDate + "', '" + dueDate + "', '" + returnBook + "')");

                        JOptionPane.showMessageDialog(null, "📚 Book successfully issued!");
                        setVisible(false);
                        new issueBook().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Incorrect Student ID");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "❌ Incorrect Book ID");
                }

                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "⚠️ Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        btnClose.addActionListener(e -> setVisible(false));

        JLabel lblBackground = new JLabel(
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png"));
        lblBackground.setBounds(-15, -28, 733, 537);
        contentPane.add(lblBackground);
    }
}
