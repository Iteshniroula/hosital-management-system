package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Patient_Details extends JPanel {
    JLabel lblTitle = new JLabel("Patient Details");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblSearch = new JLabel("Patient Name:");
    JTextField txtSearch = new JTextField();

    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();

    JTable tblpatient;
    DefaultTableModel tblmdl;

    Data_base db = new Data_base();

    Patient_Details(String uname){
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
        pnlBodyHead.add(lblSearch);
        pnlBodyHead.add(txtSearch);

        String[][] Data = {};
        String[] coloum = {"Patient_Id","Patient Name","Age","Disease","Name of tablet","Description"};

        tblmdl = new DefaultTableModel(Data, coloum) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblpatient = new JTable(tblmdl);

        tblpatient.setPreferredScrollableViewportSize(new Dimension(1200, 1200));
        tblpatient.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tblpatient);


        pnlBodyMain.add(scroll);
        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);
        pnlBody.add(pnlBodyMain,BorderLayout.CENTER);

        this.setLayout(new BorderLayout(5,5));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                javax.swing.SwingUtilities.invokeLater(() -> requestFocusInWindow());
                tblpatient.getSelectionModel().clearSelection();
            }

        });
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);

    }
}


