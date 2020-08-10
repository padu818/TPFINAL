package tpdied2020.dao;


import java.util.List;

import tpdied2020.dominio.Planta;
import tpdied2020.dominio.Ruta;
import tpdied2020.gui.auxiliar.GrafoPlanta;

public interface PlantaDao {
	public Planta saveOrUpdate(Planta c);
	public Planta buscarPorId(Integer id);
	public List<Planta> buscarTodos();
	public Ruta saveOrUpdate(Ruta r);
	public List<Ruta> buscarTodaRuta();
	public void borrar(Integer origen, Integer destino);
	public GrafoPlanta armarGrafo();
}
