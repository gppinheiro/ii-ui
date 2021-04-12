package ui;

import db.dbConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.SQLException;

public class UI {
    private JPanel rootPanel;
    private JTable showTable;
    private JButton fecharButton;
    private JComboBox ComboBox;
    private JList list1;
    private JScrollPane pane;
    private JButton atualizarButton;
    private JTable ShowTable1;
    private dbConnect db;

    public UI(){
        this.db = new dbConnect();
        createTable();
        createComboBox();
        try {
            createShowTable1();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createShowTable1() throws SQLException {
        Object[][] data = db.getTransform();
        ShowTable1.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Peça Inicial","Peça Final", "Quantidade"}));
    }

    private void createTable() {
        Object[][] data={
                {"Num de Peças"},
                {"Tempo"}
        };

        showTable.setModel(new DefaultTableModel(data, new String[] {"Peças", "Maquina 1", "Maquina 2", "Maquina 3", "Maquina 4", "Maquina 5", "Maquina 6", "Maquina 7", "Maquina 8" }));

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
