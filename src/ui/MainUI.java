package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MainUI {
    private JPanel rootPanel;
    private JTable showTable;
    private JButton fecharButton;
    private JComboBox ComboBox;
    private JList list1;
    private JScrollPane pane;
    private JButton atualizarButton;
    private JTable ShowTable1;


    public MainUI(){
        createTable();
        createComboBox();
        createShowTable1();
    }

    private void createShowTable1() {
        //exemplo de valores
        Object[][] data={
                {"10","P1","P3"},
                {"5","P2","P5"},

        };
        ShowTable1.setModel(new DefaultTableModel(data,new String[]{"Peça Inicial","Peça Final", "Num de Peças"}));
    }


    private void createTable()
        {
            Object[][] data={
                    {"Num de Peças"},
                    {"Tempo"}


            };
        showTable.setModel(new DefaultTableModel(
            data,
            new String[] {"Pecas", "Maquina 1", "Maquina 2", "Maquina 3", "Maquina 4", "Maquina 5", "Maquina 6", "Maquina 7", "Maquina 8" }

        ));
            TableColumnModel columns =showTable.getColumnModel();
            columns.getColumn(0).setMinWidth(100);
            columns.getColumn(1).setMinWidth(50);
            columns.getColumn(2).setMinWidth(50);
            columns.getColumn(3).setMinWidth(50);
            columns.getColumn(4).setMinWidth(50);
            columns.getColumn(5).setMinWidth(50);
            columns.getColumn(6).setMinWidth(50);
            columns.getColumn(7).setMinWidth(50);
            columns.getColumn(8).setMinWidth(50);
            DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
            centerRender.setHorizontalAlignment(JLabel.CENTER);
            columns.getColumn(0).setCellRenderer(centerRender);
            columns.getColumn(1).setCellRenderer(centerRender);
            columns.getColumn(2).setCellRenderer(centerRender);
            columns.getColumn(3).setCellRenderer(centerRender);
            columns.getColumn(4).setCellRenderer(centerRender);
            columns.getColumn(5).setCellRenderer(centerRender);
            columns.getColumn(6).setCellRenderer(centerRender);
            columns.getColumn(7).setCellRenderer(centerRender);
            columns.getColumn(8).setCellRenderer(centerRender);

    }
    private void createComboBox(){
        ComboBox.setModel(new DefaultComboBoxModel(new String[]{"tapete superior", "tapete inferior"}));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
