package cl.profemariostomas.classicmodels.views.especialidades;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.EspecialidadesController;
import cl.profemariostomas.classicmodels.models.EspecialidadModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EspecialidadModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCodigo = new JTextField();

    public EspecialidadModifyView() {
        super("Modificar especialidad");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("ID Especialidad"));
        panel.add(txtId);
        panel.add(new JLabel("Nuevo nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nuevo código"));
        panel.add(txtCodigo);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtCodigo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            ControllerResponse response = EspecialidadesController.update(
                new EspecialidadModel(id, txtNombre.getText(), txtCodigo.getText())
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de la especialidad debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
