package services

import enums.TipoEntidade
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import repositories.AcomodacaoRepository
import repositories.HospedeRepository
import repositories.HotelRepository
import repositories.ReservaRepository

@Service
class HomeServiceImpl(
    private val acomodacaoRepository: AcomodacaoRepository,
    private val hospedeRepository: HospedeRepository,
    private val reservaRepository: ReservaRepository,
    private val hotelRepository: HotelRepository
) {

    fun abrirHome(
        tipo: String,
        model: Model,
        page: Int = 0,
        size: Int = 6
    ): String {
        model.addAttribute("tipo", tipo)
        when (tipo) {
            TipoEntidade.ACOMODACAO.toLowerCase() -> {
                val pageable = PageRequest.of(page, size)
                val acomodacoesPage = acomodacaoRepository.findAll(pageable)
                val acomodacoes = acomodacoesPage.content.map {
                    mapOf(
                        "id" to "${it.id.numeroAcomodacao}_${it.id.cnpjHotel}",
                        "Nº Acomodação" to it.id.numeroAcomodacao,
                        "CNPJ" to it.id.cnpjHotel,
                        "Política de Uso" to it.politicaDeUso,
                        "Capacidade" to it.capacidade,
                        "Última Limpeza" to it.ultimaLimpeza,
                        "Preço Base" to it.precoBase,
                        "Tipo" to it.tipoAcomodacao
                    )
                }
                model.addAttribute("titulo", "Acomodação")
                model.addAttribute("colunas", listOf("Nº Acomodação", "CNPJ", "Política de Uso", "Capacidade", "Última Limpeza", "Preço Base", "Tipo"))
                model.addAttribute("dados", acomodacoes)
                model.addAttribute("totalPages", acomodacoesPage.totalPages)
                model.addAttribute("currentPage", acomodacoesPage.number)
            }
            TipoEntidade.HOSPEDE.toLowerCase() -> {
                val pageable = PageRequest.of(page, size)
                val hospedesPage = hospedeRepository.findAll(pageable)
                val hospedes = hospedesPage.content.map {
                    mapOf(
                        "id" to it.cpf,
                        "CPF" to it.cpf,
                        "Pontos Acumulados" to it.pontosAcumulados
                    )
                }
                model.addAttribute("titulo", "Hóspede")
                model.addAttribute("colunas", listOf("CPF", "Pontos Acumulados"))
                model.addAttribute("dados", hospedes)
                model.addAttribute("totalPages", hospedesPage.totalPages)
                model.addAttribute("currentPage", hospedesPage.number)
            }
            TipoEntidade.HOTEL.toLowerCase() -> {
                val pageable = PageRequest.of(page, size)
                val hoteisPage = hotelRepository.findAll(pageable)
                val hoteis = hoteisPage.content.map {
                    mapOf(
                        "id" to it.cnpj,
                        "CNPJ" to it.cnpj,
                        "Categoria" to it.categoria,
                        "Capacidade Máxima de Hóspedes" to it.capacidade,
                        "ID do Setor" to it.idSetor
                    )
                }
                model.addAttribute("titulo", "Hotel")
                model.addAttribute("colunas", listOf("CNPJ", "Categoria", "Capacidade Máxima de Hóspedes", "ID do Setor"))
                model.addAttribute("dados", hoteis)
                model.addAttribute("totalPages", hoteisPage.totalPages)
                model.addAttribute("currentPage", hoteisPage.number)
            }
            TipoEntidade.RESERVA.toLowerCase() -> {
                val pageable = PageRequest.of(page, size)
                val reservasPage = reservaRepository.findAll(pageable)
                val reservas = reservasPage.content.map {
                    mapOf(
                        "id" to it.idReserva,
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
                model.addAttribute("titulo", "Reserva")
                model.addAttribute("colunas", listOf("ID Reserva", "Data Início", "Data Fim", "Pago", "Valor Diárias", "Nº Acomodação", "CNPJ Hotel","CPF Hóspede", "CPF Responsável"))
                model.addAttribute("dados", reservas)
                model.addAttribute("totalPages", reservasPage.totalPages)
                model.addAttribute("currentPage", reservasPage.number)
            }
        }
        return "home"
    }
}