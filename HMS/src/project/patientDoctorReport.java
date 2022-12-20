package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class patientDoctorReport extends JPanel{
    JLabel lblTitle = new JLabel("List of appointment");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();

    JTable tblreports;
    DefaultTableModel tblmdl;
    JPanel pnlBackground = new JPanel();
    String[][] data = {};
    String[] coloum = {"A_Id","Doctor Name","Date","Timing","Disease","status"};

    JButton btnViewDetails = new JButton("View Details");

    patientDoctorReport(String uname) throws BadLocationException {


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

        tblmdl = new DefaultTableModel(data,coloum){
            @Override
            public boolean  isCellEditable(int row, int column) {
                return false;
            }
        };

        tblreports = new JTable(tblmdl);
        tblreports.setPreferredScrollableViewportSize(new Dimension(1200,1200));
        tblreports.setFillsViewportHeight(true);
        tblreports.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tblreports);
        scrollPane.setBackground(Color.WHITE);

        pnlBody.add(scrollPane);

        btnViewDetails.setBorderPainted(false);
        btnViewDetails.setFocusable(false);
        btnViewDetails.setFont(new Font("",Font.BOLD,16));
        btnViewDetails.setEnabled(false);
        btnViewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                patientRecordetails patientRecordetails = null;
                try {
                    patientRecordetails = new patientRecordetails(uname,Integer.parseInt(tblreports.getValueAt(tblreports.getSelectedRow(),0).toString()));
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                }

                removeAll();
                repaint();
                revalidate();

                add(patientRecordetails,BorderLayout.CENTER);
                repaint();
                revalidate();
                patientRecordetails.btnBack.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeAll();
                        repaint();
                        revalidate();

                        add(pnlBackground,BorderLayout.CENTER);
                    }
                });
            }
        });



        pnlFooter.setLayout(new FlowLayout(1,5,15));
        pnlFooter.setPreferredSize(new Dimension(0,80));
        pnlFooter.add(btnViewDetails);

        pnlBackground.setLayout(new BorderLayout());
        pnlBackground.add(pnlTitle,BorderLayout.NORTH);
        pnlBackground.add(pnlBody,BorderLayout.CENTER);
        pnlBackground.add(pnlFooter,BorderLayout.SOUTH);

        tblreports.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblreports.getSelectedRow();
                if(Objects.equals(tblreports.getValueAt(row, 5).toString(), "completed")){
                    btnViewDetails.setEnabled(true);
                }else {
                    btnViewDetails.setEnabled(false);
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.add(pnlBackground,BorderLayout.CENTER);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                btnViewDetails.setEnabled(false);
            }

        });
    }
    }

