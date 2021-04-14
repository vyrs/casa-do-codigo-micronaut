package br.com.zup.autores

import javax.persistence.Embeddable


@Embeddable
class Endereco(enderecoResponse: EnderecoResponse,
               val numero: String) {
    val cep = enderecoResponse.cep
    val cidade = enderecoResponse.localidade
    val estado = enderecoResponse.uf

}
