package com.elle.analyster.presentation;

import com.elle.analyster.admissions.Authorization;
import com.elle.analyster.controller.DataManager;
import com.elle.analyster.database.DBConnection;
import com.elle.analyster.database.ModifiedTableData;
import com.elle.analyster.logic.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * AddRecordsWindow
 *
 * @author Louis W.
 * @author Carlos Igreja
 * @author Xiaoqian Fu
 * @since June 10, 2015
 * @version 0.6.3
 */
public class AddRecordsWindow extends JFrame {

    // attributes
    private String[] columnNames;
    private int numRowsAdded;           // number of rows added counter
    
    private Statement statement;

    // components
    private AnalysterWindow analyster;
    private DataManager dataManager;
    private BaseTab tab;
    private LogWindow logWindow;
    private DefaultTableModel model;
    private ShortCutSetting ShortCut;

    private Color defaultSelectedBG;

    private ArrayList<Integer> rowsNotEmpty; // only includes rows that have data

    // used to notify if the table is editing
    // the table.isEditing method has issues from the tableModelListener
    private boolean isEditing;

    /**
     * Creates new form AddRecordsWindow
     */
    public AddRecordsWindow(BaseTab tab) {

        rowsNotEmpty = new ArrayList<>();
        isEditing = false;

        // initialize components
        initComponents();
        analyster = AnalysterWindow.getInstance();
        dataManager = DataManager.getInstance();
        this.tab = tab;
        
        logWindow = analyster.getLogWindow();
        
        statement = analyster.getStatement();

       
        // get default selected bg color
        defaultSelectedBG = table.getSelectionBackground();

        // create a new empty table
        createEmptyTable();
        
        //populate it with the first selected table row
        populateTableWithSelectedRow();

        // sets the keyboard focus manager
        setKeyboardFocusManager();

        // add listeners
        addTableListeners();

        // submit button does not start enabled because the table is empty
        btnSubmit.setEnabled(false);
        
        // add copy+paste short cut into table
        InputMap ip = (InputMap) UIManager.get("TextField.focusInputMap");
        ShortCut.copyAndPasteShortCut(ip);

        // set the label header
        this.setTitle("Add Records to " + table.getName());

        // set this window to appear in the middle of Analyster
        this.setLocationRelativeTo(analyster);

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
        btnSubmit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAddRow = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(894, 560));

