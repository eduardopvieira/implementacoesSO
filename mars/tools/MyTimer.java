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
	private static String name   = "My Timer";
	private static String version = "Version 0.1";
	private static String heading = "";
	
	private static JTextField insertField;
	private JTextField counterField;
	private static JComboBox<String> schedulers;
	private String[] options = {"Escalonar Linear", "Escalonar Prioridade", "Escalonar Loteria"};
	private static boolean open = false;
	
	private static int counter = 0;

	public MyTimer(String title, String heading) {
		super(title, heading);
	}
	
	public MyTimer() {
		super(name + " " + version, heading);
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
		c.insets = new Insets(0, 0, 17, 0);
		panel.add(new JLabel("Timer : "), c);
		
		c.insets = new Insets(0, 170, 17, 0);
		c.gridx++;
		panel.add(new JLabel("Instruções passadas: "), c);
		
		c.insets = new Insets(0, 0, 0, 0);
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
			if (schedulers.getSelectedItem() == "Escalonar linear") {
				Scheduler.escalonarFIFO();
			}
			if (schedulers.getSelectedItem() == "Escalonar Prioridade") {
				Scheduler.escalonarFixa();
			}
			if (schedulers.getSelectedItem() == "Escalonar Loteria") {
				Scheduler.escalonarLoteria();
			}
		}
	}
	
	public static boolean teste() {
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
			return "Escalonar linear";
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
		ProcessesTable.resetar();
		updateDisplay();
	}
}