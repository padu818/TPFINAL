package died.ejemplos.dao;

import java.util.List;

import died.ejemplos.dominio.DetallesInsumoSolicitado;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Pedido;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public interface PedidoDao {
	public Pedido saveOrUpdate(Pedido p);
//	public Pedido buscarPorId(Integer id);
	//public void borrar(Integer id);
	public List<Pedido> buscarTodos(List<Insumo> listaInsumos);
	public List<DetallesInsumoSolicitado> buscarTodosInsumosSolicitados();
	public List<Pedido> busqueda(String condicionesConsulta);


}
