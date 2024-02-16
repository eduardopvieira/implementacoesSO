package mars.mips.instructions.syscalls;
import mars.util.*;
import mars.*;
import mars.mips.hardware.RegisterFile;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;

public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
        super(60, "SyscallFork");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        

        int initialPC = RegisterFile.getProgramCounter();

        PCB novoPCB = new PCB(initialPC);

        ProcessesTable.addReady(novoPCB);
        SystemIO.printString("deu certo\n");
        
    }
}