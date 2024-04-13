package mars.tools;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mars.mips.SO.ProcessManager.Scheduler;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.AccessNotice;
import mars.mips.hardware.Memory;

@SuppressWarnings("deprecation")
public class MyTimer extends AbstractMarsToolAndApplication{
	
	private static final long serialVersionUID = 7693825262258672056L;
	private static String name   = "Schedule Timer";
	private static String version = "Version 1.0 (Artur S. Guedes, Ben Ariel França Martins, Caio Anderson Martins Moura, João Pedro Pereira Frutoso, Lucas Gabriel Oliveira da Silva, Ricardo Viana Marinho)";
	private static String heading = "Change processes after a number os instructions";
	
	private static JTextField insertField;
	private JTextField counterField;
	private static JComboBox<String> schedulers;
	private String[] options = {"Line Scheduler", "Priority Scheduler", "Lottery Scheduler"};
	private static boolean open = false;
	
	private static int counter = 0;

	public MyTimer(String title, String heading) {
		super(title, heading);
	}
	
	public MyTimer() {
		super(name + ", " + version, heading);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected JComponent buildMainDisplayArea() {
		JPanel panel = new JPanel(new GridBagLayout());

		insertField = new JTextField("0", 5);
		insertField.setEditable(true);
		
		counterField = new JTextField("0", 5);
		counterField.setEditable(false);
		
		schedulers = new JComboBox<>(options);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = c.gridwidth = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 17, 0);
		panel.add(insertField, c);
		
		c.insets = new Insets(0, 0, 17, 0);
		c.gridx++;
		panel.add(counterField, c);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridy = 1;
		c.insets = new Insets(0, -30, 17, 0);
		panel.add(new JLabel("Insert inst. number: "), c);
		
		c.insets = new Insets(0, 170, 17, 0);
		c.gridx++;
		panel.add(new JLabel("Instructions passed: "), c);
		
		c.insets = new Insets(0, 110, 0, 0);
		c.gridy++;
		panel.add(schedulers, c);
		
		return panel;
	}
	
//	@Override
	protected void addAsObserver() {
		addAsObserver(Memory.textBaseAddress, Memory.textLimitAddress);
	}
	
	@Override
	protected void processMIPSUpdate(Observable resource, AccessNotice notice) {
		if (!notice.accessIsFromMIPS()) return;
		if (notice.getAccessType() != AccessNotice.READ) return;
		if (ProcessesTable.getPCB() != null) {
			MyTimer.counter++;
		}
		updateDisplay();
	}
	
	private static int updateCounter = 0; 
	
//	@Override
	protected void updateDisplay(){ 
		int time = counter % Integer.valueOf(insertField.getText());
		updateCounter++;
	
		counterField.setText(String.valueOf(time));
		
		if (time == 0 && ProcessesTable.getPCB() != null && (updateCounter % 2) == 0) {				
			if (schedulers.getSelectedItem() == "Line Scheduler") {
				Scheduler.escalonarFIFO();
			}
			if (schedulers.getSelectedItem() == "Priority Scheduler") {
				Scheduler.escalonarFixa();
			}
			if (schedulers.getSelectedItem() == "Lottery Scheduler") {
				Scheduler.escalonarLoteria();
			}
		}
	}
	
	public static boolean isEscalonando() {
		if (open) {
			return (counter % Integer.valueOf(insertField.getText()) == 0 && (updateCounter % 2 == 0));
		} else {
			return false;
		}
	}
	
	public static String tipoEscalonador() {
		if (open) {
			return (String) schedulers.getSelectedItem();
		} else {
			return "Line Scheduler";
		}
	}
	
	@Override
	protected void initializePreGUI() {
		MyTimer.counter =  0;
		MyTimer.open = true;
	}
	
	@Override
	protected void reset() {
		counter = 0;
		updateDisplay();
	}
}