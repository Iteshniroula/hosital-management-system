package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.sql.ResultSet;

public class patientRecordetails extends JPanel {
    JLabel lblTitle = new JLabel("Report");
    JPanel pnlbtnBack = new JPanel();
    JButton btnBack = new JButton("Back");
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();

    JLabel lblUpperBorder = new JLabel("\tMy Hospital\n");
    JLabel lblLowerBorder = new JLabel("***********************************************\n");
    JLabel lblName = new JLabel("Name: ");
    JLabel lblAge = new JLabel("\n\nAge: ");
    JLabel lblGender = new JLabel("\n\nGender: ");
    JLabel lblDisease = new JLabel("\n\nDisease: ");
    JLabel lblDescription = new JLabel("\n\nDescription: ");
    JLabel lblMedicine = new JLabel("\n\nMedicine: ");

    JPanel pnlbill = new JPanel();
    JTextPane txtInformation = new JTextPane();

    Data_base db =new Data_base();

    patientRecordetails(String uname, int Apt_Id) throws BadLocationException {

        String date = null;
        String name = null;
        String Age = null;
        String Gender = null;
        String Disease = null;
        String Description = null;
        String Medicine = null;


        try {
            ResultSet rs = db.treatmentDetails(Apt_Id);
            while (rs.next()) {
             date = rs.getString("date");
             name = rs.getString("P_Name");
             ResultSet age = db.patientAge(rs.getInt("P_Id"));
             if(age.next()){
                 Age = age.getString("Age");
             }
             Gender = rs.getString("Gender");
             Disease = rs.getString("Disease");
             Description = rs.getString("Descriptions");
             Medicine = rs.getString("Tablets");
            }
        }catch (Exception exp){
            System.out.println(exp);
        }
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

        JLabel lblDate = new JLabel("\tDate:  "+date+"    \n");

        pnlbtnBack.add(btnBack);
        pnlTitle.add(lblTitle,BorderLayout.CENTER);
        pnlTitle.add(pnlbtnBack,BorderLayout.EAST);

        txtInformation.setEditable(false);
        txtInformation.setFocusable(false);
        txtInformation.setPreferredSize(new Dimension(1000,700));


        pnlbill.setLayout(new BorderLayout());
        pnlbill.add(txtInformation,BorderLayout.CENTER);

        Font font = new Font("",Font.BOLD,28);

        StyledDocument documentStyle = txtInformation.getStyledDocument();
        SimpleAttributeSet rightAttribute = new SimpleAttributeSet();


        StyleConstants.setAlignment(rightAttribute,StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontSize(rightAttribute,font.getSize());
        rightAttribute.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(),rightAttribute,false);
        txtInformation.setText(lblDate.getText());


        try{
            SimpleAttributeSet centerAttribute = new SimpleAttributeSet();

            StyleConstants.setFontSize(centerAttribute,font.getSize());
            StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
            documentStyle.setParagraphAttributes(documentStyle.getLength(), documentStyle.getLength(), centerAttribute, true);
            centerAttribute.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
            documentStyle.insertString(documentStyle.getLength(),lblUpperBorder.getText(),centerAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblLowerBorder.getText(),centerAttribute);

            SimpleAttributeSet leftAttribute = new SimpleAttributeSet();
            StyleConstants.setFontSize(leftAttribute,font.getSize());
            StyleConstants.setAlignment(leftAttribute,StyleConstants.ALIGN_LEFT);
            documentStyle.setParagraphAttributes(documentStyle.getLength(),documentStyle.getLength(),leftAttribute,true);
            leftAttribute.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
            documentStyle.insertString(documentStyle.getLength(),lblName.getText()+name,leftAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblAge.getText()+Age,leftAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblGender.getText()+Gender,leftAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblDisease.getText()+Disease,leftAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblDescription.getText()+Description,leftAttribute);
            documentStyle.insertString(documentStyle.getLength(),lblMedicine.getText()+Medicine,leftAttribute);


        }catch (Exception exp){
            System.out.println("Fuck you");
        }

        pnlBody.add(pnlbill);

        this.setLayout(new BorderLayout(0,60));
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);

    }
}
