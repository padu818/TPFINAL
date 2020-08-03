package died.ejemplos.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static final String url ="jdbc:postgresql://localhost:5432/TPDIED2020";
	private static final String user="postgres";
	private static final String pass="12345";
	
	private static boolean _TABLAS_CREADAS_CAMION = false;
	private static boolean _TABLAS_CREADAS_INSUMO = false;
	private static boolean _TABLAS_CREADAS_PLANTA = false;
	private static boolean _TABLAS_CREADAS_RUTA = false;
	
	private static final String TABLA_CREATE_CAMION = 
			"CREATE TABLE  IF NOT EXISTS CAMION ( ID integer not NULL GENERATED ALWAYS AS IDENTITY, PATENTE VARCHAR(14) not NULL,"
			+ " MARCA VARCHAR(45) not NULL,MODELO VARCHAR(45) not NULL, "
			+ "KM VARCHAR(45) not NULL, COSTO_KM DECIMAL(12,2), "+
			"COSTO_HORA DECIMAL(12,2),"
			+ " IDPLANTA integer REFERENCES PLANTA(IDPLANTA),"
			+"FECHA_COMPRA DATE not NULL,"
			+ "PRIMARY KEY(ID));";
	private static final String TABLA_CREATE_INSUMO = 
			"CREATE TABLE  IF NOT EXISTS INSUMO ( IDINSUMO integer not NULL GENERATED ALWAYS AS IDENTITY, NOMBRE VARCHAR(45) not NULL,"
			+ " DESCRIPCION VARCHAR(50) not NULL,UNIDAD_MEDIDA VARCHAR(12) not NULL, "
			+ "COSTO DECIMAL(12,2), TIPO VARCHAR(45) not NULL, PESO DECIMAL(12,2), DENSIDAD DECIMAL(12,2),  "
			+ "PRIMARY KEY(IDINSUMO));";
	private static final String TABLA_CREATE_PLANTA = 
			"CREATE TABLE  IF NOT EXISTS PLANTA ( IDPLANTA integer not NULL GENERATED ALWAYS AS IDENTITY, NOMBRE VARCHAR(45) not NULL,"
			+ "PRIMARY KEY(IDPLANTA));";
	private static final String TABLA_CREATE_RUTA = 
			"CREATE TABLE  IF NOT EXISTS RUTA ( IDRUTA integer not NULL GENERATED ALWAYS AS IDENTITY, DURACIONHS DECIMAL(12,2),"
			+ "DURACIONKM DECIMAL(12,2), CANTMAXATRANSPORTAR DECIMAL(12,2),IDPLANTAORIGEN integer REFERENCES PLANTA(IDPLANTA),"
			+ "IDPLANTADESTINO integer REFERENCES PLANTA(IDPLANTA), "
			+ "PRIMARY KEY(IDRUTA));";

	private DB(){
			// no se pueden crear instancias de esta clase
	}
	
	private static void verificarCrearTablas() {
		if(!_TABLAS_CREADAS_CAMION || !_TABLAS_CREADAS_INSUMO || !_TABLAS_CREADAS_PLANTA || !_TABLAS_CREADAS_RUTA) {
			Connection conn = DB.crearConexion();
			Statement stmt = null;
			System.out.println("llege");
			try {
				stmt = conn.createStatement();
				boolean tablaPlantaCreada = stmt.execute(TABLA_CREATE_PLANTA);
				boolean tablaInsumoCreada = stmt.execute(TABLA_CREATE_INSUMO);	
				boolean tablaCamionCreada = stmt.execute(TABLA_CREATE_CAMION);
				boolean tablaRutaCreada = stmt.execute(TABLA_CREATE_RUTA);
				_TABLAS_CREADAS_CAMION = tablaCamionCreada; //VER
				_TABLAS_CREADAS_INSUMO = tablaInsumoCreada;
				_TABLAS_CREADAS_PLANTA= tablaPlantaCreada;
				_TABLAS_CREADAS_RUTA = tablaRutaCreada;
			}catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
					try {
						if(stmt!=null) stmt.close();
						if(conn!=null) conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}

	private static Connection crearConexion(){
		Connection conn=null;
		try {
			Class.forName("org.postgresql.Driver");
			conn= DriverManager.getConnection(url,user,pass);
			System.out.println("Probando bd");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println("CONEXION LOGRADA");
		return conn;
	}
	
	public static Connection getConexion() {
		verificarCrearTablas();
		return crearConexion();
	}
	
	public static void main(String[] args) {
		DB.crearConexion();
		DB.getConexion();
	}
}
