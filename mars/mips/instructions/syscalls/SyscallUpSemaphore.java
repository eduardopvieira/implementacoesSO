package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallUpSemaphore extends AbstractSyscall {

  public SyscallUpSemaphore() {
    super(65, "SyscallUpSemaphore");
    //TODO Auto-generated constructor stub
  }

  @Override
  public void simulate(ProgramStatement statement) throws ProcessingException {
    // TODO Auto-generated method stub
    int enderecoVar = RegisterFile.getValue(4);
    
    try {
      Semaphore semaphore = SemaphoreList.obterPorEndereco(enderecoVar);
      semaphore.SemaphoreUp();
    } catch (Exception e) {
        System.out.println("Erro ao realizar up no semáforo.");
        throw new ProcessingException();
    }
    
  }
  
}
