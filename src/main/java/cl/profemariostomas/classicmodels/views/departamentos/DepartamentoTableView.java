package cl.profemariostomas.classicmodels.views.departamentos;

import cl.profemariostomas.classicmodels.controllers.DepartamentosController;
import cl.profemariostomas.classicmodels.models.DepartamentoModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DepartamentoTableView extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel();

    public DepartamentoTableView() {
        super("Departamentos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildTable();
        pack();
    }

    private void buildTable() {
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Centro de costo");

        ArrayList<DepartamentoModel> departamentos = DepartamentosController.select();
        if (departamentos != null) {
            for (DepartamentoModel depto : departamentos) {
                model.addRow(new Object[]{depto.getIdDepto(), depto.getNombreDepto(), depto.getCentroCosto()});
            }
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
