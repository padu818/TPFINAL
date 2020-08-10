package tpdied2020.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tpdied2020.dao.utils.DB;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.Ruta;
import tpdied2020.gui.auxiliar.GrafoPlanta;

public class PlantaDaoSql implements PlantaDao{
	
	private static final String SELECT_ALL_PLANTA =
			"SELECT IDPLANTA,NOMBRE FROM PLANTA";
	
	private static final String SELECT_PLANTA_ID =
			"SELECT IDPLANTA, NOMBRE FROM PLANTA WHERE IDPLANTA = ?";
	
	private static final String SELECT_ALL_RUTA =
			"SELECT IDRUTA,IDPLANTAORIGEN,IDPLANTADESTINO,DURACIONHS,DURACIONKM,CANTMAXATRANSPORTAR FROM RUTA";
	
	private static final String INSERT_PLANTA=
			"INSERT INTO PLANTA (NOMBRE) VALUES (?)";
	
	private static final String INSERT_RUTA=
			"INSERT INTO RUTA (IDPLANTAORIGEN,IDPLANTADESTINO,DURACIONHS,DURACIONKM,CANTMAXATRANSPORTAR) VALUES (?,?,?,?,?)";
	
	private static final String DELETE_RUTA = "DELETE FROM RUTA WHERE IDPLANTAORIGEN = ? AND IDPLANTADESTINO = ?";
	
	public Planta saveOrUpdate(Planta i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt= conn.prepareStatement(INSERT_PLANTA);
			pstmt.setString(1, i.getNombre());
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
		Planta pl= new Planta();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_PLANTA_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				pl.setIdPlanta(rs.getInt("IDPLANTA"));
				pl.setNombre(rs.getString("NOMBRE"));
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
		return pl;
	}
	
	public void borrar(Integer orig,Integer dest) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt= conn.prepareStatement(DELETE_RUTA);
			pstmt.setInt(1, orig);
			pstmt.setInt(2, dest);
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
		return lista;
	}
	
	
	public GrafoPlanta armarGrafo() {
		GrafoPlanta grafo = new GrafoPlanta();
		List<Planta> lista = new ArrayList<Planta>();
		List<Ruta> rut = new ArrayList<Ruta>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_RUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Ruta r = new Ruta();
				r.setId(rs.getInt("IDRUTA"));
				r.setOrigen(buscarPorId(rs.getInt("IDPLANTAORIGEN")));
				r.setDestino(buscarPorId(rs.getInt("IDPLANTADESTINO")));
				r.setDuracionHs(rs.getDouble("DURACIONHS"));
				r.setDistanciaKM(rs.getDouble("DURACIONKM"));
				r.setPesoMaxKg(rs.getDouble("CANTMAXATRANSPORTAR"));
				rut.add(r);
		}			
			pstmt.close();
			rs.close();
			pstmt= conn.prepareStatement(SELECT_ALL_PLANTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Planta p = new Planta();
				p.setIdPlanta(rs.getInt("IDPLANTA"));
				p.setNombre(rs.getString("NOMBRE"));
				lista.add(p);
			}
			
			for(Planta p : lista) {
				grafo.addNodo(p);
			}
			for(Ruta r : rut) {
				grafo.conectar(r.getOrigen(), r.getDestino(), r.getDuracionHs(), r.getDistanciaKM(), r.getPesoMaxKg());
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
		return grafo;
	}
	
	public Ruta saveOrUpdate(Ruta r) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt= conn.prepareStatement(INSERT_RUTA);
			pstmt.setInt(1, r.getOrigen().getIdPlanta());
			pstmt.setInt(2, r.getDestino().getIdPlanta());
			pstmt.setDouble(3, r.getDuracionHs());
			pstmt.setDouble(4, r.getDistanciaKM());
			pstmt.setDouble(5, r.getPesoMaxKg());
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
		return r;
	}

	public List<Ruta> buscarTodaRuta() {
		List<Ruta> lista = new ArrayList<Ruta>();
		
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_RUTA);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Ruta r = new Ruta();
				r.setId(rs.getInt("IDRUTA"));
				r.setOrigen(buscarPorId(rs.getInt("IDPLANTAORIGEN")));
				r.setDestino(buscarPorId(rs.getInt("IDPLANTADESTINO")));
				r.setDuracionHs(rs.getDouble("DURACIONHS"));
				r.setDistanciaKM(rs.getDouble("DURACIONKM"));
				r.setPesoMaxKg(rs.getDouble("CANTMAXATRANSPORTAR"));
				lista.add(r);
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
