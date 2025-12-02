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

public class AulaAddView extends JFrame {

    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCapacidad = new JTextField();

    public AulaAddView() {
        super("Agregar aula");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Capacidad"));
        panel.add(txtCapacidad);

        JButton btnGuardar = new JButton("Insertar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtNombre.getText().isBlank() || txtCapacidad.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int capacidad = Integer.parseInt(txtCapacidad.getText());
            ControllerResponse response = AulasController.insert(
                new AulaModel(txtNombre.getText(), capacidad)
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La capacidad debe ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
