package mars.mips.instructions.syscalls;

import mars.ProcessingException;
import mars.ProgramStatement;
import mars.mips.SO.ProcessManager.SemaphoreList;
import mars.mips.hardware.Memory;
import mars.mips.hardware.RegisterFile;

public class SyscallCreateSemaphore extends AbstractSyscall {

  public SyscallCreateSemaphore() {
    super(63, "SyscallCreateSemaphore");
    //TODO Auto-generated constructor stub
  }

  @Override
  public void simulate(ProgramStatement statement) throws ProcessingException {
    // TODO Auto-generated method stub
    int enderecoVar = RegisterFile.getValue(4);
    
    try{
      int valorVar = Memory.getInstance().get(enderecoVar, 4);
      SemaphoreList.criarSemaphore(valorVar, enderecoVar);
    } catch (Exception e){
      System.out.println("Erro ao criar semáforo.");
      throw new ProcessingException();
    }
    
  }
  
}
