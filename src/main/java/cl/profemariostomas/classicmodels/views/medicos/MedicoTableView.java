package cl.profemariostomas.classicmodels.views.medicos;

import cl.profemariostomas.classicmodels.controllers.MedicosController;
import cl.profemariostomas.classicmodels.models.MedicoModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MedicoTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public MedicoTableView() {
        super("Médicos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nombre", "Apellido", "Número de licencia", "ID Especialidad"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<MedicoModel> medicos = MedicosController.select();
        if (medicos == null) return;

        for (MedicoModel medico : medicos) {
            tableModel.addRow(new Object[]{
                medico.getIdMedico(),
                medico.getNombre(),
                medico.getApellido(),
                medico.getNumeroLicencia(),
                medico.getIdEspecialidad()
            });
        }
    }
}
