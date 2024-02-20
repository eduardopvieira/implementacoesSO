package mars.mips.instructions.syscalls;
import mars.util.*;
import mars.*;

public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
        super(18, "MySyscall");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        SystemIO.printString("Olá Syscall!\n");
    }
}
