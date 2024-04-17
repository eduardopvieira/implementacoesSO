package mars.mips.SO.ProcessManager;

import java.util.LinkedList;
import java.util.Queue;

public class SemaphoreList {
  
  public static Queue<Semaphore> semaphores = new LinkedList<>();

    public static void criarSemaphore(int end, int valor) {
        Semaphore semaphore = new Semaphore(end, valor);
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
