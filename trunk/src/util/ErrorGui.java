package util;

public enum ErrorGui {

	OK(0, "OK"), 
	ERROR_TRANSACCION(1, "Error en la transacci�n"), 
	ERROR_VALIDACION(2, "Por favor, verifique que todos los campos \nest�n completados de forma correcta."),
	ERROR_PASSWORD(3, "No coincide la contrase�a con la confirmaci�n de la misma."),
	ERROR_RANGO_FECHA(4, "Rango de fechas inv�lido.");

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
