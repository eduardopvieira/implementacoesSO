package mars.mips.instructions.syscalls;

import mars.*;
import mars.mips.SO.ProcessManager.Scheduler;
public class SyscallProcessChange extends AbstractSyscall {

    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        Scheduler.escalonar();

    }
}