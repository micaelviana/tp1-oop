import java.sql.*;
import javax.swing.JOptionPane;

public class DespesaDAO extends BancoDeDados{
    //id, projeto_id,descricao,categoria
    public boolean adicionaDespesa(Despesa despesa,Projeto projeto){
        String query;
        query="INSERT INTO despesa VALUES(NULL,"+despesa.getProjeto().getId()+",'"+despesa.getDescricao()+"',"+despesa.getCategoria()+","+despesa.getValor()+")";
        try {
            Statement st = conexao.createStatement();    
            st.executeUpdate(query);
            if(new ProjetoDAO().atualizaProjeto(projeto)){
                System.out.println("Despesa adicionada com sucesso");
                return true;
            }
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return false;
        }
    }

    //remover uma despesa e atualizar o projeto que se relacionava com ela
    public boolean removeDespesa(int id, Projeto projeto){
        try {
            Statement st = conexao.createStatement();
            st.execute("delete from despesa where id="+id);
            ProjetoDAO projetoDAO = new ProjetoDAO();
            if(projetoDAO.atualizaProjeto(projeto)){
                System.out.println("Despesa removida com sucesso");
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public ResultSet listaDespesas(Projeto projeto){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from despesa where projeto_id="+projeto.getId());
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }


    public int getNumDespesas(int projeto_id){
        try{
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select count(*) from despesa where projeto_id="+projeto_id);
            rs.next();
            return rs.getInt("count(*)");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        return 0;
    }

    public Despesa getDespesa(int id){
        try {
            Statement st = conexao.createStatement();
            ResultSet rs = st.executeQuery("select * from despesa where id="+id);
            if(rs.next()){
                Despesa despesa = new Despesa(null, rs.getString(3), Integer.valueOf(rs.getString(4)),
                        Double.parseDouble(rs.getString(5)));
                despesa.setId(id);
                return despesa;
            }else{
                return null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
