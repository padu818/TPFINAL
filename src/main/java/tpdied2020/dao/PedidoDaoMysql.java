package tpdied2020.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tpdied2020.dao.utils.DB;
import tpdied2020.dominio.Camion;
import tpdied2020.dominio.DetallesInsumoSolicitado;
import tpdied2020.dominio.EstadoPedido;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Pedido;
import tpdied2020.dominio.Planta;

public class PedidoDaoMysql implements PedidoDao {
	
	private static final String INSERT_PEDIDO =
			"INSERT INTO PEDIDO (IDPLANTADESTINO,FECHA_SOLICITUD,FECHA_ENTREGA,ESTADO) VALUES (?,?,?,?)";
	
	private static final String UPDATE_PEDIDO =
			" UPDATE PEDIDO SET IDPLANTADESTINO = ?, FECHA_SOLICITUD = ? ,FECHA_ENTREGA = ? , ESTADO = ?, IDCAMIONASIGNADO = ?,"
			+ "IDPLANTAORIGEN =? , COSTO =?  WHERE IDPEDIDO = ?";
	
	private static final String INSERT_DETALLEINSUMOSSOLICITADO =
			"INSERT INTO DETALLEINSUMOSOLICITADO (IDINSUMO,IDPEDIDO,CANTIDAD,PRECIO) VALUES (?,?,?,?)";
	
	private static final String SELECT_ULTIMO_IDPEDIDO =
			"SELECT MAX(IDPEDIDO) as ID FROM PEDIDO";
	
	private static final String SELECT_ALL_PEDIDO_CREADO =
			"SELECT IDPEDIDO,IDPLANTADESTINO,FECHA_SOLICITUD,FECHA_ENTREGA FROM PEDIDO WHERE ESTADO = 'CREADA'";
	
	private static final String SELECT_ALL_PEDIDO_PROCESADO =
			"SELECT IDPEDIDO,IDPLANTAORIGEN,IDPLANTADESTINO,FECHA_SOLICITUD,FECHA_ENTREGA,IDCAMIONASIGNADO,COSTO FROM PEDIDO WHERE ESTADO = 'PROCESADA'";
	
	private static final String SELECT_ALL_DETALLEINSUMOSOLICITADO =
			"SELECT IDINSUMO,IDPEDIDO,CANTIDAD,PRECIO FROM DETALLEINSUMOSOLICITADO";
	
	public Pedido saveOrUpdate(Pedido p) {
		
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if(p.getIdPedido()!=null) {
				pstmt= conn.prepareStatement(UPDATE_PEDIDO);
				pstmt.setInt(1, p.getDestino().getIdPlanta());
				pstmt.setDate(2,  java.sql.Date.valueOf(p.getFechaSolicitud().toString()));
				pstmt.setDate(3,  java.sql.Date.valueOf(p.getFechaEntrega().toString()));
				pstmt.setString(4, p.getEstado().toString());
				pstmt.setInt(5, p.getCamionAsignado().getId());
				pstmt.setInt(6, p.getOrigen().getIdPlanta());
				pstmt.setDouble(7, p.getCostoEnvio());
				pstmt.setInt(8, p.getIdPedido());
				pstmt.executeUpdate();
			}else {

				pstmt= conn.prepareStatement(INSERT_PEDIDO);
				pstmt.setInt(1, p.getDestino().getIdPlanta());
				pstmt.setDate(2, java.sql.Date.valueOf(p.getFechaSolicitud()));
				pstmt.setDate(3, java.sql.Date.valueOf(p.getFechaEntrega()));
				pstmt.setString(4, p.getEstado().toString());
				pstmt.executeUpdate();
				if(pstmt!=null) pstmt.close();
				pstmt = conn.prepareStatement(SELECT_ULTIMO_IDPEDIDO);
				rs = pstmt.executeQuery();
				while(rs.next())
					p.setIdPedido(rs.getInt("ID"));
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				for (DetallesInsumoSolicitado detalle : p.getItems()) {
					pstmt= conn.prepareStatement(INSERT_DETALLEINSUMOSSOLICITADO);
					pstmt.setInt(1, detalle.getInsumo().getIdProduto());
					pstmt.setInt(2, p.getIdPedido());
					pstmt.setInt(3, detalle.getCantidad());
					pstmt.setDouble(4, detalle.getPrecio());
					pstmt.executeUpdate();
					if(pstmt!=null) pstmt.close();
				}
			}
				
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
		return p;
		
	}

