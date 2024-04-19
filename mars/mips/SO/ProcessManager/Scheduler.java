package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class Scheduler {

	public static void escalonar() {
		PCB processoExecutando = ProcessesTable.getPCB();
			
		trocarContexto(processoExecutando);
			
		if (processoExecutando != null) {
			ProcessesTable.addReady(processoExecutando);
		}

		PCB proximoProcesso = ProcessesTable.proximoProcesso();
		executarProximoProcesso(proximoProcesso);
	}

	
	private static void executarProximoProcesso(PCB proxProcess) {
		if (proxProcess != null) {
						ProcessesTable.setCurrentProcess(proxProcess);
            proxProcess.writeRegisters();
            RegisterFile.setProgramCounter(ProcessesTable.getPCB().getInitialAdress());
		}
	}

	private static void trocarContexto(PCB processoExecutando){
		if (processoExecutando != null) {
			processoExecutando.copyRegisters();
			processoExecutando.setInitialAdress(RegisterFile.getProgramCounter());
		}

}
}
