package cl.profemariostomas.classicmodels.views.tareas;

import cl.profemariostomas.classicmodels.controllers.TareasController;
import cl.profemariostomas.classicmodels.models.TareaModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TareaTableView extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel();

    public TareaTableView() {
        super("Tareas");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildTable();
        pack();
    }

    private void buildTable() {
        model.addColumn("ID");
        model.addColumn("Empleado");
        model.addColumn("Estado");
        model.addColumn("Horas estimadas");

        ArrayList<TareaModel> tareas = TareasController.select();
        if (tareas != null) {
            for (TareaModel tarea : tareas) {
                model.addRow(new Object[]{tarea.getIdTarea(), tarea.getIdEmpleadoAsignado(), tarea.getEstado(), tarea.getHorasEstimadas()});
            }
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
