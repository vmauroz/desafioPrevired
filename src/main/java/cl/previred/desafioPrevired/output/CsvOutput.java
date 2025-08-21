package cl.previred.desafioPrevired.output;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import cl.previred.desafioPrevired.respository.dto.EmpleadoDto;

/**
 * Funcionalidades para generar los reportes en formato CSV.
 */
public class CsvOutput {

	/**
	 * Genera un archivo con los datos de los registros de datos validos.
	 * Muestra el total de registros válidos. 
	 * 
	 * @param nomina         ArrayList<EmpleadoDto> con los registro para generar la
	 *                       salida
	 * @param outputFileName Nombre absoulto del archivo de salida.
	 */
	public void generateCsvValidRecords(ArrayList<EmpleadoDto> nomina, String outputFileName) {
		String headers = "Nombre, Apellido, RUT, Cargo, SalarioBase, Bonos, BonoAntiguedad, Descuentos, SalarioFinal, FechaIngreso";

		int empleadosValidos = 0;
		long salarioFinalTotal = 0;
		int antiguedadTotal = 0;

		try (FileWriter outputFile = new FileWriter(outputFileName)) {
			// Agrega cabecera
			outputFile.append(headers);
			outputFile.append("\n"); // Salto de linea

			// Escribir datos
			for (EmpleadoDto empleadoDto : nomina) {
				if (empleadoDto.isEmpValido()) {
					outputFile.append(empleadoDto.getEmpNombre());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpApellido());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpRut());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpCargo());
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpSalarioBase()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpBonos()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpBonoAntiguedad()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpDescuentos()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpSalarioFinal()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpFechaIngreso()));
					outputFile.append("\n");

					salarioFinalTotal = salarioFinalTotal + empleadoDto.getEmpSalarioFinal();
					
					if ( empleadoDto.getEmpSalarioFinal() <0 ) {
					System.out.println("pasó");	
					}
					
					antiguedadTotal = antiguedadTotal + empleadoDto.getEmpAntiguedad();
					empleadosValidos++;
				}
			}

			System.out.println("Archivo CSV creado exitosamente: " + outputFileName);
			System.out.println("-------------------------------- ");
			System.out.println("Total registros empleados válidos: " + empleadosValidos);
			System.out.println("Promedio de Salario final : " + (salarioFinalTotal / empleadosValidos));
			System.out.println("Antiguedad Promedio de los empleados: " + antiguedadTotal / empleadosValidos);
			System.out.println("-------------------------------------");

		} catch (IOException e) {
			System.err.println("Error al crear el archivo CSV: " + e.getMessage());
		}
	}

	/**
	 * Genera un archivo con los datos de los registros de datos no validos.
	 * 
	 * @param nomina         ArrayList<EmpleadoDto> con los registro para generar la
	 *                       salida
	 * @param outputFileName Nombre absoulto del archivo de salida.
	 */
	public void generateCsvInValidRecords(ArrayList<EmpleadoDto> nomina, String outputFileName) {
		String headers = "Nombre, Apellido, RUT, Cargo, SalarioBase, Bonos, Descuentos, FechaIngreso, MotivoError";

		int empleadosNoValidos = 0;

		try (FileWriter outputFile = new FileWriter(outputFileName)) {
			// Agrega cabecera
			outputFile.append(headers);
			outputFile.append("\n"); // Salto de linea

			// Escribir datos
			for (EmpleadoDto empleadoDto : nomina) {
				if (!empleadoDto.isEmpValido()) {
					outputFile.append(empleadoDto.getEmpNombre());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpApellido());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpRut());
					outputFile.append(",");
					outputFile.append(empleadoDto.getEmpCargo());
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpSalarioBase()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpBonos()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpDescuentos()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpFechaIngreso()));
					outputFile.append(",");
					outputFile.append(String.valueOf(empleadoDto.getEmpCommentario()));
					outputFile.append("\n");

					empleadosNoValidos++;
				}
			}

			System.out.println("Archivo CSV creado exitosamente: " + outputFileName);
			System.out.println("Total registros de empleados NO válidos: " + empleadosNoValidos);

		} catch (IOException e) {
			System.err.println("Error al crear el archivo CSV: " + e.getMessage());
		}
	}
}
