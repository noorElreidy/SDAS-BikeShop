package guiWorks;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class teste extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public teste() {
		JPanel panel4 = new JPanel();
		add(panel4);
		panel4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel4.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"dwd", "ws", "dwa", "ws"},
				{"was", "ds", "w", "a"},
				{null, "w", "dw", "s"},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);

	}

}
