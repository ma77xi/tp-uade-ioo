package util;

public enum RespuestaSistema {

	OK(0, "OK"), 
	CLIENTE_INEXISTENTE(1, "No se pudo encontrar el Cliente en el sistema."), 
	CLIENTE_DUPLICADO(2, "El Cliente ya existe en el sistema."), 
	CLIENTE_CON_RESERVA(8, "Cliente tiene Reservas"), 
	MODELO_INEXISTENTE(3, "No se pudo encontrar el Modelo en el sistema."), 
	MODELO_DUPLICADO(4, "El Modelo ya existe en el sistema."), 
	AUTOMOVIL_INEXISTENTE(5, "No se pudo encontrar el Automovil en el sistema"), 
	AUTOMOVIL_DUPLICADO(6, "El Automovil ya existe en el sistema"),
	AUTOMOVIL_RESERVADO(7,"Automovil Reservado"),
	USUARIO_CONTRASEÑA_INVALIDO(9,"Usuario o contraseña inválido."),
	USUARIO_DUPLICADO(10, "El usuario ya existe en el sistema"),
	NRO_RESERVA_INEXISTENTE(11, "Númer de reserva inexistente"),
	APLICA_MULTA(12, "");

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
