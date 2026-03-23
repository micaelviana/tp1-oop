import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
public class TelaBuscaProjetos implements TelaDesenhavel{
    private JFrame frame;

    public void imprime() {
        int width=450;
        frame = new JFrame("Tela de busca");
        frame.setSize(width,300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Busca por projetos no sistema");
        titulo.setBounds(0, 10, width, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitulo = new JLabel("Você pode buscar usando o nome do projeto ou professor responsável");
        subtitulo.setBounds(0, 10, width, 20);
        subtitulo.setFont(new Font("Times",Font.BOLD,12));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);

        //adicionar ao frame
        frame.add(subtitulo,BorderLayout.NORTH);
        frame.add(titulo,BorderLayout.NORTH);

        //painel Central
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new GridLayout(3,1));
        JTextField caixaTexto = new JTextField();
        caixaTexto.setBounds(0, 10, width, 20);
        //se der certo a caixa vai ocupar 1/3 da pagina
        painelCentral.add(new JLabel());
        painelCentral.add(caixaTexto);
        painelCentral.add(new JLabel());

        //botao da tela
        JButton botaoBuscar = new JButton("Procurar");

        //adiciona componentes ao frame
        frame.add(painelCentral,BorderLayout.CENTER);
        String espacos="                         ";
        frame.add(new JLabel(espacos), BorderLayout.WEST);
        frame.add(new JLabel(espacos), BorderLayout.EAST);
        frame.add(botaoBuscar,BorderLayout.SOUTH);


        //{{Finally}}
        frame.setVisible(true);

        // {{Acao do botao de cadastrar projeto}}
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent elemento) {
                Utilitarios utilitarios = new Utilitarios();
                try {
                    ProjetoDAO projetoDAO = new ProjetoDAO();
                    String termo = utilitarios.capturaTexto(caixaTexto.getText());
                    int numProjetos = projetoDAO.getNumProjetosPorNomeouProfessor(termo);
                    if(numProjetos>0){
                        ResultSet rs = projetoDAO.listaProjetosPorNomeOuProfessor(termo);
                        TelaListaProjetos telaListaProjetos = new TelaListaProjetos();
                        telaListaProjetos.imprime(rs,numProjetos);
                    }else{
                        JOptionPane.showMessageDialog(null,"Nenhum projeto foi encontrado");
                    }
                } catch (CaixaDeTextoVaziaException e) {
                    JOptionPane.showMessageDialog(null,"Nenhum termo foi inserido");
                }
            }//fim metodo ()
        });

    }//fim imprime()
}//fim classe
