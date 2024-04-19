package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.mips.hardware.RegisterFile;


public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        ProcessesTable.removeReady();

        Scheduler.escalonar();

    }
}