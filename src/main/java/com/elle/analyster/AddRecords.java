/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elle.analyster;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 *
 * @author Louis W.
 */
public class AddRecords extends javax.swing.JFrame {

    @Autowired
    private Analyster ana;
    private LogWindow log = new LogWindow();
    private Vector columnNames = new Vector();
    private String tableName;
    private AddRecordsTable info = new AddRecordsTable();
    private Logger logger = LoggerFactory.getLogger(AddRecords.class);
    private GUI gui = new GUI();
    private int numRowsAdded;  // number of rows added counter
    private String selectedTable;

    /**
     * Creates new form ReportWin <-- does it really?
     */
    public AddRecords(Analyster a, LogWindow l) {
        ana = a;
        log = l;
        initComponents();
        this.setLocationRelativeTo(a);
        info.update(comboBoxTableSelect.getSelectedItem().toString(), ana); // sets tableservice tables - useless
        
        //initTable(6);   // without this, date column in assignments will be object with no type (instead of string object)
        // agian tableName? this was just set in update ?
        // this is never used
        tableName = comboBoxTableSelect.getSelectedItem().toString();
        
        // looks like declaring two vectors
        Vector tableDefault, table0;    // default content of table which includes empty rows
        List list = new ArrayList();

        int i = 6;
        // adds 6 empty rows
        while (i-- > 0) {    // add new empty rows
            String[] aaa = info.getEmptyRow();
            table0 = new Vector(Arrays.asList(aaa)); // initialize this vector
            list.add(table0);
        }
        
        // finally what I was looking for !
        columnNames = new Vector(Arrays.asList(info.getColumnTitles()));

        tableDefault = new Vector(list); //initialize this vector

        // finally set the table
        DefaultTableModel model = new DefaultTableModel(tableDefault, columnNames);
        table.setModel(model);
        
        setKeyboardFocusManager(); // sets the keyboard focus manager
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        scrollpane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        comboBoxTableSelect = new javax.swing.JComboBox();
        jSubmit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jCancel = new javax.swing.JButton();
        jAddRow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(550, 200));

        scrollpane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setMaximumSize(new java.awt.Dimension(260, 100));
        scrollpane.setMinimumSize(new java.awt.Dimension(130, 50));

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "symbol", "analyst", "priority", "dateAssigned", "note"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        scrollpane.setViewportView(table);

        comboBoxTableSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Assignments", "Reports" }));
        comboBoxTableSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTableSelectActionPerformed(evt);
            }
        });

        jSubmit.setText("Submit");
        jSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSubmitActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose table:");

        jCancel.setText("Cancel");
        jCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelActionPerformed(evt);
            }
        });

        jAddRow.setText("+");
        jAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxTableSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jAddRow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSubmit)
                        .addGap(18, 18, 18)
                        .addComponent(jCancel)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxTableSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSubmit)
                    .addComponent(jCancel)
                    .addComponent(jAddRow))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSubmitActionPerformed
