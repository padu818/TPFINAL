package tpdied2020.dao;

import java.util.List;

import tpdied2020.dominio.DetallesInsumoSolicitado;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Pedido;

public interface PedidoDao {
	public Pedido saveOrUpdate(Pedido p);
	public List<Pedido> buscarTodos(List<Insumo> listaInsumos);
	public List<DetallesInsumoSolicitado> buscarTodosInsumosSolicitados();
	public List<Pedido> busqueda(String condicionesConsulta);
	public List<Pedido> buscarTodoPedidoProcesado(List<Insumo> ins);


}
