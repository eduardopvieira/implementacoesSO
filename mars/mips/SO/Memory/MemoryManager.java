
package mars.mips.SO.Memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mars.mips.SO.ProcessManager.PCB;
import mars.mips.SO.ProcessManager.ProcessesTable;
import mars.mips.hardware.RegisterFile;
import mars.util.SystemIO;

public abstract class MemoryManager {
    // Atributos globais
    private static int tamPage = 3;
    private static int quantBlocos = 15;
    private static String alg = "FIFO";
    
    // Maps dos processos
    private static Map<Integer, Integer> numProcess = new HashMap<>();
    private static Map<Integer, List<Page>> pageProcess = new HashMap<>();

    public static void Page() {
        PCB currentProcess = ProcessesTable.getPCB();
        int programCounter = RegisterFile.getProgramCounter();
        boolean loaded = false;

        Page on;
        try {
            if(currentProcess.inEnd(programCounter)) {
                for (int j = 0; j < pageProcess.get(currentProcess.getPID()).size() && !loaded; j++) {
                    on = pageProcess.get(currentProcess.getPID()).get(j);
                    for (int i = 0; (i < tamPage) && !loaded; i++) {
                        if (on.getEnd()[i] == programCounter) {
                            loaded = true;
                        }
                    }
                }

                if (!loaded) {
                    pageFault();
                }
            } else {
                SystemIO.printString("Erro encontrado, acesso de página fora dos limites do processo" +
                                    "\nLimites de endereço do processo: [" + currentProcess.getEndInicio() + ", " + currentProcess.getEndFim() + "]" +
                                    "\nEndereço acessado: " + programCounter);
            }
        } catch (NullPointerException nullPointerException) {
            pageFault();
        }
    }

    public static Page pageFault() {
        PCB currentProcess = ProcessesTable.getPCB();
        int programCounter = RegisterFile.getProgramCounter();
        Page page = new Page(tamPage);

        for (int i = 0; i < tamPage; i++) {
            if (currentProcess.inEnd(programCounter + (i * 4))) {
                page.add(programCounter + (i * 4));
            } else {
                page.add(-1);
            }
        }

        int id = currentProcess.getPID();

        if (!pageProcess.containsKey(id)) {
            pageProcess.put(id, new ArrayList<>());
            numProcess.put(id, 1);
            pageProcess.get(id).add(page);

        } else {
            paginacao(page, currentProcess);
        }

        numProcess.replace(id, numProcess.get(id) % quantBlocos);
        return page;
    }

    public static void paginacao(Page pg, PCB processoAtual) {
        int id = processoAtual.getPID();
        numProcess.replace(id, numProcess.get(id) + 1);

        if (alg == "FIFO"){
            if (pageProcess.get(id).size() == numProcess.get(id) - 1) {
                pageProcess.get(id).add(pg);
            } else {
                pageProcess.get(id).set(numProcess.get(id) - 1, pg);
            }
        } else {
            SystemIO.printString("Algoritmo não definido");
        }
    }

    public static int getTamanhoPg() {
        return tamPage;
    }

    public static void setTamanhoPg(int tamanhoPg) {
        MemoryManager.tamPage = tamanhoPg;
    }

    public static int getQuantMax() {
        return quantBlocos;
    }

    public static void setQuantMax(int quantMax) {
        MemoryManager.quantBlocos = quantMax;
    }

    public static Map<Integer, Integer> getNumProcess() {
        return numProcess;
    }

    public static void setNumProcess(Map<Integer, Integer> numProcess) {
        MemoryManager.numProcess = numProcess;
    }

    public static Map<Integer, List<Page>> getPageProcess() {
        return pageProcess;
    }

    public static void setPageProcess(Map<Integer, List<Page>> pageProcess) {
        MemoryManager.pageProcess = pageProcess;
    }

    public static String getAlg() {
        return alg;
    }

    public static void setAlg(String alg) {
        MemoryManager.alg = alg;
    }    
} 
