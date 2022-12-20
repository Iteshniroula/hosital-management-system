package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.Map;

public class login extends JFrame {

    JButton btnSubmit;
    JLabel lblUserName, lblPassword, lblRestaurantName, lblRestaurantAddress, lblConfirmPassword;
    JTextField txtUserName, txtPassword, txtRestaurantName, txtRestaurantAddress, txtConfirmPassword;
    JPanel emptyPanel = new JPanel();
    JLabel lblForget;

    login() throws IOException{
        setLayout(new BorderLayout());
        add(backgroundImage(emptyPanel,Form()));

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> requestFocusInWindow());
            }
        });
    }

    private JLabel backgroundImage(JPanel pnl, JPanel pnl2){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel background = new JLabel(new ImageIcon(new ImageIcon("images/bg.jpg").getImage().getScaledInstance((int)size.width,(int)size.height,Image.SCALE_SMOOTH)));
        JPanel pnlEmpty = new JPanel();
        pnlEmpty.setOpaque(false);
        pnlEmpty.setPreferredSize(new Dimension(0,80));
        background.setLayout(new BorderLayout());
        background.add(pnlEmpty,BorderLayout.NORTH);
        background.add(pnl,BorderLayout.CENTER);
        pnl.setOpaque(false);
        pnl.add(pnl2);
        return background;
    }

    private JPanel Form(){

        JPanel mainPanel = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel logoPanel = new JPanel();

        lblRestaurantName = new JLabel("Restaurant Name:");
        lblRestaurantName.setFont(new Font("",Font.BOLD,25));
        txtRestaurantName = new JTextField();

        lblRestaurantAddress = new JLabel("Restaurant Address:");
        lblRestaurantAddress.setFont(new Font("",Font.BOLD,25));
        txtRestaurantAddress = new JTextField();

        lblUserName = new JLabel("Username:");
        lblUserName.setFont(new Font("",Font.BOLD,25));
        txtUserName = new JTextField();

        lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("",Font.BOLD,25));
        txtPassword = new JPasswordField();

        lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(new Font("",Font.BOLD, 25));
        txtConfirmPassword = new JTextField();

        lblForget = new JLabel("Already Have Account? Click me to login");
        lblForget.setFont(new Font("",Font.PLAIN,18));
        lblForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblForget.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                try {
                    new Signup();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Font original = new Font("",Font.PLAIN,18);
                Map attributes = original.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                e.getComponent().setFont(original.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Font original = new Font("",Font.PLAIN,18);
                e.getComponent().setFont(original);
            }
        });

        btnSubmit =new JButton("Sign Up");
        btnSubmit.setPreferredSize(new Dimension(50,50));
        btnSubmit.setFocusable(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setBackground(new Color(44,60,80));
        btnSubmit.setFont(new Font("",Font.PLAIN,16));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtRestaurantName != null && txtRestaurantAddress != null && txtUserName!= null && txtPassword.getText()!= null && txtPassword == txtConfirmPassword){

                }
            }
        });

        logoPanel.setLayout(new BorderLayout(0,0));
        logoPanel.setPreferredSize(new Dimension(200,100));
        logoPanel.setBackground(Color.WHITE);

        formPanel.setLayout(new GridLayout(12,1,10,10));
        formPanel.add(lblRestaurantName);
        formPanel.add(txtRestaurantName);
        formPanel.add(lblRestaurantAddress);
        formPanel.add(txtRestaurantAddress);
        formPanel.add(lblUserName);
        formPanel.add(txtUserName);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);
        formPanel.add(lblConfirmPassword);
        formPanel.add(txtConfirmPassword);
        formPanel.add(lblForget);
        formPanel.add(btnSubmit);
        formPanel.setBorder(new EmptyBorder(5,40,50,40));
        formPanel.setBackground(Color.WHITE);


        mainPanel.setLayout(new BorderLayout(0,1));
        mainPanel.setPreferredSize(new Dimension(450,650));
        mainPanel.add(logoPanel,BorderLayout.NORTH);
        mainPanel.add(formPanel,BorderLayout.CENTER);

        return mainPanel;
    }

    public static void main (String args[]) throws IOException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    login sign = new login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
