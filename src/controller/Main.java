package controller;

import util.RespuestaTransaccion;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AlquilerAutos controller = new AlquilerAutos();
		
		RespuestaTransaccion respuesta;
		
		respuesta = controller.altaClienteWeb(null, null, null, null, null, 11111L, null, null, "111", "222");
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
		respuesta = controller.altaCliente("Juan", null, null, null, null, 22222L, null, null);
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
		respuesta = controller.altaCliente("Cholo", null, null, null, null, 33333L, null, null);
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
		respuesta = controller.altaClienteWeb(null, null, null, null, null, 44444L, null, null, "aaa", "bbb");
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
		respuesta = controller.altaClienteWeb(null, null, null, null, null, 55555L, null, null, "aaa", "bbb");
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
		respuesta = controller.altaCliente("Cholo", null, null, null, null, 33333L, null, null);
		System.out.println(respuesta.getTipoRespuesta() + ": " + respuesta.getMensaje());
		
	}

}
