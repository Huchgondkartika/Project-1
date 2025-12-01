import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class bookssearch extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboDept;
    private JTable table;
    private DefaultTableModel model;
    private JTextField searchField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                bookssearch frame = new bookssearch();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public bookssearch() {

        setTitle("Book Search");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        // Background Panel
        contentPane = new JPanel(null) {
            private Image bg = new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Book Search");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setBounds(270, 10, 250, 40);
        contentPane.add(lblTitle);

        // Department Label
        JLabel lblDept = new JLabel("Department:");
        lblDept.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDept.setBounds(60, 70, 110, 30);
        contentPane.add(lblDept);

        // Dropdown
        comboDept = new JComboBox<>(new String[]{"All", "CSE", "IT", "Civil", "AIDS"});
        comboDept.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboDept.setBounds(160, 70, 150, 30);
        contentPane.add(comboDept);

        // Search Label
        JLabel lblSearch = new JLabel("Search Book:");
        lblSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblSearch.setBounds(350, 70, 120, 30);
        contentPane.add(lblSearch);

        // Search Text Field
        searchField = new JTextField();
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchField.setBounds(460, 70, 150, 30);
        contentPane.add(searchField);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(620, 70, 90, 30);
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(btnSearch);

        // Table
        table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false; // no editing
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);

                int quantity = Integer.parseInt(getValueAt(row, 4).toString());

                if (quantity < 5) {
                    comp.setBackground(Color.PINK);  // low stock
                } else {
                    comp.setBackground(Color.WHITE);
                }
                return comp;
            }
        };

        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);

        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Book ID", "Book Name", "Author", "Department", "Quantity"}
        );

        table.setModel(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(60, 130, 650, 330);
        contentPane.add(scrollPane);

        // Refresh Button
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRefresh.setBounds(200, 480, 130, 30);
        contentPane.add(btnRefresh);

        // Issue Book Button
        JButton btnIssueBook = new JButton("Issue Selected Book");
        btnIssueBook.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnIssueBook.setBounds(400, 480, 200, 30);
        contentPane.add(btnIssueBook);

        // Load all books when page opens
        loadAllBooks();

        // Button Listeners
        btnSearch.addActionListener(e -> searchBooks());
        btnRefresh.addActionListener(e -> loadAllBooks());
        comboDept.addActionListener(e -> filterByDept());
        btnIssueBook.addActionListener(e -> openIssueBookPage());
    }

    // Load every book from database
    private void loadAllBooks() {
        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM books";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author_name"),
                        rs.getString("department"),
                        rs.getInt("quantity")
                });
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading books!");
        }
    }

    // Search by book or author
    private void searchBooks() {
        String keyword = searchField.getText().trim();

        if (keyword.isEmpty()) {
            loadAllBooks();
            return;
        }

        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM books WHERE book_name LIKE ? OR author_name LIKE ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, "%" + keyword + "%");
            pst.setString(2, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author_name"),
                        rs.getString("department"),
                        rs.getInt("quantity")
                });
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Search Error!");
        }
    }

    // Filter by department
    private void filterByDept() {
        String dept = comboDept.getSelectedItem().toString();

        if (dept.equals("All")) {
            loadAllBooks();
            return;
        }

        try {
            Connection con = ConnectionProvider.getCon();
            String query = "SELECT * FROM books WHERE department = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, dept);

            ResultSet rs = pst.executeQuery();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author_name"),
                        rs.getString("department"),
                        rs.getInt("quantity")
                });
            }

            rs.close();
            pst.close();
            con.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Filter Error!");
        }
    }

    // Issue Book Window
    private void openIssueBookPage() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select a book to issue.");
            return;
        }

        int bookID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        // Open issue book window
        issueBook issueFrame = new issueBook(String.valueOf(bookID));
        issueFrame.setVisible(true);
    }
}
