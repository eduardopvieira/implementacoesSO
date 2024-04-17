package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;
import mars.tools.MyTimer;


public class SyscallFork extends AbstractSyscall{

	public SyscallFork() {
        super(60, "SyscallFork");
    }

        @Override
        public void simulate(ProgramStatement statement) throws ProcessingException {
            int Label = RegisterFile.getValue(4);
            int prioridade = RegisterFile.getValue(5);  

            PCB novoPCB = new PCB();
            novoPCB.setLabel(Label);

            String tipo = MyTimer.tipoEscalonador();
            if (tipo.equals("Escalonar Prioridade")) novoPCB.setPrioridade(prioridade);

            ProcessesTable.addReady(novoPCB);
        }

        
        
}


