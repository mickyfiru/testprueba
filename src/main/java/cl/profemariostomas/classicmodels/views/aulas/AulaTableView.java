package cl.profemariostomas.classicmodels.views.aulas;

import cl.profemariostomas.classicmodels.controllers.AulasController;
import cl.profemariostomas.classicmodels.models.AulaModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AulaTableView extends JFrame {

    private final JTable table;
    private final DefaultTableModel tableModel;

    public AulaTableView() {
        super("Aulas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{
            "ID", "Nombre", "Capacidad"
        }, 0);
        table = new JTable(tableModel);

        loadData();

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
    }

    private void loadData() {
        ArrayList<AulaModel> aulas = AulasController.select();
        if (aulas == null) return;

        for (AulaModel aula : aulas) {
            tableModel.addRow(new Object[]{
                aula.getAulaId(),
                aula.getNombre(),
                aula.getCapacidad()
            });
        }
    }
}
