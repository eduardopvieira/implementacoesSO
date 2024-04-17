.include "macros.asm"

.data
# produtor consumidor => cada um vai ser um processo
# alternancia entre processos deve ser pelo timer
# criacao do semaforo (mutex,empty e full) devem ser feitas antes da execucao dos processo de produtor e consumidor 
# buffer de dados compartilhado deve ser uma pilha 
# lw e sw para inserir e remover do buffer (observar estado da pilha durante a execucao)

	empty: .word 32
    full: .word 0
    mutex: .word 1

	buffer:
		.align 2
		.space 128

.text
	SyscallCreateSemaphore(empty)#empty	

	SyscallCreateSemaphore(full)#full

	SyscallCreateSemaphore(mutex)#mutex
	
	#criação dos processos com prioridade
	SyscallFork(Producer, 2)
	SyscallFork(Consumer, 1)
	#escalonando o primeiro processo
	SyscallProcessChange
	 
	li $t2, -1
		
Producer:
	loop1:
        SyscallDownSemaphore(empty)
        SyscallDownSemaphore(mutex)
        
        sw $t1, buffer($t0)
        addi $t1, $t1, 1 #item $t1 incrementa mais 1
        addi $t0, $t0, 4 #passa para o proximo indice
        
        SyscallUpSemaphore(mutex)
        SyscallUpSemaphore(full)	
       	beq $t0, $t2, fim1
		SyscallProcessChange
		j loop1
	fim1:	SyscallProcessTerminate
           
Consumer:
	loop2:
        SyscallDownSemaphore(full)
        SyscallDownSemaphore(mutex)
        
        lw $a0, buffer($t0)
        addi $t0, $t0, 4 #passa para o proximo indice	
        
        SyscallUpSemaphore(mutex)
        SyscallUpSemaphore(empty)
        
        beq $t0, $t2, fim2
		SyscallProcessChange
		j loop2
	fim2:	SyscallProcessTerminate
        
        
