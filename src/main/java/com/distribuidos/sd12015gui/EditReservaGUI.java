/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.distribuidos.sd12015gui;

import com.distribuidos.sd12015.Common;
import com.distribuidos.sd12015.data.ClaseConError;
import com.distribuidos.sd12015.data.ClaseConOk;
import com.distribuidos.sd12015.models.Huesped;
import com.distribuidos.sd12015.models.Reserva;
import static com.distribuidos.sd12015gui.main.DOMAIN;
import com.thoughtworks.xstream.XStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristian
 */
public class EditReservaGUI extends javax.swing.JDialog {

    XStream miStream = new XStream();

    List<Huesped> huespeds = null;
    String nif;
    int habitacion;
    int index;

    /**
     * Creates new form EditReservaGUI
     */
    public EditReservaGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public EditReservaGUI(java.awt.Frame parent, boolean modal, String nif_, Date date) {
        super(parent, modal);
        initComponents();
        this.nif = nif_;
        URL url;
        HttpURLConnection conn;
        InputStream is;
        int codigo_http;
        try {
            url = new URL(main.DOMAIN + "VerReserva?NIF=" + nif_ + "&fechaInicio=" + Common.dateToStr(date));
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

            is = conn.getInputStream();
            codigo_http = conn.getResponseCode();
            if (codigo_http / 100 != 2) {
                System.out.println("Error HTTP " + codigo_http);
            } else {
                Object o = miStream.fromXML(is);
                try {
                    Reserva rr = (Reserva) o;
                    System.out.println(rr);
                    
                    this.fechaInicio.setText(Common.dateToStr(rr.getFechaEntrada()));
                    this.fechaSalida.setText(Common.dateToStr(rr.getFechaSalida()));
                    habitacion = rr.getHabitacion();

                } catch (ClassCastException ex) {
                    ClaseConError err = (ClaseConError) o;
                    JOptionPane.showMessageDialog(null, err.getCodigoError() + " - " + err.getMensajeError());
                }
                try {
                    url = new URL(main.DOMAIN + "Huespeds");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml

                    is = conn.getInputStream();
                    codigo_http = conn.getResponseCode();
                    if (codigo_http / 100 != 2) {
                        System.out.println("Error HTTP " + codigo_http);
                    } else {
                        huespeds = (List<Huesped>) miStream.fromXML(is);
                        int n = 0;
                        for (Huesped h : huespeds) {
                            if(h.getNIF().equals(nif_)) {
                                index = n;
                            }
                            n++;
                            this.huespedsBox.addItem(h.getNombre() + " " + h.getApellidos());
                        }
                        this.huespedsBox.setSelectedIndex(index);
                    }
                } catch (MalformedURLException ex) {
                    JOptionPane.showMessageDialog(null, "No se encuentra servicio");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "No se encuentra servicio");
                }
            }
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra servicio");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se encuentra servicio");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Fecha incorrecta");
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
        jLabel1 = new javax.swing.JLabel();
        huespedsBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechaInicio = new javax.swing.JTextField();
        fechaSalida = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Añadir Reserva"));

        jLabel1.setText("NIF:");

        jLabel2.setText("Fecha Entrada:");

        jLabel3.setText("Fecha Salida: ");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fechaSalida))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(huespedsBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaInicio)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 194, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(huespedsBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(fechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
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
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // TODO: Validar los campos
        boolean checkInit = false, checkFinal = false;
        List<String> errors = new LinkedList<String>();
        if (fechaInicio.getText().isEmpty()) {
            errors.add("Inserta fecha nacimiento");
        } else {
            try {
                Common.strToDate(fechaInicio.getText());
                checkInit = true;
            } catch (ParseException ex) {
                errors.add("Fecha entrada incorrecta");
            }
        }
        if (fechaSalida.getText().isEmpty()) {
            errors.add("Inserta fecha salida");
        } else {
            try {
                Common.strToDate(fechaSalida.getText());
                checkFinal = true;
            } catch (ParseException ex) {
                errors.add("Fecha salida incorrecta");
            }
        }
        if (checkInit && checkFinal) {
            try {
                if (Common.strToDate(fechaSalida.getText()).
                        compareTo(Common.strToDate(fechaInicio.getText())) < 0) {
                    errors.add("Fecha de inicio debe ser antes que la de salida");
                }
            } catch (ParseException ex) {
                Logger.getLogger(AddReservaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, errors.toArray());
        } else {
            try {
                int index = this.huespedsBox.getSelectedIndex();
                Huesped h = huespeds.get(index);
                System.out.println(h);

                String dateInit = this.fechaInicio.getText();
                String dateFinish = this.fechaSalida.getText();
                Date fechaInit = Common.strToDate(dateInit);
                Date fechaFinish = Common.strToDate(dateFinish);

                System.out.println(fechaInit);
                System.out.println(fechaFinish);

                this.jButton1.setEnabled(false);
                URL url;
                HttpURLConnection conn;
                InputStream is;
                int codigo_http;

                url = new URL(DOMAIN + "EditarReserva");

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Accept", "text/xml");  // Pedimos formato xml
                conn.setRequestMethod("POST");

                String params
                        = "reserva.fechaInicio=" + Common.dateToStr(fechaInit)
                        + "&reserva.fechaSalida=" + Common.dateToStr(fechaFinish)
                        + "&reserva.habitacion=" + habitacion
                        + "&reserva.NIF=" + h.getNIF()
                        + "&reserva.oldNIF=" + nif;

                // Send post request
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
                    if (o instanceof ClaseConError) {
                        JOptionPane.showMessageDialog(null, "No se ha podido crear.");
                        System.out.println("No se ha podido crear.");
                        this.jButton1.setEnabled(true);
                    } else {
                        ClaseConOk ok = (ClaseConOk) o;
                        if (ok.isOk()) {
                            JOptionPane.showMessageDialog(null, "Agregado con éxito.");
                            System.out.println("Agregado con éxito.");
                            this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido crear.");
                            System.out.println("No se ha podido crear.");
                            this.jButton1.setEnabled(true);
                        }
                    }
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "Error de parseo");
            } catch (MalformedURLException ex) {
                JOptionPane.showMessageDialog(null, "No se encuentra servicio");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se encuentra servicio");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(EditReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditReservaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditReservaGUI dialog = new EditReservaGUI(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField fechaInicio;
    private javax.swing.JTextField fechaSalida;
    private javax.swing.JComboBox huespedsBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
