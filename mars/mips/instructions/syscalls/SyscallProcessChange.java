package mars.mips.instructions.syscalls;

import mars.*;
import mars.mips.hardware.*;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.Scheduler;
public class SyscallProcessChange extends AbstractSyscall {

    public SyscallProcessChange() {
        super(61, "SyscallProcessChange");
    }

    @Override
    public void simulate(ProgramStatement statement) throws ProcessingException {

        // Carrega o contexto do novo processo
        loadProcess(ProcessesTable.getPCB());

         // Chama o algoritmo de escalonamento para escolher um novo processo
        RegisterFile.setProgramCounter(ProcessesTable.getPCB().getLabel();

    }

    private void loadProcess(PCB newProcess) {

        if (newProcess != null){
            newProcess.copyRegisters();
            newProcess.setLabel(RegisterFile.getProgramCounter());
        }

        Scheduler.escalonar();
    }
}