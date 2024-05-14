import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentDatabaseGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private Object[] columnNames = {"Student ID", "Name", "Age", "Grade"};
    private Object[][] data = {
            {"1", "John Doe", new Integer(20), new Double(85.5)},
            {"2", "Jane Smith", new Integer(21), new Double(90.0)},
            {"3", "Alice Johnson", new Integer(22), new Double(88.0)},
            {"4", "Bob Brown", new Integer(23), new Double(92.5)}
    };
    private JTable table;
    private DefaultTableModel model;

    public StudentDatabaseGUI() {
        model = new DefaultTableModel(data, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
        table = new JTable(model) {
            private static final long serialVersionUID = 1L;

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        };
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        JButton button1 = new JButton("Remove Selected Rows");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length > 0) {
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        model.removeRow(selectedRows[i]);
                    }
                }
            }
        });
        JButton button2 = new JButton("Add New Student");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String id = JOptionPane.showInputDialog("Enter Student ID:");
                String name = JOptionPane.showInputDialog("Enter Name:");
                int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
                double grade = Double.parseDouble(JOptionPane.showInputDialog("Enter Grade:"));
                model.addRow(new Object[]{id, name, age, grade});
            }
        });
        JPanel southPanel = new JPanel();
        southPanel.add(button1);
        southPanel.add(button2);
        add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        StudentDatabaseGUI frame = new StudentDatabaseGUI();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
