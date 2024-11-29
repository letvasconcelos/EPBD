package controller

import AcomodacaoId
import entities.AcomodacaoEntity
import entities.HospedeEntity
import entities.ReservaEntity
import enums.TipoEntidade
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import repositories.AcomodacaoRepository
import repositories.HospedeRepository
import repositories.ReservaRepository
import services.CadastroServiceImpl
import services.EdicaoServiceImpl
import services.ExcluirServiceImpl
import services.HomeServiceImpl
import java.math.BigDecimal
import java.time.LocalDateTime

const val DEFAULT_TIPO_ENTIDADE = "acomodacao"
@Controller
class FormController(
    private val cadastroServiceImpl: CadastroServiceImpl,
    private val homeServiceImpl: HomeServiceImpl,
    private val edicaoServiceImpl: EdicaoServiceImpl,
    private val excluirServiceImpl: ExcluirServiceImpl
) {

    @GetMapping("/formulario/cadastro")
    fun obtemFormCadastro(
        @RequestParam tipo: String,
        model: Model
    ): String {

        return cadastroServiceImpl.abrirFormularioCadastro(tipo, model)
    }


    @PostMapping("/cadastrar")
    fun realizaCadastroGeral(
        @RequestParam tipo: String,
        @RequestParam params: Map<String, String>
    ): String {
       return cadastroServiceImpl.cadastrar(tipo, params)
    }


    @GetMapping("/home")
    fun home(
        @RequestParam(required = false, defaultValue = DEFAULT_TIPO_ENTIDADE ) tipo: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "6") size: Int,
        model: Model
    ): String {
        return homeServiceImpl.abrirHome(tipo, model, page, size)
    }


    @GetMapping("/formulario/edicao/{id}")
    fun obtemFormEditar(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = DEFAULT_TIPO_ENTIDADE) tipo: String,
        model: Model
    ): String {
        return edicaoServiceImpl.abrirFormularioEdicao(id, tipo, model )
    }


    @PostMapping("/editar/{id}")
    fun realizaEdicao(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = DEFAULT_TIPO_ENTIDADE) tipo: String,
        @RequestParam params: Map<String, String>
    ): String {
        return edicaoServiceImpl.editar(id, tipo, params)
    }


    @GetMapping("/excluir/{id}")
    fun excluir(
        @PathVariable id: String,
        @RequestParam(required = false, defaultValue = DEFAULT_TIPO_ENTIDADE) tipo: String,
    ) :String {
        return excluirServiceImpl.excluir(tipo, id)
    }
}