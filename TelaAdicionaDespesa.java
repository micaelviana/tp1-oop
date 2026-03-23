import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
public class TelaAdicionaDespesa implements TelaDesenhavel{
    private JFrame frame;
    private Projeto projeto;

    public TelaAdicionaDespesa(Projeto projeto){
        this.projeto=projeto;
    }

    public void imprime(){
        int width = 1250;
        frame = new JFrame("Adicionar despesas ao projeto");
        frame.setLayout(new BorderLayout());
        frame.setSize(width, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel(this.projeto.getNome());
        titulo.setBounds(0, 10, width, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subTitulo = new JLabel("Professor: "+this.projeto.getProfessor());
        subTitulo.setBounds(0, 10, width, 20);
        subTitulo.setHorizontalAlignment(SwingConstants.CENTER);


        frame.add(subTitulo, BorderLayout.NORTH);
        frame.add(titulo, BorderLayout.NORTH);


        Vector<JTextField> camposDeTexto = new Vector<>();
        camposDeTexto.add(new JTextField());//categoria
        camposDeTexto.add(new JTextField());//descricao
        camposDeTexto.add(new JTextField());//valor

        //Painel central
        JPanel painelCentral = new JPanel();

        JLabel jLabel = new JLabel();

        painelCentral.setLayout(new GridLayout(2,4));
        jLabel = new JLabel("Categoria: Insira um número de 1 a 6");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(jLabel);
        jLabel = new JLabel("Descrição");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(jLabel);
        jLabel = new JLabel("Valor");
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        painelCentral.add(jLabel);
        painelCentral.add(new JLabel("",SwingConstants.CENTER));

        //Botao efetivo
        JButton botao = new JButton("Adicionar");
        painelCentral.add(camposDeTexto.elementAt(0));
        painelCentral.add(camposDeTexto.elementAt(1));
        painelCentral.add(camposDeTexto.elementAt(2));
        painelCentral.add(botao);

        frame.add(painelCentral,BorderLayout.CENTER);


        //Painel sul - Informacoes financeiras
        JPanel painelSul = new JPanel();
        painelSul.setLayout(new GridLayout(2,6));
        //descricao da tabela
        painelSul.add(new JLabel("1 - Despesas de capital",SwingConstants.CENTER));
        painelSul.add(new JLabel("2 - Material de consumo",SwingConstants.CENTER));
        painelSul.add(new JLabel("3 - Serviços / Pessoa Física",SwingConstants.CENTER));
        painelSul.add(new JLabel("4 - Serviços / Pessoa Jurídica",SwingConstants.CENTER));
        painelSul.add(new JLabel("5 - Diárias",SwingConstants.CENTER));
        painelSul.add(new JLabel("6 - Passagens",SwingConstants.CENTER));
        //conteudo da tabela
        String aux="Valor disponível: ";
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getDespesasCapitalDisponivel()),SwingConstants.CENTER));
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getMaterialConsumoDisponivel()),SwingConstants.CENTER));
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getServicosPessoaFisicaDisponivel()),SwingConstants.CENTER));
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getServicosPessoaJuridicaDisponivel()),SwingConstants.CENTER));
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getDiariasDisponivel()),SwingConstants.CENTER));
        painelSul.add(new JLabel(aux+String.valueOf(projeto.getPassagensDisponivel()),SwingConstants.CENTER));
        frame.add(painelSul,BorderLayout.SOUTH);


        //{{Finally}}
        frame.setVisible(true);

        // {{Acao do botao de cadastrar projeto}}
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent elemento) {
                Utilitarios utilitarios = new Utilitarios();
                Despesa despesa = new Despesa();
                try{
                    despesa.setProjeto(projeto);
                    despesa.setCategoria(utilitarios.converteTextoParaInteger(camposDeTexto.elementAt(0).getText()));
                    despesa.setDescricao(utilitarios.capturaTexto(camposDeTexto.elementAt(1).getText()));
                    despesa.setValor(utilitarios.converteTextoParaDouble(camposDeTexto.elementAt(2).getText()));

                    double valor=despesa.getValor();
                    int categoria=despesa.getCategoria();

                    //checar se ta dentro do orcamento
                    if(categoria==1 && valor <= projeto.getDespesasCapitalDisponivel()){
                        projeto.setDespesasCapitalGasto(projeto.getDespesasCapitalGasto()+valor);
                    }
                    else if(categoria==2 && valor <= projeto.getMaterialConsumoDisponivel()){
                        projeto.setMaterialConsumoGasto(projeto.getMaterialConsumoGasto()+valor);
                    }
                    else if(categoria==3 && valor <= projeto.getServicosPessoaFisicaDisponivel()){
                        projeto.setServicosPessoaFisicaGasto(projeto.getServicosPessoaFisicaGasto()+valor);
                    }
                    else if(categoria==4 && valor <= projeto.getServicosPessoaJuridicaDisponivel()){
                        projeto.setServicosPessoaJuridicaGasto(projeto.getServicosPessoaJuridicaGasto()+valor);
                    }
                    else if(categoria==5 && valor <= projeto.getDiariasDisponivel()){
                        projeto.setDiariasGasto(projeto.getDiariasGasto()+valor);
                    }
                    else if(categoria==6 && valor <= projeto.getPassagensDisponivel()){
                        projeto.setPassagensGasto(projeto.getPassagensGasto()+valor);
                    }else{
                        JOptionPane.showMessageDialog(null,"O valor inserido excede o capital disponível para a categoria");
                        return;
                    }

                    //passou pelos testes
                    DespesaDAO despesaDAO = new DespesaDAO();
                    boolean resultado = despesaDAO.adicionaDespesa(despesa, projeto);
                    if(resultado){
                        JOptionPane.showMessageDialog(null,"Despesa adicionada com sucesso");
                        frame.setVisible(false);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null,"Operação falhou");
                    }

                }catch(CaixaDeTextoVaziaException e){
                    JOptionPane.showMessageDialog(null, "Erro: algum campo esta vazio");
                }catch(ValorInconversivelException e){
                    JOptionPane.showMessageDialog(null,
                            "Os valores inseridos nas rubricas devem ser inteiros ou reais separados por \".\"\nExemplo: 120 reais e 50 centavos deve ser inserido como 120.50");
                }catch(ValorNegativoException e){
                    JOptionPane.showMessageDialog(null, "Os valores não podem ser negativos");
                }
            }
        });//listener()
    }//imprime()
}
