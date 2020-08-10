package tpdied2020.gui.auxiliar;

public class Arista<T> {
	private Vertice<T> inicio;
	private Vertice<T> fin;
	private Double hs;
	private Double km;
	private Double max;

	public Arista(){
	} 
	
	public Double getHs() {
		return hs;
	}

	public void setHs(Double hs) {
		this.hs = hs;
	}

	public Double getKm() {
		return km;
	}

	public void setKm(Double km) {
		this.km = km;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Arista(Vertice<T> ini,Vertice<T> fin){
		this();
		this.inicio = ini;
		this.fin = fin;
	}

	public Arista(Vertice<T> ini,Vertice<T> fin,Double hss,Double kmt, Double maxx){
		this(ini,fin);
		this.km = kmt;
		this.hs = hss;
		this.max = maxx;
	}
	
	public Vertice<T> getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice<T> inicio) {
		this.inicio = inicio;
	}
	
	public Vertice<T> getFin() {
		return fin;
	}
	
	public void setFin(Vertice<T> fin) {
		this.fin = fin;
	}

	@Override
	public String toString() {
		return "( "+this.inicio.getValor()+" --> "+this.fin.getValor()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Arista<?>) && ((Arista<?>)obj).getHs().equals(this.hs) && ((Arista<?>)obj).getKm().equals(this.km) && ((Arista<?>)obj).getMax().equals(this.max); 
	}
}
