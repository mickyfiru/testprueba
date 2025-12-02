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

public class EquipoAddView extends JFrame {

    private final JTextField txtDescripcion = new JTextField();
    private final JTextField txtNumeroSerie = new JTextField();
    private final JComboBox<AulaModel> cmbAula = new JComboBox<>();

    public EquipoAddView() {
        super("Agregar equipo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        buildLayout();
        loadAulas();
        pack();
    }

    private void buildLayout() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.add(new JLabel("Descripción"));
        panel.add(txtDescripcion);
        panel.add(new JLabel("Número de serie"));
        panel.add(txtNumeroSerie);
        panel.add(new JLabel("Aula asignada"));
        panel.add(cmbAula);

        JButton btnGuardar = new JButton("Insertar");
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
        if (txtDescripcion.getText().isBlank() || txtNumeroSerie.getText().isBlank() || cmbAula.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        AulaModel aula = (AulaModel) cmbAula.getSelectedItem();
        ControllerResponse response = EquiposController.insert(
            new EquipoModel(txtDescripcion.getText(), txtNumeroSerie.getText(), aula.getAulaId())
        );

        if (response.getStatus()) {
            JOptionPane.showMessageDialog(this, response.getMessage());
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
