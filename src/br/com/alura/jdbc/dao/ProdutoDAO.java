package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.factory.ConnectionFactory;
import br.com.alura.jdbc.model.Produto;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvar(Produto produto) throws SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			String sql = "INSERT INTO PRODUTO (nome, descricao) VALUES(?,?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());

				pstm.execute();

				try (ResultSet rs = pstm.getGeneratedKeys()) {
					while (rs.next()) {
						produto.setId(rs.getInt(1));
					}
				}
			}
		}
	}
	
	public List<Produto> lista() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.execute();
			
			try(ResultSet rs = pstm.getGeneratedKeys()) {
				while(rs.next()) {
					Produto produto = new Produto(rs.getInt(1), rs.getString(2), rs.getString(3));
					produtos.add(produto);
				}
			}
		}
		return produtos;
	}
}
