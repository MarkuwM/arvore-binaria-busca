import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArvoreGUI extends JFrame {
    private ArvoreBinaria arvore = new ArvoreBinaria();
    private PainelArvore painel = new PainelArvore(arvore);
    private JTextField campoValor = new JTextField(5);
    private JTextArea saida = new JTextArea(5, 30);

    public ArvoreGUI() {
        super("Árvore Binária com Busca Visual (Swing)");

        JPanel painelEntrada = new JPanel();
        painelEntrada.add(new JLabel("Valor:"));
        painelEntrada.add(campoValor);

        JButton btnInserir = new JButton("Inserir");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnRemover = new JButton("Remover");
        JButton btnDFS = new JButton("Buscar DFS");
        JButton btnBFS = new JButton("Buscar BFS");

        painelEntrada.add(btnInserir);
        painelEntrada.add(btnBuscar);
        painelEntrada.add(btnRemover);
        painelEntrada.add(btnDFS);
        painelEntrada.add(btnBFS);

        JPanel painelTravessia = new JPanel();
        JButton btnPre = new JButton("Pré-Ordem");
        JButton btnEm = new JButton("Em-Ordem");
        JButton btnPos = new JButton("Pós-Ordem");
        JButton btnLargura = new JButton("Largura");
        JButton btnProfundidade = new JButton("Profundidade");

        painelTravessia.add(btnPre);
        painelTravessia.add(btnEm);
        painelTravessia.add(btnPos);
        painelTravessia.add(btnLargura);
        painelTravessia.add(btnProfundidade);

        JScrollPane scroll = new JScrollPane(saida);
        saida.setEditable(false);

        setLayout(new BorderLayout());
        add(painelEntrada, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);
        add(painelTravessia, BorderLayout.SOUTH);
        add(scroll, BorderLayout.EAST);

        btnInserir.addActionListener(e -> {
            Integer valor = lerValorInteiro();
            if (valor != null) {
                arvore.inserir(valor);
                repaint();
            }
        });

        btnBuscar.addActionListener(e -> {
            Integer valor = lerValorInteiro();
            if (valor != null) {
                boolean achou = arvore.buscar(valor);
                saida.setText("Busca por " + valor + ": " + (achou ? "Encontrado" : "Não encontrado"));
            }
        });

        btnRemover.addActionListener(e -> {
            Integer valor = lerValorInteiro();
            if (valor != null) {
                arvore.remover(valor);
                repaint();
            }
        });

        btnDFS.addActionListener(e -> {
            Integer valor = lerValorInteiro();
            if (valor != null) {
                List<No> caminho = arvore.caminhoDFS(valor);
                painel.destacarCaminhoAnimado(caminho);
                saida.setText("DFS até " + valor + ": " + getValores(caminho));
            }
        });

        btnBFS.addActionListener(e -> {
            Integer valor = lerValorInteiro();
            if (valor != null) {
                List<No> caminho = arvore.caminhoBFS(valor);
                painel.destacarCaminhoAnimado(caminho);
                saida.setText("BFS até " + valor + ": " + getValores(caminho));
            }
        });


        btnPre.addActionListener(e -> saida.setText("Pré-Ordem: " + arvore.preOrdem()));
        btnEm.addActionListener(e -> saida.setText("Em-Ordem: " + arvore.emOrdem()));
        btnPos.addActionListener(e -> saida.setText("Pós-Ordem: " + arvore.posOrdem()));
        btnLargura.addActionListener(e -> saida.setText("Largura: " + arvore.buscaEmLargura()));
        btnProfundidade.addActionListener(e -> saida.setText("Profundidade: " + arvore.buscaEmProfundidade()));

        btnDFS.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(campoValor.getText());
                List<No> caminho = arvore.caminhoDFS(valor);
                painel.destacarCaminhoAnimado(caminho);
                saida.setText("DFS até " + valor + ": " + getValores(caminho));
            } catch (NumberFormatException ex) {
                saida.setText("Digite um número válido!");
            }
        });

        btnBFS.addActionListener(e -> {
            try {
                int valor = Integer.parseInt(campoValor.getText());
                List<No> caminho = arvore.caminhoBFS(valor);
                painel.destacarCaminhoAnimado(caminho);
                saida.setText("BFS até " + valor + ": " + getValores(caminho));
            } catch (NumberFormatException ex) {
                saida.setText("Digite um número válido!");
            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setVisible(true);
    }

    private Integer lerValorInteiro() {
        String texto = campoValor.getText().trim();
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número inteiro válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private String getValores(List<No> caminho) {
        List<Integer> lista = new ArrayList<>();
        for (No no : caminho) lista.add(no.valor);
        return lista.toString();
    }

    public static void main(String[] args) {
        new ArvoreGUI();
    }
}