//        Vector data, row = new Vector();
        String title = " (";    // element of Sql statement
        ArrayList<String> rows = new ArrayList<>();
        String rowData;
        boolean flag = false;   // if add successfully, set it true

        int i = 0, j = 0, num = 0;
        int colNum = table.getColumnCount();

        for (i = 0; i < colNum - 1; i++) {
            title += columnNames.get(i).toString() + ",";
        }
        title += columnNames.get(colNum - 1).toString() + ") ";

        // rows comprise all the new information for inserting
        i = 0;

        numRowsAdded = 0; // reset numRowsAdded counter
        
        while (i != table.getRowCount() && !table.getValueAt(i, 0).equals("")) {    // within accessible rows && not null next line
            rowData = "(";

            while (j < colNum - 1) {
                if (columnNames.get(j).toString().equals(info.getDateName())) {     // first, check date format if it's date column
                    if (table.getValueAt(i, j).toString().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
                        rowData += "'" + table.getValueAt(i, j).toString() + "',";
                    } else if (table.getValueAt(i, j).toString() == null) {
                        rowData += null + ",";    // default date for null input
                    } else {
                        JOptionPane.showMessageDialog(null, "Date format is incorrect!");
                        break;
                    }
                } else if (table.getValueAt(i, j).toString().equals("")) {      // second, check null
                    rowData += null + ",";
                } else {
                    rowData += "'" + table.getValueAt(i, j).toString() + "',";
                }
                j++;
            }
            if (table.getValueAt(i, j) == null) {
                rowData += null + ")";
            } else {
                rowData += "'" + table.getValueAt(i, j).toString() + "')";
            }
            rows.add(rowData);
            numRowsAdded++; // increment a row added to row added counter
            i++;
            j = 0;
        }
        num = i;

        // insert the new rows one by one
        for (i = 0; i < num; i++) {
            try {
                String sqlChange = "INSERT INTO " + tableName + title + " VALUES " + rows.get(i);
                System.out.println(sqlChange);
                gui.stmt.executeUpdate(sqlChange);
                log.sendMessages(sqlChange);
                flag = true;
            } catch (SQLException ex) {
                ex.getErrorCode();
                JOptionPane.showMessageDialog(null, "Upload failed! ");
            } catch (Exception ex) {
                logger.error("Error: ", ex);
                JOptionPane.showMessageDialog(null, "Error!");
                ex.getStackTrace();
            }
        }

        if (flag) {
            JOptionPane.showMessageDialog(null, "Add successfully!");
            ana.loadData();
            ana.setLastUpdateTime();
            
            // Code to get the table selected
            selectedTable = comboBoxTableSelect.getSelectedItem().toString();
            if(selectedTable.equals("Assignments"))
                selectedTable = ana.ASSIGNMENTS_TABLE_NAME;
            else
                selectedTable = ana.REPORTS_TABLE_NAME;
            
            // update total records with new records added
            ana.getTabs().get(selectedTable).addToTotalRowCount(numRowsAdded);
        }
        this.dispose();
    }//GEN-LAST:event_jSubmitActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void comboBoxTableSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTableSelectActionPerformed
        info.update(comboBoxTableSelect.getSelectedItem().toString(), ana);
        initTable(4);
    }//GEN-LAST:event_comboBoxTableSelectActionPerformed

    private void jCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_jCancelActionPerformed

    private void jAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddRowActionPerformed
        int rows = table.getRowCount();
        initTable(rows + 1);
    }//GEN-LAST:event_jAddRowActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed

    }//GEN-LAST:event_tableKeyPressed

    /**
     * This method is called by netbeans components?
     * I will have to investigate this
     * @param rows 
     */
    private void initTable(int rows) {}
    
    /**
     * setKeyboardFocusManager
     * Sets the Keyboard Focus Manager
     */
    private void setKeyboardFocusManager() {
        
        /*
         No Tab key-pressed or key-released events are received by the key event listener. This is because the focus subsystem 
         consumes focus traversal keys, such as Tab and Shift Tab. To solve this, apply the following to the component that is 
         firing the key events 
         */
        table.setFocusTraversalKeysEnabled(false);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {// Allow to TAB-

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_TAB) {
                    if (e.getComponent() instanceof JTable) {
                        JTable table = (JTable) e.getComponent();
                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();
                        if (column == table.getRowCount() || column == 0) {
                            return false;
                        } else {
                            table.getComponentAt(row, column).requestFocus();
                            table.editCellAt(row, column);
                            JTextField selectCom = (JTextField) table.getEditorComponent();
                            selectCom.requestFocusInWindow();
                            selectCom.selectAll();
                        }
                    }

                } else if (e.getKeyCode() == KeyEvent.VK_D && e.isControlDown()) {
                    JTable table = (JTable) e.getComponent().getParent();
                    int column = table.getSelectedColumn();
                    if (table.getColumnName(column).toLowerCase().contains("date")) {
                        if (e.getID() != 401) {
                            return false;
                        } else {
                            JTextField selectCom = (JTextField) e.getComponent();
                            selectCom.requestFocusInWindow();
                            selectCom.selectAll();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();
                            String today = dateFormat.format(date);
                            selectCom.setText(today);
                        }// default date input with today's date}
                    }

                }
                return false; 
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboBoxTableSelect;
    private javax.swing.JButton jAddRow;
    private javax.swing.JButton jCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton jSubmit;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
