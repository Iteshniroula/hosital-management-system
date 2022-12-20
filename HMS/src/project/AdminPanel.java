package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel extends JFrame {

    CardLayout card = new CardLayout();

    JPanel pnlMainBoard = new JPanel();
    JPanel emptyPanel = new JPanel();
    JButton btnLogout = new JButton("Logout");
    JLabel lblTitle = new JLabel("Hospital Management System");
    JLabel lblBoardInfo = new JLabel("Admin Panel");
    JButton btnDoctorDetails = new JButton();
    ImageIcon iconDoctorDetails = new ImageIcon(new ImageIcon("images/Doctor.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));
    JButton btnLowerStaffDetails = new JButton();
    ImageIcon iconLowerStaffDetails = new ImageIcon(new ImageIcon("images/lowerlevelstaff.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));
    JButton btnReceptionDetails = new JButton();
    ImageIcon iconReceptionistDetails = new ImageIcon(new ImageIcon("images/Receptionist.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));
    JButton btnPatientDetails = new JButton();
    ImageIcon iconPatientDetails = new ImageIcon(new ImageIcon("images/patient.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));
    JButton btnBedAvalibility = new JButton();
    ImageIcon iconBed = new ImageIcon(new ImageIcon("images/bed.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));
    JButton btnDepartmentDetails = new JButton();
    ImageIcon iconDepartmentDetails = new ImageIcon(new ImageIcon("images/department.png").getImage().getScaledInstance(180,180,Image.SCALE_SMOOTH));

    Data_base db = new Data_base();

    AdminPanel(String uname){
        setLayout(new BorderLayout());
        add(backgroundImage(emptyPanel,MainBoard(uname)),BorderLayout.CENTER);

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
        background.setLayout(new BorderLayout());
        background.add(pnl,BorderLayout.CENTER);
        pnl.setOpaque(false);
        pnl.setLayout(new BorderLayout());
        pnl.add(pnl2);
        return background;
    }

    private JPanel MainBoard(String uname){

        AdminDoctor doctor = new AdminDoctor(uname);
        AdminReception reception = new AdminReception(uname);
        AdminPatient patient = new AdminPatient(uname);
        AdminLowerLevelStaffs lowerLevelStaffs = new AdminLowerLevelStaffs(uname);
        AdminBed bed = new AdminBed(uname);
        AdminDept dept = new AdminDept(uname);

        JPanel pnlDashBoard = new JPanel();
        JPanel pnlTitle = new JPanel();
        JPanel pnlLoginInfo = new JPanel();
        JPanel pnlDashboard = new JPanel();
        JPanel pnlLogout = new JPanel();

        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setPreferredSize(new Dimension(0,120));
        pnlTitle.setBackground(new Color(30,50,60));
        btnLogout.setBorderPainted(false);
        btnLogout.setFocusable(false);
        btnLogout.setFont(new Font("",Font.BOLD,16));
        btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlLogout.setPreferredSize(new Dimension(150,20));
        pnlLogout.setLayout(new BorderLayout());
        pnlLogout.setOpaque(false);
        pnlLogout.setBorder(new EmptyBorder(40,20,40,0));
        pnlLogout.add(btnLogout);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("",Font.BOLD,28));
        pnlTitle.add(pnlLogout,BorderLayout.WEST);
        pnlTitle.add(lblTitle,BorderLayout.CENTER);

        pnlLoginInfo.setLayout(new BorderLayout());
        pnlLoginInfo.setPreferredSize(new Dimension(0,50));
        lblBoardInfo.setHorizontalAlignment(JLabel.CENTER);
        lblBoardInfo.setFont(new Font("",Font.BOLD,20));
        pnlLoginInfo.add(lblBoardInfo,BorderLayout.CENTER);

        pnlDashboard.setLayout(new FlowLayout(20,150,80));
        pnlDashboard.setPreferredSize(new Dimension(0,630));
        pnlDashboard.setOpaque(false);
        pnlDashboard.setBorder(new EmptyBorder(10,200,10,200));

        btnDoctorDetails.setPreferredSize(new Dimension(200,200));
        btnDoctorDetails.setIcon(iconDoctorDetails);
        btnDoctorDetails.setBorderPainted(false);
        btnDoctorDetails.setFocusable(false);
        btnDoctorDetails.setBackground(Color.WHITE);
        btnDoctorDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDoctorDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doctor.tblDoctor.setModel(new DefaultTableModel(null,new String[]{"Doctor_Id", "Doctor Name", "DOB", "Specialization", "Timing", "Days", "Username"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        });
                try{
                    String username = null;
                    ResultSet rs = db.Doctor();
                    while (rs.next()){
                        int id = rs.getInt("D_Id");
                        ResultSet r = db.doctoruser(id);
                        while (r.next()){
                            username = r.getString("Username");
                        }
                        String[] Data = {Integer.toString(rs.getInt("D_Id")),rs.getString("D_Name"),rs.getDate("DOB").toString(),rs.getString("Specification"),rs.getString("Timing"),rs.getString("Days"),username};
                        DefaultTableModel tblAdd = (DefaultTableModel) doctor.tblDoctor.getModel();
                        tblAdd.addRow(Data);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"2");
            }
        });
        pnlDashboard.add(btnDoctorDetails);

        btnLowerStaffDetails.setPreferredSize(new Dimension(200,200));
        btnLowerStaffDetails.setIcon(iconLowerStaffDetails);
        btnLowerStaffDetails.setBorderPainted(false);
        btnLowerStaffDetails.setFocusable(false);
        btnLowerStaffDetails.setBackground(Color.WHITE);
        btnLowerStaffDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLowerStaffDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerLevelStaffs.tblstaff.setModel(new DefaultTableModel(null,new String[]{"Staff_Id","Staff Name","DOB","Gender","Occupatation"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                try{
                    ResultSet rs = db.Staff();
                    while (rs.next()){
                        String[] Data = {Integer.toString(rs.getInt("Staff_Id")),rs.getString("Staff_Name"),rs.getDate("DOB").toString(),rs.getString("Gender"),rs.getString("Occupatation")};
                        DefaultTableModel tblAdd = (DefaultTableModel) lowerLevelStaffs.tblstaff.getModel();
                        tblAdd.addRow(Data);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"5");
            }
        });
        pnlDashboard.add(btnLowerStaffDetails);

        btnReceptionDetails.setPreferredSize(new Dimension(200,200));
        btnReceptionDetails.setIcon(iconReceptionistDetails);
        btnReceptionDetails.setBorderPainted(false);
        btnReceptionDetails.setFocusable(false);
        btnReceptionDetails.setBackground(Color.WHITE);
        btnReceptionDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReceptionDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reception.tblReceptionist.setModel(new DefaultTableModel(null,new String[]{"Receptionist_Id","Receptionist Name","DOB","gender","shift","Username"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                try{
                    String username = null;
                    ResultSet rs = db.Recp();
                    while (rs.next()){
                        int id = rs.getInt("Rec_Id");
                        ResultSet r = db.Recpuser(id);
                        while (r.next()){
                            username = r.getString("Username");
                        }
                        String[] Data = {Integer.toString(rs.getInt("Rec_Id")),rs.getString("Rec_Name"),rs.getDate("DOB").toString(),rs.getString("Gender"),rs.getString("Shift"),username};
                        DefaultTableModel tblAdd = (DefaultTableModel) reception.tblReceptionist.getModel();
                        tblAdd.addRow(Data);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"3");
            }
        });
        pnlDashboard.add(btnReceptionDetails);

        btnPatientDetails.setPreferredSize(new Dimension(200,200));
        btnPatientDetails.setIcon(iconPatientDetails);
        btnPatientDetails.setBorderPainted(false);
        btnPatientDetails.setFocusable(false);
        btnPatientDetails.setBackground(Color.WHITE);
        btnPatientDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPatientDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    patient.tblPatient.setModel(new DefaultTableModel(null,new String[]{"Patient_Id","Patient Name","DOB","Gender","Disease","Username"}){
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    try{
                        String username = null;
                        ResultSet rs = db.Patient();
                        while (rs.next()){
                            int id = rs.getInt("P_Id");
                            ResultSet r = db.Patientuser(id);
                            while (r.next()){
                                username = r.getString("Username");
                            }
                            String[] Data = {Integer.toString(rs.getInt("P_Id")),rs.getString("P_Name"),rs.getDate("DOB").toString(),rs.getString("Gender"),rs.getString("Disease"),username};
                            DefaultTableModel tblAdd = (DefaultTableModel) patient.tblPatient.getModel();
                            tblAdd.addRow(Data);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                card.show(pnlMainBoard,"4");
            }
        });
        pnlDashboard.add(btnPatientDetails);

        btnBedAvalibility.setPreferredSize(new Dimension(200,200));
        btnBedAvalibility.setIcon(iconBed);
        btnBedAvalibility.setBorderPainted(false);
        btnBedAvalibility.setFocusable(false);
        btnBedAvalibility.setBackground(Color.WHITE);
        btnBedAvalibility.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBedAvalibility.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bed.tblBed.setModel(new DefaultTableModel(null,new String[]{"Bed_Id","Category","Room No"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                try{
                    ResultSet rs = db.Bed();
                    while (rs.next()){
                        String[] Data = {Integer.toString(rs.getInt("B_Id")),rs.getString("B_Categories"),rs.getString("B_Roomno")};
                        DefaultTableModel tblAdd = (DefaultTableModel) bed.tblBed.getModel();
                        tblAdd.addRow(Data);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"6");
            }
        });
        pnlDashboard.add(btnBedAvalibility);

        btnDepartmentDetails.setPreferredSize(new Dimension(200,200));
        btnDepartmentDetails.setIcon(iconDepartmentDetails);
        btnDepartmentDetails.setBorderPainted(false);
        btnDepartmentDetails.setFocusable(false);
        btnDepartmentDetails.setBackground(Color.WHITE);
        btnDepartmentDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDepartmentDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dept.tblDepartment.setModel(new DefaultTableModel(null,new String[]{"Department Name","Department Name","Department Description"}){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                try{
                    ResultSet rs = db.Dept();
                    while (rs.next()){
                        String[] Data = {Integer.toString(rs.getInt("Dept_Id")),rs.getString("Dept_Name"),rs.getString("Dept_Description")};
                        DefaultTableModel tblAdd = (DefaultTableModel) bed.tblBed.getModel();
                        tblAdd.addRow(Data);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                card.show(pnlMainBoard,"7");
            }
        });
        pnlDashboard.add(btnDepartmentDetails);

        pnlDashBoard.setLayout(new BorderLayout());
        pnlDashBoard.setOpaque(false);
        pnlDashBoard.add(pnlTitle,BorderLayout.NORTH);
        pnlDashBoard.add(pnlLoginInfo);
        pnlDashBoard.add(pnlDashboard,BorderLayout.SOUTH);

        doctor.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        reception.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        patient.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        lowerLevelStaffs.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        bed.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        dept.btnDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                card.show(pnlMainBoard,"1");
            }
        });

        pnlMainBoard.setLayout(card);
        pnlMainBoard.setOpaque(false);
        pnlMainBoard.add(pnlDashBoard,"1");
        pnlMainBoard.add(doctor,"2");
        pnlMainBoard.add(reception,"3");
        pnlMainBoard.add(patient,"4");
        pnlMainBoard.add(lowerLevelStaffs,"5");
        pnlMainBoard.add(bed,"6");
        pnlMainBoard.add(dept,"7");
        card.show(pnlMainBoard,"1");

       return pnlMainBoard;
    }
}
