package cl.profemariostomas.classicmodels.views.medicos;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.MedicosController;
import cl.profemariostomas.classicmodels.models.MedicoModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MedicoAddView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtLicencia = new JTextField();
    private final JTextField txtIdEspecialidad = new JTextField();

    public MedicoAddView() {
        super("Agregar médico");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 8, 8));
        panel.add(new JLabel("ID Médico"));
        panel.add(txtId);
        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido"));
        panel.add(txtApellido);
        panel.add(new JLabel("Número de licencia"));
        panel.add(txtLicencia);
        panel.add(new JLabel("ID Especialidad"));
        panel.add(txtIdEspecialidad);

        JButton btnGuardar = new JButton("Insertar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtApellido.getText().isBlank()
            || txtLicencia.getText().isBlank() || txtIdEspecialidad.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            int idEspecialidad = Integer.parseInt(txtIdEspecialidad.getText());
            ControllerResponse response = MedicosController.insert(
                new MedicoModel(id, txtNombre.getText(), txtApellido.getText(), txtLicencia.getText(), idEspecialidad)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los IDs deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
