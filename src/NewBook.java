import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class NewBook extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textField;       // Book ID
    private JTextField textField_1;     // Name
    private JTextField textField_2;     // Publisher/Author
    private JTextField textField_3;     // Price
    private JTextField textField_4;     // Publisher Year
    private JTextField textField_qty;   // Quantity

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewBook frame = new NewBook();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewBook() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Frame size tuned to fit components + background
        setSize(740, 520);
        setTitle("New Book");
        setResizable(false);
        setLocationRelativeTo(null);

        // Create background label and use it as the main container
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png"));
        background.setLayout(null);
        background.setBounds(0, 0, 740, 520);
        setContentPane(background);

        // ======== PAGE TITLE =========
        JLabel lblTitle = new JLabel("Add New Book");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLACK);
        lblTitle.setBounds(260, 20, 300, 40);
        background.add(lblTitle);
        // ==============================

        JLabel lblBookID = new JLabel("Book ID");
        lblBookID.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblBookID.setBounds(130, 90, 100, 24);
        background.add(lblBookID);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblName.setBounds(130, 130, 100, 24);
        background.add(lblName);

        JLabel lblPublisher = new JLabel("Publisher");
        lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPublisher.setBounds(130, 170, 100, 24);
        background.add(lblPublisher);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblPrice.setBounds(130, 210, 100, 24);
        background.add(lblPrice);

        JLabel lblYear = new JLabel("Publisher Year");
        lblYear.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblYear.setBounds(130, 250, 120, 24);
        background.add(lblYear);

        JLabel lblDept = new JLabel("Department");
        lblDept.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDept.setBounds(130, 290, 120, 24);
        background.add(lblDept);

        JLabel lblQty = new JLabel("Quantity");
        lblQty.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblQty.setBounds(130, 330, 120, 24);
        background.add(lblQty);

        // ===== INPUT FIELDS =====
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField.setBackground(new Color(255, 255, 128));
        textField.setBounds(270, 88, 300, 30);
        background.add(textField);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_1.setBackground(new Color(255, 255, 128));
        textField_1.setBounds(270, 128, 300, 30);
        background.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_2.setBackground(new Color(255, 255, 128));
        textField_2.setBounds(270, 168, 300, 30);
        background.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_3.setBackground(new Color(255, 255, 128));
        textField_3.setBounds(270, 208, 300, 30);
        background.add(textField_3);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_4.setBackground(new Color(255, 255, 128));
        textField_4.setBounds(270, 248, 300, 30);
        background.add(textField_4);

        // ===== Department Dropdown =====
        String[] deptOptions = {"CSE", "IT", "Civil", "AIDS"};
        JComboBox<String> comboDept = new JComboBox<>(deptOptions);
        comboDept.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboDept.setBounds(270, 288, 200, 30);
        background.add(comboDept);

        // ===== Quantity Field =====
        textField_qty = new JTextField();
        textField_qty.setFont(new Font("Tahoma", Font.BOLD, 14));
        textField_qty.setBackground(new Color(255, 255, 128));
        textField_qty.setBounds(270, 328, 200, 30);
        background.add(textField_qty);

        JButton btnSave = new JButton("Save",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\save-icon--1.png"));
        btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSave.setBounds(240, 390, 110, 34);
        background.add(btnSave);

        JButton btnClose = new JButton("Close",
                new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\red-x-mark-transparent-background-3.png"));
        btnClose.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnClose.setBounds(380, 390, 110, 34);
        background.add(btnClose);

        // ========= ACTIONS ===========
        btnClose.addActionListener(e -> setVisible(false));

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String bookID = textField.getText().trim();
                String name = textField_1.getText().trim();
                String publisher = textField_2.getText().trim();
                String price = textField_3.getText().trim();
                String publisherYear = textField_4.getText().trim();
                String dept = comboDept.getSelectedItem().toString();
                String qtyStr = textField_qty.getText().trim();

                // === VALIDATIONS ===
                if (bookID.isEmpty() || name.isEmpty() || publisher.isEmpty() ||
                        price.isEmpty() || publisherYear.isEmpty() || qtyStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields!");
                    return;
                }

                try {
                    Double.parseDouble(price);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Price must be a number!");
                    return;
                }

                if (!publisherYear.matches("\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Publisher Year must be 4 digits!");
                    return;
                }

                int quantity = 0;
                try {
                    quantity = Integer.parseInt(qtyStr);
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(null, "Quantity must be greater than 0!");
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Quantity must be a valid number!");
                    return;
                }

                // ===== INSERT INTO DATABASE =====
                try {
                    Connection con = ConnectionProvider.getCon();

                    String query = "INSERT INTO books (book_id, book_name, author_name, quantity, department) "
                            + "VALUES (?, ?, ?, ?, ?)";

                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setInt(1, Integer.parseInt(bookID));
                    pst.setString(2, name);
                    pst.setString(3, publisher);
                    pst.setInt(4, quantity);
                    pst.setString(5, dept);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Book Added Successfully!");

                    // Optionally: clear fields instead of reopening window
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_qty.setText("");
                    comboDept.setSelectedIndex(0);

                    // If you want to automatically refresh bookssearch if it's open,
                    // add the call here (bookssearch must expose a public method/instance)
                    // e.g. if(bookssearch.INSTANCE != null) { SwingUtilities.invokeLater(() -> bookssearch.INSTANCE.loadAllBooks()); }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Book ID already exists or database error!\n" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}
