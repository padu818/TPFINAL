package died.ejemplos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import died.ejemplos.dao.utils.DB;
import died.ejemplos.dominio.Camion;

public class CamionDaoMysql implements CamionDao{
	private static final String SELECT_ALL_CAMION =
			"SELECT ID,PATENTE,MARCA,MODELO,KM FROM CAMION";
	
	private static final String INSERT_CAMION =
			"INSERT INTO CAMION (PATENTE,MARCA,MODELO,KM) VALUES (?,?,?,?)";
	private static final String UPDATE_CAMION =
			" UPDATE CAMION SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =? "
			+ " WHERE ID = ?";
	

	
	@Override
	public Camion saveOrUpdate(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(c.getId()!=null && c.getId()>0) {
				System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(UPDATE_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
				pstmt.setInt(5, c.getId());
			}else {
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(INSERT_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setInt(4, c.getKm());
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}



	@Override
	public Camion buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Camion> buscarTodos() {
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_CAMION);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getInt("KM"));
				lista.add(c);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}	
		System.out.println("Resultado "+lista);
		return lista;
	}

}
