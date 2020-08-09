package died.ejemplos.dao;


import java.util.List;


import died.ejemplos.dominio.Planta;
import died.ejemplos.dominio.Ruta;
import died.ejemplos.gui.ayuda.GrafoPlanta;

public interface PlantaDao {
	public Planta saveOrUpdate(Planta c);
	public Planta buscarPorId(Integer id);
	public List<Planta> buscarTodos();
	public Ruta saveOrUpdate(Ruta r);
	public List<Ruta> buscarTodaRuta();
	public void borrar(Integer origen, Integer destino);
	public GrafoPlanta armarGrafo();
}
