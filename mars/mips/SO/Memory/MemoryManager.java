
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
    private static int tamPage = 3; // Tamanho da página
    private static int valueMax = 15; // Quantidade máxima de páginas
    private static Map<Integer, Integer> numProcess = new HashMap<>(); // Quantidade de páginas por processo
    private static Map<Integer, List<Page>> pageProcess = new HashMap<>(); // Mapa de páginas por processo
    private static String type = "FIFO"; // Algoritmo de substituição de páginas

    // Todo system.out que está aqui tem o propósito de debuggar o código, 
    // habilite-os para ver o que o programa está analisando naquele momento.

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
                        // System.out.println("comparacao: " + e.getAdress()[i] + " == " + pc + "?");
                    }
                }

                if (!loaded) {
                    // System.out.println("Page Fault, reason: loaded = false");
                    pageFault();
                }
            } else {
                SystemIO.printString("Erro encontrado, acesso de página fora dos limites do processo" +
                                    "\nLimites de endereço do processo: [" + currentProcess.getEndInicio() + ", " + currentProcess.getEndFim() + "]" +
                                    "\nEndereço acessado: " + programCounter);
            }
        } catch (NullPointerException nullPointerException) {
            // System.out.println("Page Fault, reason: First page");
            pageFault();
        }
        // System.out.println("___________Fim ensurePage___________");
    }

    public static Page pageFault() {
        PCB currentProcess = ProcessesTable.getPCB();
        int programCounter = RegisterFile.getProgramCounter();
        Page page = new Page(tamPage);

        for (int i = 0; i < tamPage; i++) {
            if (currentProcess.inEnd(programCounter + (i * 4))) {
                page.add(programCounter + (i * 4));
                // System.out.println(pc + (i * 4) + ": " + pg.getAdress()[i]);
            } else {
                page.add(-1);
            }
        }

        int id = currentProcess.getPID();

        if (!pageProcess.containsKey(id)) {
            // System.out.println("Primeira página inserida");
            pageProcess.put(id, new ArrayList<>());
            numProcess.put(id, 1);
            pageProcess.get(id).add(page);

        } else {
            paginacao(page, currentProcess);
            // System.out.println("Próxima página inserida");
        }

        numProcess.replace(id, numProcess.get(id) % valueMax);
        return page;
    }

    public static void paginacao(Page pg, PCB processoAtual) {
        int id = processoAtual.getPID();
        numProcess.replace(id, numProcess.get(id) + 1);

        switch (type) {
            case "FIFO":

                if (pageProcess.get(id).size() == numProcess.get(id) - 1) {
                    pageProcess.get(id).add(pg);
                } else {
                    pageProcess.get(id).set(numProcess.get(id) - 1, pg);
                }

                break;
        
            default:
                SystemIO.printString("Algoritmo de página não encontrado");

                break;
        }
    }

    // Getters e Setters
    public static int getTamanhoPg() {
        return tamPage;
    }

    public static void setTamanhoPg(int tamanhoPg) {
        MemoryManager.tamPage = tamanhoPg;
    }

    public static int getQuantMax() {
        return valueMax;
    }

    public static void setQuantMax(int quantMax) {
        MemoryManager.valueMax = quantMax;
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

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        MemoryManager.type = type;
    }    
} 
