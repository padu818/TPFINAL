package died.ejemplos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import died.ejemplos.dao.utils.DB;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;

public class PlantaDaoSql implements PlantaDao{
	
	private static final String SELECT_ALL_PLANTA =
			"SELECT IDPLANTA,NOMBRE FROM PLANTA";
	
	private static final String INSERT_PLANTA=
			"INSERT INTO PLANTA (NOMBRE) VALUES (?)";

	public Planta saveOrUpdate(Planta i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
//			if(c.getId()!=null && c.getId()>0) {
//				System.out.println("EJECUTA UPDATE");
//				pstmt= conn.prepareStatement(UPDATE_);
//				pstmt.setString(1, i.getNombre());
//
//			}else {
				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(INSERT_PLANTA);
				pstmt.setString(1, i.getNombre());
				
//			}
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
		return i;
	}

	@Override
	public Planta buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Planta> buscarTodos() {
		List<Planta> lista = new ArrayList<Planta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_PLANTA);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Planta p = new Planta();
					p.setIdPlanta(rs.getInt("IDPLANTA"));
					p.setNombre(rs.getString("NOMBRE"));
					lista.add(p);
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
