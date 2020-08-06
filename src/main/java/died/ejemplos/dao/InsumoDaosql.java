package died.ejemplos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import died.ejemplos.dao.utils.DB;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.General;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Liquido;
import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.dominio.StockInsumo;
import died.ejemplos.dominio.Unidad;

public class InsumoDaosql implements InsumoDao {
	
////	private static final String TABLA_CREATE_INSUMO = 
//	"CREATE TABLE  IF NOT EXISTS INSUMO ( IDINSUMO integer not NULL GENERATED ALWAYS AS IDENTITY, NOMBRE VARCHAR(45) not NULL,"
//	+ " DESCRIPCION VARCHAR(50) not NULL,UNIDAD_MEDIDA VARCHAR(12) not NULL, "
//	+ "COSTO DECIMAL(12,2), TIPO VARCHAR(10) not NULL, PESO DECIMAL(12,2), DENSIDAD DECIMAL(12,2),  "
//	+ "PRIMARY KEY(IDINSUMO));";
	
	
	
	private static final String SELECT_ALL_INSUMO =
			"SELECT IDINSUMO,NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,PESO,DENSIDAD FROM INSUMO";
	
	private static final String INSERT_INSUMO =
			"INSERT INTO INSUMO (NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,DENSIDAD,PESO) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE_INSUMO =
			" UPDATE INSUMO SET NOMBRE = ?, DESCRIPCION =? ,UNIDAD_MEDIDA = ? , COSTO =?, TIPO = ?, DENSIDAD = ?, PESO =?"
			+ " WHERE IDINSUMO = ?";
	private static final String DELETE_INSUMO = "DELETE FROM INSUMO WHERE NOMBRE = ?";
	
	private static final String INSERT_STOCKINSUMO=
			"INSERT INTO STOCKINSUMO (IDPLANTA,IDINSUMO,STOCK,PUNTOREPOSICION) VALUES (?,?,?,?)";
	
	private static final String SELECT_ALL_STOCKINSUMO =
			"SELECT IDPLANTA,IDINSUMO,STOCK,PUNTOREPOSICION FROM STOCKINSUMO";
	
	private static final String DELETE_STOCKINSUMO = "DELETE FROM STOCKINSUMO WHERE IDPLANTA = ? AND IDINSUMO = ?";

