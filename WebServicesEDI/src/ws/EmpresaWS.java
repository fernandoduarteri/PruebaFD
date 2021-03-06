package ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import Utilidades.Constantes;
import model.Empresa;
import model.ObjectReturn;
import services.EmpresaServices;
@Path("Empresa")
public class EmpresaWS {
	
	@Path("/Crear")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String CrearDepartamento(String strData) {
		ObjectReturn objReturn = new ObjectReturn();
		String resultado = "";
		Gson objJSON = new Gson();
		try {
			Empresa objEmpresa = objJSON.fromJson(strData, Empresa.class);
			EmpresaServices objEmpresaService = new EmpresaServices();
			objReturn.setData(objEmpresa);
			objEmpresaService.crear(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}
			
			resultado = objJSON.toJson(objReturn);
			return resultado;
		} catch (Exception e) {
			return resultado;
		}

	}
	
	
	
	@Path("/Actualizar")
	@POST
	@Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String ActualizarDepartamento(String strData) {
		ObjectReturn objReturn = new ObjectReturn();
		String resultado = "";
		Gson objJSON = new Gson();
		JsonObject objJsonAux = new	JsonObject();
		try {
			Empresa objEmpresa = objJSON.fromJson(strData, Empresa.class);
			EmpresaServices objEmpresaService = new EmpresaServices();
			objReturn.setData(objEmpresa);
			objEmpresaService.actualizar(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}
			objJsonAux.addProperty("Result", "OK");
			resultado = objJSON.toJson(objJsonAux);
			return resultado;
		} catch (Exception e) {
			objJsonAux.addProperty("Result", "ERROR");
			objJsonAux.addProperty("Message", e.getMessage());
			resultado = objJSON.toJson(objJsonAux);
			return resultado;
		}

	}
	
	
	@Path("/Lista")
	@GET
	@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
	public String getEmpresa() {
		ObjectReturn objReturn = new ObjectReturn();
		String resultado = "";
		Gson objJSON = new Gson();
		JsonObject objJsonAux = new	JsonObject();
		try {
			EmpresaServices objEmpresaService = new EmpresaServices();
			objEmpresaService.getall(objReturn);
			if (!objReturn.getExito()) {
				throw new Exception(objReturn.getMensaje());
			}
			resultado = objJSON.toJson(objReturn);
			return resultado;
		} catch (Exception e) {
			objReturn.setData("");
			objReturn.setMensaje(e.getMessage());
			objReturn.setExito(Constantes.FLAG_EXITO_FALLA);
			objReturn.setTotal(0);
			resultado = objJSON.toJson(objReturn);
			return resultado;
		}

	}
	
	
	
	
	

}
