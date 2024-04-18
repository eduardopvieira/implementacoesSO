package mars.mips.SO.ProcessManager;

import java.util.LinkedList;
import java.util.Queue;

import mars.mips.instructions.syscalls.SyscallProcessTerminate;

public class Semaphore {
 	private int endereco;
	private int valor;
	
	private Queue<PCB> processosBloqueados = new LinkedList<>();
	
	Semaphore(int endereco, int valor) {
		this.endereco = endereco;
		this.valor = valor;
	}
	
	public void SemaphoreDown() {
		System.out.println("Valor do semáforo DOWN = " + this.valor);

		if (this.valor > 0) {
				valor--;
		} else if (valor == 0) {
				// Confere quem chamou o semaforo e o bloqueia
				PCB processoEmExecucao = ProcessesTable.getPCB();
				processoEmExecucao.estadoBloqueado();
				
				// Adiciona o processo na fila de bloqueados
				processosBloqueados.add(processoEmExecucao);

				System.out.println("Novo processo bloqueado: " + processoEmExecucao);
				//System.out.println("Processos bloqueados: " + processosBloqueados + "\n");

				try {
					SyscallProcessTerminate syscallProcessTerminate = new SyscallProcessTerminate();
					syscallProcessTerminate.simulate(null);
				} catch (Exception e) {
					System.out.println("Erro ao realizar down no semáforo.");
				}			
		}
		
	}
	
	public void SemaphoreUp() {
		System.out.println("Valor do semáforo UP = " + this.valor);

		valor++;
		if (!processosBloqueados.isEmpty()) {
			PCB processoDesbloqueado = processosBloqueados.poll();
			processoDesbloqueado.estadoPronto();

			System.out.println("Novo processo Desbloqueado: " + processoDesbloqueado);
			//System.out.println("Processos bloqueados: " + processosBloqueados + "\n");

			ProcessesTable.addReady(processoDesbloqueado);
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

	@Override
	public String toString() {
		return "Semaphore [processosBloqueados=" + processosBloqueados + "]";
	}

}
