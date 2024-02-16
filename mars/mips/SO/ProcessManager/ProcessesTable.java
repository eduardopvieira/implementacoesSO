package mars.mips.SO.ProcessManager;

import java.util.LinkedList;

public class ProcessesTable {
	private static LinkedList<PCB> ready = new LinkedList<>();
	private static PCB currentProcess;
	
	public static void addReady(PCB process) {
		ready.add(process);
	}
	
	public static boolean removeReady(PCB process) {
		if(! ready.remove(process)) {
			System.out.println("Erro: processo não faz parte da lista");
			return false;	
		}
		
		return true;
	}
	
	public static void promoteProcess(PCB process) {
		if(removeReady(process)) {
			ready.add(currentProcess);
			currentProcess = process;
		} else
			System.out.println("Erro: processo não faz parte da lista");		
	}
	
	public static PCB getCurrentProcess() {
		return currentProcess;
	}

	public static PCB getPCB() {
		return currentProcess;
	}
}
