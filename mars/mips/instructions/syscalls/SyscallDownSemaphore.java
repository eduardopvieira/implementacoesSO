package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallDownSemaphore extends AbstractSyscall{

  public SyscallDownSemaphore() {
    super(66, "SyscallDownSemaphore");
    //TODO Auto-generated constructor stub
  }

  @Override
  public void simulate(ProgramStatement statement) throws ProcessingException {
    // TODO Auto-generated method stub
    int enderecoVar = RegisterFile.getValue(4);
    
    try {
      System.out.println("entrei aqui no down");
      Semaphore semaphore = SemaphoreList.obterPorEndereco(enderecoVar);
      System.out.println("entrei aqui no down 2");
      semaphore.SemaphoreDown();
      System.out.println("entrei aqui no down 3");
    } catch (Exception e) {
        System.out.println("Erro ao realizar down no semáforo.");
        throw new ProcessingException();
    }
    
  }
  
}
