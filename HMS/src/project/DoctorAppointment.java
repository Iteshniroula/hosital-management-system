package project;


import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class DoctorAppointment extends JPanel {

    JLabel lblTitle = new JLabel("Create Appointment");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblSearch = new JLabel("Doctor Name:");
    JTextField txtSearch = new JTextField();
    String[] category = {"Select Category","Heart","Brain","Head","Eye"};
    JComboBox comboBoxCategory = new JComboBox(category);

    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();

    JLabel lblDate = new JLabel("Date");
    JDateChooser dc = new JDateChooser();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String chooseDate;
    JLabel lblTimming = new JLabel("Timming");
    String[] Time = {"Choose Time","12:00 PM","11:00 PM","10:00 PM","09:00 PM","08:00 PM","07:00 PM","06:00 PM","05:00 PM","04:00 PM","03:00 PM","02:00 PM","01:00 PM",
            "12:00 AM","11:00 AM","10:00 AM","09:00 AM","08:00 AM","07:00 AM","06:00 AM","05:00 AM","04:00 AM","03:00 AM","02:00 AM","01:00 AM"};
    JComboBox comboBoxTiming = new JComboBox(Time);
    JPanel pnlDateTime = new JPanel();
    JButton btnCreate = new JButton("Create");
    JPanel pnlBtnCreate = new JPanel();

    JTable tblappointment;
    DefaultTableModel tblmdl;

    Data_base db = new Data_base();

    DoctorAppointment(String uname){
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setBackground(new Color(36,172,223));
        pnlTitle.setPreferredSize(new Dimension(0,50));
        lblTitle.setFont(new Font("Arial",Font.BOLD,35));
        lblTitle.setForeground(Color.WHITE);

        pnlbtnDashboard.setOpaque(false);
        pnlbtnDashboard.setBorder(new EmptyBorder(0,20,0,50));
        pnlbtnDashboard.setPreferredSize(new Dimension(80,80));

        btnDashboard.setFocusable(false);
        btnDashboard.setBorderPainted(false);
        btnDashboard.setIcon(iconHome);
        btnDashboard.setBackground(Color.WHITE);
        btnDashboard.setPreferredSize(new Dimension(50,40));
        btnDashboard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnlbtnDashboard.add(btnDashboard);
        pnlTitle.add(lblTitle,BorderLayout.CENTER);
        pnlTitle.add(pnlbtnDashboard,BorderLayout.EAST);

        pnlBody.setLayout(new BorderLayout());
        pnlBodyHead.setLayout(new FlowLayout(1,15,10));
        pnlBodyHead.setPreferredSize(new Dimension(0,50));
        lblSearch.setHorizontalAlignment(JLabel.RIGHT);
        lblSearch.setFont(new Font("",Font.BOLD,28));
        lblSearch.setPreferredSize(new Dimension(200,30));
        txtSearch.setPreferredSize(new Dimension(550,40));
        comboBoxCategory.setPreferredSize(new Dimension(180,40));
        comboBoxCategory.setFont(new Font("",Font.BOLD,18));
        pnlBodyHead.add(lblSearch);
        pnlBodyHead.add(txtSearch);
        pnlBodyHead.add(comboBoxCategory);

        String[][] Data = {};
        String[] coloum = {"Doctor_Id","Doctor Name","Age","Specialization","Timing","Days"};

        tblmdl = new DefaultTableModel(Data, coloum) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblappointment = new JTable(tblmdl);

        tblappointment.setPreferredScrollableViewportSize(new Dimension(1200, 1200));
        tblappointment.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tblappointment);


        pnlBodyMain.add(scroll);
        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);
        pnlBody.add(pnlBodyMain,BorderLayout.CENTER);

        lblDate.setFont(new Font("",Font.BOLD,16));
        lblTimming.setFont(new Font("",Font.BOLD,16));

        pnlDateTime.setLayout(new GridLayout(2,2,0,10));
        pnlDateTime.setPreferredSize(new Dimension(400,0));
        pnlDateTime.setBorder(new EmptyBorder(30,50,30,0));
        pnlDateTime.add(lblDate);
        pnlDateTime.add(dc);
        pnlDateTime.add(lblTimming);
        pnlDateTime.add(comboBoxTiming);

        btnCreate.setFont(new Font("",Font.BOLD,16));
        btnCreate.setBorderPainted(false);
        btnCreate.setFocusable(false);
        btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCreate.setEnabled(false);
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dc.getDate()!=null && comboBoxTiming.getSelectedIndex()!=0 ){
                    String chooseDate = df.format(dc.getDate());
                    String aptId = null;
                    int patId = 0;
                    try {
                        ResultSet rs = db.appointmentAuto();
                        while (rs.next()){
                            aptId = rs.getString("AUTO_INCREMENT");
                        }
                        db.insertAppointment(Date.valueOf(chooseDate), Objects.requireNonNull(comboBoxTiming.getSelectedItem()).toString(),"pending");
                        ResultSet rsPatient = db.PatientId(uname);
                        if (rsPatient.next()){
                            patId = rsPatient.getInt("P_Id");
                        }
                        db.insertServes(Integer.parseInt(aptId),Integer.parseInt(tblappointment.getValueAt(tblappointment.getSelectedRow(),0).toString()),patId);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null,"Your Appointment is created!!");
                } else {
                    JOptionPane.showMessageDialog(null,"Please fill the data");
                }
            }
        });

        tblappointment.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnCreate.setEnabled(true);
            }
        });

        pnlBtnCreate.setLayout(new GridLayout(1,1));
        pnlBtnCreate.setPreferredSize(new Dimension(350,0));
        pnlBtnCreate.setBorder(new EmptyBorder(40,0,40,100));
        pnlBtnCreate.add(btnCreate);

        pnlFooter.setLayout(new BorderLayout());
        pnlFooter.setPreferredSize(new Dimension(100,150));
        pnlFooter.add(pnlDateTime,BorderLayout.WEST);
        pnlFooter.add(pnlBtnCreate,BorderLayout.EAST);

        this.setLayout(new BorderLayout(5,5));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> requestFocusInWindow());
                tblappointment.getSelectionModel().clearSelection();
            }

        });
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);

    }

}
