import java.util.*;

public class ArvoreBinaria {
    public No raiz;

    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private No inserirRec(No atual, int valor) {
        if (atual == null) return new No(valor);
        if (valor < atual.valor)
            atual.esquerdo = inserirRec(atual.esquerdo, valor);
        else if (valor > atual.valor)
            atual.direito = inserirRec(atual.direito, valor);
        return atual;
    }

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(No atual, int valor) {
        if (atual == null) return false;
        if (valor == atual.valor) return true;
        return valor < atual.valor ? buscarRec(atual.esquerdo, valor) : buscarRec(atual.direito, valor);
    }

    public void remover(int valor) {
        raiz = removerRec(raiz, valor);
    }

    private No removerRec(No atual, int valor) {
        if (atual == null) return null;
        if (valor < atual.valor)
            atual.esquerdo = removerRec(atual.esquerdo, valor);
        else if (valor > atual.valor)
            atual.direito = removerRec(atual.direito, valor);
        else {
            if (atual.esquerdo == null) return atual.direito;
            if (atual.direito == null) return atual.esquerdo;
            No sucessor = encontrarMinimo(atual.direito);
            atual.valor = sucessor.valor;
            atual.direito = removerRec(atual.direito, sucessor.valor);
        }
        return atual;
    }

    private No encontrarMinimo(No no) {
        while (no.esquerdo != null) no = no.esquerdo;
        return no;
    }

    public List<Integer> preOrdem() {
        List<Integer> resultado = new ArrayList<>();
        preOrdemRec(raiz, resultado);
        return resultado;
    }

    private void preOrdemRec(No no, List<Integer> lista) {
        if (no != null) {
            lista.add(no.valor);
            preOrdemRec(no.esquerdo, lista);
            preOrdemRec(no.direito, lista);
        }
    }

    public List<Integer> emOrdem() {
        List<Integer> resultado = new ArrayList<>();
        emOrdemRec(raiz, resultado);
        return resultado;
    }

    private void emOrdemRec(No no, List<Integer> lista) {
        if (no != null) {
            emOrdemRec(no.esquerdo, lista);
            lista.add(no.valor);
            emOrdemRec(no.direito, lista);
        }
    }

    public List<Integer> posOrdem() {
        List<Integer> resultado = new ArrayList<>();
        posOrdemRec(raiz, resultado);
        return resultado;
    }

    private void posOrdemRec(No no, List<Integer> lista) {
        if (no != null) {
            posOrdemRec(no.esquerdo, lista);
            posOrdemRec(no.direito, lista);
            lista.add(no.valor);
        }
    }

    public List<Integer> buscaEmLargura() {
        List<Integer> resultado = new ArrayList<>();
        if (raiz == null) return resultado;
        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            resultado.add(atual.valor);
            if (atual.esquerdo != null) fila.add(atual.esquerdo);
            if (atual.direito != null) fila.add(atual.direito);
        }
        return resultado;
    }

    public List<Integer> buscaEmProfundidade() {
        List<Integer> resultado = new ArrayList<>();
        Stack<No> pilha = new Stack<>();
        if (raiz != null) pilha.push(raiz);
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            resultado.add(atual.valor);
            if (atual.direito != null) pilha.push(atual.direito);
            if (atual.esquerdo != null) pilha.push(atual.esquerdo);
        }
        return resultado;
    }

    public List<No> caminhoDFS(int valor) {
        List<No> caminho = new ArrayList<>();
        dfs(raiz, valor, caminho);
        return caminho;
    }

    private boolean dfs(No no, int valor, List<No> caminho) {
        if (no == null) return false;
        caminho.add(no);
        if (no.valor == valor) return true;
        if (dfs(no.esquerdo, valor, caminho)) return true;
        if (dfs(no.direito, valor, caminho)) return true;
        caminho.remove(caminho.size() - 1);
        return false;
    }

    public List<No> caminhoBFS(int valor) {
        List<No> caminho = new ArrayList<>();
        Queue<No> fila = new LinkedList<>();
        Set<No> visitados = new HashSet<>();
        if (raiz != null) fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            caminho.add(atual);
            if (atual.valor == valor) break;
            if (atual.esquerdo != null && !visitados.contains(atual.esquerdo)) fila.add(atual.esquerdo);
            if (atual.direito != null && !visitados.contains(atual.direito)) fila.add(atual.direito);
            visitados.add(atual);
        }

        return caminho;
    }
}
