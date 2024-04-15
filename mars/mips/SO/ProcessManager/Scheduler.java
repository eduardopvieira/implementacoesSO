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

				executarProximoProcesso(proximoProcesso);

			if (processoExecutando != null){
				ProcessesTable.addPrioridade(processoExecutando);
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
			
			executarProximoProcesso(proximoProcesso);
				
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
			
			executarProximoProcesso(proximoProcesso);
			
			if (processoExecutando != null) {
				ProcessesTable.addReady(processoExecutando);
			}
		}
	
		private static void executarProximoProcesso(PCB proxProcess) {
			if (proxProcess != null) {
						ProcessesTable.setCurrentProcess(proxProcess);
            proxProcess.estadoExecutando();
            proxProcess.writeRegisters();
            RegisterFile.setProgramCounter(ProcessesTable.getPCB().getLabel());
			}
		}

}
