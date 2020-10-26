package ar.com.manutesting.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ar.com.manutesting.utiles.datosExcel.FabricaDatosExcel;
import ar.com.manutesting.utiles.soporte.MetodosBasicos;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Suite RestAssured-TestNG-Challenge")
@Feature("Test servicio Get - Listados de cervecerias")
public class GetBreweries {
	
	private Logger log = LoggerFactory.getLogger(GetBreweries.class);
	private MetodosBasicos metodosBasicos;
	
	@BeforeMethod
	public void setearURL() {
		metodosBasicos = new MetodosBasicos();
	}
	
	@Test(dataProvider = "datosCervecerias", dataProviderClass = FabricaDatosExcel.class, priority=1, description="Validar servicio GET de cervecerías")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Validar servicio GET de cervecerías")	
	@Story("Test servicio breweries")
	public void getCervecerias(String texto, String name, String state, String id, String street, String phone) throws Exception {
		JSONArray listadoCervecerias = obtenerCerveceriasSegunTexto(texto);
		JSONArray listadoCerveceriasSegunName = obtenerCerveceriasSegunName(name, listadoCervecerias);
		JSONArray listadoCerveceriasSegunState = obtenerCerveceriasSegunState(state, listadoCerveceriasSegunName);
		validarDatosCerveceria(name, id, street, phone, listadoCerveceriasSegunState);
	}

	public JSONArray obtenerCerveceriasSegunTexto(String texto) {
		JSONArray listadoCervecerias = new JSONArray(metodosBasicos.ejecutarServicioGetQueryParam(texto));
		log.info("Listado de cervecerias segun texto "+texto+" es "+listadoCervecerias.toString());
		return listadoCervecerias;
	}
	
	public JSONArray obtenerCerveceriasSegunName(String name, JSONArray listadoCervecerias) {
		JSONArray listadoCerveceriasSegunName = new JSONArray();
		for (int i = 0; i < listadoCervecerias.length(); i++) {
			if (listadoCervecerias.getJSONObject(i).get("name").equals(name)) {listadoCerveceriasSegunName.put(listadoCervecerias.getJSONObject(i));}
		}
		log.info("Listado de cervecerias segun name "+name+" es "+listadoCerveceriasSegunName.toString());
		return listadoCerveceriasSegunName;
	}
	
	public JSONArray obtenerCerveceriasSegunState(String state, JSONArray listadoCerveceriasSegunName) {
		JSONArray listadoCerveceriasSegunState = new JSONArray();
		String idCerveceria;
		JSONObject cerveceria;
		for (int i = 0; i < listadoCerveceriasSegunName.length(); i++) {
			idCerveceria = listadoCerveceriasSegunName.getJSONObject(i).getString("id");
			cerveceria = new JSONObject(metodosBasicos.ejecutarServicioGet(idCerveceria));
			if (cerveceria.get("state").equals(state)) {listadoCerveceriasSegunState.put(cerveceria);}
		}
		log.info("Listado de cervecerias segun state "+state+" es "+listadoCerveceriasSegunState.toString());
		return listadoCerveceriasSegunState;
	}

	public void validarDatosCerveceria(String name, String id, String street, String phone, JSONArray listadoCerveceriasSegunState) {
		for (int i = 0; i < listadoCerveceriasSegunState.length(); i++) {
			metodosBasicos.verificarTextosIguales(listadoCerveceriasSegunState.getJSONObject(i).get("id").toString(), id);
			metodosBasicos.verificarTextosIguales(listadoCerveceriasSegunState.getJSONObject(i).get("name").toString(), name);
			metodosBasicos.verificarTextosIguales(listadoCerveceriasSegunState.getJSONObject(i).get("street").toString(), street);
			metodosBasicos.verificarTextosIguales(listadoCerveceriasSegunState.getJSONObject(i).get("phone").toString(), phone);
		}
	}


}
