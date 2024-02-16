package mars.mips.SO.ProcessManager;

import java.util.LinkedList;

public class Scheduler {
	public static void escalonar(LinkedList<PCB> ready) {
		ProcessesTable.promoteProcess(ready.get(0));
	}
}
