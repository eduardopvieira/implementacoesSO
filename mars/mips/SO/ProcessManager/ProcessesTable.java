package mars.mips.SO.ProcessManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class ProcessesTable {
	private static final int prioridadeMaxima = 5;
	private static Queue<PCB> ready;
	private static Map<Integer, Queue<PCB>> readyPrioridade;
	private static PCB currentProcess;
	

	static {
		readyPrioridade = new HashMap<>();
		ready = new LinkedList<>();
		currentProcess = null;
		
		// Inicializa as filas de prioridade
		for (int prioridade = 0; prioridade <= prioridadeMaxima; prioridade++) {
				readyPrioridade.put(prioridade, new LinkedList<>());
		}

}
	public static void resetar(){
		ready = new LinkedList<>();
		readyPrioridade = new HashMap<>();
		currentProcess = null;
	}

	public static void addReady(PCB process) {
		ready.add(process);
	}

	public static Queue<PCB> getReady() {
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
        Queue<PCB> list = readyPrioridade.get(prioridade);
        list.add(ready);
				ProcessesTable.addReady(ready);
    }

	public static PCB addProxPrioridade() {
        for (int prioridadeMaior = prioridadeMaxima; prioridadeMaior >= 0; prioridadeMaior--) {
            Queue<PCB> list = readyPrioridade.get(prioridadeMaior);
            if (!list.isEmpty()) {
                return list.poll();
            }
        }
        return null;
    }

	public static PCB proximoProcesso(){
		if(!ready.isEmpty()){
			return ready.poll();
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

	public static Map<Integer, Queue<PCB>> getReadyPrioridade() {
		return readyPrioridade;
	}

}
