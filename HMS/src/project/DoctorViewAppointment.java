package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DoctorViewAppointment extends JPanel{

    AddReport addReport;

    JLabel lblTitle = new JLabel("Appointment");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblHeading = new JLabel("List of Appointment");
    JButton btnAdddetails = new JButton("Add Details");

    static JTable tblappointment;
    DefaultTableModel tblmdl;

    int back=0;
    

    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();



    static Data_base db = new Data_base();

    static int decline=0;
    static int completed = 0;
    static int approve = 0;

    private JLabel backgroundImage(JPanel pnl2) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        JLabel background = new JLabel(new ImageIcon(new ImageIcon("images/bg.jpg").getImage().getScaledInstance((int) size.width, (int) size.height, Image.SCALE_SMOOTH)));
        background.setLayout(new BorderLayout());
        background.add(pnl2, BorderLayout.CENTER);
        return background;

    }

    DoctorViewAppointment(String uname){

        String[][] Data = {};
        String[] coloum = {"Appointment Id","Patient_Id","Patient Name","Age","Disease","timing","Day","Status","Action Decline","Action approve","Action completed"};

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
        lblHeading.setHorizontalAlignment(JLabel.RIGHT);
        lblHeading.setFont(new Font("",Font.BOLD,28));
        lblHeading.setPreferredSize(new Dimension(300,30));
        pnlBodyHead.add(lblHeading);
        pnlBodyHead.setOpaque(false);
        pnlBodyMain.setOpaque(false);

        tblmdl = new DefaultTableModel(Data, coloum) {

            @Override
            public boolean isCellEditable(int row, int column) {
                try {
                    String result="";
                    if(tblappointment.getSelectedRow()>-1) {
                        result = tblappointment.getValueAt(tblappointment.getSelectedRow(), 7).toString();
                    }
                    if (Objects.equals(result, "pending")) {
                        System.out.println(result);
                        return column == 8 || column == 9 || column == 10;
                    } else if (Objects.equals(result, "decline")) {
                        return column == 8;
                    } else if (Objects.equals(result, "approve")) {
                        return column == 9 || column == 10;
                    } else {
                        return column == 10;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                return column == 8 || column == 9 || column == 10;
            }
        };



        tblappointment = new JTable(tblmdl);

        tblappointment.getColumnModel().getColumn(10).setCellRenderer(new ButtonRenderer());

        tblappointment.getColumnModel().getColumn(10).setCellEditor(new ButtonEditor(new JTextField()));

        tblappointment.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());

        tblappointment.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JTextField()));


        tblappointment.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());

        tblappointment.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor(new JTextField()));

        tblappointment.setPreferredScrollableViewportSize(new Dimension(1200, 1200));
        tblappointment.setFillsViewportHeight(true);

        tblappointment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane scroll = new JScrollPane(tblappointment);

        pnlBodyMain.add(scroll);
        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);
        pnlBody.add(pnlBodyMain,BorderLayout.CENTER);
        pnlBody.setOpaque(false);

        btnAdddetails.setFocusable(false);
        btnAdddetails.setBorderPainted(false);
        btnAdddetails.setFont(new Font("",Font.BOLD,16));
        btnAdddetails.setEnabled(false);
        btnAdddetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addReport = new AddReport(uname,Integer.parseInt(tblappointment.getValueAt(tblappointment.getSelectedRow(),1).toString()),Integer.parseInt(tblappointment.getValueAt(tblappointment.getSelectedRow(),0).toString()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                removeAll();
                repaint();
                revalidate();
                add(addReport,BorderLayout.CENTER);
                repaint();
                revalidate();


                    addReport.btnBack.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            removeAll();
                            repaint();
                            revalidate();
                            add(pnlBackground, BorderLayout.CENTER);
                            back=0;
                        }
                    });
                }
        });



        tblappointment.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                btnAdddetails.setEnabled(true);
            }
        });

        pnlFooter.setPreferredSize(new Dimension(0,50));
        pnlFooter.setOpaque(false);
        pnlFooter.setLayout(new FlowLayout());
        pnlFooter.add(btnAdddetails);

        pnlBackground.setOpaque(false);
        pnlBackground.setLayout(new BorderLayout());
        pnlBackground.add(pnlTitle,BorderLayout.NORTH);
        pnlBackground.add(pnlBody,BorderLayout.CENTER);
        pnlBackground.add(pnlFooter,BorderLayout.SOUTH);

        this.setOpaque(false);
        this.setLayout(new BorderLayout());
        this.add(backgroundImage(pnlBackground));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tblappointment.getSelectionModel().clearSelection();
                btnAdddetails.setEnabled(false);
            }

        });

    }

    static class ButtonRenderer extends JButton implements TableCellRenderer{

        public ButtonRenderer(){
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row, int column) {
            setText((obj==null)?"":obj.toString());
            return this;
        }
    }

    static class ButtonEditor extends DefaultCellEditor{

        protected JButton btn;
        private String lbl;
        private Boolean clicked;

        public  ButtonEditor(JTextField txt){
            super(txt);

            btn = new JButton();
            btn.setOpaque(true);


            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
            }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int column) {
            lbl=(obj==null)?"":obj.toString();
            btn.setText(lbl);
            clicked=true;
            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            if(clicked){
                tblappointment.setValueAt(lbl,tblappointment.getSelectedRow(),7);
                try {
                    db.updateStatus(Integer.parseInt(tblappointment.getValueAt(tblappointment.getSelectedRow(),0).toString()),lbl);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(lbl=="approve" ) {
                    approve =1;
                    JOptionPane.showMessageDialog(btn, "btn declined is disabled because the appointment status is approved");
                } else if(lbl=="decline"){
                    decline=1;
                    JOptionPane.showMessageDialog(btn,"btn approve and btn completed is disabled because the appointment status id declined");
                } else if(lbl == "completed"){
                    completed=1;
                    JOptionPane.showMessageDialog(btn,"btn approve and btn decline is disabled because the appointment status is completed");
                }
            }
            clicked=false;
            return lbl;
        }

        @Override
        public boolean stopCellEditing() {
            clicked=false;

            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
    }


