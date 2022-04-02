Feature: WhenDo

  Background:
    Given mi aplicacion WhenDo esta abierto

  Scenario: create Nota
    When yo registro una nueva Nota
      | titulo | Pruebita               |
      | Dnota  | descripcion de la nota |
    Then la nota "Pruebita" deberia ser creado

  Scenario: Borrar Nota
    And yo registro una nueva Nota
      | titulo | Pruebita               |
      | Dnota  | descripcion de la nota |
    When selecciono Nota con titulo: "Pruebita"
    Then valido que ya no se visualice la nota