package model;

public enum TipoModelo {

	ECONOMICO(1, "económico"), 
	COMPACTO(2, "compacto"), 
	PREMIUM(3, "premium"), 
	MINIVAN(4, "minivan"), 
	CAMIONETA(5, "camioneta");

	private int key;
	private String codigo;
	private String descripcion;

	public int getKey() {
		return this.key;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	TipoModelo(int key, String descripcion) {
		this.key = key;
		this.codigo = String.valueOf(key);
		this.descripcion = descripcion;
	}

}
