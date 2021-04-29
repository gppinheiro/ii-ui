package ui;

import db.dbConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.SQLException;

public class UI {
    // Global var for DB
    private final dbConnect db;

    // Global vars for UI
    private JPanel Conjunto;
    // TABS
    private JTabbedPane TABS;
    //  Armazem TAB
    private JTable ArmazemTable;

    //  Ordens a Fazer Tabela
    private JTable OTTable;

    //  Transformações a Decorrer TAB
    private JTable TADTable;

    //  Transformações Realizadas TAB
    private JTable TRTable;

    private JComboBox<String> DescarregadasComboBox;

    //  Estatisticas
    private JTabbedPane EstatisticasTabbedPane;
    private JTable EstatisticasMaquinasTable;
    private JTable EstatisticasDescargasTable;

    // BUTTONS
    private JPanel Buttons;
    private JButton ButtonAtualizar;
    private JButton ButtonFechar;
    private JTable DescarregadasTable;

    public UI(){
        this.db = new dbConnect();
        try {
            createOrdensTransformacao();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        createEstatisticasMaquinas();
    }

    private void createOrdensTransformacao() throws SQLException {
        Object[][] data = db.getTransform();
        this.OTTable.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Peça Inicial","Peça Final", "Quantidade"}));
    }

    private void createEstatisticasMaquinas() {
        Object[][] data={ {"Num de Peças"}, {"Tempo"} };

        this.EstatisticasMaquinasTable.setModel(new DefaultTableModel(data, new String[] {"Peças", "Maquina 1", "Maquina 2", "Maquina 3", "Maquina 4", "Maquina 5", "Maquina 6", "Maquina 7", "Maquina 8" }));

        TableColumnModel columns = this.EstatisticasMaquinasTable.getColumnModel();
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

    public JPanel getRootPanel() {
        return Conjunto;
    }
}
