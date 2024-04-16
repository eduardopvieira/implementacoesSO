package mars.mips.instructions.syscalls;

import mars.*;
import mars.tools.MyTimer;
import mars.mips.SO.ProcessManager.Scheduler;
public class SyscallProcessChange extends AbstractSyscall {

    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {
		String tipo = MyTimer.tipoEscalonador();

		switch (tipo) {
			case "Escalonar linear":
				Scheduler.escalonarFIFO();
				System.out.println("Escalonar Linear");
				break;

			case "Escalonar Prioridade":
				Scheduler.escalonarFixa();
				break;

			case "Escalonar Loteria":
				Scheduler.escalonarLoteria();
				break;

			default:
				Scheduler.escalonarFIFO();
		}
    }
}