package cl.profemariostomas.classicmodels.views.especialidades;

import cl.profemariostomas.classicmodels.controllers.EspecialidadesController;
import cl.profemariostomas.classicmodels.models.EspecialidadModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EspecialidadTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public EspecialidadTableView() {
        super("Especialidades");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nombre", "Código"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<EspecialidadModel> especialidades = EspecialidadesController.select();
        if (especialidades == null) return;

        for (EspecialidadModel especialidad : especialidades) {
            tableModel.addRow(new Object[]{
                especialidad.getIdEspecialidad(),
                especialidad.getNombreEspecialidad(),
                especialidad.getCodigo()
            });
        }
    }
}
