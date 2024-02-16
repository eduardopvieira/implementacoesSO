package mars.mips.instructions.syscalls;
import mars.util.*;
import mars.*;

public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        SystemIO.printString("Olá SyscallProcessTerminate!\n");
    }
}