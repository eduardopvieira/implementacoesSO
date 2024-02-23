package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;


public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
        super(60, "SyscallFork");
    }
        

        int initialPC = RegisterFile.getProgramCounter();

        PCB novoPCB = new PCB(initialPC);

        @Override
        public void simulate(ProgramStatement statement) throws ProcessingException {
            ProcessesTable.addReady(novoPCB);
        }

        
        
}


