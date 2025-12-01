package cl.profemariostomas.classicmodels.views.empleados;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.EmpleadosController;
import cl.profemariostomas.classicmodels.models.EmpleadoModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmpleadoModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtCargo = new JTextField();
    private final JTextField txtDepto = new JTextField();

    public EmpleadoModifyView() {
        super("Modificar empleado");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 8, 8));
        panel.add(new JLabel("ID Empleado"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido"));
        panel.add(txtApellido);
        panel.add(new JLabel("Cargo"));
        panel.add(txtCargo);
        panel.add(new JLabel("ID Depto"));
        panel.add(txtDepto);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(e -> update());
        panel.add(new JLabel());
        panel.add(btnActualizar);

        add(panel);
    }

    private void update() {
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtApellido.getText().isBlank()
            || txtCargo.getText().isBlank() || txtDepto.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            int idDepto = Integer.parseInt(txtDepto.getText());
            ControllerResponse response = EmpleadosController.update(
                new EmpleadoModel(id, txtNombre.getText(), txtApellido.getText(), txtCargo.getText(), idDepto)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los campos ID deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
