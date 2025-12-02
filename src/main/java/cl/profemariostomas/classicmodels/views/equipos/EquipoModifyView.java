package cl.profemariostomas.classicmodels.views.equipos;

import cl.profemariostomas.classicmodels.ControllerResponse;
import cl.profemariostomas.classicmodels.controllers.AulasController;
import cl.profemariostomas.classicmodels.controllers.EquiposController;
import cl.profemariostomas.classicmodels.models.AulaModel;
import cl.profemariostomas.classicmodels.models.EquipoModel;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquipoModifyView extends JFrame {

    private final JTextField txtId = new JTextField();
    private final JTextField txtDescripcion = new JTextField();
    private final JTextField txtNumeroSerie = new JTextField();
    private final JComboBox<AulaModel> cmbAula = new JComboBox<>();

    public EquipoModifyView() {
        super("Modificar equipo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        loadAulas();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 8, 8));
        panel.add(new JLabel("ID Equipo"));
        panel.add(txtId);
        panel.add(new JLabel("Nueva descripción"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Nuevo número de serie"));
        panel.add(txtNumeroSerie);
        panel.add(new JLabel("Aula asignada"));
        panel.add(cmbAula);

        JButton btnGuardar = new JButton("Actualizar");
        btnGuardar.addActionListener(e -> save());
        panel.add(new JLabel());
        panel.add(btnGuardar);

        add(panel);
    }

    private void loadAulas() {
        cmbAula.removeAllItems();
        ArrayList<AulaModel> aulas = AulasController.select();
        if (aulas == null || aulas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Primero cree aulas para poder asignarlas", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        aulas.forEach(cmbAula::addItem);
    }

    private void save() {
        if (txtId.getText().isBlank() || txtDescripcion.getText().isBlank() || txtNumeroSerie.getText().isBlank() || cmbAula.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int id = Integer.parseInt(txtId.getText());
            AulaModel aula = (AulaModel) cmbAula.getSelectedItem();
            ControllerResponse response = EquiposController.update(
                new EquipoModel(id, txtDescripcion.getText(), txtNumeroSerie.getText(), aula.getAulaId(), aula.getNombre())
            );

            if (response.getStatus()) {
                JOptionPane.showMessageDialog(this, response.getMessage());
            } else {
                JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID del equipo debe ser numérico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
