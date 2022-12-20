package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AdminBed  extends JPanel {
    JLabel lblTitle = new JLabel("Bed Details");
    JPanel pnlbtnDashboard = new JPanel();
    JButton btnDashboard = new JButton();
    ImageIcon iconHome = new ImageIcon(new ImageIcon("images/home.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
    JPanel pnlTitle = new JPanel();
    JPanel pnlBody = new JPanel();
    JPanel pnlFooter = new JPanel();
    JLabel lblSearch = new JLabel("Search");
    JTextField txtSearch = new JTextField();

    JPanel pnlBodyHead = new JPanel();
    JPanel pnlBodyMain = new JPanel();
    JLabel lblCategory = new JLabel("Category");
    String[] catagegory ={"ICU","Emergency","CCU"};
    JComboBox comboBoxCategory = new JComboBox(catagegory);
    JLabel lblRoomNo = new JLabel("Room No");
    JTextField txtRoomNo = new JTextField();

    JPanel pnlFooterBtn = new JPanel();
    JButton btnAdd = new JButton("Add Bed Details");
    JButton btnDelete = new JButton("Delete Bed Details");
    JButton btnUpdate = new JButton("Update Bed Details");
    JPanel pnlFooterTable = new JPanel();

    DefaultTableModel tblMdl;
    JTable tblBed;


    AdminBed(String uname){

        Data_base db = new Data_base();

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
        pnlBodyMain.setLayout(new GridLayout(3,2,30,50));
        pnlBodyMain.setPreferredSize(new Dimension(950,500));
        pnlBodyMain.setBorder(new EmptyBorder(80,500,80,0));


        lblCategory.setFont(new Font("",Font.BOLD,16));
        lblCategory.setHorizontalAlignment(JLabel.RIGHT);

        lblRoomNo.setFont(new Font("",Font.BOLD,16));
        lblRoomNo.setHorizontalAlignment(JLabel.RIGHT);

        pnlBodyMain.add(lblCategory);
        pnlBodyMain.add(comboBoxCategory);
        pnlBodyMain.add(lblRoomNo);
        pnlBodyMain.add(txtRoomNo);


        pnlBody.add(pnlBodyMain,BorderLayout.WEST);
        pnlBody.add(pnlBodyHead,BorderLayout.NORTH);

        btnAdd.setPreferredSize(new Dimension(200,50));
        btnAdd.setFont(new Font("",Font.BOLD,16));
        btnAdd.setBorderPainted(false);
        btnAdd.setFocusable(false);
        btnAdd.setBackground(Color.WHITE);
        btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAdd.addActionListener(e -> {
            int flag = 0;
            DefaultTableModel tblAdd = (DefaultTableModel) tblBed.getModel();
            if (Objects.equals(txtRoomNo.getText(), "") && txtRoomNo.getText() == null) {
                flag = 0;
            } else {
                flag = 1;
            }
            if (flag == 1) {
                String id = "";
                try {
                    ResultSet rs = db.BedAuto();
                    while (rs.next()) {
                        id = rs.getString("AUTO_INCREMENT");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    db.insertBed(comboBoxCategory.getSelectedItem().toString(), txtRoomNo.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                String item[] = {id,comboBoxCategory.getSelectedItem().toString(),txtRoomNo.getText()};
                tblAdd.addRow(item);
            } else {
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
            DefaultTableModel tbldel = (DefaultTableModel) tblBed.getModel();
            if(tblBed.getSelectedRowCount()==1){

                try {
                    db.deleteBed(Integer.parseInt(tblBed.getValueAt(tblBed.getSelectedRow(),0).toString()));
                    JOptionPane.showMessageDialog(null,"Data has been deleted");
                    txtRoomNo.setText("");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                tbldel.removeRow(tblBed.getSelectedRow());
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
            if(tblBed.getSelectedRowCount()==1){
                try {
                    db.updateBed(Integer.parseInt(tblBed.getValueAt(tblBed.getSelectedRow(),0).toString()),comboBoxCategory.getSelectedItem().toString(),txtRoomNo.getText());
                    JOptionPane.showMessageDialog(null,"Data has been updated");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null,"Please select data from table");
            }
        });

        pnlFooterBtn.setPreferredSize(new Dimension(300,0));
        pnlFooterBtn.setBorder(new EmptyBorder(30,20,30,20));
        pnlFooterBtn.setLayout(new FlowLayout(20,50,30));
        pnlFooterBtn.add(btnAdd);
        pnlFooterBtn.add(btnDelete);
        pnlFooterBtn.add(btnUpdate);

        Object Data[][] = {};
        Object Coloums[] ={"Bed_Id","Category","Room No"};

        tblMdl = new DefaultTableModel(Data, Coloums) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblBed = new JTable(tblMdl);

        tblBed.setPreferredScrollableViewportSize(new Dimension(1200, 1600));
        tblBed.setFillsViewportHeight(true);

        JScrollPane scroll = new JScrollPane(tblBed);
        scroll.setBackground(Color.WHITE);

        pnlFooterTable.add(scroll);

        tblBed.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int row = tblBed.getSelectedRow();
                if(tblBed.getSelectedRowCount() == 1) {
                    try {
                        ResultSet rs = db.Bed(Integer.parseInt(tblBed.getValueAt(row,0).toString()));
                        while (rs.next()){
                            txtRoomNo.setText(rs.getString("B_Roomno"));
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        pnlFooter.setPreferredSize(new Dimension(0,300));
        pnlFooter.setLayout(new BorderLayout());
        pnlFooter.add(pnlFooterBtn,BorderLayout.WEST);
        pnlFooter.add(pnlFooterTable,BorderLayout.CENTER);


        this.setLayout(new BorderLayout(15,15));
        this.add(pnlTitle,BorderLayout.NORTH);
        this.add(pnlBody,BorderLayout.CENTER);
        this.add(pnlFooter,BorderLayout.SOUTH);

    }
}
