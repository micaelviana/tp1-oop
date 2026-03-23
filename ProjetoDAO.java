import java.sql.*;

import javax.swing.JOptionPane;

public class ProjetoDAO extends BancoDeDados{
    public boolean adicionaProjeto(Projeto projeto){
        String query;
        query = "insert into projeto values (NULL, " +
        "'" + projeto.getNome() + "', " +
        "'" + projeto.getProfessor() + "', " + 
        String.valueOf(projeto.getDespesasCapitalPrevisto()) + ", " +
        "0, "+
        String.valueOf(projeto.getMaterialConsumoPrevisto()) + ", " +
        "0, "+
        String.valueOf(projeto.getServicosPessoaFisicaPrevisto()) + ", " +
        "0, "+
        String.valueOf(projeto.getServicosPessoaJuridicaPrevisto())+ ", " +
        "0, "+
        String.valueOf(projeto.getDiariasPrevisto())+ ", " +
        "0 ,"+
        String.valueOf(projeto.getPassagensPrevisto())+ ", " +
        "0)"; 

        try {
            Statement st = conexao.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"Operação realizada com sucesso");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }
    }

    public ResultSet listaProjetos(){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from projeto");
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }

    public ResultSet listaProjetosPorNomeOuProfessor(String termo){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from projeto where nome="+"'"+termo+"' or professor="+"'"+termo+"'");
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }


    public boolean removeProjeto(int id){
        try{
            Statement st = conexao.createStatement();
            st.execute("delete from despesa where projeto_id="+id);
            st.execute("delete from projeto where id="+id);
            System.out.println("Projeto removido com sucesso");
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return false;
    }

    public boolean atualizaProjeto(Projeto projeto){
        try{
            Statement st = conexao.createStatement();
            String query;
            query="UPDATE projeto SET nome="+"'"+projeto.getNome()+"'"+",professor="+"'"+projeto.getProfessor()+"'"+",despesasCapitalPrevisto="+String.valueOf(projeto.getDespesasCapitalPrevisto())+",despesasCapitalGasto="+String.valueOf(projeto.getDespesasCapitalGasto())+",materialConsumoGasto="+String.valueOf(projeto.getMaterialConsumoGasto())+",materialConsumoPrevisto="+String.valueOf(projeto.getMaterialConsumoPrevisto())+",servicosPessoaFisicaPrevisto="+String.valueOf(projeto.getServicosPessoaFisicaPrevisto())+",servicosPessoaFisicaGasto="+String.valueOf(projeto.getServicosPessoaFisicaGasto())+",servicosPessoaJuridicaPrevisto="+String.valueOf(projeto.getServicosPessoaJuridicaPrevisto())+",servicosPessoaJuridicaGasto="+String.valueOf(projeto.getServicosPessoaJuridicaGasto())+",diariasPrevisto="+String.valueOf(projeto.getDiariasPrevisto())+",diariasGasto="+projeto.getDiariasGasto()+",passagensPrevisto="+projeto.getPassagensPrevisto()+",passagensGasto="+projeto.getPassagensGasto()+" WHERE id="+projeto.getId();

            //finally 
            st.executeUpdate(query);
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Erro. Não foi possivel atualizar o projeto");
            return false;
        }
    }

    public int getNumProjetos(){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from projeto");
            rs.next();
            return rs.getInt("count(*)");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return 0;
    }
    
    public int getNumProjetosPorNomeouProfessor(String termo){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from projeto where nome="+"'"+termo+"' or professor="+"'"+termo+"'");
            rs.next();
            return rs.getInt("count(*)");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return 0;
    }

    public Projeto getProjeto(int id){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from projeto where id="+id);
            if(rs.next()){
                Projeto projeto = new Projeto(rs.getString(2),rs.getString(3),Double.parseDouble(rs.getString(4)),Double.parseDouble(rs.getString(5)),Double.parseDouble(rs.getString(6)),Double.parseDouble(rs.getString(7)),Double.parseDouble(rs.getString(8)),Double.parseDouble(rs.getString(9)),Double.parseDouble(rs.getString(10)),Double.parseDouble(rs.getString(11)),Double.parseDouble(rs.getString(12)),Double.parseDouble(rs.getString(13)),Double.parseDouble(rs.getString(14)),Double.parseDouble(rs.getString(15)));
                projeto.setId(id);;
                return projeto;
            }else{
                return null;
            }
        }catch(SQLException e){
            System.out.println("Não foi possivel obter o projeto pedido. Retornando \"null\"");
            return null;
        }
    }
}