	@Override
	public Insumo saveOrUpdate(Insumo i) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			if(i.getIdProduto()!=null && i.getIdProduto()>0) {
		//		System.out.println("EJECUTA UPDATE");
				pstmt= conn.prepareStatement(UPDATE_INSUMO);
				pstmt.setString(1, i.getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setString(3, i.getUnidadMedida());
				pstmt.setDouble(4, i.getCosto());
				pstmt.setString(5, i.getTipoInsumo());

				if (i.getTipoInsumo().equals("LIQUIDO")) {
					pstmt.setDouble(6, ((Liquido)i).getDensidad());
					pstmt.setDouble(7, 0);
				}else {
					pstmt.setDouble(6, 0);
					pstmt.setDouble(7, ((General)i).getPeso());
				}
				pstmt.setInt(8, i.getIdProduto());
			}else {
		//		System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(INSERT_INSUMO);

				pstmt.setString(1, i.getNombre());
				pstmt.setString(2, i.getDescripcion());
				pstmt.setString(3, i.getUnidadMedida());
				pstmt.setDouble(4, i.getCosto());
				pstmt.setString(5, i.getTipoInsumo());
				if (i.getTipoInsumo().equals("LIQUIDO")) {
					pstmt.setDouble(6, ((Liquido)i).getDensidad());
					pstmt.setDouble(7, 0);
				}else {
					pstmt.setDouble(6, 0);
					pstmt.setDouble(7, ((General)i).getPeso());
				}	
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
		return i;
	}

	public List<Insumo> buscarPorId(String id) {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Insumo i;
				if (rs.getString("TIPO").equals("GENERAL")) {
					i = new General();
					((General)i).setPeso(rs.getDouble("PESO"));
					
				}else {
					i = new Liquido();
					((Liquido)i).setDensidad(rs.getDouble("DENSIDAD"));
				}
				
				i.setIdProduto((rs.getInt("IDINSUMO")));
				i.setNombre(rs.getString("NOMBRE"));
				i.setDescripcion(rs.getString("DESCRIPCION"));
				i.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
				i.setCosto(rs.getDouble("COSTO"));
				lista.add(i);
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
	public List<Insumo> buscarTodos() {
		List<Insumo> lista = new ArrayList<Insumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_INSUMO);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Insumo i;
				if (rs.getString("TIPO").equals("GENERAL")) {
					i = new General();
					((General)i).setPeso(rs.getDouble("PESO"));
					
				}else {
					i = new Liquido();
					((Liquido)i).setDensidad(rs.getDouble("DENSIDAD"));
				}
				
				i.setIdProduto((rs.getInt("IDINSUMO")));
				i.setNombre(rs.getString("NOMBRE"));
				i.setDescripcion(rs.getString("DESCRIPCION"));
				i.setUnidadMedida(rs.getString("UNIDAD_MEDIDA"));
				i.setCosto(rs.getDouble("COSTO"));
				
//				((General)i).setPeso(rs.getDouble("PESO"));
//				((Liquido)i).setDensidad(rs.getDouble("DENSIDAD"));
				lista.add(i);
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
	public List<Insumo> busqueda(String condicionesConsulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void borrar(String nombre) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
		//	System.out.println("EJECUTA DELETE");
			pstmt= conn.prepareStatement(DELETE_INSUMO);
			pstmt.setString(1, nombre);
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

	public List<StockInsumo> busquedaStock(String condicionesConsulta, List<Insumo> insumos, List<Planta> plantas) {
		List<StockInsumo> lista = new ArrayList<StockInsumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {		
			pstmt= conn.prepareStatement(condicionesConsulta);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer idInsumo = rs.getInt("IDINSUMO");
				Integer idPlanta =rs.getInt("IDPLANTA");
				StockInsumo s = new StockInsumo();

				for(Insumo ins: insumos) {
					if(idInsumo == ins.getIdProduto()) {
						System.out.println(ins);
						s.setInsumo(ins);

						break;
						}
	
				}


				for(Planta pl : plantas) {
					if(idPlanta == pl.getIdPlanta()) {
						s.setPlanta(pl);
	
						break;
					}
				}
				s.setStock(rs.getInt("STOCK"));
				s.setPuntoReposicion(rs.getInt("PUNTOREPOSICION"));
				lista.add(s);
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
	public StockInsumo saveOrUpdate(StockInsumo s) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
//			if(c.getId()!=null && c.getId()>0) {
//				System.out.println("EJECUTA UPDATE");
//				pstmt= conn.prepareStatement(UPDATE_);
//				pstmt.setString(1, i.getNombre());
//
//			}else {
//				System.out.println("EJECUTA INSERT");
				pstmt= conn.prepareStatement(INSERT_STOCKINSUMO);
				pstmt.setInt(1, s.getPlanta().getIdPlanta());
				pstmt.setInt(2, s.getInsumo().getIdProduto());
				pstmt.setDouble(3, s.getStock());
				pstmt.setDouble(4, s.getPuntoReposicion());
				
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
		return s;
	}

	@Override
	public List<StockInsumo> busquedaStockInsumos(Planta p) {
		List<StockInsumo> lista = new ArrayList<StockInsumo>();
		
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_STOCKINSUMO);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					if (rs.getInt("IDPLANTA") == p.getIdPlanta()) {
						StockInsumo s = new StockInsumo();
						s.setCantidad(rs.getInt("STOCK"));
						s.setPuntoReposicion(rs.getInt("PUNTOREPOSICION"));
						s.setPlanta(p);
						List<Insumo> insumos = new ArrayList<Insumo>();
						insumos = buscarTodos();
//						insumos.stream().filter((Insumo i)->((i.getIdProduto())==(rs.getInt("IDINSUMO"))).collect(Collectors.toList()));
						for (Insumo insumo : insumos) {
							if (insumo.getIdProduto() == rs.getInt("IDINSUMO")) {
								s.setInsumo(insumo);
								break;
							}
						}
//						s.setInsumo(buscarPorId(rs.getInt("IDINSUMO")));
						lista.add(s);
					}
					
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
	public void borrar(Integer idPlanta, Integer idInsumo) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
			pstmt= conn.prepareStatement(DELETE_STOCKINSUMO);
			pstmt.setInt(1, idPlanta);
			pstmt.setInt(2, idInsumo);
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

}
