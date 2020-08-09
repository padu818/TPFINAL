package died.ejemplos.gestor;


import java.util.Collection;
import java.util.List;

import died.ejemplos.dao.PedidoDao;
import died.ejemplos.dao.PedidoDaoMysql;
import died.ejemplos.dominio.Camion;
import died.ejemplos.dominio.Insumo;
import died.ejemplos.dominio.Pedido;
import died.ejemplos.dominio.Planta;

public class GestorPedido {
	
	private PedidoDao pedidoDao = new PedidoDaoMysql();

	public Pedido crearPedido(Pedido c) {
		return this.pedidoDao.saveOrUpdate(c);
	}

	public List<Pedido> buscarTodos(List<Insumo> listaInsumos) {
		List<Pedido>  pedidos = pedidoDao.buscarTodos(listaInsumos);
		
		return pedidos;
	}

	public List<Pedido> listarTodoPedidoProcesado(List<Insumo> ins) {
		
		return pedidoDao.buscarTodoPedidoProcesado(ins);
	}
	
}
