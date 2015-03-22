/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015gui;

import com.distribuidos.sd12015.data.ClaseConError;
import com.distribuidos.sd12015.data.ClaseConOk;
import com.distribuidos.sd12015.models.Huesped;
import com.thoughtworks.xstream.XStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristian
 */
public class HuespedsGUI extends javax.swing.JDialog {

    XStream miStream = new XStream();

    URL url;
    HttpURLConnection conn;
    InputStream is;
    int codigo_http;

    private void updateTable() {
        try {
            url = new URL(main.DOMAIN + "Huespeds");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

            is = conn.getInputStream();
            codigo_http = conn.getResponseCode();
            if (codigo_http / 100 != 2) {
                System.out.println("Error HTTP " + codigo_http);
            } else {
                List<Huesped> huespeds = (List<Huesped>) miStream.fromXML(is);
                DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
                model.setRowCount(0);
                for (Huesped h : huespeds) {
                    model.addRow(new Object[]{h.getNIF(), h.getNombre(), h.getApellidos()});
                }
            }
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra servicio");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra servicio");
        }
    }

    /**
     * Creates new form HuespedsGUI
     *
     * @param parent
     * @param modal
     */
    public HuespedsGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.jPanel1.setEnabled(false);
        updateTable();

        this.jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = jTable1.rowAtPoint(e.getPoint());
                if (r >= 0 && r < jTable1.getRowCount()) {
                    jTable1.setRowSelectionInterval(r, r);
                } else {
                    jTable1.clearSelection();
                }

                final int rowindex = jTable1.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    System.out.println("ola k ase?");
                    JPopupMenu popup = new JPopupMenu();
                    ActionListener menuListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            System.out.println("Popup menu item ["
                                    + event.getActionCommand() + "] was pressed.");
                            String nif = (String) jTable1.getModel().getValueAt(rowindex, 0);
                            if (event.getActionCommand().compareTo("Ver") == 0) {
                                ShowHuespedGUI show = new ShowHuespedGUI(null, true, nif);
                                show.setVisible(true);
                            } else if (event.getActionCommand().compareTo("Editar") == 0) {
                                EditHuespedGUI edit = new EditHuespedGUI(null, true, nif);
                                edit.setVisible(true);
                            } else if (event.getActionCommand().compareTo("Borrar") == 0) {
                                int dialogButton = JOptionPane.YES_NO_OPTION;
                                int dialogResult = JOptionPane.showConfirmDialog(null, "¿Deseas borrar?", "Peligro", dialogButton);
                                if (dialogResult == 0) {
                                    try {
                                        System.out.println("Yes option");

                                        url = new URL(main.DOMAIN + "EliminarHuesped");

                                        conn = (HttpURLConnection) url.openConnection();
                                        conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml
                                        conn.setRequestMethod("POST");

                                        String params = "huesped.NIF=" + nif;
                                        conn.setDoOutput(true);
                                        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                                        wr.writeBytes(params);
                                        wr.flush();
                                        wr.close();

                                        is = conn.getInputStream();
                                        codigo_http = conn.getResponseCode();
                                        if (codigo_http / 100 != 2) {
                                            System.out.println("Error HTTP " + codigo_http);
                                        } else {
                                            Object o = miStream.fromXML(is);
                                            if (o instanceof ClaseConOk) {
                                                ClaseConOk ok = (ClaseConOk) o;
                                                if (ok.isOk()) {
                                                    JOptionPane.showMessageDialog(null, "Borrado con éxito");
                                                    updateTable();
                                                } else {
                                                    JOptionPane.showMessageDialog(null, "Error al borrar");
                                                }
                                            } else if (o instanceof ClaseConError) {
                                                ClaseConError error = (ClaseConError) o;
                                                JOptionPane.showMessageDialog(null, error.getMensajeError());
                                            }
                                        }
                                    } catch (MalformedURLException ex) {
                                        JOptionPane.showMessageDialog(null, "No se encuentra servicio");
                                    } catch (IOException ex) {
                                        JOptionPane.showMessageDialog(null, "No se encuentra servicio");
                                    }

                                } else {
                                    System.out.println("No Option");
                                }
                            }

                        }
                    };
                    JMenuItem item;
                    popup.add(item = new JMenuItem("Ver", new ImageIcon("1.gif")));
                    item.setHorizontalTextPosition(JMenuItem.RIGHT);
                    item.addActionListener(menuListener);
                    popup.add(item = new JMenuItem("Editar", new ImageIcon("2.gif")));
                    item.setHorizontalTextPosition(JMenuItem.RIGHT);
                    item.addActionListener(menuListener);
                    popup.add(item = new JMenuItem("Borrar", new ImageIcon("3.gif")));
                    item.setHorizontalTextPosition(JMenuItem.RIGHT);
                    item.addActionListener(menuListener);

                    popup.setLabel("Justification");
                    popup.setBorder(new BevelBorder(BevelBorder.RAISED));
                    popup.addPopupMenuListener(new PopupPrintListener());

                    addMouseListener(new MousePopupListener());
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    // An inner class to check whether mouse events are the popup trigger
    class MousePopupListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            checkPopup(e);
        }

        public void mouseClicked(MouseEvent e) {
            checkPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            checkPopup(e);
        }

        private void checkPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                //popup.show(PopupMenuExample.this, e.getX(), e.getY());
            }
        }
    }

    // An inner class to show when popup events occur
    class PopupPrintListener implements PopupMenuListener {

        public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            System.out.println("Popup menu will be visible!");
        }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            System.out.println("Popup menu will be invisible!");
        }

        public void popupMenuCanceled(PopupMenuEvent e) {
            System.out.println("Popup menu is hidden!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Huespeds"));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIF", "Nombre", "Apellidos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddHuespedGUI ah = new AddHuespedGUI(null, true);
        ah.setVisible(true);
        System.out.println("Actualizamos tabla");
        updateTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HuespedsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HuespedsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HuespedsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HuespedsGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HuespedsGUI dialog = new HuespedsGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
