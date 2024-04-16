package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class Scheduler {

		public static void escalonarFixa() {
			PCB processoExecutando = ProcessesTable.getPCB();

			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}

			if (processoExecutando != null){
				ProcessesTable.addPrioridade(processoExecutando);
			}	

			PCB proximoProcesso = ProcessesTable.addProxPrioridade();
			System.out.println(proximoProcesso.getPrioridade());
			executarProximoProcesso(proximoProcesso);
				
		}
		
		public static void escalonarLoteria() {
			PCB processoExecutando = ProcessesTable.getPCB();
			
			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}
				
				if (processoExecutando != null) {
					ProcessesTable.addReady(processoExecutando);
				}

				PCB proximoProcesso = ProcessesTable.addProxLoteria();
				executarProximoProcesso(proximoProcesso);
			}
		
		public static void escalonarFIFO() {
			PCB processoExecutando = ProcessesTable.getPCB();
			
			if (processoExecutando != null) {
				processoExecutando.estadoPronto();
				processoExecutando.copyRegisters();
				processoExecutando.setLabel(RegisterFile.getProgramCounter());
			}
			
			if (processoExecutando != null) {
				ProcessesTable.addReady(processoExecutando);
			}

			PCB proximoProcesso = ProcessesTable.proximoProcesso();
			executarProximoProcesso(proximoProcesso);

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
