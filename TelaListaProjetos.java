import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

public class TelaListaProjetos implements TelaDesenhavel{
    private JFrame frame;
    
    public void imprime(){
        int width=1200;
        frame = new JFrame("Listar projetos cadastrados");
        frame.setLayout(new BorderLayout());
        frame.setSize(width,250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel titulo = new JLabel("Lista dos projetos cadastrados");
        titulo.setBounds(0, 10, width, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titulo, BorderLayout.NORTH);

        // estrutura de dados que salva os botoes e seus ids
        Vector<BotaoComId> botoes = new Vector<>();
        //buscar projetos

        try{
            //o primeiro passo eh descobrir quantas linhas a tabela tem
            ResultSet rs = new ProjetoDAO().listaProjetos();
            //move o cursor pra ultima linha
            //diz onde o cursor esta, 
            int numLinhas = new ProjetoDAO().getNumProjetos();

            //restaurar o rs para a primeira linha da tabela

            //criacao do painel central
            JPanel painelCentral = new JPanel();
            //5: id, nome, professor,botaoeditar, botaoremover
            //Primeira linha
            painelCentral.setLayout(new GridLayout(numLinhas+1,5));
            painelCentral.add(new JLabel("ID"));
            painelCentral.add(new JLabel("Nome do projeto"));
            painelCentral.add(new JLabel("Professor responsável"));
            painelCentral.add(new JLabel());
            painelCentral.add(new JLabel());


            //Rows com conteudo
            while(rs.next()){
                painelCentral.add(new JLabel(rs.getString(1)));
                painelCentral.add(new JLabel(rs.getString(2)));
                painelCentral.add(new JLabel(rs.getString(3)));

                //botao de edicao
                BotaoComId aux = new BotaoComId(Integer.valueOf(rs.getString(1)), "Visão Geral/Edição", 'e');
                botoes.add(aux);
                painelCentral.add(aux.getBotao());

                //botao de remocao
                aux = new BotaoComId(Integer.valueOf(rs.getString(1)), "Apagar projeto do sistema", 'r');
                aux.getBotao().setBackground(new Color(168,0,0));
                aux.getBotao().setForeground(new Color(255,255,255));
                botoes.add(aux);
                painelCentral.add(aux.getBotao());
            }

            //adicionar o painel que contem as informacoes a tela
            frame.add(painelCentral,BorderLayout.CENTER);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Não foi possível listar os projetos");
            frame.setVisible(false);
            frame.dispose();
            
        }

        //{{Manipulacao de eventos}}
        //{{Remover um projeto}}
        for(BotaoComId algumBotao: botoes){
            algumBotao.getBotao().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(algumBotao.getTipo()=='r'){
                        boolean resultado = new ProjetoDAO().removeProjeto(algumBotao.getId());
                        if(resultado == true){
                            JOptionPane.showMessageDialog(null,"Operação realizada com sucesso");
                            //espero que a tela suma
                            frame.setVisible(false);
                            frame.dispose();
                        }
                    }else{
                      Projeto projeto = new ProjetoDAO().getProjeto(algumBotao.getId());
                      if(projeto != null){
                        TelaExibeProjeto telaExibeProjeto = new TelaExibeProjeto(projeto);
                        telaExibeProjeto.imprime();

                        frame.setVisible(false);
                        frame.dispose();
                      }
                    }
                }
            });
        }//for

        frame.setVisible(true);
    }//fim do metodo imprime


    public void imprime(ResultSet rs,int numLinhas){
        int width=1200;
        frame = new JFrame("Listar projetos cadastrados");
        frame.setLayout(new BorderLayout());
        frame.setSize(width,250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel titulo = new JLabel("Lista dos projetos cadastrados");
        titulo.setBounds(0, 10, width, 20);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titulo, BorderLayout.NORTH);

        // estrutura de dados que salva os botoes e seus ids
        Vector<BotaoComId> botoes = new Vector<>();
        //buscar projetos

        try{

            //restaurar o rs para a primeira linha da tabela

            //criacao do painel central
            JPanel painelCentral = new JPanel();
            //5: id, nome, professor,botaoeditar, botaoremover
            //Primeira linha
            painelCentral.setLayout(new GridLayout(numLinhas+1,5));
            painelCentral.add(new JLabel("ID"));
            painelCentral.add(new JLabel("Nome do projeto"));
            painelCentral.add(new JLabel("Professor responsável"));
            painelCentral.add(new JLabel());
            painelCentral.add(new JLabel());


            //Rows com conteudo
            while(rs.next()){
                painelCentral.add(new JLabel(rs.getString(1)));
                painelCentral.add(new JLabel(rs.getString(2)));
                painelCentral.add(new JLabel(rs.getString(3)));

                //botao de edicao
                BotaoComId aux = new BotaoComId(Integer.valueOf(rs.getString(1)), "Visão Geral/Edição", 'e');
                botoes.add(aux);
                painelCentral.add(aux.getBotao());

                //botao de remocao
                aux = new BotaoComId(Integer.valueOf(rs.getString(1)), "Apagar projeto do sistema", 'r');
                aux.getBotao().setBackground(new Color(168,0,0));
                aux.getBotao().setForeground(new Color(255,255,255));
                botoes.add(aux);
                painelCentral.add(aux.getBotao());
            }

            //adicionar o painel que contem as informacoes a tela
            frame.add(painelCentral,BorderLayout.CENTER);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Não foi possível listar os projetos");
            frame.setVisible(false);
            frame.dispose();
            
        }

        //{{Manipulacao de eventos}}
        //{{Remover um projeto}}
        for(BotaoComId algumBotao: botoes){
            algumBotao.getBotao().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    if(algumBotao.getTipo()=='r'){
                        boolean resultado = new ProjetoDAO().removeProjeto(algumBotao.getId());
                        if(resultado == true){
                            JOptionPane.showMessageDialog(null,"Operação realizada com sucesso");
                            //espero que a tela suma
                            frame.setVisible(false);
                            frame.dispose();
                        }else{
                        }
                    }else{
                      Projeto projeto = new ProjetoDAO().getProjeto(algumBotao.getId());
                      if(projeto != null){
                        TelaExibeProjeto telaExibeProjeto = new TelaExibeProjeto(projeto);
                        telaExibeProjeto.imprime();

                        frame.setVisible(false);
                        frame.dispose();
                      }
                    }
                }
            });
        }//for

        frame.setVisible(true);
    }//fim do metodo imprime(rs,Linhas)

}//fim classe
