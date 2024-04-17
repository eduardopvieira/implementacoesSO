package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.Semaphore;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.RegisterFile;

public class SyscallTerminateSemaphore extends AbstractSyscall{

  public SyscallTerminateSemaphore() {
    super(64, "SyscallTerminateSemaphore");
    //TODO Auto-generated constructor stub
  }

  @Override
  public void simulate(ProgramStatement statement) throws ProcessingException {
    // TODO Auto-generated method stub
    int enderecoVar = RegisterFile.getValue(4);

    try{
      Semaphore semaphore = SemaphoreList.obterPorEndereco(enderecoVar);
      SemaphoreList.semaphores.remove(semaphore);
      semaphore.getProcessosBloqueados().clear();
    } catch (Exception e){
      System.out.println("Erro ao remover semáforo.");
      throw new ProcessingException();
    }
  }
  
}
