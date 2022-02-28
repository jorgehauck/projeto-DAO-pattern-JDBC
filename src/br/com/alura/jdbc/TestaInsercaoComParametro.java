package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.alura.jdbc.factory.ConnectionFactory;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		String nome = "TV";
		String descricao = "Samsung";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection conn = factory.recuperarConexao()) {

			conn.setAutoCommit(false);

			try (PreparedStatement st = conn.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?,?)",
					Statement.RETURN_GENERATED_KEYS);) {
				adicionarVariavel("Smart TV", "45 polegadas", st);
				adicionarVariavel("Xiaomi M9", "M9-Xiaomi", st);

				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO!");
				conn.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement st) throws SQLException {
		st.setString(1, nome);
		st.setString(2, descricao);

		if (nome.equals("Xiaomi M9")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}

		st.execute();
		try (ResultSet rs = st.getGeneratedKeys()) {

			while (rs.next()) {
				Integer id = rs.getInt(1);
				System.out.println("id criado: " + id);
			}

		}
	}
}
