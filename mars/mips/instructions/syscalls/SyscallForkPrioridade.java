package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;

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
      novopcb.setPrioridade(prioridade);
      
      ProcessesTable.addPrioridade(novopcb);
    }

    
}
