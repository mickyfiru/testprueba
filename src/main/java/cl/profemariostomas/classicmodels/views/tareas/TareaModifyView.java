package cl.profemariostomas.classicmodels.views.tareas;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.TareasController;
import cl.profemariostomas.classicmodels.models.TareaModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TareaModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtEmpleado = new JTextField();
    private final JTextField txtEstado = new JTextField();
    private final JTextField txtHoras = new JTextField();

    public TareaModifyView() {
        super("Modificar tarea");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.add(new JLabel("ID Tarea"));
        panel.add(txtId);
        panel.add(new JLabel("ID Empleado Asignado"));
        panel.add(txtEmpleado);
        panel.add(new JLabel("Estado"));
        panel.add(txtEstado);
        panel.add(new JLabel("Horas estimadas"));
        panel.add(txtHoras);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> update());
        panel.add(new JLabel());
        panel.add(btnActualizar);

        add(panel);
    }

    private void update() {
        if (txtId.getText().isBlank() || txtEmpleado.getText().isBlank() || txtEstado.getText().isBlank() || txtHoras.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            int empleado = Integer.parseInt(txtEmpleado.getText());
            int horas = Integer.parseInt(txtHoras.getText());
            ControllerResponse response = TareasController.update(
                new TareaModel(id, empleado, txtEstado.getText(), horas)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos ID y horas deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
