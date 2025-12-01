package cl.profemariostomas.classicmodels.views.citas;

import cl.profemariostomas.classicmodels.controllers.CitasController;
import cl.profemariostomas.classicmodels.models.CitaModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CitaTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public CitaTableView() {
        super("Citas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "ID Médico", "ID Paciente", "Fecha", "Estado", "Motivo"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<CitaModel> citas = CitasController.select();
        if (citas == null) return;

        for (CitaModel cita : citas) {
            tableModel.addRow(new Object[]{
                cita.getIdCita(),
                cita.getIdMedico(),
                cita.getIdPaciente(),
                cita.getFechaCita(),
                cita.getEstado(),
                cita.getMotivo()
            });
        }
    }
}
