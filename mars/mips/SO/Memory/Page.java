
package mars.mips.SO.Memory;

public class Page {
    private int tamPage;
    private int[] enderecos;
    private int ultimaPosicao;
    private boolean read = false;

    public Page(int tamPage, int[] end) {
        this.tamPage = tamPage;
        this.enderecos = end;
        ultimaPosicao = 0;
    }

    public Page(int tamPage) {
        this.tamPage = tamPage;
        enderecos = new int[this.tamPage];
        ultimaPosicao = 0;
    }

    public int[] getEnd() {
        return enderecos;
    }

    public boolean add(int endereco) {
        if (!(ultimaPosicao < tamPage)) {
            return false;
        } else {
            enderecos[ultimaPosicao] = endereco;
            ultimaPosicao++;
        }

        return true;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}

