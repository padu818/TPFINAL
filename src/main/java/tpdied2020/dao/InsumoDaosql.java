package tpdied2020.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tpdied2020.dao.utils.DB;
import tpdied2020.dominio.General;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Liquido;
import tpdied2020.dominio.Planta;
import tpdied2020.dominio.StockInsumo;

public class InsumoDaosql implements InsumoDao {
	
	private static final String SELECT_ALL_INSUMO =
			"SELECT IDINSUMO,NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,PESO,DENSIDAD FROM INSUMO";
	
	private static final String INSERT_INSUMO =
			"INSERT INTO INSUMO (NOMBRE,DESCRIPCION,UNIDAD_MEDIDA,COSTO,TIPO,DENSIDAD,PESO) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE_INSUMO =
			" UPDATE INSUMO SET NOMBRE = ?, DESCRIPCION =? ,UNIDAD_MEDIDA = ? , COSTO =?, TIPO = ?, DENSIDAD = ?, PESO =?"
			+ " WHERE IDINSUMO = ?";
	private static final String DELETE_INSUMO = "DELETE FROM INSUMO WHERE NOMBRE = ?";
	
	private static final String UPDATE_STOCKINSUMO=
			" UPDATE STOCKINSUMO SET STOCK = ? WHERE IDINSUMO = ? AND IDPLANTA = ?";
	
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
		return null;
	}

	@Override
	public void borrar(String nombre) {
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		try {
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
	
	
	public List<StockInsumo> busquedaStock( List<Insumo> insumos/*, List<Planta> plantas*/) {
		List<StockInsumo> lista = new ArrayList<StockInsumo>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {		
			pstmt= conn.prepareStatement("SELECT IDPLANTA,IDINSUMO,STOCK,PUNTOREPOSICION FROM STOCKINSUMO");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer idInsumo = rs.getInt("IDINSUMO");
				Integer idPlanta =rs.getInt("IDPLANTA");
				StockInsumo s = new StockInsumo();
				Planta p = new Planta();
				p.setIdPlanta(idPlanta);
				s.setPlanta(p);
				for(Insumo ins: insumos) {
					if(idInsumo == ins.getIdProduto()) {
						s.setInsumo(ins);

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
			if(s.getIDRegistro()!=null) {
				pstmt= conn.prepareStatement(UPDATE_STOCKINSUMO);
				pstmt.setInt(1, s.getStock());
				pstmt.setInt(2, s.getInsumo().getIdProduto());
				pstmt.setInt(3, s.getPlanta().getIdPlanta());

			}else {
				pstmt= conn.prepareStatement(INSERT_STOCKINSUMO);
				pstmt.setInt(1, s.getPlanta().getIdPlanta());
				pstmt.setInt(2, s.getInsumo().getIdProduto());
				pstmt.setDouble(3, s.getStock());
				pstmt.setDouble(4, s.getPuntoReposicion());
				
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
						for (Insumo insumo : insumos) {
							if (insumo.getIdProduto() == rs.getInt("IDINSUMO")) {
								s.setInsumo(insumo);
								break;
							}
						}
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

	@Override
	public List<Planta> obtener(String consulta) {
		List<Planta> lista = new ArrayList<Planta>();
		
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(consulta);	
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

}
