package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.SO.ProcessManager.Scheduler;
import mars.mips.hardware.RegisterFile;
import mars.tools.MyTimer;

public class SyscallForkPrioridade extends AbstractSyscall {
  
  public SyscallForkPrioridade()  {
    super(63, "SyscallForkPrioridade");
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
