
package mars.mips.SO.Memory;

public class Page {
    private int tamPage; // Tamanho da página
    private int[] end; // Endereços da página
    private int i = 0;
    private boolean read = false;

    public Page(int tamPage, int[] end) {
        this.tamPage = tamPage;
        this.end = end;
    }

    public Page(int tamPage) {
        this.tamPage = tamPage;
        end = new int[this.tamPage];
    }

    public int[] getEnd() {
        return end;
    }

    public boolean add(int endereco) {
        if (!(i < tamPage)) {
            return false;
        } else {
            end[i] = endereco;
            i++;
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

