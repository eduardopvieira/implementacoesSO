package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.tools.MyTimer;


public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        ProcessesTable.setCurrentProcess(null);
		
		try {
			String tipo = MyTimer.tipoEscalonador();

			switch (tipo) {
				case "Escalonar linear":
					Scheduler.escalonarFIFO();

					break;
				case "Escalonar Prioridade":
                    Scheduler.escalonarFixa();

					break;
				case "Escalonar Loteria":
                    Scheduler.escalonarLoteria();

					break;
				default:
                    Scheduler.escalonarFIFO();
			}

		} catch (Exception e) {
			e.printStackTrace();

			if (ProcessesTable.getPCB().getPrioridade() != 0) {
				Scheduler.escalonarFixa();
			} else {
				Scheduler.escalonarFIFO();
			}
        

		
		

        

    }
    }
}