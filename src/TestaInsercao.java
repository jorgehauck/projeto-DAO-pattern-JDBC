import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.recuperarConexao();
		
		Statement st = conn.createStatement();
		st.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')"
				, Statement.RETURN_GENERATED_KEYS);
		
	
		
		ResultSet rs = st.getGeneratedKeys();
		
		while(rs.next()) {
			Integer id = rs.getInt(1);
			System.out.println("id criado: " + id);
		}
	}
}
