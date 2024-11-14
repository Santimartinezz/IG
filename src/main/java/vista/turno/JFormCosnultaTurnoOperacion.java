/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.turno;

import java.util.HashMap;
import repositorio.dao.conexion.ConexionDB;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import modelo.turno.Turno;
import negocvio.abm.turno.ITurno;
import negocvio.abm.turno.TurnoOperacionServicio;

/**
 *
 * @author Alumno
 */
public class JFormCosnultaTurnoOperacion extends javax.swing.JFrame {
    
    DefaultTableModel modelTurnos;

    private ITurno iTurno=new TurnoOperacionServicio();
    private List<Turno> turnos=new ArrayList<Turno>();
    private ConexionDB conexionDB;
    /**
     * Creates new form JFormCosnultaTurno
     */
    public JFormCosnultaTurnoOperacion() {
        initComponents();
        modelTurnos = (DefaultTableModel)jTableCosnultaTurno.getModel();
        cargarModeloTablaTurnos(buscarTurnos(0));
        conexionDB = new ConexionDB();
    }
    private void searchTurnos(String dniVet, String nombreVet, String apellidoVet, 
                              String dniDue, String nombreDue, String apellidoDue, 
                              String nombreMascota, String fechaDesde, String fechaHasta) {

        String query = """
            SELECT t.fecha AS fecha_turno, v.dni AS dni_vet, v.nombre AS nombre_vet, 
                   v.apellido AS apellido_vet, d.dni AS dni_due, d.nombre AS nombre_due, 
                   d.apellido AS apellido_due, m.nombre AS mascota,
                   CASE WHEN m.tipo = 1 THEN 'Perro' ELSE 'Gato' END AS tipo_mascota
            FROM turnos t
            JOIN veterinarios v ON t.id_veterinario = v.id
            JOIN duenios d ON t.id_duenio = d.id
            JOIN mascotas m ON m.id_duenio = d.id
            JOIN personas pv ON v.id_persona = pv.id
            JOIN personas pd ON d.id_persona = pd.id
            WHERE (v.dni = ? OR ? IS NULL)
              AND (v.nombre LIKE ? OR ? IS NULL)
              AND (v.apellido LIKE ? OR ? IS NULL)
              AND (d.dni = ? OR ? IS NULL)
              AND (d.nombre LIKE ? OR ? IS NULL)
              AND (d.apellido LIKE ? OR ? IS NULL)
              AND (m.nombre LIKE ? OR ? IS NULL)
              AND (t.fecha >= ? OR ? IS NULL)
              AND (t.fecha <= ? OR ? IS NULL);
        """;

        // Creating a HashMap to hold query parameters
        HashMap<Integer, Object> params = new HashMap<>();
        params.put(0, dniVet); params.put(1, dniVet);
        params.put(2, "%" + nombreVet + "%"); params.put(3, nombreVet);
        params.put(4, "%" + apellidoVet + "%"); params.put(5, apellidoVet);
        params.put(6, dniDue); params.put(7, dniDue);
        params.put(8, "%" + nombreDue + "%"); params.put(9, nombreDue);
        params.put(10, "%" + apellidoDue + "%"); params.put(11, apellidoDue);
        params.put(12, "%" + nombreMascota + "%"); params.put(13, nombreMascota);

        try {
            // Parse date fields and add to params if not empty
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (fechaDesde != null && !fechaDesde.isEmpty()) {
                params.put(14, dateFormat.parse(fechaDesde));
                params.put(15, dateFormat.parse(fechaDesde));
            } else {
                params.put(14, null); // Set to null for non-matching
                params.put(15, null);
            }

            if (fechaHasta != null && !fechaHasta.isEmpty()) {
                params.put(16, dateFormat.parse(fechaHasta));
                params.put(17, dateFormat.parse(fechaHasta));
            } else {
                params.put(16, null); // Set to null for non-matching
                params.put(17, null);
            }

            // Use ConexionDB to execute query with parameters
            ResultSet rs = conexionDB.ejecutarConsultaSqlConParametros(query, params);
            modelTurnos.setRowCount(0); // Clear the table

            int countGatos = 0, countPerros = 0, countTurnos = 0;

            // Process the result set
            while (rs.next()) {
                String tipoMascota = rs.getString("tipo_mascota");
                modelTurnos.addRow(new Object[]{
                    rs.getString("fecha_turno"), rs.getString("dni_vet"), rs.getString("nombre_vet"), 
                    rs.getString("apellido_vet"), rs.getString("dni_due"), rs.getString("nombre_due"), 
                    rs.getString("apellido_due"), rs.getString("mascota"), tipoMascota
                });
                
                // Count logic
                if ("Perro".equals(tipoMascota)) countPerros++;
                if ("Gato".equals(tipoMascota)) countGatos++;
                countTurnos++;
            }

            // Update the count fields in UI
            CantTruno.setText(String.valueOf(countTurnos));
            CantPerros.setText(String.valueOf(countPerros));
            CantGatos.setText(String.valueOf(countGatos));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void cargarModeloTablaTurnos(List<Turno> mTurnos){
        modelTurnos.setNumRows(0);
        for(Turno turno:mTurnos){
            if (turno != null) {
            modelTurnos.addRow(new Object[]{
                turno.getFecha(),
                turno.getDuenio().getApellido(),
                turno.getDuenio().getNombre(),
                turno.getVeterinario().getNombre(),
                turno.getMascota().getNombre()
            });
        } else {
            System.out.println("Warning: Null turno entry encountered in cargarModeloTablaTurnos");
        }
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

        DniVet = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NomVet = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ApeVet = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCosnultaTurno = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        NomDue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ApeDue = new javax.swing.JTextField();
        DniDue = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        NomMasc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        BtnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CantGatos = new javax.swing.JTextField();
        CantTruno = new javax.swing.JTextField();
        CantPerros = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        FechaDesde = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        FechaHasta = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Dni");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido");

        ApeVet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApeVetActionPerformed(evt);
            }
        });

