// https://app.zenuml.com/
Server.meterAcaLaURLDeLaApi() {
  medicamentoDto = medicamentoController.getById(id) {
    medicamentoDto = medicamentoService.findById(id) {
      medicamento = medicamentoRepository.findById(id) {
        medicamento = Database.findById(id)
        return medicamento
      }
      medicamentoDto = MedicamentoDto.from(medicamento) {
        medicamentoDto = new MedicamentoDto()
        id = medicamento.getId()
        medicamentoDto.setId(id)
        nombre = medicamento.getNombre()
        medicamentoDto.setNombre(nombre)
        laboratorio = medicamento.getLaboratorio()
        medicamentoDto.setLaboratorio(laboratorio)
        precio = medicamento.getPrecio()
        medicamentoDto.setPrecio(precio)
        if(medicamentoEsCombo) {
          //medicamento instanceof ComboMedicamentos (la herramienta que usamos para generar el diagrama <br>
          //no soporta poner la condicion directamente)
          medicamentos = medicamento.getMedicamentos()
          medicamentoDto.setMedicamentos(medicamentos)
        }
        return medicamentoDto
      }
      return medicamentoDto
    }
    return medicamentoDto
  }
  return medicamentoDto
}
