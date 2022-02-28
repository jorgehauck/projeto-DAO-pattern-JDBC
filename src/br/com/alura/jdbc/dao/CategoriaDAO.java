package br.com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alura.jdbc.model.Categoria;

public class CategoriaDAO {

	private Connection connection;
	
	public CategoriaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public List<Categoria> listar() throws SQLException {
		List<Categoria> listaCategorias = new ArrayList<>();
		
		String sql = "SELECT ID, NOME FROM CATEGORIA";
		
		try(PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			
			try(ResultSet rs = pstm.getResultSet()) {
				while(rs.next()) {
					Categoria categoria = new Categoria(rs.getInt(1), rs.getString(2));
					
					listaCategorias.add(categoria);
				}
			}
		}
		return listaCategorias;
	}
}
