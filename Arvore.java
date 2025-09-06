import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

class No {
    public int chave = 0;
    public String nome;
    public No esq = null, dir = null;
    public No(){
        chave = 0; esq = null; dir = null;
    }
    public No(int v){
        chave = v; esq = null; dir = null;
    }

    public No(String n){
        nome = n; esq = null; dir = null;
    }
}

public class Arvore{
    public No raiz;

    void insere(String texto) {
    String[] linhas = texto.split("\n");
    No[] nodes = new No[linhas.length * 2];
    int nodeCount = 0; // Contador para próxima posição livre
    
    for(String linha : linhas) {
        String[] nomes = linha.split(" ");
        No filho = null;
        No pai = null;
        
        // Buscar ou criar o FILHO
        for(int j = 0; j < nodeCount; j++) {
            if(nodes[j] != null && nodes[j].nome.equals(nomes[0])) {
                filho = nodes[j];
                break;
            }
        }
        if(filho == null) {
            filho = new No(nomes[0]);
            nodes[nodeCount++] = filho;
        }
        
        // Buscar ou criar o PAI
        for(int j = 0; j < nodeCount; j++) {
            if(nodes[j] != null && nodes[j].nome.equals(nomes[1])) {
                pai = nodes[j];
                break;
            }
        }
        if(pai == null) {
            pai = new No(nomes[1]);
            nodes[nodeCount++] = pai;
        }
        
        // Definir raiz se necessário
        if(raiz == null) {
            raiz = pai;
        }
        
        // Criar relação
        if(pai.esq == null) {
            pai.esq = filho;
        } else if(pai.dir == null) {
            pai.dir = filho;
        } else {
            System.out.println("Pai " + pai.nome + " já tem dois filhos!");
        }
    }
    }

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

    // No busca(No p, int chave){
    //     if (p != null){
    //         if (chave < p.chave)
    //         p = busca(p.esq, chave);
    //         else if (chave > p.chave)
    //         p = busca(p.dir, chave);
    //     }
    //     return p;
    // }

    public No BuscaPreOrdem(No p, String nome){
        if (p != null){
        if(p.nome.equals(nome))
            return p;
        BuscaPreOrdem(p.esq, nome);
        BuscaPreOrdem(p.dir, nome);
        }

        return null;
    }



    public  int altura(No alvo){
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

    // public  void MostrarNiveis(No alvo, int nivel){
    //     if (alvo != null){
    //         System.out.println(alvo.chave + " " + nivel);
    //         MostrarNiveis(alvo.esq, nivel + 1);
    //         MostrarNiveis(alvo.dir, nivel + 1);
    //     }
    // }

    public  void MostrarNiveis(No alvo, int nivel){
        if (alvo != null){
            System.out.println(alvo.nome + " " + nivel);
            MostrarNiveis(alvo.esq, nivel + 1);
            MostrarNiveis(alvo.dir, nivel + 1);
        }
    }

    public  void imprimir(No alvo, String espaco){
         if (alvo != null){
            imprimir(alvo.esq, espaco + "   ");
            System.out.println(espaco + alvo.nome);
            imprimir(alvo.dir, espaco + "   ");
        }
    }
    
    public  String lerArquivo(String filename) {
        try{
            return String.join("\n", Files.readAllLines(Paths.get(filename)));
        }catch (IOException e) {
            System.out.println("Arquivo inválido");
            return null;
        }
    }

    public void acharParentesco(String linha){
        String[] nomes = linha.split(" ");
        //Checar se os dois nomes constam na arvore
        if(BuscaPreOrdem(raiz, nomes[0]) == null || BuscaPreOrdem(raiz, nomes[1]) == null){
            System.out.println("sem relação");
            return;
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
        Arvore arvore = new Arvore();
        String conteudo = arvore.lerArquivo("familia.txt");
        System.out.println(conteudo);

        arvore.insere(conteudo);
        arvore.imprimir(arvore.raiz, " ");
        arvore.MostrarNiveis(arvore.raiz, 0);

    }
 }

  