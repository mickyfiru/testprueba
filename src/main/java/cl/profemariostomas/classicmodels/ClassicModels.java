package cl.profemariostomas.classicmodels;

import cl.profemariostomas.classicmodels.views.MainWindow;
import javax.swing.SwingUtilities;

public class ClassicModels {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
