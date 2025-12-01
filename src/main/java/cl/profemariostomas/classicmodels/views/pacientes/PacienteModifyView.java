package cl.profemariostomas.classicmodels.views.pacientes;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.PacientesController;
import cl.profemariostomas.classicmodels.models.PacienteModel;
import java.awt.GridLayout;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PacienteModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtRut = new JTextField();
    private final JTextField txtFechaNacimiento = new JTextField("1990-01-01");

    public PacienteModifyView() {
        super("Modificar paciente");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 8, 8));
        panel.add(new JLabel("ID Paciente"));
        panel.add(txtId);
        panel.add(new JLabel("Nuevo nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nuevo apellido"));
        panel.add(txtApellido);
        panel.add(new JLabel("RUT"));
        panel.add(txtRut);
        panel.add(new JLabel("Fecha de nacimiento (YYYY-MM-DD)"));
        panel.add(txtFechaNacimiento);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtApellido.getText().isBlank()
            || txtRut.getText().isBlank() || txtFechaNacimiento.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            Date fechaNacimiento = Date.valueOf(txtFechaNacimiento.getText());
            ControllerResponse response = PacientesController.update(
                new PacienteModel(id, txtNombre.getText(), txtApellido.getText(), txtRut.getText(), fechaNacimiento)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
