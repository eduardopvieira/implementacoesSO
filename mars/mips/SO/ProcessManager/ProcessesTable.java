package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ProcessesTable {
	private static final int prioridadeMaxima = 9;
	private static LinkedList<PCB> ready = new LinkedList<>();
	private static Map<Integer, LinkedList<PCB>> readyPrioridade = new HashMap<>();
	

	private static PCB currentProcess=null;
	
	public static void resetar(){
		ready = new LinkedList<>();
		currentProcess = null;
	}

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

	public static PCB getPCB() {
		return currentProcess;
	}

	public static void setCurrentProcess(PCB currentProcess) {
		ProcessesTable.currentProcess = currentProcess;
	}

	public static void addPrioridade(PCB ready) {
        int prioridade = ready.getPrioridade();
        LinkedList<PCB> list = readyPrioridade.getOrDefault(prioridade, new LinkedList<>());
        list.add(ready);
        readyPrioridade.put(prioridade, list); 
    }

	public static PCB addProxPrioridade() {
        for (int prioridadeMaior = prioridadeMaxima; prioridadeMaior >= 0; prioridadeMaior--) {
            LinkedList<PCB> list = readyPrioridade.getOrDefault(prioridadeMaior, new LinkedList<>());
            if (!list.isEmpty()) {
                return list.getFirst();
            }
        }
        return null;
    }

	public static void promoteProcess(PCB process) {
		if(removeReady(process)) {
			ready.add(getPCB());
			currentProcess = process;
		} else
			System.out.println("Erro: processo não faz parte da lista");		
	}

	public static PCB proximoProcesso(){
		if(!ready.isEmpty()){
			return ready.getFirst();
		} else {
			return null;
		}
		
	}

	public static PCB addProxLoteria() {
        if (!ready.isEmpty()) {
            List<PCB> listaDeReady = new ArrayList<>(ready);
            int ind = new Random().nextInt(listaDeReady.size());
            return listaDeReady.get(ind);
        } else {
            return null;
        }
    }

	public static Map<Integer, LinkedList<PCB>> getReadyPrioridade() {
		return readyPrioridade;
	}

}
