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

public class EspecialidadAddView extends JFrame {

    private final JTextField txtNombre = new JTextField();
    private final JTextField txtCodigo = new JTextField();

    public EspecialidadAddView() {
        super("Agregar especialidad");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.add(new JLabel("Nombre"));
        panel.add(txtNombre);
        panel.add(new JLabel("Código"));
        panel.add(txtCodigo);

        JButton btnGuardar = new JButton("Insertar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtNombre.getText().isBlank() || txtCodigo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ControllerResponse response = EspecialidadesController.insert(
            new EspecialidadModel(txtNombre.getText(), txtCodigo.getText())
        );

        if (response.getStatus()) {
            JOptionPane.showMessageDialog(this, response.getMessage());
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
