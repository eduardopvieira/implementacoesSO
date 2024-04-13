package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;


public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        ProcessesTable.setCurrentProcess(null);
        if (!ProcessesTable.getReadyPrioridade().isEmpty()) {			
			Scheduler.escalonarFixa();
		} else {
			Scheduler.escalonarFIFO();
		}
        

		
		

        

    }
}