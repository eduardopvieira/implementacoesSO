package mars.mips.SO.ProcessManager;

import java.util.LinkedList;
import java.util.Queue;

public class SemaphoreList {
  
  public static Queue<Semaphore> semaphores = new LinkedList<>();

    public static void criarSemaphore(int valor, int endSemaphore) {
        Semaphore semaphore = new Semaphore(valor, endSemaphore);
        semaphores.add(semaphore);
    }

    public static Semaphore obterPorEndereco(int end) throws Exception {
        for(Semaphore semaphore : semaphores) {
            if(semaphore.getEndereco() == end) {
                return semaphore;
            }
        }
        return null;
    }
}
