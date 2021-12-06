/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
/* global fetch */

'use strict';
const eNumero = (numero) => /^[0-9]+$/.test(numero);
const cepValido = (cep) => cep.length === 8 && eNumero(cep);

const preencherFormulario = (value) => {
    document.getElementById('menu:rua').value = value.logradouro;
    document.getElementById('menu:cidade').value = value.localidade;
    document.getElementById('menu:bairro').value = value.bairro;
    document.getElementById('menu:estado').value = value.uf;
};

const buscarCep = async (cep) => {
    const url = `http://viacep.com.br/ws/${cep}/json/`;
    if (cepValido(cep)) {
        const dados = await fetch(url);
        const endereco = await dados.json();
        if (endereco.hasOwnProperty('erro')) {
            document.getElementById('menu:rua').value = 'CEP não encontrado!';
            document.getElementById('menu:cidade').value = 'CEP não encontrado!';
            document.getElementById('menu:bairro').value = 'CEP não encontrado!';
            document.getElementById('menu:estado').value = 'CEP não encontrado!';

        } else {
            console.log(endereco);
            preencherFormulario(endereco);
        }
    } else {
        document.getElementById('menu:rua').value = 'CEP incorreto!';
        document.getElementById('menu:cidade').value = 'CEP incorreto!';
        document.getElementById('menu:bairro').value = 'CEP incorreto!';
        document.getElementById('menu:estado').value = 'CEP incorreto!';
    }
};