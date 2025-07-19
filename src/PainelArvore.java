import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PainelArvore extends JPanel {
    private ArvoreBinaria arvore;
    private Map<No, Integer> ordemVisitacao = new HashMap<>();


    public PainelArvore(ArvoreBinaria arvore) {
        this.arvore = arvore;
    }

    public void destacarCaminhoAnimado(List<No> caminho) {
        ordemVisitacao.clear();
        if (caminho == null || caminho.isEmpty()) return;

        Timer timer = new Timer(500, null); // 500ms por nó
        final int[] index = {0};

        timer.addActionListener(e -> {
            if (index[0] < caminho.size()) {
                No atual = caminho.get(index[0]);
                ordemVisitacao.put(atual, index[0] + 1);
                repaint();
                index[0]++;
            } else {
                timer.stop();
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenhar(g, arvore.raiz, getWidth() / 2, 30, getWidth() / 4);
    }

    private void desenhar(Graphics g, No no, int x, int y, int distancia) {
        if (no == null) return;

        // Cor do nó
        if (ordemVisitacao.containsKey(no)) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.BLACK);
        }

        g.fillOval(x - 15, y - 15, 30, 30);

        // Valor do nó
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(no.valor), x - 7, y + 5);

        // Número da visita
        if (ordemVisitacao.containsKey(no)) {
            g.setColor(Color.BLUE);
            g.drawString("#" + ordemVisitacao.get(no), x - 10, y - 20);
        }

        g.setColor(Color.BLACK);

        // Conexões
        if (no.esquerdo != null) {
            g.drawLine(x, y, x - distancia, y + 50);
            desenhar(g, no.esquerdo, x - distancia, y + 50, distancia / 2);
        }

        if (no.direito != null) {
            g.drawLine(x, y, x + distancia, y + 50);
            desenhar(g, no.direito, x + distancia, y + 50, distancia / 2);
        }
    }

}
