package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientPanel extends JFrame {

    JPanel pnlMainBoard = new JPanel();
    CardLayout card = new CardLayout();

    JPanel pnlDashBoard = new JPanel();
    JPanel pnlTitle = new JPanel();
    JPanel pnlLoginInfo = new JPanel();
    JPanel pnlDashboard = new JPanel();

    JButton btnLogout = new JButton("Logout");
    JLabel lblTitle = new JLabel("Hospital Management System");
    JLabel lblBoardInfo = new JLabel("Patient Panel");

    JButton btnCreateAppointment = new JButton("Create Appointment");
    JButton btnDoctorDetails = new JButton("Doctor Details");
    JButton btnPatientDetails = new JButton("Treatment Details");

    JPanel pnlbtn = new JPanel();

    Data_base db = new Data_base();

    PatientPanel(String Uname) throws BadLocationException {
        setLayout(new BorderLayout());
        add(backgroundImage(MainBoard(Uname)));

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

    private JLabel backgroundImage(JPanel pnl2) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel background = new JLabel(new ImageIcon(new ImageIcon("images/bg.jpg").getImage().getScaledInstance((int) size.width, (int) size.height, Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());
        background.add(pnl2, BorderLayout.CENTER);
        return background;

    }

    private JPanel MainBoard(String Uname) throws BadLocationException {

        DoctorAppointment doctorAppointment = new DoctorAppointment(Uname);
        PatientDoctorDetails patientDoctorDetails = new PatientDoctorDetails(Uname);
        patientDoctorReport patientDoctorReport = new patientDoctorReport(Uname);

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
        btnCreateAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"2");
                doctorAppointment.tblappointment.setModel(new DefaultTableModel(null ,new String[]{"Doctor_Id","Doctor Name","Age","Specialization","Timing","Days"}));
                try {
                    ResultSet rs = db.Doctor();
                    while (rs.next()) {
                        ResultSet age = db.DoctorAge(rs.getInt("D_Id"));
                        if(age.next()) {
                            String Data[] = {Integer.toString(rs.getInt("D_Id")), rs.getString("D_Name"), Long.toString(age.getLong("Age")), rs.getString("Specification"), rs.getString("Timing"), rs.getString("Days")};
                            DefaultTableModel tblAdd = (DefaultTableModel) doctorAppointment.tblappointment.getModel();
                            tblAdd.addRow(Data);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnDoctorDetails.setBorderPainted(false);
        btnDoctorDetails.setFocusable(false);
        btnDoctorDetails.setFont(new Font("",Font.BOLD,16));
        btnDoctorDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDoctorDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientDoctorDetails.tblappointment.setModel(new DefaultTableModel(null ,new String[]{"Doctor_Id","Doctor Name","Age","Specialization","Timing","Days"}));
                try {
                    ResultSet rs = db.Doctor();
                    while (rs.next()) {
                        ResultSet age = db.DoctorAge(rs.getInt("D_Id"));
                        if(age.next()) {
                            String Data[] = {Integer.toString(rs.getInt("D_Id")), rs.getString("D_Name"), Long.toString(age.getLong("Age")), rs.getString("Specification"), rs.getString("Timing"), rs.getString("Days")};
                            DefaultTableModel tblAdd = (DefaultTableModel) patientDoctorDetails.tblappointment.getModel();
                            tblAdd.addRow(Data);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"3");
            }
        });

        btnPatientDetails.setBorderPainted(false);
        btnPatientDetails.setFocusable(false);
        btnPatientDetails.setFont(new Font("",Font.BOLD,16));
        btnPatientDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPatientDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patientDoctorReport.tblreports.setModel(new DefaultTableModel(null,new String[]{"A_Id","Doctor Name","Date","Timing","Disease","status"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });

                try{
                    ResultSet rs = new Data_base().doctorPatientAppointment(Uname);
                    while (rs.next()){
                        String[] Data = {String.valueOf(rs.getInt("Apt_Id")),rs.getString("D_Name"),rs.getString("Date"),rs.getString("Timing"),rs.getString("Disease"),rs.getString("status")};
                        DefaultTableModel tbladd = (DefaultTableModel) patientDoctorReport.tblreports.getModel();
                        tbladd.addRow(Data);
                    }

                }catch (Exception exp) {
                    System.out.println(exp);
                }
                card.show(pnlMainBoard,"4");
            }
        });

        pnlbtn.setOpaque(false);
        pnlbtn.setPreferredSize(new Dimension(200,100));
        pnlbtn.setLayout(new GridLayout(4,1,15,15));
        pnlbtn.setBorder(new EmptyBorder(180,0,180,0));
        pnlbtn.add(btnCreateAppointment);
        pnlbtn.add(btnDoctorDetails);
        pnlbtn.add(btnPatientDetails);
        pnlbtn.add(btnLogout);


        pnlDashboard.setLayout(new BorderLayout());
        pnlDashboard.setPreferredSize(new Dimension(0,630));
        pnlDashboard.setOpaque(false);
        pnlDashboard.setBorder(new EmptyBorder(10,80,10,50));
        pnlDashboard.add(pnlbtn,BorderLayout.WEST);


        pnlDashBoard.setLayout(new BorderLayout());
        pnlDashBoard.setOpaque(false);
        pnlDashBoard.add(pnlTitle,BorderLayout.NORTH);
        pnlDashBoard.add(pnlLoginInfo);
        pnlDashBoard.add(pnlDashboard,BorderLayout.SOUTH);

        doctorAppointment.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        patientDoctorDetails.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        patientDoctorReport.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        pnlMainBoard.setLayout(card);
        pnlMainBoard.add(pnlDashBoard,"1");
        pnlMainBoard.add(doctorAppointment,"2");
        pnlMainBoard.add(patientDoctorDetails,"3");
        pnlMainBoard.add(patientDoctorReport,"4");
        card.show(pnlMainBoard,"1");


        pnlMainBoard.setOpaque(false);
        return pnlMainBoard;
    }
}
