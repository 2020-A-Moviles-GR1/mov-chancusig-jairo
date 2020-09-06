/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {
  tableName: 'epn_usuario',
  attributes: {
      cedula: {//nombre del atributo
        type: 'string',
        required: true,
        columnName: 'epn_cedula',
        unique: true,
        minLength: 10,
        maxLength: 25
    },

    nombre:{
        type: 'string',
        minLength: 3,
      required: true //Por defecto es false
    },
    correo:{
        type:'string',
      isEmail: true
    },
    estadoCivil:{
        type: 'string',
      isIn: ['Soltero', 'Casado', 'Divorciado', 'Viudo', 'Unión libre'],
      defaultsTo: 'Soltero' //Valor por Defecto
    },
    password:{
        type: 'string',
      regex: /^[a-zA-Z]\w{3,14}$/
    },

    pokemons: { //one to many (plural)
        collection: 'pokemon', //referencia al modelo
      via: 'usuario' //Nombre de la Foreign Key
    }

  },

};

