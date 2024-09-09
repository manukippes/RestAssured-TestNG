package com.kimatesting.qa.utiles.datosExcel;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class FabricaDatosExcel {
	
	@DataProvider(name="datosCervecerias")
	public Object [][] getDatosGetUsuarios() throws IOException{
		Object dataprovider[][] = generarArrayDeDatos("src/test/resources/datos/datosCerveceria.xlsx", "getCerveza");
		return dataprovider;
	}
	
	public Object[][] generarArrayDeDatos(String rutaArchivoExcel, String hojaMetodo) throws IOException {
		ManejoExcel excel = new ManejoExcel(rutaArchivoExcel, hojaMetodo);
		int numeroFilas = excel.getCantidadFilas();
		int numeroColumnas = excel.getCantidadColumnas();
		Object datos[][] = new Object [numeroFilas-1][numeroColumnas]; 
		for (int i = 1; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				datos [i-1][j] = excel.getDatoCelda(i, j);
			}
		}
		return datos;
	}
}
