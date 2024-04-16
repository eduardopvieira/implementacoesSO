package mars.mips.SO.ProcessManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class ProcessesTable {
	private static final int prioridadeMaxima = 5;
	private static Map<Integer, LinkedList<PCB>> processosProntos;
	private static PCB processoAtual;
	

	static {
		processosProntos = new HashMap<>();
		processoAtual = null;
		
		// Inicializa as filas de prioridade
		for (int prioridade = 0; prioridade <= prioridadeMaxima; prioridade++) {
			processosProntos.put(prioridade, new LinkedList<>());
		}
	}

	public static void resetar(){
		processosProntos = new HashMap<>();
		for (int prioridade = 0; prioridade <= prioridadeMaxima; prioridade++) {
			processosProntos.put(prioridade, new LinkedList<>());
		}
		processoAtual = null;
	}

	public static void addReady(PCB process) {
        int prioridade = process.getPrioridade();
        Queue<PCB> list = processosProntos.get(prioridade);
		list.add(process);
    }

	public static Queue<PCB> getReady() {
		return processosProntos.get(0);
	}

	public static void removeReady() {
		System.out.println(processoAtual);
		processosProntos.get(processoAtual.getPrioridade()).remove(processoAtual);
		processoAtual = null;
	}

	public static PCB getPCB() {
		return processoAtual;
	}

	public static void setProcessoAtual(PCB processoAtual) {
		if (processoAtual != null)
			ProcessesTable.processoAtual = processoAtual;
	}

	public static PCB addProxPrioridade() {
        for (int prioridadeMaior = prioridadeMaxima; prioridadeMaior >= 0; prioridadeMaior--) {
            Queue<PCB> list = processosProntos.get(prioridadeMaior);
            if (!list.isEmpty()) {
                return list.poll();
            }
        }
        return null;
    }

	public static PCB proximoProcesso(){
		if(!processosProntos.get(0).isEmpty()){
			return processosProntos.get(0).poll();
		}
		
		return null;
	}

	public static PCB addProxLoteria() {
        if (!processosProntos.get(0).isEmpty()) {
            int ind = new Random().nextInt(processosProntos.get(0).size());

			PCB processoSorteado = processosProntos.get(0).get(ind);
            processosProntos.get(0).remove(processoSorteado);

			return processoSorteado;
        } else {
            return null;
        }
    }

	public static Map<Integer, LinkedList<PCB>> getprocessosProntos() {
		return processosProntos;
	}
}
