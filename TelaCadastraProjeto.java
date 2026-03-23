import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
public class TelaCadastraProjeto implements TelaDesenhavel{
    private JFrame frame;

    //uma string ruim eh uma string que so contem espacos, ou eh nula ou eh vazia, de qualquer forma nao serve
    //para o banco de dados

    public void imprime(){
        frame = new JFrame("Cadastrar um novo projeto");
        frame.setLayout(new BorderLayout());
        frame.setSize(800,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("Dados do projeto\n\n");
        titulo.setBounds(0, 10, 500, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titulo,BorderLayout.NORTH);

        //painel onde serao inseridos os dados
        Vector<JTextField> camposDeTexto = new Vector<>();
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());
        camposDeTexto.add(new JTextField());

        JPanel painelInsercao = new JPanel();
        painelInsercao.setLayout(new GridLayout(8,2));
        
        painelInsercao.add(new JLabel("Nome do projeto"));
        painelInsercao.add(camposDeTexto.elementAt(0));
        painelInsercao.add(new JLabel("Professor responsável"));
        painelInsercao.add(camposDeTexto.elementAt(1));
        painelInsercao.add(new JLabel("Despesas de capital"));
        painelInsercao.add(camposDeTexto.elementAt(2));
        painelInsercao.add(new JLabel("Material de consumo"));
        painelInsercao.add(camposDeTexto.elementAt(3));
        painelInsercao.add(new JLabel("Serviços de terceiros/Pessoa física"));
        painelInsercao.add(camposDeTexto.elementAt(4));
        painelInsercao.add(new JLabel("Serviços de terceiros/Pessoa jurídica"));
        painelInsercao.add(camposDeTexto.elementAt(5));
        painelInsercao.add(new JLabel("Diárias"));
        painelInsercao.add(camposDeTexto.elementAt(6));
        painelInsercao.add(new JLabel("Passagens"));
        painelInsercao.add(camposDeTexto.elementAt(7));

        //botao de efetivar cadastro
        JButton botaoEfetivar = new JButton("Efetuar cadastro");


        //adicionar o painel
        frame.add(painelInsercao,BorderLayout.CENTER);

        //adicionar o botao
        frame.add(botaoEfetivar,BorderLayout.SOUTH);

        frame.setVisible(true);

        //{{Manipulacao de eventos}}
        //{{Efetivar um cadastro}}
        botaoEfetivar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //O que pode dar errado? os campos estao vazios
            //os valores sao inconversiveis ou negativos
            Utilitarios utilitarios = new Utilitarios();
            Projeto projeto = new Projeto();

            //Tentar inserir os valores fornecidos no objeto
            try {
                //preencher o objeto
                projeto.setNome(utilitarios.capturaTexto(camposDeTexto.elementAt(0).getText()));
                projeto.setProfessor(utilitarios.capturaTexto(camposDeTexto.elementAt(1).getText()));
                projeto.setDespesasCapitalPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(2).getText()));
                projeto.setMaterialConsumoPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(3).getText()));
                projeto.setServicosPessoaFisicaPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(4).getText()));
                projeto.setServicosPessoaJuridicaPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(5).getText()));
                projeto.setDiariasPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(6).getText()));
                projeto.setPassagensPrevisto(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(7).getText()));

                //adicionar o projeto ao banco de dados
                ProjetoDAO projetoDAO = new ProjetoDAO();
                projetoDAO.adicionaProjeto(projeto);
            } catch (CaixaDeTextoVaziaException excecao) {
                JOptionPane.showMessageDialog(null,"Erro: algum campo está vazio");
            }catch(ValorInconversivelException excecao){
                    JOptionPane.showMessageDialog(null,
                            "Os valores inseridos nas rubricas devem ser inteiros ou reais separados por \".\"\nExemplo: 120 reais e 50 centavos deve ser inserido como 120.50");
            }catch(ValorNegativoException excecao){
                    JOptionPane.showMessageDialog(null, "Os valores não podem ser negativos");
            }
        }//fim do metodo actionPerformed
      });
   }//fim do metodo

}// fim classe
