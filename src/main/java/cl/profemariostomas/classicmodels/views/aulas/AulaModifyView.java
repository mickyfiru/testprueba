package cl.profemariostomas.classicmodels.views.aulas;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.AulasController;
import cl.profemariostomas.classicmodels.models.AulaModel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AulaModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCapacidad = new JTextField();

    public AulaModifyView() {
        super("Modificar aula");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("ID Aula"));
        panel.add(txtId);
        panel.add(new JLabel("Nuevo nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nueva capacidad"));
        panel.add(txtCapacidad);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtNombre.getText().isBlank() || txtCapacidad.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            ControllerResponse response = AulasController.update(
                new AulaModel(id, txtNombre.getText(), capacidad)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID y la capacidad deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
