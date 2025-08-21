package cl.previred.desafioPrevired.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;

import cl.previred.desafioPrevired.commons.CommonsUtils;
import cl.previred.desafioPrevired.respository.CvsRepository;
import cl.previred.desafioPrevired.respository.dto.EmpleadoDto;

public class CsvService {
	// Repositorio para obtener los registros de la nómina
	private CvsRepository cvsRepo = new CvsRepository();

	// Delimitador
	private String cvsDelimiter = ",";

	/**
	 * Extrae los registros de la nomina de empleados desde un archivo CSV y por
	 * cada uno genera un objeto EmpleadoDto con los datos validados.
	 * 
	 * @return Objeto EmpleadoDto con la nómina de empleados.
	 */
	public ArrayList<EmpleadoDto> getCsvRecords() {
		ArrayList<String> recordList = cvsRepo.getCsvRecords();
		ArrayList<EmpleadoDto> empleadosList = new ArrayList<>();

		if (recordList.size() < 2) {
			return empleadosList;
		}

		recordList.remove(0);

		// para verificar rut repetidos
		HashMap<String, String> listaRut = new HashMap();

		recordList.forEach(textRow -> {
			EmpleadoDto empDto = delimitedToEmpleadoDto(textRow);

			// valida RUT repetido
			if (listaRut.get(empDto.getEmpRut()) != null) {
				String comentario = empDto.getEmpCommentario().concat(" - " + CommonsUtils.MENSAJE_RUT_DUPLICADO);
				empDto.setEmpCommentario(comentario);
				empDto.setEmpValido(false);
			}
			else {
				listaRut.put(empDto.getEmpRut(), empDto.getEmpRut());
			}

			empleadosList.add(empDto);
		});
	

		return empleadosList;
	}

	/**
	 * Crea un nuevo objeto EmpleadoDto a partir de los datos delimitados contenidos
	 * en String textRow, realiza loas validaciones correspondientes para determinar
	 * si el registro es válido o no.
	 * 
	 * @param textRow texto delimitado con los datos del empleado
	 * @return
	 */
	private EmpleadoDto delimitedToEmpleadoDto(String textRow) {
		EmpleadoDto empleadoDto = new EmpleadoDto();

		empleadoDto.setEmpRegistroFuente(textRow); // guarda el registro original del archivo

		String comentario = ""; // contiene los mensajes de error de validaciones

		String[] splittedRow = textRow.split(cvsDelimiter);

		try {
			if (splittedRow.length == 8) {
				empleadoDto.setEmpNombre(splittedRow[0]);
				empleadoDto.setEmpApellido(splittedRow[1]);
				empleadoDto.setEmpRut(splittedRow[2]);
				empleadoDto.setEmpCargo(splittedRow[3]);
				empleadoDto.setEmpSalarioBase(Integer.parseInt(splittedRow[4]));
				empleadoDto.setEmpBonos(Integer.parseInt(splittedRow[5]));
				empleadoDto.setEmpDescuentos(Integer.parseInt(splittedRow[6]));
				empleadoDto.setEmpFechaIngreso(
						CommonsUtils.stringToLocalDate(splittedRow[7], CommonsUtils.DATE_FORMAT_YYYY_MM_DD));

				/* Validaciones */

				if (empleadoDto.getEmpSalarioBase() < CommonsUtils.SALARIO_MINIMO) {
					comentario = comentario.concat(" - " + CommonsUtils.MENSAJE_SALARIO_BAJO_MINIMO);
				}
				
				// Valida que los descuentos no superen el salario base.
				if (empleadoDto.getEmpDescuentos() > empleadoDto.getEmpSalarioBase()) {
					comentario = comentario.concat(" - " + CommonsUtils.MENSAJE_DESCUENTO_SUPERIOR_SALARIO_BASE);
				}

				// Calcula porcentaje de bono para compararlos con el salario base
				double porcentajeBono = (double) (empleadoDto.getEmpBonos() / empleadoDto.getEmpSalarioBase()) * 100;

				if (porcentajeBono > CommonsUtils.BONO_PORCENTAJE_MAXIMO) {
					comentario = comentario.concat(" - " + CommonsUtils.MENSAJE_BONO_MAYOR_SALARIO + " (") + CommonsUtils.BONO_PORCENTAJE_MAXIMO + "%)";
				}

				// Si la fecha es es inválida
				if (empleadoDto.getEmpFechaIngreso() == null) {
					comentario = comentario.concat(" - " + CommonsUtils.MENSAJE_FECHA_INGRESO_INVALIDA);
				}
				// valida fecha de ingreso futura
				else if (empleadoDto.getEmpFechaIngreso().isAfter(LocalDate.now())) {
					comentario = comentario.concat(" - " + CommonsUtils.MENSAJE_FECHA_INGRESO_FUTURO);
				}
				// si las fechas son correctas entonces calcula bonificación por antiguedad y
				// salario final
				else if (empleadoDto.getEmpSalarioBase() > 0) {		
					// Calcula antiguedad
					Period antiguedad = Period.between(empleadoDto.getEmpFechaIngreso(), LocalDate.now());

					// Calculo el bono por antiguedad
					int porcentaje = (antiguedad.getYears() > 5) ? 10 : (antiguedad.getYears() >= 3) ? 5 : 0;	
					int bono = (int) (porcentaje * empleadoDto.getEmpSalarioBase() / 100);
		
					empleadoDto.setEmpAntiguedad( antiguedad.getYears());
					empleadoDto.setEmpBonoAntiguedad( bono);
					empleadoDto.setEmpSalarioFinal(empleadoDto.getEmpSalarioBase() + empleadoDto.getEmpBonos()
							+ empleadoDto.getEmpBonoAntiguedad() - empleadoDto.getEmpDescuentos());
				}

				empleadoDto.setEmpCommentario(comentario);
				empleadoDto.setEmpValido(comentario.isEmpty());
			} else {
				comentario.concat(" - " + CommonsUtils.MENSAJE_ERROR_CANTIDAD_CAMPOS);
				empleadoDto.setEmpValido(false);
			}
		} catch (NumberFormatException e) {
			comentario.concat(" - " + CommonsUtils.MENSAJE_ERROR_FORMATO_NUMERICO);
			empleadoDto.setEmpValido(false);
		}

		return empleadoDto;
	}
}
