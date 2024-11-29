package enums

enum class TipoEntidade {
    ACOMODACAO, HOSPEDE, RESERVA, HOTEL;

    fun toLowerCase(): String {
        return this.name.lowercase().toString()
    }
}
