package project;

import com.toedter.calendar.JDateChooser;

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
import java.text.SimpleDateFormat;
import java.util.Objects;

public class AdminDoctor<gender> extends JPanel {

    JDateChooser date = new JDateChooser();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String chooseDate;

    JLabel lblTitle = new JLabel("Doctor Details");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblSearch = new JLabel("Search");
    JTextField txtSearch = new JTextField();

    JLabel lblName = new JLabel("Name");
    JTextField txtName = new JTextField();
    JLabel lblAddress = new JLabel("Address");
    JTextField txtAddress = new JTextField();
    JLabel lblDOB = new JLabel("Date of birth");
    JLabel lblCatagorey = new JLabel("Specialization");
    String[] Category = {"Heart","Brain","Head","Eye"};
    JComboBox comboboxCategory = new JComboBox(Category);
    JLabel lblContact = new JLabel("Contact");
    JTextField txtContact = new JTextField();
    JLabel lblTiming = new JLabel("Timing");
    JTextField txtTiming = new JTextField();
    JLabel lblDays = new JLabel("Days");
    JCheckBox chkSun = new JCheckBox("Sun");
    JCheckBox chkMon = new JCheckBox("Mon");
    JCheckBox chkTue = new JCheckBox("Tue");
    JCheckBox chkWen = new JCheckBox("Wen");
    JCheckBox chkThr = new JCheckBox("Thr");
    JCheckBox chkFri = new JCheckBox("Fri");
    JCheckBox chkSat = new JCheckBox("Sat");
    JLabel lblGender = new JLabel("Gender");
    String[] gender ={"Male","Female","Others"};
    JComboBox comboBox= new JComboBox(gender);
    JLabel lblPhoto = new JLabel();
    JButton btnBrowse = new JButton("Browse");
    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();
    JPanel pnlBodyForm = new JPanel();
    JPanel pnlBodyImage = new JPanel();
    JLabel lblUser = new JLabel("Username");
    JTextField txtUser = new JTextField();
    JLabel lblPassword = new JLabel("Password");
    JPasswordField txtPassword = new JPasswordField();

    JPanel pnlFooterBtn = new JPanel();
    JButton btnAdd = new JButton("Add Doctor Details");
    JButton btnDelete = new JButton("Delete Doctor Details");
    JButton btnUpdate = new JButton("Update Doctor Details");
    JPanel pnlFooterTable = new JPanel();

    JFileChooser chooser = new JFileChooser();
    DefaultTableModel tblMdl;
    JTable tblDoctor;
    String filename;

    Data_base db = new Data_base();
    String Id;

    int Check=0;


