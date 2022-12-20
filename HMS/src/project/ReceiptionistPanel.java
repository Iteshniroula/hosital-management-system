package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ReceiptionistPanel extends JFrame {

    JPanel emptyPanel = new JPanel();
    JPanel pnlMainBoard = new JPanel();
    CardLayout card = new CardLayout();

    JPanel pnlDashBoard = new JPanel();
    JPanel pnlTitle = new JPanel();
    JPanel pnlLoginInfo = new JPanel();
    JPanel pnlDashboard = new JPanel();

    JButton btnLogout = new JButton("Logout");
    JLabel lblTitle = new JLabel("Hospital Management System");
    JLabel lblBoardInfo = new JLabel("Receptionist Panel");

    JButton btnCreateAppointment = new JButton("Create Appointment");
    JButton btnDoctorDetails = new JButton("Doctor Details");
    JButton btnPatientDetails = new JButton("Patient Details");

    JPanel pnlbtn = new JPanel();
    JPanel pnlTable = new JPanel();

    DefaultTableModel tblMdl;
    JTable tblRequestAppointment;

    JLabel lblRequestAppointment = new JLabel("Pending Appointments");

    Data_base db = new Data_base();

    ReceiptionistPanel(){
        setLayout(new BorderLayout());
        add(backgroundImage(emptyPanel,MainBoard()));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> requestFocusInWindow());
            }
        });
    }

    private JLabel backgroundImage(JPanel pnl, JPanel pnl2){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel background = new JLabel(new ImageIcon(new ImageIcon("images/receptionistpanel.jpg").getImage().getScaledInstance((int)size.width,(int)size.height,Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());
        background.add(pnl,BorderLayout.CENTER);
        pnl.setOpaque(false);
        pnl.setLayout(new BorderLayout());
        pnl.add(pnl2);
        return background;

    }

    private JPanel MainBoard(){

        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setPreferredSize(new Dimension(0,120));
        pnlTitle.setOpaque(false);
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusable(false);
        btnLogout.setFont(new Font("",Font.BOLD,16));
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("",Font.BOLD,28));
        pnlTitle.add(lblTitle,BorderLayout.CENTER);

        pnlLoginInfo.setLayout(new BorderLayout());
        pnlLoginInfo.setPreferredSize(new Dimension(0,50));
        pnlLoginInfo.setBackground(new Color(158,174,254,80));
        lblBoardInfo.setHorizontalAlignment(JLabel.CENTER);
        lblBoardInfo.setFont(new Font("",Font.BOLD,20));
        pnlLoginInfo.add(lblBoardInfo,BorderLayout.CENTER);


        btnCreateAppointment.setBorderPainted(false);
        btnCreateAppointment.setFocusable(false);
        btnCreateAppointment.setFont(new Font("",Font.BOLD,16));
        btnCreateAppointment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnDoctorDetails.setBorderPainted(false);
        btnDoctorDetails.setFocusable(false);
        btnDoctorDetails.setFont(new Font("",Font.BOLD,16));
        btnDoctorDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnPatientDetails.setBorderPainted(false);
        btnPatientDetails.setFocusable(false);
        btnPatientDetails.setFont(new Font("",Font.BOLD,16));
        btnPatientDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnlbtn.setOpaque(false);
        pnlbtn.setPreferredSize(new Dimension(200,100));
        pnlbtn.setLayout(new GridLayout(4,1,15,15));
        pnlbtn.setBorder(new EmptyBorder(180,0,180,0));
        pnlbtn.add(btnCreateAppointment);
        pnlbtn.add(btnDoctorDetails);
        pnlbtn.add(btnPatientDetails);
        pnlbtn.add(btnLogout);

        Object[][] Data = {};
        Object[] Coloums ={"Patient Id","Patient Name","Timing","Date","Disease"};

        tblMdl = new DefaultTableModel(Data, Coloums) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblRequestAppointment = new JTable(tblMdl);

        tblRequestAppointment.setPreferredScrollableViewportSize(new Dimension(1600, 800));
        tblRequestAppointment.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tblRequestAppointment);
        scroll.setBackground(Color.WHITE);

        JPanel pnlAppointment = new JPanel();
        pnlAppointment.setPreferredSize(new Dimension(100,50));
        pnlAppointment.setLayout(new BorderLayout());
        pnlAppointment.setBackground(new Color(158,174,254,80));

        lblRequestAppointment.setFont(new Font("",Font.BOLD,30));
        lblRequestAppointment.setHorizontalAlignment(JLabel.CENTER);
//        lblRequestAppointment.setForeground(new Color(28,54,118));
        lblRequestAppointment.setPreferredSize(new Dimension(100,33));
        pnlAppointment.add(lblRequestAppointment,BorderLayout.CENTER);

//        pnlTable.setBorder(new EmptyBorder(0,0,80,0));
        pnlTable.setPreferredSize(new Dimension(500,100));
        pnlTable.setOpaque(false);
        pnlTable.setLayout(new BorderLayout());
        pnlTable.add(pnlAppointment,BorderLayout.NORTH);
        pnlTable.add(scroll,BorderLayout.CENTER);


        pnlDashboard.setLayout(new BorderLayout());
        pnlDashboard.setPreferredSize(new Dimension(0,630));
        pnlDashboard.setOpaque(false);
        pnlDashboard.setBorder(new EmptyBorder(10,200,10,50));
        pnlDashboard.add(pnlbtn,BorderLayout.WEST);
        pnlDashboard.add(pnlTable,BorderLayout.EAST);

        pnlDashBoard.setLayout(new BorderLayout());
        pnlDashBoard.setOpaque(false);
        pnlDashBoard.add(pnlTitle,BorderLayout.NORTH);
        pnlDashBoard.add(pnlLoginInfo);
        pnlDashBoard.add(pnlDashboard,BorderLayout.SOUTH);

        pnlMainBoard.setLayout(card);
        pnlMainBoard.add(pnlDashBoard,"1");
        card.show(pnlMainBoard,"1");


        pnlMainBoard.setOpaque(false);
        return pnlMainBoard;
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
                ReceiptionistPanel receiptionist = new ReceiptionistPanel();
                receiptionist.setLocationRelativeTo(null);
                receiptionist.setDefaultCloseOperation(EXIT_ON_CLOSE);
                receiptionist.setVisible(true);
                receiptionist.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });
    }


}
