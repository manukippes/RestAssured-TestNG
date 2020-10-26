package ar.com.manutesting.utiles.datosExcel;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ManejoExcel {
	
	private Workbook libro;
	private XSSFSheet hoja;
	
	public ManejoExcel(String rutaExcel, String nombreHoja) throws IOException {
		libro = new XSSFWorkbook(rutaExcel);
		hoja = (XSSFSheet) libro.getSheet(nombreHoja);
	}
	
	public int getCantidadFilas() {
		return hoja.getPhysicalNumberOfRows();
	}
	
	public int getCantidadColumnas() {
		return hoja.getRow(0).getPhysicalNumberOfCells();
	}
	
	public Object getDatoCelda(int fila, int columna) throws IOException {
		DataFormatter formatoCelda = new DataFormatter();
		Object celda = formatoCelda.formatCellValue(hoja.getRow(fila).getCell(columna));
		libro.close();
		return celda;
	}
}
