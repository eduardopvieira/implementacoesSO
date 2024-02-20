package mars.mips.instructions.syscalls;
import mars.util.*;
import mars.*;
<<<<<<< HEAD
=======
import mars.mips.hardware.RegisterFile;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
>>>>>>> 9c143e2f4182647ea473f8437c5fca090c11c343

public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
<<<<<<< HEAD
        super(18, "MySyscall");
=======
        super(60, "SyscallFork");
>>>>>>> 9c143e2f4182647ea473f8437c5fca090c11c343
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
<<<<<<< HEAD
        SystemIO.printString("Olá Syscall!\n");
    }
}
=======
        

        int initialPC = RegisterFile.getProgramCounter();

        PCB novoPCB = new PCB(initialPC);

        ProcessesTable.addReady(novoPCB);
        SystemIO.printString("deu certo\n");
        
    }
}
>>>>>>> 9c143e2f4182647ea473f8437c5fca090c11c343
