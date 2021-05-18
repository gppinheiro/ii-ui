package ui;

import db.dbConnect;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UI implements ActionListener {
    // Global var for DB
    private final dbConnect db;

    // Global vars for UI
    private JPanel Conjunto;
    // TABS
    private JTabbedPane TABS;
    //  Armazem TAB
    private JTable ArmazemTable;

    //  Ordens a Fazer TAB
    private JTable OTTable;

    //  Transformações a Decorrer TAB
    private JTable TADTable;

    //  Transformações Realizadas TAB
    private JTable TRTable;

    // MAquinas Stats
    private JTable TablesMaquina;

    // BUTTONS
    private JPanel Buttons;
    private JButton ButtonAtualizar;
    private JButton ButtonFechar;
    private JTable DescarregadasTable;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane1;
    private JTable DescargasADecorrer;
    private JTabbedPane tabbedPane2;
    private JTable TablesPusher;
    private JTable TablesTemposMaquinas;

    public UI(){
        this.db = new dbConnect();
        ButtonAtualizar.addActionListener(this::actionPerformed);
        ButtonFechar.addActionListener(this::actionPerformed2);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==ButtonAtualizar) {
            try {
                createOrdensTransformacao();
                createTransformacaoDecorrer();
                createArmazem();
                createTransformacaoRealizadas();
                createDescarregadas();
                createDescargasARealizar();
                createMaquinas();
                createTemposMaquinas();
                createPusher();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void actionPerformed2(ActionEvent l){
        System.exit(0);
    }

    private void createArmazem() throws SQLException {
        Object[][] data = db.getCurrentStores();
        this.ArmazemTable.setModel(new DefaultTableModel(data, new String[]{"Tipo de Peça", "Quantidade"}));
        TableColumnModel columns = this.ArmazemTable.getColumnModel();
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        columns.getColumn(0).setCellRenderer(centerRender);
        columns.getColumn(1).setCellRenderer(centerRender);

    }
    private void createOrdensTransformacao() throws SQLException { //Transformações fituras
        Object[][] data = db.getTransform();
        this.OTTable.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Peça Inicial","Peça Final", "Quantidade", "MaxDelay" , "InitPenalty", "Chegada ao MES", "TT Excepted"}));
    }

    private void createTransformacaoDecorrer() throws SQLException {
        Object[][] data = db.getElapseTransform();
        this.TADTable.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Peça Inicial","Peça Final", "Quantidade", "MaxDelay" , "InitPenalty", "Chegada ao MES", "Lado", "Início"}));
    }

    private void createTransformacaoRealizadas() throws SQLException {
        Object[][] data = db.getEndTransform();
        this.TRTable.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Peça Inicial","Peça Final", "Quantidade", "MaxDelay" , "InitPenalty", "Chegada ao MES", "Lado", "Início", "Fim", "Tempo de Transformação", "Penalty"}));
    }

    private void createDescarregadas() throws SQLException { //DescargasRealizadas
        Object[][] data = db.getUnloaded();
        this.DescarregadasTable.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Tipo de Peça","Destino", "Quantidade"}));

    }

    private void createDescargasARealizar() throws SQLException {
        Object[][] data = db.getUnload();
        this.DescargasADecorrer.setModel(new DefaultTableModel(data,new String[]{"NºOrdem","Tipo de Peça","Destino", "Quantidade"}));
    }

    private void createMaquinas() throws SQLException {
        Object[][] data = db.getMaquinas();
        this.TablesMaquina.setModel(new DefaultTableModel(data, new String[] {"Máquina", "P1-P2", "P2-P3", "P3-P4", "P4-P5", "P5-P6", "P6-P7", "P6-P8", "P5-P9", "Total" }));
    }

    private void createPusher() throws SQLException {
        Object[][] data = db.getPusher();
        this.TablesPusher.setModel(new DefaultTableModel(data, new String[] {"Pusher", "P1", "P2", "P3", "P4", "P5", "P6", "P7", "P8", "P9", "Total"}));
    }

    private void createTemposMaquinas() throws SQLException {
        Object[][] data = db.getTemposMaquinas();
        this.TablesTemposMaquinas.setModel(new DefaultTableModel(data, new String[] {"Máquina", "P1-P2", "P2-P3", "P3-P4", "P4-P5", "P5-P6", "P6-P7", "P6-P8", "P5-P9", "Total" }));
    }

    public JPanel getRootPanel() {
        return Conjunto;
    }


}
