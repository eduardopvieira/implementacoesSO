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
            
            PCB novopcb = new PCB();
            novopcb.setLabel(Label);

            String tipo = MyTimer.tipoEscalonador();
            if (tipo.equals("Escalonar Prioridade")) novopcb.setPrioridade(prioridade);

            ProcessesTable.addReady(novopcb);
        }

        
        
}


