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

public class DoctorPanel extends JFrame{

    JPanel pnlMainBoard = new JPanel();
    CardLayout card = new CardLayout();

    JPanel pnlDashBoard = new JPanel();
    JPanel pnlTitle = new JPanel();
    JPanel pnlLoginInfo = new JPanel();
    JPanel pnlDashboard = new JPanel();

    JButton btnLogout = new JButton("Logout");
    JLabel lblTitle = new JLabel("Hospital Management System");
    JLabel lblBoardInfo = new JLabel("Doctor Panel");

    JButton btnViewAppointment = new JButton("View Appointment");
    JButton btnPatientDetails = new JButton("Patient Details");

    JPanel pnlbtn = new JPanel();

    Data_base db = new Data_base();

    DoctorPanel(String uname) throws BadLocationException {
        setLayout(new BorderLayout());
        add(backgroundImage(MainBoard(uname)));

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

    private JPanel MainBoard(String uname) throws BadLocationException {
        DoctorViewAppointment doctorViewAppointment = new DoctorViewAppointment(uname);
        Patient_Details patientDetails = new Patient_Details(uname);

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


        btnViewAppointment.setBorderPainted(false);
        btnViewAppointment.setFocusable(false);
        btnViewAppointment.setFont(new Font("",Font.BOLD,16));
        btnViewAppointment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnViewAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctorViewAppointment.tblappointment.setModel(new DefaultTableModel(null,new String[]{"Appointment Id","Patient_Id","Patient Name","Age","Disease","timing","Day","Status","Action Decline","Action approve","Action completed"}));

                doctorViewAppointment.tblappointment.getColumnModel().getColumn(10).setCellRenderer(new DoctorViewAppointment.ButtonRenderer());
                doctorViewAppointment.tblappointment.getColumnModel().getColumn(10).setCellEditor(new DoctorViewAppointment.ButtonEditor(new JTextField()));

                doctorViewAppointment.tblappointment.getColumnModel().getColumn(8).setCellRenderer(new DoctorViewAppointment.ButtonRenderer());
                doctorViewAppointment.tblappointment.getColumnModel().getColumn(8).setCellEditor(new DoctorViewAppointment.ButtonEditor(new JTextField()));

                doctorViewAppointment.tblappointment.getColumnModel().getColumn(9).setCellRenderer(new DoctorViewAppointment.ButtonRenderer());
                doctorViewAppointment.tblappointment.getColumnModel().getColumn(9).setCellEditor(new DoctorViewAppointment.ButtonEditor(new JTextField()));

                try {
                    ResultSet rs = db.patientDoctorAppointment(uname);
                    while (rs.next()) {
                        ResultSet age = db.patientAge(rs.getInt("P_Id"));
                        if(age.next()) {
                            String data[] = {Integer.toString(rs.getInt("Apt_Id")), String.valueOf(rs.getInt("P_Id")), rs.getString("P_Name"), Long.toString(age.getLong("Age")), rs.getString("Disease"), rs.getString("Timing"), rs.getString("Date"),rs.getString("status"),"decline","approve","completed"};
                            DefaultTableModel tblAdd = (DefaultTableModel) doctorViewAppointment.tblappointment.getModel();
                            tblAdd.addRow(data);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                card.show(pnlMainBoard,"2");

            }
        });

        btnPatientDetails.setBorderPainted(false);
        btnPatientDetails.setFocusable(false);
        btnPatientDetails.setFont(new Font("",Font.BOLD,16));
        btnPatientDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPatientDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"3");
            }
        });

        pnlbtn.setOpaque(false);
        pnlbtn.setPreferredSize(new Dimension(200,100));
        pnlbtn.setLayout(new GridLayout(3,1,15,25));
        pnlbtn.setBorder(new EmptyBorder(190,0,190,0));
        pnlbtn.add(btnViewAppointment);
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

        doctorViewAppointment.btnDashboard.addActionListener(e -> card.show(pnlMainBoard,"1"));

        patientDetails.btnDashboard.addActionListener(e -> {
            card.show(pnlMainBoard,"1");
        });

        pnlMainBoard.setLayout(card);
        pnlMainBoard.add(pnlDashBoard,"1");
        pnlMainBoard.add(doctorViewAppointment,"2");
        pnlMainBoard.add(patientDetails,"3");
        card.show(pnlMainBoard,"1");


        pnlMainBoard.setOpaque(false);
        return pnlMainBoard;
    }
    }

