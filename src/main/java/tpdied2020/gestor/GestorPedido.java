package tpdied2020.gestor;


import java.util.List;

import tpdied2020.dao.PedidoDao;
import tpdied2020.dao.PedidoDaoMysql;
import tpdied2020.dominio.Insumo;
import tpdied2020.dominio.Pedido;

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
