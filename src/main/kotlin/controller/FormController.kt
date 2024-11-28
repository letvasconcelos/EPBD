package controller

import AcomodacaoId
import entities.AcomodacaoEntity
import entities.HospedeEntity
import entities.ReservaEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import repositories.AcomodacaoRepository
import repositories.HospedeRepository
import repositories.ReservaRepository
import java.math.BigDecimal
import java.time.LocalDateTime

@Controller
class ormController(
    private val acomodacaoRepository: AcomodacaoRepository,
    private val hospedeRepository: HospedeRepository,
    private val reservaRepository: ReservaRepository
) {

    @GetMapping("/formulario/cadastro")
    fun abrirFormularioCadastro(
        @RequestParam tipo: String,
        model: Model
    ): String {
        when (tipo) {
            "acomodacao" -> model.addAttribute("entidade", AcomodacaoEntity())
            "hospede" -> model.addAttribute("entidade", HospedeEntity())
            "reserva" -> model.addAttribute("entidade", ReservaEntity())
        }
        model.addAttribute("tipo", tipo) // Propague o valor de "tipo"
        return "formulario-cadastro"
    }


    @PostMapping("/cadastrar")
    fun cadastrar(
        @RequestParam tipo: String,
        @RequestParam params: Map<String, String>
    ): String {
        when (tipo) {
            "acomodacao" -> {
                // Tratar a chave composta separadamente
                val numeroAcomodacao = params["numeroAcomodacao"]?.toIntOrNull()
                    ?: throw IllegalArgumentException("Número Acomodação inválido")
                val cnpjHotel = params["cnpjHotel"] ?: throw IllegalArgumentException("CNPJ inválido")

                val id = AcomodacaoId(numeroAcomodacao = numeroAcomodacao, cnpjHotel = cnpjHotel)

                val acomodacao = AcomodacaoEntity(
                    id = id, // Chave composta
                    politicaDeUso = params["politicaDeUso"] ?: "",
                    capacidade = params["capacidade"]?.toIntOrNull() ?: 0,
                    ultimaLimpeza = params["ultimaLimpeza"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
                    precoBase = params["precoBase"]?.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                    tipoAcomodacao = params["tipo"] ?: ""
                )
                acomodacaoRepository.save(acomodacao)
            }
            "hospede" -> {
                val hospede = HospedeEntity(
                    cpf = params["cpf"] ?: "",
                    pontosAcumulados = params["pontosAcumulados"]?.toIntOrNull() ?: 0
                )

                hospedeRepository.save(hospede)

            }
            "reserva" -> {
                val reserva = ReservaEntity(
                    idReserva = params["idReserva"] ?: "",
                    dataInicio = params["dataInicio"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
                    dataFim = params["dataFim"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
                    pago = params["pago"]?.toBoolean() ?: false,
                    valorDiarias = params["valorDiarias"]?.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                    numeroAcomodacao = params["numeroAcomodacao"]?.toIntOrNull() ?: 0,
                    cnpjHotel = params["cnpjHotel"] ?: "",
                    cpfHospede = params["cpfHospede"] ?: "",
                    cpfResponsavel = params["cpfResponsavel"] ?: ""
                )
                reservaRepository.save(reserva)
            }
        }
        return "redirect:/home?tipo=${tipo}"
    }




    @GetMapping("/home")
    fun abrirHome(
        @RequestParam(required = false, defaultValue = "acomodacao") tipo: String,
        model: Model
    ): String {
        model.addAttribute("tipo", tipo) // Adicionado para propagar o valor de "tipo"
        when (tipo) {
            "acomodacao" -> {
                val acomodacoes = acomodacaoRepository.findAll().map {
                    mapOf(
                        "Nº Acomodação" to it.id.numeroAcomodacao,
                        "CNPJ" to it.id.cnpjHotel,
                        "Política de Uso" to it.politicaDeUso,
                        "Capacidade" to it.capacidade,
                        "Última Limpeza" to it.ultimaLimpeza,
                        "Preço Base" to it.precoBase,
                        "Tipo" to it.tipoAcomodacao
                    )
                }
                model.addAttribute("titulo", "Entidade-Acomodação")
                model.addAttribute("colunas", listOf("Nº Acomodação", "CNPJ", "Política de Uso", "Capacidade", "Última Limpeza", "Preço Base", "Tipo"))
                model.addAttribute("dados", acomodacoes)
            }
            "hospede" -> {
                val hospedes = hospedeRepository.findAll().map {
                    mapOf(
                        "CPF" to it.cpf,
                        "Pontos Acumulados" to it.pontosAcumulados
                    )
                }
                model.addAttribute("titulo", "Entidade-Hóspede")
                model.addAttribute("colunas", listOf("CPF", "Pontos Acumulados"))
                model.addAttribute("dados", hospedes)
            }
            "reserva" -> {
                val reservas = reservaRepository.findAll().map {
                    mapOf(
                        "ID Reserva" to it.idReserva,
                        "Data Início" to it.dataInicio,
                        "Data Fim" to it.dataFim,
                        "Pago" to it.pago,
                        "Valor Diárias" to it.valorDiarias,
                        "Nº Acomodação" to it.numeroAcomodacao,
                        "CNPJ Hotel" to it.cnpjHotel,
                        "CPF Hóspede" to it.cpfHospede,
                        "CPF Responsável" to it.cpfResponsavel
                    )
                }
                model.addAttribute("titulo", "Entidade-Reserva")
                model.addAttribute("colunas", listOf("ID Reserva", "Data Início", "Data Fim", "Pago", "Valor Diárias", "Nº Acomodação", "CPF Hóspede", "CPF Responsável"))
                model.addAttribute("dados", reservas)
            }
        }
        return "home"
    }

    @GetMapping("/formulario/edicao/{numeroAcomodacao}/{cnpjHotel}")
    fun abrirFormularioEdicao(
        @PathVariable numeroAcomodacao: Int,
        @PathVariable cnpjHotel: String,
        @RequestParam(required = false, defaultValue = "acomodacao") tipo: String,
        model: Model
    ): String {
        val id = AcomodacaoId(numeroAcomodacao, cnpjHotel)
        val acomodacao = acomodacaoRepository.findById(id).orElseThrow {
            IllegalArgumentException("Acomodação com ID $id não encontrada")
        }
        model.addAttribute("entidade", acomodacao)
        model.addAttribute("tipo", tipo)
        return "formulario-edicao"
    }

    @PostMapping("/editar/{numeroAcomodacao}/{cnpjHotel}")
    fun editar(
        acomodacao: AcomodacaoEntity,
        @PathVariable numeroAcomodacao: Int,
        @PathVariable cnpjHotel: String
    ): String {
        val id = AcomodacaoId(numeroAcomodacao, cnpjHotel)
        acomodacao.id = id

        acomodacaoRepository.save(acomodacao)
        return "redirect:/home"
    }
}