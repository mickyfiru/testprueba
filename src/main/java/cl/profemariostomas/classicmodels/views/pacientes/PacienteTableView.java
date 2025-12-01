package cl.profemariostomas.classicmodels.views.pacientes;

import cl.profemariostomas.classicmodels.controllers.PacientesController;
import cl.profemariostomas.classicmodels.models.PacienteModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PacienteTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public PacienteTableView() {
        super("Pacientes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nombre", "Apellido", "RUT", "Fecha nacimiento"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<PacienteModel> pacientes = PacientesController.select();
        if (pacientes == null) return;

        for (PacienteModel paciente : pacientes) {
            tableModel.addRow(new Object[]{
                paciente.getIdPaciente(),
                paciente.getNombre(),
                paciente.getApellido(),
                paciente.getRut(),
                paciente.getFechaNacimiento()
            });
        }
    }
}
