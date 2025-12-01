package cl.profemariostomas.classicmodels.views.citas;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.CitasController;
import cl.profemariostomas.classicmodels.models.CitaModel;
import java.awt.GridLayout;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CitaModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtMedico = new JTextField();
    private final JTextField txtPaciente = new JTextField();
    private final JTextField txtFecha = new JTextField("2025-01-10 09:30:00");
    private final JTextField txtEstado = new JTextField("Programada");
    private final JTextField txtMotivo = new JTextField();

    public CitaModifyView() {
        super("Modificar cita");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 8, 8));
        panel.add(new JLabel("ID Cita"));
        panel.add(txtId);
        panel.add(new JLabel("ID Médico"));
        panel.add(txtMedico);
        panel.add(new JLabel("ID Paciente"));
        panel.add(txtPaciente);
        panel.add(new JLabel("Fecha y hora (YYYY-MM-DD HH:MM:SS)"));
        panel.add(txtFecha);
        panel.add(new JLabel("Estado"));
        panel.add(txtEstado);
        panel.add(new JLabel("Motivo"));
        panel.add(txtMotivo);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtMedico.getText().isBlank() || txtPaciente.getText().isBlank()
            || txtFecha.getText().isBlank() || txtEstado.getText().isBlank() || txtMotivo.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            int idMedico = Integer.parseInt(txtMedico.getText());
            int idPaciente = Integer.parseInt(txtPaciente.getText());
            Timestamp fechaCita = Timestamp.valueOf(txtFecha.getText());
            ControllerResponse response = CitasController.update(
                new CitaModel(id, idMedico, idPaciente, fechaCita, txtEstado.getText(), txtMotivo.getText())
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "La fecha debe tener el formato YYYY-MM-DD HH:MM:SS", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Los IDs deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
