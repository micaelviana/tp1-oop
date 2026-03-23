import java.sql.*;

import javax.swing.JOptionPane;

public class BancoDeDados {
  private static String url = "jdbc:mysql://localhost:3306/TrabalhoBD";
  private static String user = "micael";
  private static String pass = "1234";
  protected static Connection conexao = null;

  public BancoDeDados() {
    if (conexao == null) conecta();
  }

  private static boolean conecta(){
    try {
      conexao = DriverManager.getConnection(url, user, pass);
      // JOptionPane.showInputDialog(null, "deu");
      return true;
    } catch (SQLException e) { 
      System.out.println(e.getMessage());
      JOptionPane.showMessageDialog(null,e.getMessage());
      return false; 
    }
  }

  public static boolean desconecta() {
    try {
      conexao.close();
      return true;
    } catch (SQLException e) { return false; }
  }
}