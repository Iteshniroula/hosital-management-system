package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;



public class PatientDoctorDetails extends JPanel{

    JLabel lblTitle = new JLabel("Doctor Details");
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

    JTable tblappointment;
    DefaultTableModel tblmdl;

    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();

    private JLabel backgroundImage(JPanel pnl2) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel background = new JLabel(new ImageIcon(new ImageIcon("images/doctordetails.jpg").getImage().getScaledInstance((int) size.width, (int) size.height, Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());
        background.add(pnl2, BorderLayout.CENTER);
        return background;

    }



    PatientDoctorDetails(String uname){

        JPanel pnlBackground = new JPanel();

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

        pnlBody.setLayout(new BorderLayout(5,25));
        pnlBodyHead.setLayout(new FlowLayout(1,15,10));
        pnlBodyHead.setPreferredSize(new Dimension(0,50));
        lblSearch.setHorizontalAlignment(JLabel.RIGHT);
        lblSearch.setFont(new Font("",Font.BOLD,28));
        lblSearch.setPreferredSize(new Dimension(200,30));
        txtSearch.setPreferredSize(new Dimension(550,40));
        comboBoxCategory.setPreferredSize(new Dimension(180,40));
        comboBoxCategory.setFont(new Font("",Font.BOLD,14));
        pnlBodyHead.add(lblSearch);
        pnlBodyHead.add(txtSearch);
        pnlBodyHead.add(comboBoxCategory);
        pnlBodyHead.setOpaque(false);
        pnlBodyMain.setOpaque(false);

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
        pnlBody.setOpaque(false);

        pnlFooter.setPreferredSize(new Dimension(0,50));
        pnlFooter.setOpaque(false);

        pnlBackground.setOpaque(false);
        pnlBackground.setLayout(new BorderLayout());
        pnlBackground.add(pnlTitle,BorderLayout.NORTH);
        pnlBackground.add(pnlBody,BorderLayout.CENTER);
        pnlBackground.add(pnlFooter,BorderLayout.SOUTH);

        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(backgroundImage(pnlBackground));

    }
}
