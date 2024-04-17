package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class Scheduler {

	public static void escalonarFixa() {		
		PCB processoExecutando = ProcessesTable.getPCB();
		trocarContexto(processoExecutando);

		if (processoExecutando != null){
			ProcessesTable.addReady(processoExecutando);
		}	
		System.out.println("Processos prontos: " + ProcessesTable.getprocessosProntos() + "\n");
		PCB proximoProcesso = ProcessesTable.addProxPrioridade();
		System.out.println("Processo escolhido: " + proximoProcesso + " com prioridade: " + proximoProcesso.getPrioridade() + "\n");
		executarProximoProcesso(proximoProcesso);			
	}
		
	public static void escalonarLoteria() {
		PCB processoExecutando = ProcessesTable.getPCB();
			
		trocarContexto(processoExecutando);
				
		if (processoExecutando != null) {
			ProcessesTable.addReady(processoExecutando);
		}

		PCB proximoProcesso = ProcessesTable.addProxLoteria();
		executarProximoProcesso(proximoProcesso);
	}
		
	public static void escalonarFIFO() {
		System.out.println("Processos: " + ProcessesTable.getprocessosProntos());
		PCB processoExecutando = ProcessesTable.getPCB();
			
		trocarContexto(processoExecutando);
			
		if (processoExecutando != null) {
			ProcessesTable.addReady(processoExecutando);
		}

		PCB proximoProcesso = ProcessesTable.proximoProcesso();
		System.out.println("Processo escolhido: " + proximoProcesso + " com prioridade: " + proximoProcesso.getPrioridade() + "\n");
		executarProximoProcesso(proximoProcesso);
	}
	
	private static void executarProximoProcesso(PCB proxProcess) {
		if (proxProcess != null) {
			ProcessesTable.setProcessoAtual(proxProcess);
            proxProcess.estadoExecutando();
            proxProcess.writeRegisters();
            RegisterFile.setProgramCounter(ProcessesTable.getPCB().getLabel());
		}
	}

	private static void trocarContexto(PCB processoExecutando){
		if (processoExecutando != null) {
			processoExecutando.estadoPronto();
			processoExecutando.copyRegisters();
			processoExecutando.setLabel(RegisterFile.getProgramCounter());
		}
	}
}
