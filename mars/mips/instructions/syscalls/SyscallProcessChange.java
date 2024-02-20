package mars.mips.instructions.syscalls;

import mars.util.*;
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
        // Salva o contexto do processo atual
        saveContext();

        // Chama o algoritmo de escalonamento para escolher um novo processo
        Scheduler.escalonar(ProcessesTable.getReady());

        // Carrega o contexto do novo processo
        loadContext(ProcessesTable.getCurrentProcess());

        // Retorno à execução do novo processo
        return;
    }

    private void saveContext() {
        // Obtém o PC atual
        int currentPC = RegisterFile.getProgramCounter();
        
        // Obtém os valores dos registradores e os salva no PCB do processo atual
        PCB currentPCB = ProcessesTable.getCurrentProcess();
        currentPCB.copyRegisters();
        RegisterFile.setProgramCounter(currentPCB.getInitialAdress());
    }

    private void loadContext(PCB newProcess) {
        // Carrega o PC do novo processo
        RegisterFile.setProgramCounter(newProcess.getInitialAdress());

        // Carrega os valores dos registradores do PCB do novo processo nos registradores físicos
        // Exemplo:
        RegisterFile.updateRegister(ProcessesTable.getPCB(), RegisterFile.getValue(getNumber(PID)));
        // Repetir para todos os registradores que precisam ser carregados
    }
}