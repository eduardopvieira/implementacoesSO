package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.tools.MyTimer;
import mars.util.SystemIO;


public class SyscallProcessTerminate extends AbstractSyscall{

	public SyscallProcessTerminate() {
        super(62, "SyscallProcessTerminate");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

		System.out.println("-=- Terminate -=-");
        ProcessesTable.removeReady();
		//System.out.println(ProcessesTable.getprocessosProntos());
		
		try {
			String tipo = MyTimer.tipoEscalonador();
			System.out.print("Escalonador: ");

			switch (tipo) {
				case "Escalonar linear":
					System.out.println("Linear");
					Scheduler.escalonarFIFO();

					break;
				case "Escalonar Prioridade":
					System.out.println("Prioridade");
                    Scheduler.escalonarFixa();

					break;
				case "Escalonar Loteria":
					System.out.println("Loteria");
                    Scheduler.escalonarLoteria();

					break;
				default:
                    Scheduler.escalonarFIFO();
			}
		} catch (Exception e) {
			SystemIO.printString("Lista de processos esvaziada!");
			throw new ProcessingException();  // Lista de processos vazia: encerra
    	}
    }
}