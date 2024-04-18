package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;

public class PCB {
    
    private final int registersSize = 35;
    private int[] registerList = new int[registersSize];
    private int PID;
    private int Label;

    private int endInicio = 0; // Limite Superior
    private int endFim = 0; // Limite Inferior

    private Estado estado;
    private int prioridade=0;

    public PCB (){}

    public PCB (int PID, Estado estado, int Label, int prioridade) {
        this.PID = PID;
        this.estado = estado;
        this.Label = Label;
        this.prioridade = prioridade;
    }

    // Caso não tenha prioridade
    public PCB (int PID, Estado estado, int Label) {
        this.PID = PID;
        this.estado = estado;
        this.Label = Label;
        this.prioridade = 0;
    }

    

    public void copyRegisters() {
        for (int i = 0; i < registersSize; i++) {
            if (i == 32) {
                registerList[32] = RegisterFile.getProgramCounter();
            } else {
                registerList[i] = RegisterFile.getValue(i);  
            }
              
        }
    }

    public void writeRegisters() {
        for (int i = 0; i < registersSize; i++) {
            RegisterFile.updateRegister(i, registerList[i]);    
        }
        
    }

    public void setPID(int PID) {
        if (PID >= 0) {
            this.PID = PID;
        } else {
            System.out.println("Erro: PID menor que 0");
        }
    }

    public int getPID() {
        return this.PID;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void estadoPronto(){
        this.estado = Estado.PRONTO;
    }

    public void estadoExecutando(){
        this.estado = Estado.EXECUTANDO;
    }

    public void estadoBloqueado(){
        this.estado = Estado.BLOQUEADO;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getLabel() {
        return Label;
    }

    public void setLabel(int Label) {
        if (Label >= 0) {
            this.Label = Label;
        } else {
            System.out.println("Erro: endereco menor que 0");
        }
    }

    public int getRegistersSize() {
        return registersSize;
    }

    public int[] getRegisterList() {
        return registerList;
    }

    public void setRegisterList(int[] registerList) {
        this.registerList = registerList;
    }

    public int getEndInicio() {
        return endInicio;
    }

    public void setEndInicio(int endInicio) {
        this.endInicio = endInicio;
    }

    public boolean inEnd(int end) {
        return (endFim >= end && end >= endInicio);
    }

    public int getEndFim() {
        return endFim;
    }

    public void setEndFim(int endFim) {
        this.endFim = endFim;
    }
    

}

