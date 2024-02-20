package mars.mips.SO.ProcessManager;

import mars.mips.hardware.RegisterFile;
import mars.mips.hardware.Register;

public class PCB {
    private int registersSize = 36;
    private int[] registerList = new int[registersSize];
    
    private int initialAdress;
    private int PID;
    private boolean estado;
    
    private static int count;


    public void copyRegisters() {
        for (int i = 0; i < registersSize-1; i++) {
            if (i == 32) {
                registerList[i] = RegisterFile.getProgramCounter();
            }
            registerList[i] = RegisterFile.getValue(i);    
        }
    }

    public void writeRegisters() {
        for (int i = 0; i < registersSize-1; i++) {
            RegisterFile.updateRegister(i, registerList[i]);    
        }
        
        RegisterFile.initializeProgramCounter(registerList[35]);
    }

    public void setInitialAdress(int initialAdress) {
        if (initialAdress >= 0) {
            this.initialAdress = initialAdress;
        } else {
            System.out.println("Erro: endereco menor que 0");
        }
    }

    public int getInitialAdress() {
        return this.initialAdress;    
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

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public boolean getEstado() {
        return this.estado;
    }
}

