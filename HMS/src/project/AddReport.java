package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AddReport extends JPanel {


    JLabel lblTitle = new JLabel("Add Report");
    JPanel pnlbtnBack = new JPanel();
    JButton btnBack = new JButton("Back");
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblPatientId = new JLabel("Patient_Id");
    JTextField txtPatientID = new JTextField();

    JLabel lblName = new JLabel("Name");
    JTextField txtName = new JTextField();
    JLabel lblAddress = new JLabel("Address");
    JTextField txtAddress = new JTextField();
    JLabel lblAge = new JLabel("Age");
    JTextField txtAge = new JTextField();
    JLabel lblContact = new JLabel("Contact");
    JTextField txtContact = new JTextField();
    JLabel lblGender = new JLabel("Gender");
    String[] gender ={"Male","Female","Others"};
    JComboBox comboBoxGender = new JComboBox(gender);
    JLabel lblDisease = new JLabel("Disease");
    JTextField txtDisease = new JTextField();
    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();
    JPanel pnlBodyForm = new JPanel();
    JPanel pnlBodyImage = new JPanel();
    JLabel lblUser = new JLabel("Username");
    JTextField txtUser = new JTextField();
    JLabel lblPassword = new JLabel("Password");
    JPasswordField txtPassword = new JPasswordField();

    String name;
    String address;
    String dob;
    String contact;
    String disease;
    String Gender;

    Data_base db = new Data_base();
    
    AddReport(String uname,int id,int apt_id) throws SQLException {
        pnlTitle.setLayout(new BorderLayout());
        pnlTitle.setBackground(new Color(36,172,223));
        pnlTitle.setPreferredSize(new Dimension(0,50));
        lblTitle.setFont(new Font("Arial",Font.BOLD,35));
        lblTitle.setForeground(Color.WHITE);

        pnlbtnBack.setOpaque(false);
        pnlbtnBack.setBorder(new EmptyBorder(0,20,0,50));
        pnlbtnBack.setPreferredSize(new Dimension(150,80));

        btnBack.setFocusable(false);
        btnBack.setBorderPainted(false);
        btnBack.setBackground(Color.WHITE);
        btnBack.setFont(new Font("",Font.BOLD,16));
        btnBack.setPreferredSize(new Dimension(100,40));
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        pnlbtnBack.add(btnBack);
        pnlTitle.add(lblTitle,BorderLayout.CENTER);
        pnlTitle.add(pnlbtnBack,BorderLayout.EAST);

        pnlBody.setLayout(new BorderLayout());
        pnlBodyHead.setLayout(new FlowLayout(1,15,10));
        pnlBodyHead.setPreferredSize(new Dimension(0,50));

        lblPatientId.setHorizontalAlignment(JLabel.RIGHT);
        lblPatientId.setFont(new Font("",Font.BOLD,28));
        lblPatientId.setPreferredSize(new Dimension(200,30));

        txtPatientID.setPreferredSize(new Dimension(350,40));
        txtPatientID.setEditable(false);

        pnlBodyHead.add(lblPatientId);
        pnlBodyHead.add(txtPatientID);
        pnlBodyMain.setLayout(new BorderLayout());

        lblName.setFont(new Font("",Font.BOLD,16));
        txtName.setText("");
        txtName.setEditable(false);

        lblAge.setFont(new Font("",Font.BOLD,16));
        txtAge.setEditable(false);

        lblGender.setFont(new Font("",Font.BOLD,16));

        lblDisease.setFont(new Font("",Font.BOLD,16));
        txtDisease.setEditable(false);

        lblUser.setFont(new Font("",Font.BOLD,16));
        txtUser.setText("");

        lblAddress.setFont(new Font("",Font.BOLD,16));
        txtAddress.setText("");

        lblPassword.setFont(new Font("",Font.BOLD,16));
        txtPassword.setText("");

        lblContact.setFont(new Font("",Font.BOLD,16));
        txtContact.setText("");
        txtContact.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });

        try {
            ResultSet rs = db.Patient(id);
            ResultSet age = db.patientAge(id);
            while (rs.next()){
                name = rs.getString("P_Name");
                address = rs.getString("P_Address");
                if(age.next()){
                    dob = Long.toString(age.getLong("Age"));
                }
                contact = rs.getString("Contact");
                disease = rs.getString("Disease");
                Gender = rs.getString("Gender");
            }
        }catch (Exception exp){
            System.out.println(exp);
        }
        GridBagConstraints gbc=new GridBagConstraints();
        pnlBodyForm.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,0,15,50);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblName,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        txtName.setText(name);
        pnlBodyForm.add(txtName,gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,50,15,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblAddress,gbc);

        gbc.gridx=4;
        gbc.gridy=0;
        gbc.gridwidth=3;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        txtAddress.setText(address);
        pnlBodyForm.add(txtAddress,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,50,15,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblAge,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        txtAge.setText(dob);
        pnlBodyForm.add(txtAge,gbc);

        gbc.gridx=3;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,50,15,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblContact,gbc);



        gbc.gridx=4;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        txtContact.setText(contact);
        pnlBodyForm.add(txtContact,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,50,15,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblDisease,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        txtDisease.setText(disease);
        pnlBodyForm.add(txtDisease,gbc);

        gbc.gridx=3;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,50,15,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblGender,gbc);

        gbc.gridx=4;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,0,35,0);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        if(Gender.equals("Male")) {
            comboBoxGender.setSelectedIndex(0);
        } if(Gender.equals("Female")){
            comboBoxGender.setSelectedIndex(1);
        } else {
            comboBoxGender.setSelectedIndex(2);
        }
        pnlBodyForm.add(comboBoxGender,gbc);

        lblUser.setPreferredSize(new Dimension(10,33));

        txtUser.setPreferredSize(new Dimension(100,33));

        lblPassword.setPreferredSize(new Dimension(10,33));

        txtPassword.setPreferredSize(new Dimension(100,33));

        pnlBodyImage.setPreferredSize(new Dimension(400,0));

        JPanel pnlImageSide = new JPanel();
        pnlImageSide.setLayout(new FlowLayout());

        JPanel pnlMedicinePrescription = new JPanel();
        JPanel pnlMedicinePrescriptionTitle = new JPanel();
        JLabel lblMedicinePrescription = new JLabel("Medicine Prescription");
        JPanel pnlAddInfo = new JPanel();
        JLabel lblTablet = new JLabel("Name Of Tablet");
        JTextArea txtTablet = new JTextArea();
        JLabel lblDescription = new JLabel("Description");
        JTextArea txtDescription = new JTextArea(10,10);
        JPanel pnlBtnAdd = new JPanel();
        JButton btnAdd = new JButton("Add Report");

       lblMedicinePrescription.setFont(new Font("",Font.BOLD,18));
       lblMedicinePrescription.setHorizontalAlignment(JLabel.CENTER);

        pnlMedicinePrescriptionTitle.setLayout(new BorderLayout());
        pnlMedicinePrescriptionTitle.add(lblMedicinePrescription,BorderLayout.CENTER);
        pnlMedicinePrescriptionTitle.setPreferredSize(new Dimension(0,30));

        lblTablet.setFont(new Font("",Font.BOLD,16));
        lblTablet.setHorizontalAlignment(JLabel.CENTER);
        lblDescription.setFont(new Font("",Font.BOLD,16));
        lblDescription.setHorizontalAlignment(JLabel.CENTER);

        pnlAddInfo.setLayout(new GridLayout(4,1));
        pnlAddInfo.setBorder(new EmptyBorder(0,180,0,180));
        pnlAddInfo.add(lblTablet);
        pnlAddInfo.add(txtTablet);
        pnlAddInfo.add(lblDescription);
        pnlAddInfo.add(txtDescription);

        btnAdd.setFocusable(false);
        btnAdd.setBorderPainted(false);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if(txtTablet.getText()!=null && txtDescription.getText()!=null){
                        int rep_id = 0;
                        int docId = 0;
                        ResultSet rs = db.reportAuto();
                        while(rs.next()){
                            rep_id = rs.getInt("AUTO_INCREMENT");
                        }
                        db.insertReport(txtTablet.getText(),txtDescription.getText());
                        ResultSet doc_id = db.doctorPatient(uname);
                        if(doc_id.next()){
                           docId = doc_id.getInt("D_ID");
                        }
                        db.insertCreates(id,rep_id,docId,apt_id);
                        db.updateStatus(apt_id,"completed");
                    }


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        pnlBtnAdd.setPreferredSize(new Dimension(0,50));
        pnlBtnAdd.setLayout(new FlowLayout());
        pnlBtnAdd.add(btnAdd);

        pnlMedicinePrescription.setBorder(new EmptyBorder(25,5,25,5));
        pnlMedicinePrescription.setLayout(new BorderLayout());
        pnlMedicinePrescription.setPreferredSize(new Dimension(800,100));
        pnlMedicinePrescription.add(pnlMedicinePrescriptionTitle,BorderLayout.NORTH);
        pnlMedicinePrescription.add(pnlAddInfo,BorderLayout.CENTER);
        pnlMedicinePrescription.add(pnlBtnAdd,BorderLayout.SOUTH);

        pnlBodyMain.add(pnlBodyForm,BorderLayout.CENTER);
        pnlBodyMain.add(pnlMedicinePrescription,BorderLayout.EAST);

        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);
        pnlBody.add(pnlBodyMain,BorderLayout.CENTER);

        pnlFooter.setPreferredSize(new Dimension(0,100));
        pnlFooter.setLayout(new BorderLayout());

        this.setLayout(new BorderLayout(5,5));
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);
    }
}
