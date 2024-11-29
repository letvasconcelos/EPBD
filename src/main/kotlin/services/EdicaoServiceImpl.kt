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
class EdicaoServiceImpl (
    private val acomodacaoRepository: AcomodacaoRepository,
    private val hospedeRepository: HospedeRepository,
    private val reservaRepository: ReservaRepository,
    private val hotelRepository: HotelRepository
) {
    fun abrirFormularioEdicao(
        id: String,
        tipo: String,
        model: Model
    ) :String {
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> {
                val ids = id.split("_")
                val acomodacaoId = AcomodacaoId(
                    numeroAcomodacao = ids[0].toInt(),
                    cnpjHotel = ids[1]
                )
                val acomodacao = acomodacaoRepository.findById(acomodacaoId).orElseThrow {
                    IllegalArgumentException("Acomodação com ID $acomodacaoId não encontrada")
                }
                model.addAttribute("entidade", acomodacao)
                model.addAttribute("id", "${acomodacaoId.numeroAcomodacao}_${acomodacaoId.cnpjHotel}")
            }
            TipoEntidade.HOSPEDE.toLowerCase() -> {
                val hospede = hospedeRepository.findById(id).orElseThrow {
                    IllegalArgumentException("Hóspede com CPF $id não encontrado")
                }
                model.addAttribute("entidade", hospede)
                model.addAttribute("id", hospede.cpf)
            }
            TipoEntidade.HOTEL.toLowerCase() -> {
                val hotel = hotelRepository.findById(id).orElseThrow {
                    IllegalArgumentException("Hotel com CNPJ $id não encontrado")
                }
                model.addAttribute("entidade", hotel)
                model.addAttribute("id", hotel.cnpj)
            }
            TipoEntidade.RESERVA.toLowerCase() -> {
                val reserva = reservaRepository.findById(id).orElseThrow {
                    IllegalArgumentException("Reserva com ID $id não encontrada")
                }
                model.addAttribute("entidade", reserva)
                model.addAttribute("id", reserva.idReserva)
            }
        }
        model.addAttribute("tipo", tipo)
        return "formulario-edicao"
    }

    fun editar(
       id: String,
       tipo: String,
       params: Map<String, String>
    ):String{
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> {
                val ids = id.split("_")
                val acomodacaoId = AcomodacaoId(
                    numeroAcomodacao = ids[0].toInt(),
                    cnpjHotel = ids[1]
                )
                val acomodacao = AcomodacaoEntity(
                    id = acomodacaoId,
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
                    cpf = id,
                    pontosAcumulados = params["pontosAcumulados"]?.toIntOrNull() ?: 0
                )
                hospedeRepository.save(hospede)
            }
            TipoEntidade.HOTEL.toLowerCase() -> {
                val hotel = HotelEntity(
                    cnpj = id,
                    categoria = params["categoria"] ?: "",
                    capacidade = params["capacidade"]?.toIntOrNull() ?: 0,
                    idSetor = params["idSetor"]?.toIntOrNull() ?: 0
                )

                hotelRepository.save(hotel)
            }
            TipoEntidade.RESERVA.toLowerCase() -> {
                val reserva = ReservaEntity(
                    idReserva = id,
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
        return "redirect:/home?tipo=$tipo"
    }
}