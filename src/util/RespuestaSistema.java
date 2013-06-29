package util;

public enum RespuestaSistema {

	OK(0, "OK"), 
	CLIENTE_INEXISTENTE(1, "Cliente inexistente."), 
	CLIENTE_DUPLICADO(2, "Cliente duplicado."), 
	CLIENTE_CON_RESERVA(8, "Cliente tiene Reservas"), 
	MODELO_INEXISTENTE(3, "Modelo inexistente."), 
	MODELO_DUPLICADO(4, "Modelo duplicado."), 
	AUTOMOVIL_INEXISTENTE(5, "Automovil inexistente"), 
	AUTOMOVIL_DUPLICADO(6, "Automovil duplicado"),
	AUTOMOVIL_RESERCADO(7,"Automovil Reservado");

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
