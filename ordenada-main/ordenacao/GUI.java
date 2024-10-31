package ordenacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI extends JFrame {
    
    private JTextArea resultArea;
    private JButton loadFileButton, sortButton;
    private File dataFile;
    
    public GUI() {
        // Configurações da janela principal
        setTitle("Análise de Algoritmos de Ordenação");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Configuração dos componentes
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        
        loadFileButton = new JButton("Carregar Arquivo de Dados");
        sortButton = new JButton("Iniciar Ordenação");
        sortButton.setEnabled(false);  // Desabilitado até que um arquivo seja carregado
        
        // Configura o painel superior com os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadFileButton);
        buttonPanel.add(sortButton);
        
        // Configura a área de exibição dos resultados
        JScrollPane scrollPane = new JScrollPane(resultArea);
        
        // Adiciona os componentes à janela
        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Ação para carregar o arquivo
        loadFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(GUI.this);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    dataFile = fileChooser.getSelectedFile();
                    resultArea.append("Arquivo carregado: " + dataFile.getAbsolutePath() + "\n");
                    sortButton.setEnabled(true); // Ativa o botão de ordenação
                }
            }
        });
        
        // Ação para iniciar a ordenação
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultArea.append("\nIniciando a ordenação...\n");
                
                try {
                    Main.runSorting(dataFile, resultArea);
                } catch (Exception ex) {
                    resultArea.append("Erro ao processar a ordenação: " + ex.getMessage() + "\n");
                }
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI gui = new GUI();
            gui.setVisible(true);
        });
    }
}
