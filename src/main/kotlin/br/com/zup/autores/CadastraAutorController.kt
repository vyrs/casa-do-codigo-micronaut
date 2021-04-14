package br.com.zup.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(val autorRepository: AutorRepository,
                              val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra(@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {

        println("Requisicao => ${request}")

        val enderecoResponse: HttpResponse<EnderecoResponse> = enderecoClient.consulta(request.cep)

        val autor = request.paraAutor(enderecoResponse.body()!!)
        autorRepository.save(autor)
        println("Autor => ${autor.nome}")

        val uri = UriBuilder.of("/autores/{id}")
                            .expand(mutableMapOf("id" to autor.id))

        return HttpResponse.created(uri)
    }
}