	public List<Pedido> buscarTodos(List<Insumo> listaInsumos){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_PEDIDO_CREADO);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Pedido pedido = new Pedido();
				Planta planta = new Planta();
				pedido.setIdPedido(rs.getInt("IDPEDIDO"));
				planta.setIdPlanta(rs.getInt("IDPLANTADESTINO"));
				pedido.setDestino(planta);
				pedido.setEstado(EstadoPedido.CREADA);
				pedido.setFechaEntrega(rs.getDate("FECHA_ENTREGA").toLocalDate());
				pedido.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD").toLocalDate());
				List<DetallesInsumoSolicitado>aux = new ArrayList<DetallesInsumoSolicitado>();
				pedido.setItems(aux);
				pedidos.add(pedido);
			}
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			pstmt= conn.prepareStatement(SELECT_ALL_DETALLEINSUMOSOLICITADO);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DetallesInsumoSolicitado d = new DetallesInsumoSolicitado();
				Integer idInsumo = rs.getInt("IDINSUMO");
				for (Insumo insumo : listaInsumos) {
					if (idInsumo == insumo.getIdProduto()) {
						d.setInsumo(insumo);
						break;
					}
				}
				d.setCantidad(rs.getInt("CANTIDAD"));
				d.setPrecio(rs.getDouble("PRECIO"));
				Integer idPedido = rs.getInt("IDPEDIDO");
				for (Pedido pedido : pedidos) {
					if (idPedido == pedido.getIdPedido()) {
						d.setPedido(pedido);
						pedido.getItems().add(d);
						break;
					}
				}
			}
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
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
		return pedidos;
		
	}
	public List<DetallesInsumoSolicitado> buscarTodosInsumosSolicitados(){
		return null;
		
	}
	public List<Pedido> busqueda(String condicionesConsulta){
		return null;
		
	}

	@Override
	public List<Pedido> buscarTodoPedidoProcesado(List<Insumo> ins) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		Connection conn = DB.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt= conn.prepareStatement(SELECT_ALL_PEDIDO_PROCESADO);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Pedido pedido = new Pedido();
				Planta planta = new Planta();
				Planta p2 = new Planta();
				Camion c = new Camion();
				pedido.setIdPedido(rs.getInt("IDPEDIDO"));
				planta.setIdPlanta(rs.getInt("IDPLANTAORIGEN"));
				pedido.setOrigen(planta);
				p2.setIdPlanta(rs.getInt("IDPLANTADESTINO"));
				pedido.setDestino(p2);
				pedido.setEstado(EstadoPedido.PROCESADA);
				pedido.setFechaEntrega(rs.getDate("FECHA_ENTREGA").toLocalDate());
				pedido.setFechaSolicitud(rs.getDate("FECHA_SOLICITUD").toLocalDate());
				c.setId(rs.getInt("IDCAMIONASIGNADO"));
				pedido.setCamionAsignado(c);
				pedido.setCostoEnvio(rs.getDouble("COSTO"));
				List<DetallesInsumoSolicitado>aux = new ArrayList<DetallesInsumoSolicitado>();
				pedido.setItems(aux);
				pedidos.add(pedido);
			}
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			pstmt= conn.prepareStatement(SELECT_ALL_DETALLEINSUMOSOLICITADO);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DetallesInsumoSolicitado d = new DetallesInsumoSolicitado();
				Integer idInsumo = rs.getInt("IDINSUMO");
				for (Insumo insumo : ins) {
					if (idInsumo == insumo.getIdProduto()) {
						d.setInsumo(insumo);
						break;
					}
				}
				d.setCantidad(rs.getInt("CANTIDAD"));
				d.setPrecio(rs.getDouble("PRECIO"));
				Integer idPedido = rs.getInt("IDPEDIDO");
				for (Pedido pedido : pedidos) {
					if (idPedido == pedido.getIdPedido()) {
						d.setPedido(pedido);
						pedido.getItems().add(d);
						break;
					}
				}
			}
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
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
		return pedidos;
	};

}
