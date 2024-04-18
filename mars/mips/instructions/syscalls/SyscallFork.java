package mars.mips.instructions.syscalls;
import mars.*;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.Memory;
import mars.mips.hardware.RegisterFile;
import mars.tools.MyTimer;


public class SyscallFork extends AbstractSyscall{

    static int id = 0;
    static PCB ultimoPCB;
	public SyscallFork() {
        super(60, "SyscallFork");
    }

        @Override
        public void simulate(ProgramStatement statement) throws ProcessingException {
            id++;
            int Label = RegisterFile.getValue(4);
            int prioridade = RegisterFile.getValue(5);  

            PCB novoPCB = new PCB();
            novoPCB.setLabel(Label);
            novoPCB.setEndInicio(Label);
            novoPCB.setPID(id);

            if(id != 1) {
                limit(novoPCB);
            } else {
                novoPCB.setEndFim(Memory.textLimitAddress);
            }

            ultimoPCB = novoPCB;

            System.out.println("ultimo processo "+ultimoPCB);

            String tipo = MyTimer.tipoEscalonador();
            if (tipo.equals("Escalonar Prioridade")) novoPCB.setPrioridade(prioridade);

            ProcessesTable.addReady(novoPCB);
        }


        public void limit(PCB currentProcess) {
            if (currentProcess.getEndInicio() > ultimoPCB.getEndInicio()) {
                ultimoPCB.setEndFim(currentProcess.getEndInicio() - 4);
                currentProcess.setEndFim(Memory.textLimitAddress);
    
            } else if (currentProcess.getEndInicio() < ultimoPCB.getEndInicio()) {
                currentProcess.setEndFim(ultimoPCB.getEndInicio() - 4);
                ultimoPCB.setEndFim(Memory.textLimitAddress);
    
            }

        
        }   
}


