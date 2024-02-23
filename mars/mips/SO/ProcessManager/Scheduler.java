package mars.mips.SO.ProcessManager;

public class Scheduler {

	public static void escalonar() {
		
		PCB processoExcutando = ProcessesTable.getPCB();
		PCB proximoProcesso = ProcessesTable.proximoProcesso();

		if (proximoProcesso != null){
			ProcessesTable.setCurrentProcess(proximoProcesso);
			proximoProcesso.writeRegisters();

			if(processoExcutando != null){
				ProcessesTable.addReady(processoExcutando);
			}

		} else {
			ProcessesTable.removeReady(processoExcutando);
		}

	}
}
