import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

class No {
    public String nome;
    public List<No> filhos;

    public No(String nome) {
        this.nome = nome;
        this.filhos = new ArrayList<>();
    }

    public void adicionarFilho(No filho) {
        filhos.add(filho);
    }
}

public class Arvore {
    public No raiz;

    // Inserir relações pai-filho a partir do arquivo
    void insere(String texto) {
        String[] linhas = texto.split("\n");
        List<No> nodes = new ArrayList<>();

        for (String linha : linhas) {
            String[] nomes = linha.split(" ");
            if (nomes.length != 2) continue; // ignorar linhas inválidas
            String filhoNome = nomes[0];
            String paiNome = nomes[1];

            No filho = buscarOuCriar(nodes, filhoNome);
            No pai = buscarOuCriar(nodes, paiNome);

            // Definir raiz se necessário
            if (raiz == null) raiz = pai;

            pai.adicionarFilho(filho);
        }
    }

    // Busca ou cria nó na lista
    private No buscarOuCriar(List<No> nodes, String nome) {
        for (No n : nodes) {
            if (n.nome.equals(nome)) return n;
        }
        No novo = new No(nome);
        nodes.add(novo);
        return novo;
    }

    // Busca nó pelo nome
    public No busca(No raiz, String nome) {
        if (raiz == null) return null;
        if (raiz.nome.equals(nome)) return raiz;

        for (No filho : raiz.filhos) {
            No encontrado = busca(filho, nome);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    // Encontra LCA
    public No LCA(No raiz, No n1, No n2) {
        if (raiz == null) return null;
        if (raiz == n1 || raiz == n2) return raiz;

        List<No> matches = new ArrayList<>();
        for (No filho : raiz.filhos) {
            No res = LCA(filho, n1, n2);
            if (res != null) matches.add(res);
        }

        if (matches.size() == 2) return raiz; // n1 e n2 em subárvores diferentes
        if (matches.size() == 1) return matches.get(0); // ambos na mesma subárvore
        return null;
    }

    // Distância de raiz até alvo
    int distancia(No raiz, No alvo) {
        if (raiz == null) return -1;
        if (raiz == alvo) return 0;

        for (No filho : raiz.filhos) {
            int d = distancia(filho, alvo);
            if (d >= 0) return d + 1;
        }
        return -1;
    }

    // Classificar parentesco de p em relação a q
    void classificarParentesco(String p, String q) {
    No n1 = busca(raiz, p);
    No n2 = busca(raiz, q);

    if (n1 == null || n2 == null) {
        System.out.println("sem relacao");
        return;
    }

    No lca = LCA(raiz, n1, n2);
    int d1 = distancia(lca, n1);
    int d2 = distancia(lca, n2);

    //Descendência ou ascendência direta
    if (d1 == 0 && d2 > 0) { // n1 é ancestral de n2
        switch (d2) {
            case 1: System.out.println("pai"); break;
            case 2: System.out.println("avô"); break;
            case 3: System.out.println("bisavô"); break;
            case 4: System.out.println("tataravô"); break;
            default: System.out.println("ancestor em grau " + (d2 - 1));
        }
        return;
    }

    if (d2 == 0 && d1 > 0) { // n2 é ancestral de n1
        switch (d1) {
            case 1: System.out.println("filho"); break;
            case 2: System.out.println("neto"); break;
            case 3: System.out.println("bisneto"); break;
            case 4: System.out.println("tataraneto"); break;
            default: System.out.println("descendente em grau " + (d1 - 1));
        }
        return;
    }

    //Irmãos
    if (d1 == 1 && d2 == 1) {
        System.out.println("irmao");
        return;
    }

    //Primos
    if (d1 >= 1 || d2 >= 1) {
        int grau = Math.abs(d1 - d2);       // diferença de gerações
        int m = Math.min(d1, d2) - 1;       // grau do primo
        if (m == 0) {                        // primo-0
            System.out.println("primo-" + m+ " em grau " + grau);
        } else {                             
            System.out.println("primo-" + m + " em grau " + grau);
        }
        return;
    }

    //Caso não se encaixe em nenhum padrão
    System.out.println("sem relacao");
}


    // Imprime árvore em pré-ordem
    void imprimir(No alvo, String espaco) {
        if (alvo != null) {
            System.out.println(espaco + alvo.nome);
            for (No f : alvo.filhos) {
                imprimir(f, espaco + "   ");
            }
        }
    }

    // Lê arquivo
    public String lerArquivo(String filename) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Arquivo inválido");
            return null;
        }
    }

    // Main de teste com input do usuário
    public static void main(String[] args) {
        Arvore arvore = new Arvore();
        String conteudo = arvore.lerArquivo("familia.txt");

        if (conteudo == null) return;

        arvore.insere(conteudo);

        //System.out.println("Árvore:");
        //arvore.imprimir(arvore.raiz, "");

        Scanner sc = new Scanner(System.in);
        System.out.println("\nDigite os pares de nomes separados por espaço (ou 'fim' para sair):");

        while (true) {
            String linha = sc.nextLine().trim();
            if (linha.equalsIgnoreCase("fim")) break;
            String[] nomes = linha.split(" ");
            if (nomes.length != 2) continue;
            arvore.classificarParentesco(nomes[0], nomes[1]);
        }

        sc.close();
    }
}
