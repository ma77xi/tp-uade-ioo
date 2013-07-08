package util;

public enum ErrorGui {

	OK(0, "OK"), 
	ERROR_TRANSACCION(1, "Error en la transacción"), 
	ERROR_VALIDACION(2, "Por favor, verifique que todos los campos \nestén completados de forma correcta."),
	ERROR_PASSWORD(3, "No coincide la contraseña con la confirmación de la misma."),
	ERROR_RANGO_FECHA(4, "Rango de fechas inválido.");

	private int key;
	private String descripcion;

	public int getKey() {
		return this.key;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	ErrorGui(int key, String descripcion) {
		this.key = key;
		this.descripcion = descripcion;
	}

}
