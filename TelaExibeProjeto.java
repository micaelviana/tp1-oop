import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TelaExibeProjeto implements TelaDesenhavel{
    private JFrame frame;
    private Projeto projeto;

   public TelaExibeProjeto(){}
   
   public TelaExibeProjeto(Projeto projeto){
    this.projeto=projeto;
   }

   public void imprime(){
       frame = new JFrame("Visão geral de um projeto");
       frame.setLayout(new BorderLayout());
       int width=1200;
       frame.setSize(width, 450);
       frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

       JLabel titulo = new JLabel("Visão geral do projeto");
       titulo.setBounds(0, 10, width, 20);
       titulo.setHorizontalAlignment(SwingConstants.CENTER);
       frame.add(titulo, BorderLayout.NORTH);

       //Estrutura de dados para guardar os Jfields
       Vector<JTextField> camposDeTexto = new Vector<>();
       camposDeTexto.add(new JTextField(projeto.getNome()));
       camposDeTexto.add(new JTextField(projeto.getProfessor()));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getDespesasCapitalPrevisto())));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getMaterialConsumoPrevisto())));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getServicosPessoaFisicaPrevisto())));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getServicosPessoaJuridicaPrevisto())));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getDiariasPrevisto())));
       camposDeTexto.add(new JTextField(String.valueOf(projeto.getPassagensPrevisto())));

       //Painel central - informacoes financeiras
       JPanel painelCentral = new JPanel();
       /*
        * Despesas|valor previsto|valor gasto|valor disponivel|BOTAO_SALVAR
        * Material|valor previsto|valor gasto|valor disponivel|BOTAO_SALVAR
       */
       painelCentral.setLayout(new GridLayout(9,4));
       painelCentral.add(new JLabel("Nome: "));
       painelCentral.add(camposDeTexto.elementAt(0));
       painelCentral.add(new JLabel());
       painelCentral.add(new JLabel());

       painelCentral.add(new JLabel("Professor"));
       painelCentral.add(camposDeTexto.elementAt(1));
       painelCentral.add(new JLabel());
       painelCentral.add(new JLabel());
       //definicao das colunas
       painelCentral.add(new JLabel("Rubrica" ));
       painelCentral.add(new JLabel("Valor previsto" ));
       painelCentral.add(new JLabel("Valor gasto" ));
       painelCentral.add(new JLabel("Valor disponível" ));

       painelCentral.add(new JLabel("Despesas de capital" ));
       painelCentral.add(camposDeTexto.elementAt(2) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getDespesasCapitalGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getDespesasCapitalDisponivel())) );

       painelCentral.add(new JLabel("Material de consumo") );
       painelCentral.add(camposDeTexto.elementAt(3) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getMaterialConsumoGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getMaterialConsumoDisponivel())) );

       painelCentral.add(new JLabel("Serviços de Terceiros / Pessoa Física") );
       painelCentral.add(camposDeTexto.elementAt(4) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getServicosPessoaFisicaGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getServicosPessoaFisicaDisponivel())) );

       painelCentral.add(new JLabel("Serviços de terceiros / Pessoa Jurídica") );
       painelCentral.add(camposDeTexto.elementAt(5) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getServicosPessoaJuridicaGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getServicosPessoaJuridicaDisponivel())) );

       painelCentral.add(new JLabel("Diárias") );
       painelCentral.add(camposDeTexto.elementAt(6) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getDiariasGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getDiariasDisponivel())) );

       painelCentral.add(new JLabel("Passagens") );
       painelCentral.add(camposDeTexto.elementAt(7) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getPassagensGasto())) );
       painelCentral.add(new JLabel(String.valueOf(projeto.getPassagensDisponivel())) );

       //adiciona o painel
       frame.add(painelCentral);
        
       //Painel do sul
       JPanel painelSul = new JPanel();
       painelSul.setLayout(new BorderLayout());
       JButton botaoEfetivar = new JButton("Salvar alterações");
       JButton botaoEditarDespesas = new JButton("Editar despesas");
       painelSul.add(botaoEfetivar,BorderLayout.WEST);
       painelSul.add(botaoEditarDespesas,BorderLayout.EAST);


        //adicionar o botao
        frame.add(painelSul,BorderLayout.SOUTH);

       //me da as ibagens
       frame.setVisible(true);

       //{{Manipulacao de eventos}}
      //{{Atualizar um projeto}} 
       botaoEfetivar.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               // O que pode dar errado? os campos estao vazios
               // os valores sao inconversiveis ou negativos
               Utilitarios utilitarios = new Utilitarios();

               // Tentar inserir os valores fornecidos no objeto
               try {
                   // preencher o objeto
                   projeto.setNome(utilitarios.capturaTexto(camposDeTexto.elementAt(0).getText()));
                   projeto.setProfessor(utilitarios.capturaTexto(camposDeTexto.elementAt(1).getText()));
                   projeto.setDespesasCapitalPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(2).getText()));
                   projeto.setMaterialConsumoPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(3).getText()));
                   projeto.setServicosPessoaFisicaPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(4).getText()));
                   projeto.setServicosPessoaJuridicaPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(5).getText()));
                   projeto.setDiariasPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(6).getText()));
                   projeto.setPassagensPrevisto(
                           utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(7).getText()));

                           //o novo valor previsto nao pode ser menor do que o que ja foi gasto
                    if(projeto.getDespesasCapitalPrevisto()>= projeto.getDespesasCapitalGasto() && projeto.getMaterialConsumoPrevisto() >= projeto.getMaterialConsumoGasto() && projeto.getServicosPessoaFisicaPrevisto() >= projeto.getServicosPessoaFisicaGasto() && projeto.getServicosPessoaJuridicaPrevisto() >= projeto.getServicosPessoaJuridicaGasto() && projeto.getDiariasPrevisto() >= projeto.getDiariasGasto() && projeto.getPassagensPrevisto() >= projeto.getPassagensGasto()){

                        ProjetoDAO projetoDAO = new ProjetoDAO();
                        if(projetoDAO.atualizaProjeto(projeto)){
                           JOptionPane.showMessageDialog(null, "Projeto atualizado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(null,"Erro. Não foi possível atualizar o projeto");
                        }

                    }else{
                        JOptionPane.showMessageDialog(null,"Os valores previstos não podem ser menores do que os valores gastos");
                    }

               } catch (CaixaDeTextoVaziaException excecao) {
                   JOptionPane.showMessageDialog(null, "Erro: algum campo está vazio");
               } catch (ValorInconversivelException excecao) {
                   JOptionPane.showMessageDialog(null,
                           "Os valores inseridos nas rubricas devem ser inteiros ou reais separados por \".\"\nExemplo: 120 reais e 50 centavos deve ser inserido como 120.50");
               } catch (ValorNegativoException excecao) {
                   JOptionPane.showMessageDialog(null, "Os valores não podem ser negativos");
               }
           }// fim do metodo actionPerformed
       });

       //{{Botao de editar despesas}}
       botaoEditarDespesas.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                TelaListaDespesas telaListaDespesas = new TelaListaDespesas(projeto);
                telaListaDespesas.imprime();

                frame.setVisible(false);
                frame.dispose();
            }
       });

   }//final do metodo "imprime"

}//fim classe
