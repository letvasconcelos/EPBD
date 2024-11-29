package services

import AcomodacaoId
import enums.TipoEntidade
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import repositories.AcomodacaoRepository
import repositories.HospedeRepository
import repositories.HotelRepository
import repositories.ReservaRepository

@Service
class ExcluirServiceImpl (
    private val acomodacaoRepository: AcomodacaoRepository,
    private val hospedeRepository: HospedeRepository,
    private val reservaRepository: ReservaRepository,
    private val hotelRepository: HotelRepository
) {

    fun excluir(tipo: String, id: String) : String{
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> {
                val ids = id.split("_")
                val acomodacaoId = AcomodacaoId(
                    numeroAcomodacao = ids[0].toInt(),
                    cnpjHotel = ids[1]
                )
                acomodacaoRepository.deleteById(acomodacaoId)
            }
            TipoEntidade.HOSPEDE.toLowerCase() -> {
                hospedeRepository.deleteById(id)
            }
            TipoEntidade.RESERVA.toLowerCase() -> {
                reservaRepository.deleteById(id)
            }
            TipoEntidade.HOTEL.toLowerCase() -> {
                hotelRepository.deleteById(id)
            }
        }
        return "redirect:/home?tipo=$tipo"
    }

}