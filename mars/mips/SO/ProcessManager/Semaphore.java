package mars.mips.SO.ProcessManager;

import java.util.LinkedList;
import java.util.Queue;

import mars.tools.MyTimer;

public class Semaphore {
  private int endereco;
	private int valor;
	
	private Queue<PCB> processosBloqueados = new LinkedList<>();
	
	Semaphore(int endereco, int valor) {
		this.endereco = endereco;
		this.valor = valor;
	}
	
	public void SemaphoreDown() {
		System.out.println("Entrei no semaphore 0.1");
		if (this.valor > 0) {
				System.out.println("Entrei no semaphore 0");
				valor--;
		} else if (valor == 0) {
				System.out.println("Entrei no semaphore 1");
				PCB processoEmExecucao = ProcessesTable.getPCB();
				System.out.println("Entrei no semaphore 2");
				ProcessesTable.setProcessoAtual(null);
				System.out.println("Entrei no semaphore 3");
				processoEmExecucao.estadoBloqueado();
				System.out.println("Entrei no semaphore 4");
				
				processosBloqueados.add(processoEmExecucao);
				System.out.println("Entrei no semaphore 5");
			
				if (!MyTimer.teste()) {
						System.out.println("Entrei no semaphore 6");
						switch (MyTimer.tipoEscalonador()) {
						case "Escalonar linear":
						System.out.println("Entrei no semaphore 7");
							Scheduler.escalonarFIFO();
							
							break;
						case "Escalonar Prioridade":
						System.out.println("Entrei no semaphore 8");
							Scheduler.escalonarFixa();
							
							break;
						case "Escalonar Loteria":
						System.out.println("Entrei no semaphore 9");
							Scheduler.escalonarLoteria();
							
							break;
						default:
							System.out.println("Tipo inválio!");
					}
			}
		}
	}
	
	public void SemaphoreUp() {
		if (processosBloqueados.isEmpty()) {
			valor++;
		} else {
			switch (MyTimer.tipoEscalonador()) {
        case "Escalonar linear":
          Scheduler.escalonarFIFO();
          System.out.println("Escalonar Linear");
          break;
  
        case "Escalonar Prioridade":
          Scheduler.escalonarFixa();
          break;
  
        case "Escalonar Loteria":
          Scheduler.escalonarLoteria();
          break;
  
        default:
          System.out.println("Tipo inválio!");
      }
		}
	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public Queue<PCB> getProcessosBloqueados() {
		return processosBloqueados;
	}

	public void setProcessosBloqueados(Queue<PCB> processosBloqueados) {
		this.processosBloqueados = processosBloqueados;
	}
}
