package cl.profemariostomas.classicmodels.views;

import cl.profemariostomas.classicmodels.views.aulas.AulaAddView;
import cl.profemariostomas.classicmodels.views.aulas.AulaModifyView;
import cl.profemariostomas.classicmodels.views.aulas.AulaTableView;
import cl.profemariostomas.classicmodels.views.equipos.EquipoAddView;
import cl.profemariostomas.classicmodels.views.equipos.EquipoModifyView;
import cl.profemariostomas.classicmodels.views.equipos.EquipoTableView;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Universidad");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));
        buildMenu();
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }

    private void buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mantenedores = new JMenu("Mantenedores");

        JMenu menuAulas = new JMenu("Aulas");
        JMenuItem addAula = new JMenuItem("Agregar");
        addAula.addActionListener(e -> new AulaAddView().setVisible(true));
        JMenuItem modifyAula = new JMenuItem("Modificar");
        modifyAula.addActionListener(e -> new AulaModifyView().setVisible(true));
        JMenuItem viewAulas = new JMenuItem("Ver registros");
        viewAulas.addActionListener(e -> new AulaTableView().setVisible(true));
        menuAulas.add(addAula);
        menuAulas.add(modifyAula);
        menuAulas.add(viewAulas);

        JMenu menuEquipos = new JMenu("Equipos");
        JMenuItem addEquipo = new JMenuItem("Agregar");
        addEquipo.addActionListener(e -> new EquipoAddView().setVisible(true));
        JMenuItem modifyEquipo = new JMenuItem("Modificar");
        modifyEquipo.addActionListener(e -> new EquipoModifyView().setVisible(true));
        JMenuItem viewEquipos = new JMenuItem("Ver registros");
        viewEquipos.addActionListener(e -> new EquipoTableView().setVisible(true));
        menuEquipos.add(addEquipo);
        menuEquipos.add(modifyEquipo);
        menuEquipos.add(viewEquipos);

        mantenedores.add(menuAulas);
        mantenedores.add(menuEquipos);

        menuBar.add(mantenedores);
        setJMenuBar(menuBar);
    }
}
