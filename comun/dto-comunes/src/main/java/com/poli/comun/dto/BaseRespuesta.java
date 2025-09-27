package com.poli.comun.dto;

/**
 * Clase base genérica para respuestas simples
 */
public class BaseRespuesta {

  private String mensaje;

  public BaseRespuesta() {
  }

  public BaseRespuesta(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}