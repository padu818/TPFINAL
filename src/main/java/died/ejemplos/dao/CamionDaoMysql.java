package died.ejemplos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import died.ejemplos.dao.utils.DB;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Planta;

public class CamionDaoMysql implements CamionDao{
	private static final String SELECT_ALL_CAMION =
			"SELECT ID,PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA,IDPLANTA FROM CAMION";
	
	private static final String INSERT_CAMION =
			"INSERT INTO CAMION (PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA,IDPLANTA) VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String INSERT_CAMION_SINID =
			"INSERT INTO CAMION (PATENTE,MARCA,MODELO,KM,COSTO_KM,COSTO_HORA,FECHA_COMPRA) VALUES (?,?,?,?,?,?,?)";
	
	private static final String UPDATE_CAMION =
			" UPDATE CAMION SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =?, COSTO_KM = ?, COSTO_HORA =?, FECHA_COMPRA = ?,IDPLANTA = ?"
			+ " WHERE ID = ?";
	private static final String DELETE_CAMION = "DELETE FROM CAMION WHERE PATENTE = ?";
	
	/* prueba
	 * 	private static final String INSERT_CAMION =
			"INSERT INTO CAMION (PATENTE,MARCA,MODELO,KM,FECHA_COMPRA,COSTO_KM,COSTO_HORA) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE_CAMION =
			" UPDATE CAMION SET PATENTE = ?, MARCA =? ,MODELO = ? , KM =?,FECHA_COMPRA=?,COSTO_KM =?, COSTO_HORA =?"
			+ " WHERE ID = ?";
	 */

	
	@Override
	public Camion saveOrUpdate(Camion c) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(c.getId()!=null && c.getId()>0) {
			//	System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(UPDATE_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setString(4, c.getKm());
				pstmt.setDouble(5, c.getCostoKM());
				pstmt.setDouble(6, c.getCostoPorHora());
				pstmt.setDate(7, java.sql.Date.valueOf(c.getFechaCompra()));
				pstmt.setInt(8, c.getPlanta().getIdPlanta());
				pstmt.setInt(9, c.getId());
			}else {
			//	System.out.println("EJECUTA INSERT");
				if(c.getPlanta().getIdPlanta() == -1)
					pstmt= conn.prepareStatement(INSERT_CAMION_SINID);
				else
					pstmt= conn.prepareStatement(INSERT_CAMION);
				pstmt.setString(1, c.getPatente());
				pstmt.setString(2, c.getMarca());
				pstmt.setString(3, c.getModelo());
				pstmt.setString(4, c.getKm());
				pstmt.setDouble(5, c.getCostoPorHora());
				pstmt.setDouble(6, c.getCostoKM());
				pstmt.setDate(7,java.sql.Date.valueOf(c.getFechaCompra()));
				if(c.getPlanta().getIdPlanta() != -1)
					pstmt.setInt(8, c.getPlanta().getIdPlanta());
				
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
	public void borrar(String patente) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
	//		System.out.println("EJECUTA DELETE");
			pstmt= conn.prepareStatement(DELETE_CAMION);
			pstmt.setString(1, patente);
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
				Planta p = new Planta();
				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getString("KM"));
				c.setCostoKM(rs.getDouble("COSTO_KM"));
				c.setCostoHora(rs.getDouble("COSTO_HORA"));
				c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
				if(rs.getInt("IDPLANTA") == -1)
					p.setIdPlanta(-1);
				else
					p.setIdPlanta(rs.getInt("IDPLANTA"));
				c.setPlanta(p);
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
		return lista;
	}



	@Override
	public List<Camion> busqueda(String condicionesConsulta) {
		List<Camion> lista = new ArrayList<Camion>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(condicionesConsulta);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Camion c = new Camion();
				Planta p = new Planta();
				c.setId(rs.getInt("ID"));
				c.setMarca(rs.getString("MARCA"));
				c.setModelo(rs.getString("MODELO"));
				c.setPatente(rs.getString("PATENTE"));
				c.setKm(rs.getString("KM"));
				c.setCostoHora(rs.getDouble("COSTO_HORA"));
				c.setCostoKM(rs.getDouble("COSTO_KM"));
				c.setFechaCompra(rs.getDate("FECHA_COMPRA").toLocalDate());
				if(rs.getInt("IDPLANTA") == -1)
					p.setIdPlanta(-1);
				else
					p.setIdPlanta(rs.getInt("IDPLANTA"));
				c.setPlanta(p);
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
		return lista;
	}
}
