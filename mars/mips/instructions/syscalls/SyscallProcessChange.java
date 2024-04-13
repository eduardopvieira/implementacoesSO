package mars.mips.instructions.syscalls;

import mars.*;
import mars.mips.hardware.*;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.Scheduler;
public class SyscallProcessChange extends AbstractSyscall {

    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        if(!ProcessesTable.getReadyPrioridade().isEmpty()){
            Scheduler.escalonarFixa();
        } else {
            Scheduler.escalonarFIFO();
        }

    }

}