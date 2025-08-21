package cl.previred.desafioPrevired;

import java.util.ArrayList;

import cl.previred.desafioPrevired.output.CsvOutput;
import cl.previred.desafioPrevired.respository.dto.EmpleadoDto;
import cl.previred.desafioPrevired.service.CsvService;

/**
 * Aplicación que permite procesar un archivo CSV con los datos de los empleados, validar y calcular los salrios
 * para luego generar los reportes de empleados válidos y no válidos
 * 
 */
public class DesafioApplication {

	public static void main(String[] args) {
		DesafioApplication desafioApp = new DesafioApplication();
		desafioApp.startProcess();
	}
	
	/**
	 * Inicializar el proceso de extración de datos desde el CSV y generar las salidas.
	 */
	public void startProcess() {
		CsvService csvService = new CsvService(); // Servicio para obtener la data desde el archivo CSV
		CsvOutput csvOutput = new CsvOutput(); // Servivio para generar las salidas a archivos
		
		//Extrae los datos desde el archivo Csv
		ArrayList<EmpleadoDto> listaEmpleados = csvService.getCsvRecords();
		
		// Extrae la información desde el CSV y genera la salida de registro válidos y salarios calculados
		csvOutput.generateCsvValidRecords( listaEmpleados, "src/main/resources/output/empleadosValidos.csv");

		// Extrae la información desde el CSV y genera la salida de registro NO válidos
		csvOutput.generateCsvInValidRecords( listaEmpleados, "src/main/resources/output/empleadosNoValidos.csv");

		
		System.out.println( "Proceso finalizado " );
		
	}
}
