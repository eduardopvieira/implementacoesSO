package mars.mips.SO.ProcessManager;

import java.util.LinkedList;

public class ProcessesTable {
	private static LinkedList<PCB> ready = new LinkedList<>();

	private static PCB currentProcess=null;
	

	public static void addReady(PCB process) {
		ready.add(process);
	}
	
	public static boolean removeReady(PCB process) {
		if(!ready.remove(process)) {
			System.out.println("Erro: processo não faz parte da lista");
			return false;	
		}
		
		return true;
	}

	public static LinkedList<PCB> getReady() {
		return ready;
	}

	public static PCB proximoProcesso(){
		if(!ready.isEmpty()){
			return ready.getFirst();
		} else {
			return null;
		}
		
	}

	public static PCB getPCB() {
		return currentProcess;
	}

	public static void setCurrentProcess(PCB currentProcess) {
		ProcessesTable.currentProcess = currentProcess;
	}


	public static void promoteProcess(PCB process) {
		if(removeReady(process)) {
			ready.add(getPCB());
			currentProcess = process;
		} else
			System.out.println("Erro: processo não faz parte da lista");		
	}


}
