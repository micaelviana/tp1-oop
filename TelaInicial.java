import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaInicial implements TelaDesenhavel{
  private JFrame frame;

   public void imprime(){
    frame = new JFrame("Tela do app");
    frame.setSize(600,200);
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel titulo = new JLabel("Sistema de controle financeiro para projetos de pesquisa\n\n");
    titulo.setBounds(0, 10, 500, 20);
    titulo.setHorizontalAlignment(SwingConstants.CENTER);
    frame.add(titulo,BorderLayout.NORTH);

    frame.add(new JLabel("<<"), BorderLayout.WEST);
    frame.add(new JLabel(">>"), BorderLayout.EAST);
    frame.add(new JLabel("Fim"), BorderLayout.SOUTH);

    //painel centro
    JPanel painelCentro = new JPanel();
    painelCentro.setLayout(new GridLayout(1,3));
    JButton botaoListar = new JButton("Listar projetos");
    painelCentro.add(botaoListar);
    JButton botaoBuscar = new JButton("Buscar projetos");
    painelCentro.add(botaoBuscar);
    JButton botaoCadastrar = new JButton("Cadastrar novo projeto");
    painelCentro.add(botaoCadastrar);



    //adicionar o painel a janela
    frame.add(painelCentro,BorderLayout.CENTER);
    //me da as ibagens
    frame.setVisible(true);


    //{{Acao do botao de cadastrar projeto}}
    botaoCadastrar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          TelaCadastraProjeto telaCadastro = new TelaCadastraProjeto();
          telaCadastro.imprime();

        }
      });

    //{{Acao do botao de buscar projetos}}
    botaoBuscar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          TelaBuscaProjetos telaBuscaProjetos = new TelaBuscaProjetos();
          telaBuscaProjetos.imprime();

        }
      });

      //{{Acao do botao de listar projetos}}
      botaoListar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          TelaListaProjetos telaListarCadastros = new TelaListaProjetos();
          telaListarCadastros.imprime();
        }
      });
   
   }//fim do metodo

    //{{Exemplo de refresh na tela}}
  //   botaoCadastro.addActionListener(new ActionListener() {
  //       @Override
  //       public void actionPerformed(ActionEvent e) {
  //         JButton novo = new JButton("nvo botao");
  //         painelCentro.remove(botaoCadastro);
  //         painelCentro.add(novo);
  //         painelCentro.revalidate();

  //       }
  //     });
  //  }

}//fim classe
