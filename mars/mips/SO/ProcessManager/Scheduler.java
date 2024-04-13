package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class Scheduler {

		public static void escalonarFixa() {
			PCB processoExecutando = ProcessesTable.getPCB();
			PCB proximoProcesso = ProcessesTable.addProxPrioridade();
			
			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}
			
			executarProximoProcesso(proximoProcesso, processoExecutando);
	
			if (processoExecutando != null)
			{
				ProcessesTable.addPrioridade(proximoProcesso);
			}
		}
		
		public static void escalonarLoteria() {
			PCB processoExecutando = ProcessesTable.getPCB();
			PCB proximoProcesso = ProcessesTable.addProxLoteria();
			
			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}
			
			executarProximoProcesso(proximoProcesso, processoExecutando);
				
				if (processoExecutando != null) {
					ProcessesTable.addReady(processoExecutando);
				}
			}
		
		public static void escalonarFIFO() {
			PCB processoExecutando = ProcessesTable.getPCB();
			PCB proximoProcesso = ProcessesTable.proximoProcesso();
			
			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}
			
			executarProximoProcesso(proximoProcesso, processoExecutando);
			
			if (processoExecutando != null) {
				ProcessesTable.addReady(processoExecutando);
			}
		}
	
		private static void executarProximoProcesso(PCB proxProcess, PCB currentProcess) {
			if (proxProcess != null) {
					currentProcess.estadoPronto();
					currentProcess.copyRegisters();
					currentProcess.setLabel(RegisterFile.getProgramCounter());
					proxProcess.estadoExecutando();
					proxProcess.copyRegisters();
					RegisterFile.setProgramCounter(proxProcess.getLabel());
			}
		}
	
	
		// Retorna true se houver processos prontos na fila da TabelaDeProcessos
		public static boolean hasProcessosProntos() {
			return ProcessesTable.getReady().size() > 0;
		}

		
	
}
