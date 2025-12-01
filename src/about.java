

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class about extends JFrame {

    public about() {
        setTitle("About Us - Library Management System");
        setSize(730, 466);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(new Color(240, 248, 255));

        JLabel title = new JLabel("Library Management System");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setBounds(158, 37, 400, 30);
        getContentPane().add(title);

        JTextArea aboutText = new JTextArea();
        aboutText.setText(
            "This Library Management System helps in maintaining records of books, \r\nreturn book,students, and issued books efficiently.\r\n\r\nFeatures:\r\n- Add, update, and delete book details\r\n- Manage student records\r\n- Issue and return books\r\n- aboutus and feedback submit\r\n\r\nDeveloped by: Kartika Huchgond,Shama Ansari,Bhoomi Bhiungade\r\nVersion: 1.0\r\nYear: 2025"
        );
        aboutText.setFont(new Font("SansSerif", Font.PLAIN, 16));
        aboutText.setEditable(false);
        aboutText.setBackground(new Color(255, 255, 128));
        aboutText.setBounds(54, 78, 550, 290);
        getContentPane().add(aboutText);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBackground(new Color(192, 192, 192));
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(283, 379, 100, 30);
        closeBtn.addActionListener(e -> dispose());
        getContentPane().add(closeBtn);
        
        JLabel lblNewLabel = new JLabel(new ImageIcon("C:\\Users\\huchg\\Downloads\\Icon 1\\Icon 1\\123456.png"));
        lblNewLabel.setBounds(-23, -33, 706, 451);
        getContentPane().add(lblNewLabel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new about().setVisible(true));
    }
}
