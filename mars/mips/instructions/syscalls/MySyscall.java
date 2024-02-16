package mars.mips.instructions.syscalls;
import mars.util.*;
import mars.*;

public class MySyscall extends AbstractSyscall{

	public MySyscall() {
        super(18, "MySyscall");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
        SystemIO.printString("Olá Syscall!\n");
    }
}