import javax.swing.table.DefaultTableModel;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.awt.*;

public class Menu extends javax.swing.JFrame {
    List list = new List();
    DefaultTableModel tableModel;

    int namePos = 0, surnamePos = 1, genderPos = 2, birthDatePos = 3, agePos = 4, idPos = 5, remHolidayPos = 6,
            reqHolidayPos = 7;

    public Menu() {
        initComponents();

        addCol("Name");
        addCol("Surname");
        addCol("Gender");
        addCol("Birth Date");
        addCol("Age");
        addCol("ID");
        addCol("Remining holidays");
        addCol("Requested holidays");

        list.load(list.fileName);
        fetchList();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        SaveButton = new javax.swing.JButton();
        RefreshButton = new javax.swing.JButton();
        NewEmployeeButton = new javax.swing.JButton();
        DeleteEmployeeButton = new javax.swing.JButton();
        EditEmployeeButton = new javax.swing.JButton();
        HolidayReqButton = new javax.swing.JButton();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        ExitButton = new javax.swing.JButton();
        SaveAsButton = new javax.swing.JButton();
        OpenFileButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Employee Managament System");
        setBackground(new java.awt.Color(0, 0, 37));
        setMaximumSize(new java.awt.Dimension(1280, 960));
        setMinimumSize(new java.awt.Dimension(1280, 960));

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        RefreshButton.setText("Refresh");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        NewEmployeeButton.setText("New");
        NewEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewEmployeeButtonActionPerformed(evt);
            }
        });

        DeleteEmployeeButton.setText("Del");
        DeleteEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteEmployeeButtonActionPerformed(evt);
            }
        });

        EditEmployeeButton.setText("Edit");
        EditEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditEmployeeButtonActionPerformed(evt);
            }
        });

        HolidayReqButton.setText("Reqest Holiday");
        HolidayReqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HolidayReqButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(jTable);

        ExitButton.setText("Exit");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        SaveAsButton.setText("Save as");
        SaveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveAsButtonActionPerformed(evt);
            }
        });

        OpenFileButton.setText("Open file");
        OpenFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenFileButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup().addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(RefreshButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(EditEmployeeButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(HolidayReqButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                        .addComponent(ExitButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                layout.createSequentialGroup()
                                        .addComponent(DeleteEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(NewEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(SaveAsButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(OpenFileButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(SaveButton, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addContainerGap().addGroup(layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup().addComponent(RefreshButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EditEmployeeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(HolidayReqButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(DeleteEmployeeButton).addComponent(NewEmployeeButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SaveButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SaveAsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(OpenFileButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ExitButton)))
                        .addContainerGap()));

        pack();
    }

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        list.save(list.fileName);
    }

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteElements();
        fetchList();
    }

    private void HolidayReqButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int rem = Integer.parseInt(list.getNode(jTable.getSelectedRow(), remHolidayPos));
        int req = Integer.parseInt(list.getNode(jTable.getSelectedRow(), reqHolidayPos));
        String input = JOptionPane.showInputDialog(null, "Employee: " + list.getNode(jTable.getSelectedRow(), 0)
                + "\nRemaining holidays: " + list.getNode(jTable.getSelectedRow(), remHolidayPos),
                JOptionPane.QUESTION_MESSAGE);

        if (!input.isEmpty()) {
            if (input.matches("\\d+")) {
                int in = Integer.parseInt(input);
                if (in <= rem) {
                    list.setNode(jTable.getSelectedRow(), remHolidayPos, String.valueOf(rem - in));
                    list.setNode(jTable.getSelectedRow(), reqHolidayPos, String.valueOf(req + in));
                } else {
                    JOptionPane.showMessageDialog(null, "Input exceeds remaning holidays","Error!",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (jTable.getSelectedColumn() == birthDatePos || jTable.getSelectedColumn() == agePos) {
            list.setAge(jTable.getSelectedRow());
        }
        deleteElements();
        fetchList();
    }

    private void EditEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String input = JOptionPane.showInputDialog(null, "Employee: " + list.getNode(jTable.getSelectedRow(), 0),
                JOptionPane.QUESTION_MESSAGE);
        if (!input.isEmpty()) {
            list.setNode(jTable.getSelectedRow(), jTable.getSelectedColumn(), input);
        }
        if (jTable.getSelectedColumn() == birthDatePos || jTable.getSelectedColumn() == agePos) {
            list.setAge(jTable.getSelectedRow());
        }
        deleteElements();
        fetchList();
    }

    private void NewEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = JOptionPane.showInputDialog(null, "Name: ", JOptionPane.QUESTION_MESSAGE);
        String surname = JOptionPane.showInputDialog(null, "Surname: ", JOptionPane.QUESTION_MESSAGE);
        String gender = JOptionPane.showInputDialog(null, "Gender: ", JOptionPane.QUESTION_MESSAGE);
        String birthDate = JOptionPane.showInputDialog(null, "Birth Date(yyyy-mm-dd): ", JOptionPane.QUESTION_MESSAGE);
        String remHolidays = JOptionPane.showInputDialog(null, "Remaning Holidays: ", JOptionPane.QUESTION_MESSAGE);
        String reqHolidays = JOptionPane.showInputDialog(null, "Requested Holidays: ", JOptionPane.QUESTION_MESSAGE);
        if (name.matches("[A-Za-z]+") && surname.matches("[A-Za-z]+") && !gender.isEmpty()
                && birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            list.nEmployee(name, surname, gender, birthDate,
                    Integer.toString(Integer.parseInt(list.getNode(list.totalEmployees() - 1, idPos)) + 1), remHolidays,
                    reqHolidays);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input");
        }
        deleteElements();
        fetchList();
    }

    private void DeleteEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        list.rEmployee(jTable.getSelectedRow());
        deleteElements();
        fetchList();
    }

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        list.exit();
    }

    private void SaveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser jfs = new JFileChooser("./");
        jfs.setAcceptAllFileFilterUsed(false);
        jfs.addChoosableFileFilter(new FileNameExtensionFilter(".csv", "csv"));
        jfs.showSaveDialog(null);

        File file = new File(jfs.getSelectedFile().getAbsolutePath());

        list.save(file.toString());
    }

    private void OpenFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser jfs = new JFileChooser("./");
        jfs.setAcceptAllFileFilterUsed(false);
        jfs.addChoosableFileFilter(new FileNameExtensionFilter(".csv", "csv"));
        jfs.showOpenDialog(null);

        File file = new File(jfs.getSelectedFile().getAbsolutePath());

        list.eList.clear();
        deleteElements();

        list.fileName = file.toString();
        list.load(list.fileName);
        fetchList();
    }

    void addCol(String value) {
        tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addColumn(value);
    }

    void addRow(Object[] value) {
        tableModel = (DefaultTableModel) jTable.getModel();
        tableModel.addRow(value);
    }

    void deleteElements() {
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
    }

    void fetchList() {
        for (int i = 0; i < list.totalEmployees(); i++) {
            addRow(list.returnArray(i));
        }
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    private javax.swing.JButton DeleteEmployeeButton;
    private javax.swing.JButton EditEmployeeButton;
    private javax.swing.JButton HolidayReqButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton NewEmployeeButton;
    private javax.swing.JButton OpenFileButton;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton SaveAsButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
}