        jTableCosnultaTurno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha", "CodVet", "Nombre", "Apellido", "CodDue", "Nombre", "Apellido", "Mascota", "TipoMascota", "TipoTurno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableCosnultaTurno);

        jLabel4.setText("Nombre");

        jLabel5.setText("Apellido");

        ApeDue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApeDueActionPerformed(evt);
            }
        });

        jLabel6.setText("Dni");

        NomMasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NomMascActionPerformed(evt);
            }
        });

        jLabel7.setText("Nombre");

        jLabel8.setText("Codigo veterinario");

        jLabel9.setText("Codigo dueño");

        jLabel10.setText("Mascota");

        BtnBuscar.setText("BUSCAR");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        jLabel11.setText("Cantidad turno:");

        jLabel12.setText("Cantidad perros:");

        jLabel13.setText("Cantidad gatos:");

        CantGatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantGatosActionPerformed(evt);
            }
        });

        CantTruno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantTrunoActionPerformed(evt);
            }
        });

        CantPerros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantPerrosActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Historic", 1, 36)); // NOI18N
        jLabel14.setText("Consulta turno operacion");

        FechaDesde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaDesdeActionPerformed(evt);
            }
        });

        jLabel15.setText("Fecha Desde");

        jLabel16.setText("Fecha Hasta");

        FechaHasta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FechaHastaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CantTruno, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CantPerros, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CantGatos, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DniVet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NomVet, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ApeVet, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DniDue, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(NomDue, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ApeDue, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(348, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(NomMasc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)
                            .addComponent(jLabel16)
                            .addGap(18, 18, 18)
                            .addComponent(FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(BtnBuscar)
                            .addGap(98, 98, 98)))))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jLabel14)
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DniVet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(NomVet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ApeVet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DniDue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(NomDue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(ApeDue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NomMasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(FechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(FechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscar))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(CantTruno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(CantGatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CantPerros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        DniVet.getAccessibleContext().setAccessibleName("DniVet");
        NomVet.getAccessibleContext().setAccessibleName("NomVet");
        ApeVet.getAccessibleContext().setAccessibleName("ApeVet");
        NomDue.getAccessibleContext().setAccessibleName("NombDuenio");
        ApeDue.getAccessibleContext().setAccessibleName("ApeDuenio");
        DniDue.getAccessibleContext().setAccessibleName("DniDuenio");
        NomMasc.getAccessibleContext().setAccessibleName("NomMascota");
        NomMasc.getAccessibleContext().setAccessibleDescription("");
        CantGatos.getAccessibleContext().setAccessibleName("TxtCantidadGatos");
        CantTruno.getAccessibleContext().setAccessibleName("TxtCantidadTurno");
        CantPerros.getAccessibleContext().setAccessibleName("TxtCantidadPerros");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ApeVetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApeVetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApeVetActionPerformed
    
    private List<Turno>buscarTurnos(int tipoBusquedad){
        if(tipoBusquedad==0){
            return turnos=(List<Turno>)iTurno.buscarTurno(0,"", "");
        }else{
            
           return turnos=(List<Turno>)iTurno.buscarTurno(Integer.parseInt(DniVet.getText().toString()),NomVet.getText().toString(), ApeVet.getText().toString()); 
        }
    }
    private void ApeDueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApeDueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ApeDueActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        searchTurnos(DniVet.getText(), NomVet.getText(), ApeVet.getText(),
                 DniDue.getText(), NomDue.getText(), ApeDue.getText(),
                 NomMasc.getText(), FechaDesde.getText(), FechaHasta.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void CantGatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantGatosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantGatosActionPerformed

    private void CantTrunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantTrunoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantTrunoActionPerformed

    private void CantPerrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantPerrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantPerrosActionPerformed

    private void FechaDesdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaDesdeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaDesdeActionPerformed

    private void NomMascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NomMascActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NomMascActionPerformed

    private void FechaHastaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FechaHastaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FechaHastaActionPerformed

    /**
     * @param args the command line arguments
     */
   /*
    public static void main(String args[]) {
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFormCosnultaTurnoOperacion().setVisible(true);
            }
        });
    }
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ApeDue;
    private javax.swing.JTextField ApeVet;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JTextField CantGatos;
    private javax.swing.JTextField CantPerros;
    private javax.swing.JTextField CantTruno;
    private javax.swing.JTextField DniDue;
    private javax.swing.JTextField DniVet;
    private javax.swing.JTextField FechaDesde;
    private javax.swing.JTextField FechaHasta;
    private javax.swing.JTextField NomDue;
    private javax.swing.JTextField NomMasc;
    private javax.swing.JTextField NomVet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCosnultaTurno;
    // End of variables declaration//GEN-END:variables
}
