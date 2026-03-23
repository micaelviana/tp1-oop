import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.*;


public class TelaListaDespesas implements TelaDesenhavel{
    private final String []categorias={"","Despesas de capital","Material de consumo","Serviços de terceiros / Pessoa Física","Serviços de terceiros / Pessoa Jurídica","Diárias","Passagens"};
    private JFrame frame;
    private Projeto projeto;

    public TelaListaDespesas(Projeto projeto){
        this.projeto=projeto;
    }

    public void imprime(){
        int width = 1200;
        frame = new JFrame("Listar despesas do projeto");
        frame.setLayout(new BorderLayout());
        frame.setSize(width, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel(this.projeto.getNome());
        titulo.setBounds(0, 10, width, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subTitulo = new JLabel("Professor: "+this.projeto.getProfessor());
        subTitulo.setBounds(0, 10, width, 20);
        subTitulo.setHorizontalAlignment(SwingConstants.CENTER);


        frame.add(subTitulo, BorderLayout.NORTH);
        frame.add(titulo, BorderLayout.NORTH);

        // estrutura de dados que salva os botoes e seus ids
        Vector<BotaoComId> botoes = new Vector<>();
        try {
            ResultSet rs = new DespesaDAO().listaDespesas(this.projeto);
            int numLinhas = new DespesaDAO().getNumDespesas(projeto.getId());
            
            JPanel painelCentral = new JPanel();
            painelCentral.setLayout(new GridLayout(numLinhas+1,4));
            painelCentral.add(new JLabel("Categoria"));
            painelCentral.add(new JLabel("Descrição"));
            painelCentral.add(new JLabel("Valor"));
            //espaco vazio
            painelCentral.add(new JLabel());

            //rows com conteúdo
            while(rs.next()){
                painelCentral.add(new JLabel(categorias[Integer.valueOf(rs.getString(4))]));
                painelCentral.add(new JLabel(rs.getString(3)));
                painelCentral.add(new JLabel(rs.getString(5)));

                BotaoComId aux = new BotaoComId(Integer.valueOf(rs.getString(1)),"Remover despesa do projeto",'r');
                aux.getBotao().setBackground(new Color(168,0,0));
                aux.getBotao().setForeground(new Color(255,255,255));
                botoes.add(aux);
                painelCentral.add(aux.getBotao());
            }

            frame.add(painelCentral,BorderLayout.CENTER);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            frame.setVisible(false);
            frame.dispose();
        }//fim catch        

        //botao de adicionar nova despesa
        JButton botaoAdicionar = new JButton("Adicionar nova despesa");
        frame.add(botaoAdicionar,BorderLayout.SOUTH);

        //{{Manipulacao de eventos}}
        //Remover um projeto
        for(BotaoComId algumBotao: botoes){
            algumBotao.getBotao().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent elemento){
                    try {
                        Despesa despesa = new DespesaDAO().getDespesa(algumBotao.getId());

                        if(despesa==null) throw new SistemaException();
                        
                        int categoria = despesa.getCategoria();

                        switch(categoria){
                            case 1:
                                projeto.setDespesasCapitalGasto(projeto.getDespesasCapitalGasto()-despesa.getValor());
                                break;
                            case 2:
                                projeto.setMaterialConsumoGasto(projeto.getMaterialConsumoGasto()-despesa.getValor());
                                break;
                            case 3:
                                projeto.setServicosPessoaFisicaGasto(projeto.getServicosPessoaFisicaGasto()-despesa.getValor());
                                break;
                            case 4:
                                projeto.setServicosPessoaJuridicaGasto(projeto.getServicosPessoaJuridicaGasto()-despesa.getValor());
                                break;
                            case 5:
                                projeto.setDiariasGasto(projeto.getDiariasGasto()-despesa.getValor());
                                break;
                            case 6:
                                projeto.setPassagensGasto(projeto.getPassagensGasto()-despesa.getValor());
                                break;
                            default:
                                throw new SistemaException();
                        }
                        boolean resultado = new DespesaDAO().removeDespesa(despesa.getId(), projeto);
                        if(resultado==true){
                            JOptionPane.showMessageDialog(null,"Operação realizada com sucesso");
                            frame.setVisible(false);
                            frame.dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Não foi possível remover a despesa");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        }

        //{{Manipulacao de eventos}}
        //{{Adicionar uma nova despesa}}
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAdicionaDespesa telaAdicionaDespesa = new TelaAdicionaDespesa(projeto);
                telaAdicionaDespesa.imprime();
                frame.setVisible(false);
                frame.dispose();
            }
        });

        //super final
        frame.setVisible(true);
    }//fim do metodo imprime
}//fim classe
