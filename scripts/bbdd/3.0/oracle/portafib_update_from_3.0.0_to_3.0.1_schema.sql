
--######################################################################
--##### 04/10/2024 Quins Revisors mostrar al flux de firmes: revisors globals, revisors de destinatari o tots dos.#895
--######################################################################

ALTER TABLE pfi_usuariaplicacio
  ADD COLUMN tipusrevisors number(1,0) DEFAULT 0;
COMMENT ON COLUMN pfi_usuariaplicacio.tipusrevisors IS 'En les consultes que requereixen revisors, indica quin tipus de revisors seleccionarà:
    * true: revisors globals
    * false: revisors de destinatari
    * null: revisors globals i revisors de destinatari';



