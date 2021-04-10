package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainUI {
    private JPanel rootPanel;
    private JTable showTable;
    private JButton fecharButton;
    private JComboBox comboBox1;
    private JList list1;
    private JScrollPane pane;
    private JButton atualizarButton;


    public MainUI(){
        createTable();
    }

    private void createTable()
        {
            Object[][] data={
                    {}

            };
        showTable.setModel(new DefaultTableModel(
            null,
            new String[] {"Maquina 1", "Maquina 2", "Maquina 3", "Maquina 4", "Maquina 5", "Maquina 6", "Maquina 7", "Maquina 8" }

        ));


    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
