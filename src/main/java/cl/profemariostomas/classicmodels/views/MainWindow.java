package cl.profemariostomas.classicmodels.views;

import cl.profemariostomas.classicmodels.views.citas.CitaAddView;
import cl.profemariostomas.classicmodels.views.citas.CitaModifyView;
import cl.profemariostomas.classicmodels.views.citas.CitaTableView;
import cl.profemariostomas.classicmodels.views.especialidades.EspecialidadAddView;
import cl.profemariostomas.classicmodels.views.especialidades.EspecialidadModifyView;
import cl.profemariostomas.classicmodels.views.especialidades.EspecialidadTableView;
import cl.profemariostomas.classicmodels.views.medicos.MedicoAddView;
import cl.profemariostomas.classicmodels.views.medicos.MedicoModifyView;
import cl.profemariostomas.classicmodels.views.medicos.MedicoTableView;
import cl.profemariostomas.classicmodels.views.pacientes.PacienteAddView;
import cl.profemariostomas.classicmodels.views.pacientes.PacienteModifyView;
import cl.profemariostomas.classicmodels.views.pacientes.PacienteTableView;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

    public MainWindow() {
        super("Clinica Médica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 400));
        buildMenu();
        pack();
    }

    private void buildMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mantenedores = new JMenu("Mantenedores");

        JMenu menuEspecialidades = new JMenu("Especialidades");
        JMenuItem addEspecialidad = new JMenuItem("Agregar");
        addEspecialidad.addActionListener(e -> new EspecialidadAddView().setVisible(true));
        JMenuItem modifyEspecialidad = new JMenuItem("Modificar");
        modifyEspecialidad.addActionListener(e -> new EspecialidadModifyView().setVisible(true));
        JMenuItem viewEspecialidades = new JMenuItem("Ver registros");
        viewEspecialidades.addActionListener(e -> new EspecialidadTableView().setVisible(true));
        menuEspecialidades.add(addEspecialidad);
        menuEspecialidades.add(modifyEspecialidad);
        menuEspecialidades.add(viewEspecialidades);

        JMenu menuMedicos = new JMenu("Médicos");
        JMenuItem addMedico = new JMenuItem("Agregar");
        addMedico.addActionListener(e -> new MedicoAddView().setVisible(true));
        JMenuItem modifyMedico = new JMenuItem("Modificar");
        modifyMedico.addActionListener(e -> new MedicoModifyView().setVisible(true));
        JMenuItem viewMedicos = new JMenuItem("Ver registros");
        viewMedicos.addActionListener(e -> new MedicoTableView().setVisible(true));
        menuMedicos.add(addMedico);
        menuMedicos.add(modifyMedico);
        menuMedicos.add(viewMedicos);

        JMenu menuPacientes = new JMenu("Pacientes");
        JMenuItem addPaciente = new JMenuItem("Agregar");
        addPaciente.addActionListener(e -> new PacienteAddView().setVisible(true));
        JMenuItem modifyPaciente = new JMenuItem("Modificar");
        modifyPaciente.addActionListener(e -> new PacienteModifyView().setVisible(true));
        JMenuItem viewPacientes = new JMenuItem("Ver registros");
        viewPacientes.addActionListener(e -> new PacienteTableView().setVisible(true));
        menuPacientes.add(addPaciente);
        menuPacientes.add(modifyPaciente);
        menuPacientes.add(viewPacientes);

        JMenu menuCitas = new JMenu("Citas");
        JMenuItem addCita = new JMenuItem("Agregar");
        addCita.addActionListener(e -> new CitaAddView().setVisible(true));
        JMenuItem modifyCita = new JMenuItem("Modificar");
        modifyCita.addActionListener(e -> new CitaModifyView().setVisible(true));
        JMenuItem viewCitas = new JMenuItem("Ver registros");
        viewCitas.addActionListener(e -> new CitaTableView().setVisible(true));
        menuCitas.add(addCita);
        menuCitas.add(modifyCita);
        menuCitas.add(viewCitas);

        mantenedores.add(menuEspecialidades);
        mantenedores.add(menuMedicos);
        mantenedores.add(menuPacientes);
        mantenedores.add(menuCitas);

        menuBar.add(mantenedores);
        setJMenuBar(menuBar);
    }
}
