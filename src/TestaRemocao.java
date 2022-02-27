import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.recuperarConexao();
		
		PreparedStatement st = conn.prepareStatement("DELETE FROM PRODUTO WHERE ID > ?");
		st.setInt(1, 2);
		st.execute();
		
		int count = st.getUpdateCount();
		
		System.out.println(count);
		
	}
}
