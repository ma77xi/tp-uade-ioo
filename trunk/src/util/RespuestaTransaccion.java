package util;

public class RespuestaTransaccion {

	private RespuestaSistema tipoRespuesta;
	private String mensaje;

	public RespuestaTransaccion(RespuestaSistema tipoRespuesta, String mensaje) {
		this.tipoRespuesta = tipoRespuesta;
		this.mensaje = mensaje;
	}
	
	public RespuestaTransaccion(RespuestaSistema tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public RespuestaSistema getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(RespuestaSistema tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
