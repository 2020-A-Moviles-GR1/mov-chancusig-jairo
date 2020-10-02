/**
 * Libro.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    precio: {
      required: true,
      type: 'number',
      min: 0
    },
    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 20,
    },
    autor: {
      type: 'string',
      required: true,
      minLength: 0,
      maxLength: 20,
    },
    genero: {
      type: 'string',
      required: true,
      minLength: 0,
      maxLength: 20,
    },
    fechaPublicacion: {
      required: true,
      type: 'string'
    },
    numPaginas: {
      required: true,
      type: 'number',
      min: 0
    },
    longitud: {
      required: true,
      type: 'string'
    },
    latitud: {
      required: true,
      type: 'string'
    },
    idBiblioteca: {         // Nombre del fk para la relación
      model: 'biblioteca',   // Nombre del modelo a relacionar (padre)
      required: true   // OPCIONAL-> Simpre se ingrese el fk
    }
  },

};
