import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class No {
    public int chave = 0;
    public String Nome;
    public No esq = null, dir = null;
    public No(){
        chave = 0; esq = null; dir = null;
    }
    public No(int v){
        chave = v; esq = null; dir = null;
    }
}

public class Arvore{
    private No raiz;
    void insere (int valor) {
        No p = raiz, q = null , novo;
        while (p != null) {
        q = p;
        if (valor == p.chave){
        System.out.println("Elemento ja existe!");
        return;
        }
        if (valor < p.chave) p = p.esq;
        else p = p.dir;
        } 
        novo = new No(valor);
        if (raiz == null)
        raiz = novo;
        else {
        if (valor < q.chave)
        q.esq = novo;
        else q.dir = novo;
        }
    }

    No maisDireita(No p) {
        while (p.dir != null)
        p = p.dir;
        return p;
    }
    No maisEsquerda(No p) {
        while (p.esq != null)
        p = p.esq;
        return p;
    }   

    No remove(No p, int chave)
    {
        // se n~ao encontrou volta na recurs~ao
        if (p == null) return null;
        else {
            if (chave < p.chave)
            p.esq = remove(p.esq, chave);
            else if (chave > p.chave)
            p.dir = remove(p.dir, chave);
            else // se encontrou o no a ser removido
            {
                // verifica se o n´o tem filho a esquerda (caso 2)
                if (p.esq != null) {
                    // buscar o filho mais a
                    // direita da subarvore esquerda
                    No aux = maisDireita(p.esq);
                    p.chave = aux.chave;
                    // remover o filho
                    // mais a direita da subarvore esquerda
                    p.esq = remove(p.esq, aux.chave);
                } else if (p.dir != null) { // caso 3
                    // buscar o filho mais a
                    // esquerda da subarvore direita
                    No aux = maisEsquerda(p.dir);
                    p.chave = aux.chave;
                    // remover o filho mais a
                    // esquerda da subarvore direita
                    p.dir = remove(p.dir, aux.chave);
                }
                else p = null; // caso 1
            }
        }
        return p;
    } 


    public static int altura(No alvo){
        if(alvo == null){
            return -1;
        }
        int aesq = altura(alvo.esq);
        int adir = altura(alvo.dir);

        if(alvo.esq == null && alvo.dir == null){
            return 0;
        }else{
            if(aesq >= adir){
                return aesq++;
            }else{
                return adir++;
            }
        }
    }

    public static void MostrarNiveis(No alvo, int nivel){
        if (alvo != null){
            System.out.println(alvo.chave + " " + nivel);
            MostrarNiveis(alvo.esq, nivel + 1);
            MostrarNiveis(alvo.dir, nivel + 1);
        }
    }

    public static void imprimir(No alvo, String espaco){
         if (alvo != null){
            imprimir(alvo.esq, espaco + "   ");
            System.out.println(espaco + alvo.chave);
            imprimir(alvo.dir, espaco + "   ");
        }
    }
    
    public static String lerArquivo(String filename) {
        try{
            return String.join("", Files.readAllLines(Paths.get(filename)));
        }catch (IOException e) {
            System.out.println("Arquivo inválido");
            return null;
        }
    }

    public static void main(String[] args){
        // System.out.println(altura(null));
        // No raiz = new No(100);
        // raiz.esq = new No(25);
        // raiz.esq.esq = new No(18);
        // raiz.dir = new No(45);
        // raiz.dir.esq = new No(75);
        // MostrarNiveis(raiz, 0);
        // imprimir(raiz, " ");
        String conteudo = lerArquivo("familia.txt");
        System.out.println(conteudo);
    }
 }