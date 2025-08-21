package cl.previred.desafioPrevired.respository;

import java.util.ArrayList;

import cl.previred.desafioPrevired.commons.CommonsUtils;

/**
 * Repositorio con las funcionalidades para acceder a los datos contenidos en un
 * archivo CVS
 * 
 */
public class CvsRepository {
	// Nombre de archivo de entrada
	private String cvsPath = "src/main/resources/input/empleados.csv";

	/**
	 * Extrae los registros de la nomina de empleados desde un archivo CSV
	 * 
	 * @return ArrayList<String> con los registros obtenidos desde el CSV
	 */
	public ArrayList<String> getCsvRecords() {
		return CommonsUtils.readTextFile(cvsPath);
	}
}
