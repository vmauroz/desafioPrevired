package cl.previred.desafioPrevired.commons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Funcionalidades y propiedades comunes para la aplicación.  
 */
public class CommonsUtils {
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final int SALARIO_MINIMO = 400000;
	public static final int BONO_PORCENTAJE_MAXIMO = 50;

	// Mensajes
	public static final String MENSAJE_SALARIO_BAJO_MINIMO = "Salario base es inferior al salarioa mínimo";
	public static final String MENSAJE_DESCUENTO_SUPERIOR_SALARIO_BASE= "Los descuentos superan el Salario Base";
	public static final String MENSAJE_BONO_MAYOR_SALARIO = "El monto de bonos supera el porcentaje máximo sobre el salario base";
	public static final String MENSAJE_FECHA_INGRESO_FUTURO = "La fecha de ingreso es a futuro respecto a la fecha de hoy";
	public static final String MENSAJE_FECHA_INGRESO_INVALIDA = "La fecha de ingreso no es válida";
	public static final String MENSAJE_RUT_DUPLICADO = "Rut repetido";
	public static final String MENSAJE_ERROR_FORMATO_NUMERICO = "Se encontró un error en el formato numérico de uno de los campos";

	public static final String MENSAJE_ERROR_CANTIDAD_CAMPOS = "Error, la cantidad de campos no coincide";

	/**
	 * Extrae los registros o líneas de datos desde un archivo de texto
	 * 
	 * @param textFilePath Ruta absoluta del archivo de texto fuente
	 * @return Una lista con los los registros obtenidos desde el archivo de texto
	 *         fuente
	 */
	public static ArrayList<String> readTextFile(String textFilePath) {
		ArrayList<String> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
			String textRow;

			while ((textRow = br.readLine()) != null) {
				records.add(textRow);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}

	/**
	 * Convierte un string en formato de fecha determinadox a un objeto LocalDate
	 * 
	 * @param stringDate String con formato de fecha válido
	 * @returnUn Si la fecha es válida retorna un objeto LocalDate, de lo contrario
	 *           retorna un null
	 */
	public static LocalDate stringToLocalDate(String stringDate, String dateFormat) {

		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(dateFormat);

		try {
			return LocalDate.parse(stringDate, dateTimeFormat);

		} catch (DateTimeParseException e) {
			return null;
		}
	}

}