        scrollpane.setBorder(null);
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

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAddRow.setText("+");
        btnAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRowActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddRow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancel)
                .addContainerGap())
            .addComponent(scrollpane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancel, btnClear, btnSubmit});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSubmit)
                    .addComponent(btnCancel)
                    .addComponent(btnAddRow)
                    .addComponent(btnClear))
                .addGap(6, 6, 6))
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

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

    }//GEN-LAST:event_tableMouseClicked

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed

    }//GEN-LAST:event_tableKeyPressed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed

        try {
            submit();
            this.dispose();
            this.transferFocus();
        } catch (Exception ex) {
            Logger.getLogger(AddRecordsWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
        this.transferFocus();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed

        // add an empty row to the table
        model.addRow(new Object[]{});
    }//GEN-LAST:event_btnAddRowActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        model.setRowCount(0);
        model.addRow(new Object[]{});
    }//GEN-LAST:event_btnClearActionPerformed

    /**
     * submit This is used when the submit button is pressed or if the enter key
     * is pressed when the table is finished editing to submit the data to the
     * database.
     */
    private void submit() throws Exception {

        int numRowsAdded = 0;
        // check if data is valid
        if (validateData()) {
            switch(tab.getTable().getName()) {
                case "Assignments": {
                    ArrayList<Object[]> rowsData = getRowsData();
                    if (rowsData.size() > 0){
                        dataManager.insertAssignmentsFromRows(rowsData);
                        numRowsAdded = rowsData.size();
                    }
                        
                    break;
                }
                case "Reports" :{
                    ArrayList<Object[]> rowsData = getRowsData();
                    if (rowsData.size() > 0){
                        dataManager.insertReportsFromRows(rowsData);
                        numRowsAdded = rowsData.size();
                    }
                        
                    break;
                }
                default: break;
            }
            
            if (numRowsAdded > 0) {
                // update table and records label
                tab.reloadTable();

                String text = numRowsAdded + " Add successfully!";
                analyster.setInformationLabel(text, 10);                // show information label in project manager that upload was successful
                                                         
            }
        }
        
        
    }
    
    
    private ArrayList<Object[]> getRowsData() {
        int row = 0;
        int col = 0;
        ArrayList<Object[]> rowsData = new ArrayList();
        for(row = 0; row < table.getRowCount(); row++) {
            Object[] data = new Object[table.getColumnCount()+1];
            data[0] = -1;
            boolean isValid = true;
            for (col = 0; col < table.getColumnCount(); col++) {
                //if symbol is empty, skip
                if (table.getValueAt(row, 0).toString().equals("")) {
                    isValid = false;
                    break;
                }
                data[col+1] =  table.getValueAt(row, col);
            }
            
            if (isValid) rowsData.add(data);
                        
        }
        
        
        return rowsData;       
           
    }
    

    /**
     * setKeyboardFocusManager Sets the Keyboard Focus Manager
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

                if (e.getComponent() instanceof JTable) {

                    // this is called to either clear data or submit data
                    if (e.getKeyCode() == KeyEvent.VK_ENTER && !table.isEditing()) {

                        // clear the row(s)
                        if (e.getID() == KeyEvent.KEY_PRESSED) {
                            if (table.getSelectionBackground() == Color.RED) {
                                int[] rows = table.getSelectedRows();

                                if (rows != null) {
                                    for (int row : rows) {
                                        for (int col = 0; col < table.getColumnCount(); col++) {
                                            table.getModel().setValueAt("", row, col);
                                        }
                                    }
                                }
                                table.setSelectionBackground(defaultSelectedBG);

                                // check for empty rows/table
                                checkForEmptyRows();
                                if (rowsNotEmpty.isEmpty()) {
                                    btnSubmit.setEnabled(false);
                                } else {
                                    btnSubmit.setEnabled(true);
                                }
                            } // submit the data
                            else if (table.getSelectionBackground() != Color.RED) {
                                try {
                                    submit();
                                } catch (Exception ex) {
                                    Logger.getLogger(AddRecordsWindow.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    } // this toggles the red bg for clearing row data
                    else if (e.getKeyCode() == KeyEvent.VK_DELETE) {

                        if (e.getID() == KeyEvent.KEY_RELEASED) {
                            if (table.isEditing()) {
                                table.getCellEditor().stopCellEditing();
                            }

                            if (table.getSelectionBackground() == defaultSelectedBG) {
                                table.setSelectionBackground(Color.RED);
                            } else {
                                table.setSelectionBackground(defaultSelectedBG);
                            }
                        }
                    }
                     // ctrl + D fills in the current date
                else if (e.getKeyCode() == KeyEvent.VK_D && e.isControlDown()) {
                    JTable table = (JTable) e.getComponent();
                    int column = table.getSelectedColumn();
                    
                    if (table.getColumnName(column).toLowerCase().contains("date")) {
                        if (e.getID() != 401) {
                            return false;
                        } else {
//                            JTextField selectCom = (JTextField) e.getComponent();
//                            selectCom.requestFocusInWindow();
//                            selectCom.selectAll();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = new Date();
                            String today = dateFormat.format(date);
                            int row = table.getSelectedRow();
                            table.setValueAt(today, row, column);
//                            selectCom.setText(today);
                        }// default date input with today's date}
                    }
                }

                } // end table component condition
               

                return false;
            }
        });
    }

    /**
     * createEmptyTable creates an empty table with default 10 rows
     */
    private void createEmptyTable() {
        // get column names for selected Analyster table
        columnNames = tab.getTableColNames();

        // we don't want the ID column 
        columnNames = Arrays.copyOfRange(columnNames, 1, columnNames.length);

        // set the table model - add 10 empty rows
        model = new DefaultTableModel(columnNames,0);

        // add the table model to the table
        table.setModel(model);

        // get table column width format
        float[] widths = tab.getColWidthPercent();
        widths = Arrays.copyOfRange(widths, 1, widths.length);

        //analyster.setColumnFormat(widths, table);
       
    }
    
    
    private void populateTableWithSelectedRow() {
        
        JTable selectedTable = tab.getTable();
        int[] selectedRows = selectedTable.getSelectedRows();
        
        if (selectedRows.length > 0) {
            DefaultTableModel selectedModel = (DefaultTableModel)selectedTable.getModel();
            Vector rowdata = (Vector) selectedModel.getDataVector().elementAt(selectedRows[0]);
            Vector rowcopy = (Vector)rowdata.clone();  
            rowcopy.remove(0);
            //add the selected row to the table
            model.addRow(rowcopy);
            
        }
        else{
            model.addRow(new Object[]{});
        }
        
        
        
        
        
        
    }

    /**
     * addTableListeners This is called to add the listeners to the table The
     * listeners added are the TableModel listener the MouseListener and the
     * KeyListener
     */
    public void addTableListeners() {

        // add tableModelListener
        table.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {

                // isEditing is a class boolean triggered true on double click
                if (!isEditing) {
                    int row = e.getFirstRow();            // row index
                    int col = e.getColumn();             // column index
                    System.out.println("we are now at: " + row + " " + col);
                    if (col >= 0) {
                        // if clearing row then do not validate
                        if (table.getSelectionBackground() != Color.RED) {
                            // check the cell for valid entry
                            validateCell(row, col);
                        }

                        // get value of cell
                        Object value = table.getValueAt(row, col);
                        System.out.println("Its value is: " + value);

                        // if cell value is empty
                        if (value == null || value.equals("")) {
                            // check to see if it was a deletion
                            if (!rowsNotEmpty.isEmpty() && rowsNotEmpty.contains(row)) {
                                checkForEmptyRows();
                            }
                        } // else add the row to the list as not empty
                        else {
                            rowsNotEmpty.add(row);
                        }
                    }

                    // if list is empty then the table is empty
                    if (!rowsNotEmpty.isEmpty() && !isEditing) {
                        btnSubmit.setEnabled(true);
                    }
                }

                // reset isEditing boolean
                isEditing = false;
            }

        });

        // add mouseListener
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    // if we click away the red delete should go away
                    if (table.getSelectionBackground() == Color.RED && !e.isControlDown()) {
                        table.setSelectionBackground(defaultSelectedBG);
                    }
                } // this enters edit mode
                else if (e.getClickCount() == 2) {
                    btnSubmit.setEnabled(false);
                    isEditing = true;
                    selectAllText(e);
                }
            }
        });
    }

    /**
     * selectAllText Select all text inside jTextField or a cell
     *
     * @param e
     */
    private void selectAllText(MouseEvent e) {

        JTable table = (JTable) e.getComponent();
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        if (column != -1) {
            table.getComponentAt(row, column).requestFocus();
            table.editCellAt(row, column);
            JTextField selectCom = (JTextField) table.getEditorComponent();
            if (selectCom != null) {
                selectCom.requestFocusInWindow();
                selectCom.selectAll();
            }
        }
    }

    /**
     * validateCell
     *
     * @param row
     * @param col
     * @return returns true if valid or false if error
     */
    public boolean validateCell(int row, int col) {

        String colName = table.getColumnName(col);           // column name
        Object cellValue = table.getValueAt(row, col);       // store cell value
        String errorMsg = "Error with " + colName
                + " in row " + (row + 1) + ".\n";            // error message
        boolean error = false;                               // error occurred

        switch (colName) {
            case "symbol":
                if (cellValue == null || cellValue.toString().equals("")) {
                    errorMsg += "Symbol cannot be null";
                    error = true;
                }
                break;
            case "analyst":
                break;
            case "priority":
                if (cellValue != null && !cellValue.toString().equals("")) {
                    if (!cellValue.toString().matches("[1-5]{1}")) {
                        errorMsg += "Priority must be an Integer (1-5)";
                        error = true;
                    }
                }
                break;
            case "dateAssigned":
                if (cellValue != null && !cellValue.toString().equals("")) {
                    if (!Validator.isValidDate("yyyy-MM-dd", cellValue.toString())) {
                        errorMsg += "Date format not correct: YYYY-MM-DD";
                        error = true;
                    }
                }
                break;
            case "dateDone":
                if (cellValue != null && !cellValue.toString().equals("")) {
                    if (!Validator.isValidDate("yyyy-MM-dd", cellValue.toString())) {
                        errorMsg += "Date format not correct: YYYY-MM-DD";
                        error = true;
                    }
                }
                break;
            case "notes":
                break;
            case "author":
                break;
            case "analysisDate":
                if (cellValue != null && !cellValue.toString().equals("")) {
                    if (!Validator.isValidDate("yyyy-MM-dd", cellValue.toString())) {
                        errorMsg += "Date format not correct: YYYY-MM-DD";
                        error = true;
                    }
                }
                break;
            case "path":
                break;
            case "document":
                break;
            case "notesL":
                break;
            default:
                break;

        }// end switch

        if (error) {
            JOptionPane.showMessageDialog(table, errorMsg);
            //btnSubmit.setEnabled(true); 
        }

        return !error;  // if there was an error, return false for failed
    }

    /**
     * validateData Validates all the data in the table to make sure it is
     * correct. This is used to validate the data before it is executed to the
     * server and the database so that there will not be any errors.
     *
     * @return returns true if the data is all valid and false if the is a
     * single error
     */
    public boolean validateData() {

        int col = 0;                    // column index
        boolean isCellValid = true;    // if cell is valid entry 

        // if table is empty
        if (!rowsNotEmpty.isEmpty()) {

            // check data
            for (int row : rowsNotEmpty) {

                // if there was an error stop
                if (!isCellValid) {
                    break;
                }

                for (col = 0; col < table.getColumnCount(); col++) {

                    // if there was an error stop
                    if (!isCellValid) {
                        break;
                    }

                    // begin error message
                    isCellValid = validateCell(row, col);

                }// end col for loop
            }// end row for loop

            return isCellValid;
        }

        return false; // table is empty
    }

    /**
     * checkForEmptyRows This should be used when data is removed or deleted
     */
    public void checkForEmptyRows() {

        ArrayList<Integer> arrayCopy = new ArrayList(rowsNotEmpty);
        rowsNotEmpty.clear();

        // check List for empty rows
        for (int row : arrayCopy) {
            boolean isNotEmpty = false;
            for (int col = 0; col < table.getColumnCount(); col++) {
                Object value = table.getValueAt(row, col);
                if (value != null && !value.equals("")) {
                    isNotEmpty = true;
                    break;
                }
            }
            if (isNotEmpty) {
                rowsNotEmpty.add(row);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

}
