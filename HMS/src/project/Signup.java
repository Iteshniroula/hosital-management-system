package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class Signup extends JFrame {

    JButton btnSubmit;
    JLabel lblUserName, lblPassword;
    JTextField txtUserName, txtPassword;
    JPanel emptyPanel = new JPanel();
    JLabel lblForget;

public    Signup() throws IOException {

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
            pnlEmpty.setPreferredSize(new Dimension(0,200));
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

            lblUserName = new JLabel("Username:");
            lblUserName.setFont(new Font("",Font.BOLD,25));
            txtUserName = new JTextField();

            lblPassword = new JLabel("Password:");
            lblPassword.setFont(new Font("",Font.BOLD,25));
            txtPassword = new JPasswordField();

            lblForget = new JLabel("Forget Password?");
            lblForget.setFont(new Font("",Font.PLAIN,18));
            lblForget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblForget.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

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

            btnSubmit =new JButton("SUBMIT");
            btnSubmit.setPreferredSize(new Dimension(50,50));
            btnSubmit.setFocusable(false);
            btnSubmit.setBorderPainted(false);
            btnSubmit.setBackground(new Color(44,60,80));
            btnSubmit.setFont(new Font("",Font.PLAIN,16));
            btnSubmit.setForeground(Color.WHITE);
            btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnSubmit.addActionListener(e -> {
                Data_base db = new Data_base();
                try {
                    ResultSet rs = db.user(txtUserName.getText());
                    while (rs.next()){
                        if(Objects.equals(rs.getString("Username"), txtUserName.getText()) && Objects.equals(rs.getString("Password"), txtPassword.getText())){
                            if(Objects.equals(rs.getString("post"), "Admin")){
                                dispose();
                                AdminPanel ap = new AdminPanel(txtUserName.getText());
                            } else  if(Objects.equals(rs.getString("post"), "Patient")){
                                dispose();
                                PatientPanel pp = new PatientPanel(txtUserName.getText());
                            } else if(Objects.equals(rs.getString("post"), "Doctor")){
                                dispose();
                                DoctorPanel dp = new DoctorPanel(txtUserName.getText());
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"Wrong Credentials");
                            txtUserName.setText("");
                            txtPassword.setText("");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

            });

            logoPanel.setLayout(new BorderLayout(0,0));
            logoPanel.setPreferredSize(new Dimension(200,100));
            logoPanel.setBackground(Color.WHITE);

            formPanel.setLayout(new GridLayout(6,1,10,0));
            formPanel.add(lblUserName);
            formPanel.add(txtUserName);
            formPanel.add(lblPassword);
            formPanel.add(txtPassword);
            formPanel.add(lblForget);
            formPanel.add(btnSubmit);
            formPanel.setBorder(new EmptyBorder(5,40,50,40));
            formPanel.setBackground(Color.WHITE);


            mainPanel.setLayout(new BorderLayout(0,1));
            mainPanel.setPreferredSize(new Dimension(400,400));
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
                        Signup sign = new Signup();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
}