    AdminDoctor(String Username){


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
        lblSearch.setPreferredSize(new Dimension(100,30));
        txtSearch.setPreferredSize(new Dimension(350,40));
        pnlBodyHead.add(lblSearch);
        pnlBodyHead.add(txtSearch);
        pnlBodyMain.setLayout(new BorderLayout());

        lblName.setFont(new Font("",Font.BOLD,16));
        txtName.setText("");

        lblDOB.setFont(new Font("",Font.BOLD,16));

        lblGender.setFont(new Font("",Font.BOLD,16));

        lblUser.setFont(new Font("",Font.BOLD,16));
        txtUser.setText("");

        lblAddress.setFont(new Font("",Font.BOLD,16));
        txtAddress.setText("");

        lblPassword.setFont(new Font("",Font.BOLD,16));
        txtPassword.setText("");

        lblTiming.setFont(new Font("",Font.BOLD,16));
        txtTiming.setText("");
        txtTiming.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    if(c!='-'){
                        e.consume();
                    }
                }
            }
        });

        lblCatagorey.setFont(new Font("",Font.BOLD,16));

        lblDays.setFont(new Font("",Font.BOLD,16));


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

        GridBagConstraints gbc=new GridBagConstraints();
        pnlBodyForm.setLayout(new GridBagLayout());
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblName,gbc);

        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(txtName,gbc);

        gbc.gridx=3;
        gbc.gridy=0;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblAddress,gbc);

        gbc.gridx=4;
        gbc.gridy=0;
        gbc.gridwidth=3;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(txtAddress,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblDOB,gbc);

        gbc.gridx=1;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(date,gbc);

        gbc.gridx=3;
        gbc.gridy=1;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblCatagorey,gbc);


        gbc.gridx=4;
        gbc.gridy=1;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(comboboxCategory,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblTiming,gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        gbc.gridwidth=2;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=6.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(txtTiming,gbc);

        JPanel pnlDays = new JPanel();
        pnlDays.setLayout(new FlowLayout());
        pnlDays.setPreferredSize(new Dimension(10,50));
        pnlDays.add(chkSun);
        pnlDays.add(chkMon);
        pnlDays.add(chkTue);
        pnlDays.add(chkWen);
        pnlDays.add(chkThr);
        pnlDays.add(chkFri);
        pnlDays.add(chkSat);

        gbc.gridx=3;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblContact,gbc);

        gbc.gridx=4;
        gbc.gridy=2;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(35,10,35,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(txtContact,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblGender,gbc);

        gbc.gridx=1;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
//        gbc.insets=new Insets(10,35,10,35);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(comboBox,gbc);

        gbc.gridx=3;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,10,15,10);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(lblDays,gbc);

        gbc.gridx=4;
        gbc.gridy=3;
        gbc.gridwidth=1;
        gbc.gridheight=1;
        gbc.insets=new Insets(15,5,15,5);
        gbc.weightx=3.0;
        gbc.weighty=1.0;
        gbc.anchor=GridBagConstraints.EAST;
        gbc.fill=GridBagConstraints.BOTH;
        pnlBodyForm.add(pnlDays,gbc);

        lblPhoto.setPreferredSize(new Dimension(200,200));
        lblPhoto.setBorder(new LineBorder(Color.black,1,true));

        btnBrowse.setPreferredSize(new Dimension(200,50));
        btnBrowse.setFont(new Font("",Font.BOLD,16));
        btnBrowse.setBorderPainted(false);
        btnBrowse.setFocusable(false);
        btnBrowse.setBackground(Color.WHITE);
        btnBrowse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGES","png","jpg","jpeg","tiff");
                chooser.addChoosableFileFilter(filter);
                int result = chooser.showOpenDialog(null);
                chooser.setAcceptAllFileFilterUsed(false);
               if(result == JFileChooser.APPROVE_OPTION){
                   File file = chooser.getSelectedFile();
                   filename = file.getAbsolutePath();
                   Image getAbsolutetePath = null;
                   ImageIcon icon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblPhoto.getWidth(),lblPhoto.getHeight(),Image.SCALE_SMOOTH));
                   lblPhoto.setIcon(icon);
               }
            }
        });

        lblUser.setPreferredSize(new Dimension(10,33));

        txtUser.setPreferredSize(new Dimension(100,33));

        lblPassword.setPreferredSize(new Dimension(10,33));

        txtPassword.setPreferredSize(new Dimension(100,33));

        pnlBodyImage.setPreferredSize(new Dimension(400,0));

        JPanel pnlImageSide = new JPanel();
        pnlImageSide.setLayout(new FlowLayout());
        pnlImageSide.add(lblPhoto);
        pnlImageSide.add(btnBrowse);

        JPanel pnlLoginInfoSide = new JPanel();
        pnlLoginInfoSide.setBorder(new EmptyBorder(10,50,10,50));
        pnlLoginInfoSide.setLayout(new GridLayout(2,2,5,5));
        pnlLoginInfoSide.setPreferredSize(new Dimension(250,100));
        pnlLoginInfoSide.add(lblUser);
        pnlLoginInfoSide.add(txtUser);
        pnlLoginInfoSide.add(lblPassword);
        pnlLoginInfoSide.add(txtPassword);

        pnlBodyImage.setLayout(new BorderLayout());
        pnlBodyImage.add(pnlImageSide,BorderLayout.CENTER);
        pnlBodyImage.add(pnlLoginInfoSide,BorderLayout.SOUTH);


        pnlBodyMain.add(pnlBodyForm,BorderLayout.CENTER);
        pnlBodyMain.add(pnlBodyImage,BorderLayout.EAST);

        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);
        pnlBody.add(pnlBodyMain,BorderLayout.CENTER);

        btnAdd.setPreferredSize(new Dimension(200,50));
        btnAdd.setFont(new Font("",Font.BOLD,16));
        btnAdd.setBorderPainted(false);
        btnAdd.setFocusable(false);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(e -> {
            DefaultTableModel tblAdd =(DefaultTableModel) tblDoctor.getModel();
            int flag = 0;
            char [] charPass = txtPassword.getPassword();
            String password = new String(charPass);
            String pass = password;

            if(date.getDate() != null) {
                String chooseDate = df.format(date.getDate());

                if (Objects.equals(txtName.getText(), "") && txtName.getText() == null && Objects.equals(txtAddress.getText(), "") && txtAddress.getText() == null && Objects.equals(chooseDate, "") && Objects.equals(txtTiming.getText(), "") && txtTiming.getText() == null && Objects.equals(txtContact.getText(), "") && txtContact.getText() == null) {
                    flag = 0;
                } else {
                    if (Objects.equals(txtUser.getText(), "")) {
                        flag = 0;
                    } else if (pass.length() <= 0) {
                        flag = 0;
                    } else if (lblPhoto.getIcon() == null) {
                        flag = 0;
                    } else {
                        flag = 1;
                        if (chkSun.isSelected() || chkMon.isSelected() || chkTue.isSelected() || chkWen.isSelected() || chkThr.isSelected() || chkFri.isSelected() || chkSat.isSelected()) {
                            flag = 1;
                        } else {
                            flag = 0;
                        }
                    }
                }
                if (flag == 1) {
                    String id = "";
                    try {
                        ResultSet rs = db.doctorauto();
                        while (rs.next()){
                            id = rs.getString("AUTO_INCREMENT");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    String Days = "";
                    if (chkSun.isSelected()) {
                        Days = Days+"Sunday";
                    }
                    if (chkMon.isSelected()) {
                        Days = Days+",Monday";
                    }
                    if (chkTue.isSelected()) {
                        Days = Days+",Tuesday";
                    }
                    if (chkWen.isSelected()) {
                        Days = Days+",Wenesday";
                    }
                    if (chkThr.isSelected()) {
                        Days = Days+",Thrusday";
                    }
                    if (chkFri.isSelected()) {
                        Days = Days+",Friday";
                    }
                    if (chkSat.isSelected()) {
                        Days = Days+",Saturday";
                    }

                    String image = filename;
                    image = image.replace("\\","\\\\");

                    try{
                        ResultSet rs = db.user();
                        while(rs.next()){
                            String user = rs.getString("Username");
                            if(user == txtUser.getText()){
                                Check=1;
                            }else {
                                Check = 0;
                            }
                        }
                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }

                    if(Check==0){
                        try{
                            db.insertUser(txtUser.getText(),txtPassword.getText(),"Doctor");

                        }catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        try {
                            ResultSet rs = db.user(txtUser.getText());
                            if(rs.next()){
                                Id = Integer.toString(rs.getInt("U_Id"));
                            }else {
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        try {

                            db.insertDoctor(txtName.getText(),txtAddress.getText(), Date.valueOf(chooseDate), comboboxCategory.getSelectedItem().toString(),txtTiming.getText(),txtContact.getText(), Objects.requireNonNull(comboBox.getSelectedItem()).toString(),Days,image,Integer.parseInt(Id));
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        String item[] = {id, txtName.getText(), chooseDate, comboboxCategory.getSelectedItem().toString(), txtTiming.getText(), Days, txtUser.getText()};
                        tblAdd.addRow(item);
                    }else {
                        JOptionPane.showMessageDialog(null,"Username already exist");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Fill the data correctly");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Fill the data correctly");
            }
        });

        btnDelete.setPreferredSize(new Dimension(200,50));
        btnDelete.setFont(new Font("",Font.BOLD,16));
        btnDelete.setBorderPainted(false);
        btnDelete.setFocusable(false);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(e -> {
            int uid = 0;
            DefaultTableModel tbldel = (DefaultTableModel) tblDoctor.getModel();
            if(tblDoctor.getSelectedRowCount()==1){
                try {
                    db.deleteDoctor(Integer.parseInt(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),0).toString()));
                    JOptionPane.showMessageDialog(null,"Data has been deleted");
                    txtName.setText("");
                    txtAddress.setText("");
                    chooseDate = null;
                    txtTiming.setText("");
                    txtContact.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    ResultSet rs = db.doctoruser(Integer.parseInt(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),0).toString()));
                    while (rs.next()){
                        uid = Integer.parseInt(rs.getString("U_Id"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                try {
                    db.deleteUser(uid);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                tbldel.removeRow(tblDoctor.getSelectedRow());
            }else {
                JOptionPane.showMessageDialog(null,"Please Choose a data");
            }
        });

        btnUpdate.setPreferredSize(new Dimension(200,50));
        btnUpdate.setFont(new Font("",Font.BOLD,16));
        btnUpdate.setBorderPainted(false);
        btnUpdate.setFocusable(false);
        btnUpdate.setBackground(Color.WHITE);
        btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(e -> {
        if(tblDoctor.getSelectedRowCount()==1){
            String Days="";
            if (chkSun.isSelected()) {
                Days = Days+"Sunday";
            }
            if (chkMon.isSelected()) {
                Days = Days+",Monday";
            }
            if (chkTue.isSelected()) {
                Days = Days+",Tuesday";
            }
            if (chkWen.isSelected()) {
                Days = Days+",Wenesday";
            }
            if (chkThr.isSelected()) {
                Days = Days+",Thrusday";
            }
            if (chkFri.isSelected()) {
                Days = Days+",Friday";
            }
            if (chkSat.isSelected()) {
                Days = Days+",Saturday";
            }
            String chooseDate = df.format(date.getDate());
            try {
                db.updateDoctor(Integer.parseInt(tblDoctor.getValueAt(tblDoctor.getSelectedRow(),0).toString()),txtName.getText(),txtAddress.getText(),Date.valueOf(chooseDate), comboboxCategory.getSelectedItem().toString(),txtTiming.getText(),txtContact.getText(),comboBox.getSelectedItem().toString(),Days);
                JOptionPane.showMessageDialog(null,"Data has been updated");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null,"Plese select data from table");
        }
        });

        pnlFooterBtn.setPreferredSize(new Dimension(300,0));
        pnlFooterBtn.setBorder(new EmptyBorder(30,20,30,20));
        pnlFooterBtn.setLayout(new FlowLayout(20,50,30));
        pnlFooterBtn.add(btnAdd);
        pnlFooterBtn.add(btnDelete);
        pnlFooterBtn.add(btnUpdate);

        Object Data[][] = {};
        Object Coloums[] ={"Doctor_Id","Doctor Name","Age","Specialization","Timing","Days","Username"};

        tblMdl = new DefaultTableModel(Data, Coloums) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblDoctor = new JTable(tblMdl);

        tblDoctor.setPreferredScrollableViewportSize(new Dimension(1200, 1200));
        tblDoctor.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tblDoctor);
        scroll.setBackground(Color.WHITE);

        pnlFooterTable.add(scroll);

        tblDoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblDoctor.getSelectedRow();
                if(tblDoctor.getSelectedRowCount() == 1) {
                    try {
                        ResultSet rs = db.Doctor(Integer.parseInt(tblDoctor.getValueAt(row,0).toString()));
                        while (rs.next()){
                            txtName.setText(rs.getString("D_Name"));
                            txtAddress.setText(rs.getString("D_Address"));
                            date.setDate(rs.getDate("DOB"));
                            comboboxCategory.setSelectedItem(rs.getString("Specification"));
                            txtTiming.setText(rs.getString("Timing"));
                            txtContact.setText(rs.getString("Contact"));
                            String image = rs.getString("Photo");
                            image.replace("\\\\","\\");
                            ImageIcon icon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(lblPhoto.getWidth(),lblPhoto.getHeight(),Image.SCALE_SMOOTH));
                            lblPhoto.setIcon(icon);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        pnlFooter.setPreferredSize(new Dimension(0,300));
        pnlFooter.setLayout(new BorderLayout());
        pnlFooter.add(pnlFooterBtn,BorderLayout.WEST);
        pnlFooter.add(pnlFooterTable,BorderLayout.CENTER);

        this.setLayout(new BorderLayout(5,5));
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);
    }

}
