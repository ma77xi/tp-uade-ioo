package util;

public enum RespuestaSistema {

	OK(0, "OK"), 
	CLIENTE_INEXISTENTE(1, "Cliente inexistente."), 
	CLIENTE_DUPLICADO(2, "Cliente duplicado."), 
	MODELO_INEXISTENTE(3, "Modelo inexistente."), 
	MODELO_DUPLICADO(4, "Modelo duplicado."), 
	AUTOMOVIL_INEXISTENTE(5, "Automovil inexistente"), 
	AUTOMOVIL_DUPLICADO(6, "Automovil duplicado");

	private int key;
	private String descripcion;

	public int getKey() {
		return this.key;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	RespuestaSistema(int key, String descripcion) {
		this.key = key;
		this.descripcion = descripcion;
	}

	public static RespuestaSistema fromCode(int code) {
		for (RespuestaSistema element : RespuestaSistema.values()) {
			if (element.getKey() == code) {
				return element;
			}
		}
		return null;
	}

}
