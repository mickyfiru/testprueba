package cl.profemariostomas.classicmodels.views.empleados;

import cl.profemariostomas.classicmodels.controllers.EmpleadosController;
import cl.profemariostomas.classicmodels.models.EmpleadoModel;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmpleadoTableView extends JFrame {

    private final DefaultTableModel model = new DefaultTableModel();

    public EmpleadoTableView() {
        super("Empleados");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildTable();
        pack();
    }

    private void buildTable() {
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Cargo");
        model.addColumn("Depto");

        ArrayList<EmpleadoModel> empleados = EmpleadosController.select();
        if (empleados != null) {
            for (EmpleadoModel empleado : empleados) {
                model.addRow(new Object[]{empleado.getIdEmpleado(), empleado.getNombre(), empleado.getApellido(), empleado.getCargo(), empleado.getIdDepto()});
            }
        }

        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
