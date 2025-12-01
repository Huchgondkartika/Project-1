import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import javax.swing.JComboBox;


public class Home extends JFrame {

    private static final long serialVersionUID = 1L;
    JTextField jTextField1 = new JTextField();
    JTextField jTextField2 = new JTextField();
    JTextField jTextField3 = new JTextField();
    JComboBox<String> jComboBoxCourse = new JComboBox<>(new String[]{"B.Tech", "M.Tech"});
    JComboBox<String> jComboBoxBranch = new JComboBox<>(new String[]{"CSE", "IT", "ECE"});


    public Home() {
        setTitle("Home Page");
        setSize(1297, 762);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JButton btnNewStudent = new JButton("New Student", 
        	    new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\member-add-on-300x300.png"));

        btnNewStudent.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewStudent.setBounds(-12, 11, 199, 49); // Adjust coordinates as needed
        getContentPane().add(btnNewStudent);

        btnNewStudent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String studentID = jTextField1.getText();
                String name = jTextField2.getText();
                String fatherName = jTextField3.getText();
                String courseName = (String) jComboBoxCourse.getSelectedItem();
                String branchName = (String) jComboBoxBranch.getSelectedItem();
              
        

        	        try {
        	            Connection con = ConnectionProvider.getCon();
        	            Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO student VALUES ('" + studentID + "','" + name + "','" + fatherName + "','" + courseName + "','" + branchName + "')");
        	            JOptionPane.showMessageDialog(null, "Successfully updated");
        	            //setVisible(false);
        	            new newStudent().setVisible(true);
        	        } catch (Exception ex) {
        	            //setVisible(false);
        	            new newStudent().setVisible(true);
        	        }
    }
        	});
        	

        
    JButton btnNewButton_1 = new JButton("NewBook", new ImageIcon("C:\\Users\\huchg\\Downloads\\New book.png"));
    btnNewButton_1.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    	new NewBook().setVisible(true);	
    	}
    });
    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
    btnNewButton_1.setBounds(197, 11, 177, 49);
    getContentPane().add(btnNewButton_1);
    
        JButton btnNewButton_2 = new JButton("AboutUs", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\icon-developer-icon.png"));
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new about().setVisible(true);

        	}
        });
        
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_2.setBounds(384, 11, 177, 49);
        getContentPane().add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("IssueBook",new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\issue book.png"));
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new issueBook().setVisible(true);
        	}
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_3.setBounds(754, 11, 177, 49);
        getContentPane().add(btnNewButton_3);
        
        JButton btnNewButton_4 = new JButton("ReturnBook",new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\return book png.png"));
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	new returnBook().setVisible(true);	
        	}
        });
        btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_4.setBounds(567, 11, 177, 49);
        getContentPane().add(btnNewButton_4);
        
        JButton btnNewButton_5 = new JButton("Logout", new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\exit.png"));

        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Hide current frame
                setVisible(false);

                // Open Login frame
                new Login().setVisible(true);

                // Optional: maximize the new frame
                // newLogin.setExtendedState(JFrame.MAXIMIZED_BOTH); 
            }
        });
        JButton btnfeedback = new JButton("Feedback",
        	    new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\Feedback-icon.png"));
        btnfeedback.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new feedback().setVisible(true);
        	}
        });
        	btnfeedback.setBounds(950, 11, 164, 49);
        	btnfeedback.setFont(new Font("Tahoma", Font.BOLD, 14));
        	btnfeedback.setOpaque(true);
        	btnfeedback.setBackground(new Color(192, 192, 192));
        	btnfeedback.setFocusPainted(false);
        	
        
        	getContentPane().add(btnfeedback);

        
        
        

        // ↓ This closing brace was misplaced earlier, now correctly positioned
        btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton_5.setBounds(1124, 11, 149, 49);
        getContentPane().add(btnNewButton_5);
        
        JButton btnNewButton = new JButton("BookSearch",new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\issue book.png"));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(-2, 81, 189, 49);
        getContentPane().add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel(new ImageIcon(("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\Library_Book_532388_1366x768.jpg")));
        lblNewLabel.setBounds(-2, 0, 1341, 743);
        getContentPane().add(lblNewLabel);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new bookssearch().setVisible(true); // ← Replace with your book search class name
            }
        });
        
      
    }


public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
        try {
            Home frame = new Home();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}
}


