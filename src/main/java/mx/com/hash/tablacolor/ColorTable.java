/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.hash.tablacolor;

/**
 *
 * @author David
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ColorTable extends JFrame implements ActionListener {

    private final JTable tabla;
    private final JScrollPane barras;
    private final DefaultTableModel modelo;
    private final JTextField entrada;
    private final JButton boton;
    private final String[] columnas = {"Indice", "Dato"};
    Resaltador resaltado;

    /**
     * Crea la ventana con la tabla
     */
    public ColorTable() {
        entrada = new JTextField();
        boton = new JButton("Resaltar");
        modelo = new DefaultTableModel();
        tabla = new JTable();
        barras = new JScrollPane(tabla);
        resaltado = new Resaltador(0);

        // Detalles de la ventana
        this.setTitle("Tabla Color");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Establecemos el modelo de la tabla.
        modelo.setColumnIdentifiers(columnas);
        tabla.setModel(modelo);

        //Colocamos los elementos en su lugar
        this.getContentPane().add(barras, BorderLayout.CENTER);
        this.getContentPane().add(entrada, BorderLayout.NORTH);
        this.getContentPane().add(boton, BorderLayout.SOUTH);

        // El evento del boton
        boton.addActionListener(this);

        // Iniciamos el valor del texto
        entrada.setText("0");

        // Indicamos como sera el resaltado de la tabla
        tabla.setDefaultRenderer(Object.class, resaltado);

    }

    /**
     * Limpia y agrega los datos en la tabla
     */
    private void agregarDatos() {
        String[] datos = {"CERO", "Uno", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Once"};

        modelo.setRowCount(0);

        for (int i = 0; i < datos.length; i++) {
            Object[] row = {i, datos[i]};
            modelo.addRow(row);
        }
    }

    /**
     * Muestra la ventana en pantalla
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ColorTable color = new ColorTable();

        color.pack();
        color.setVisible(true);
    }

    /**
     * Lo que se realiza cuando se presiona el boton
     *
     * @param e Evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Integer fila;
        try {
            fila = Integer.parseInt(entrada.getText());
        } catch (NumberFormatException ex) {
            fila = 0;
        }

        resaltado.setFila(fila);

        this.agregarDatos();
    }
}
