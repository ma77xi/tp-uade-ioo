package util;

public class RespuestaGui {

	private ErrorGui tipoRespuesta;
	private String mensaje;

	public RespuestaGui(ErrorGui tipoRespuesta, String mensaje) {
		this.tipoRespuesta = tipoRespuesta;
		this.mensaje = mensaje;
	}

	public RespuestaGui(ErrorGui tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public ErrorGui getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(ErrorGui tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
