package services

import AcomodacaoId
import entities.AcomodacaoEntity
import entities.HospedeEntity
import entities.HotelEntity
import entities.ReservaEntity
import enums.TipoEntidade
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import repositories.AcomodacaoRepository
import repositories.HospedeRepository
import repositories.HotelRepository
import repositories.ReservaRepository
import java.math.BigDecimal
import java.time.LocalDateTime

@Service
class CadastroServiceImpl (
    private val acomodacaoRepository: AcomodacaoRepository,
    private val hospedeRepository: HospedeRepository,
    private val reservaRepository: ReservaRepository,
    private val adicionarPessoaImpl: AdicionarPessoaImpl,
    private val hotelRepository: HotelRepository,
    private val adicionaUnidadeHotelImpl: AdicionaUnidadeHotelImpl
) {

    fun abrirFormularioCadastro(tipo: String, model: Model) : String{
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> model.addAttribute("entidade", AcomodacaoEntity())
            TipoEntidade.HOSPEDE.toLowerCase() -> model.addAttribute("entidade", HospedeEntity())
            TipoEntidade.RESERVA.toLowerCase() -> model.addAttribute("entidade", ReservaEntity())
        }
        model.addAttribute("tipo", tipo)
        return "formulario-cadastro"
    }

    fun cadastrar(
        tipo: String, params: Map<String, String>
    ) : String {
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> {
                val numeroAcomodacao = params["numeroAcomodacao"]?.toIntOrNull()
                    ?: throw IllegalArgumentException("Número Acomodação inválido")
                val cnpjHotel = params["cnpjHotel"] ?: throw IllegalArgumentException("CNPJ inválido")

                val id = AcomodacaoId(numeroAcomodacao = numeroAcomodacao, cnpjHotel = cnpjHotel)

                val acomodacao = AcomodacaoEntity(
                    id = id,
                    politicaDeUso = params["politicaDeUso"] ?: "",
                    capacidade = params["capacidade"]?.toIntOrNull() ?: 0,
                    ultimaLimpeza = params["ultimaLimpeza"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now(),
                    precoBase = params["precoBase"]?.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                    tipoAcomodacao = params["tipoAcomodacao"] ?: ""
                )
                acomodacaoRepository.save(acomodacao)
            }
            TipoEntidade.HOSPEDE.toLowerCase() -> {
                val hospede = HospedeEntity(
                    cpf = params["cpf"] ?: "",
                    pontosAcumulados = params["pontosAcumulados"]?.toIntOrNull() ?: 0
                )

                adicionarPessoaImpl.adicionarHospedePessoa(params)

                hospedeRepository.save(hospede)

            }
            TipoEntidade.HOTEL.toLowerCase() -> {
                val hotel = HotelEntity(
                    cnpj = params["cnpj"] ?: "",
                    categoria = params["categoria"] ?: "",
                    capacidade = params["capacidade"]?.toIntOrNull() ?: 0,
                    idSetor = params["idSetor"]?.toIntOrNull() ?: 0
                )

                adicionaUnidadeHotelImpl.adicionarUnidade(params)

                hotelRepository.save(hotel)
            }
            TipoEntidade.RESERVA.toLowerCase() -> {
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

}