/**
 * Biblioteca.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 30,
    },
    ciudad: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 30,
    },
    fechaFundacion: {
      required: true,
      type: 'string'
    },
    numSedes: {
      required: true,
      type: 'number',
      min: 0
    },
    publica:{
      required: true,
      type: 'boolean'
    },
    libroDeBiblioteca: {     // Nombre atributo de la relación
      collection: 'libro', // Nombre del modelo a relacionar
      via: 'idBiblioteca'        // Nombre del campo a hacer la relacion
    },
  },
};

