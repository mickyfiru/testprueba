package cl.profemariostomas.classicmodels.views.equipos;

import cl.profemariostomas.classicmodels.controllers.EquiposController;
import cl.profemariostomas.classicmodels.models.EquipoModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EquipoTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public EquipoTableView() {
        super("Equipos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Descripción", "Número de serie", "Aula"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<EquipoModel> equipos = EquiposController.select();
        if (equipos == null) return;

        for (EquipoModel equipo : equipos) {
            tableModel.addRow(new Object[]{
                equipo.getEquipoId(),
                equipo.getDescripcion(),
                equipo.getNumeroSerie(),
                equipo.getAulaNombre()
            });
        }
    }
}
