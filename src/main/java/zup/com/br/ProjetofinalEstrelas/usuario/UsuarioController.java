package zup.com.br.ProjetofinalEstrelas.usuario;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.exception.UsuarioSemPermissao;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.AtualizarDTO;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;
import zup.com.br.ProjetofinalEstrelas.usuarioLogado.UsuarioLogadoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
@Api(value = "API para Ajudar zuppers a consultar seus benefícios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioLogadoService usuarioLogadoService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Método responsável por cadastrar um usuário")
    public void cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    @ApiOperation(value = "Método responsável por exibir usuários")
    public List<UsuarioDTO> exibirUsuarios() {
        List<UsuarioDTO> resumoDTO = new ArrayList<>();
        for (Usuario musicaRef : usuarioService.exibirUsuarios()) {
            UsuarioDTO resumo = modelMapper.map(musicaRef, UsuarioDTO.class);
            resumoDTO.add(resumo);
        }
        return resumoDTO;
    }

    @GetMapping("/{email}")
    @ApiOperation(value = "Método responsável por exibir usuário pelo seu email")
    public UsuarioDTO buscarUsuarioPorEmail(@PathVariable String email) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = usuarioService.buscarUsuarioPeloOEmail(email);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Método responsável por deletar um usuário pelo seu email")
    public void deletarUsuario(@PathVariable String email) {
        usuarioService.deletarUsuario(email);
    }


    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Método responsável por atualizar a senha do usuário pelo seu email")
    public void atualizarSenhaDeUsuario(@PathVariable String email, @RequestBody AtualizarDTO atualizarDTO) {
        if(!usuarioLogadoService.pegarEmail().equals(email)){
            throw new UsuarioSemPermissao("Você não tem permissão para atualizar esse usuário");
        }

        Usuario usuario = usuarioService.atualizarSenhaDeUsuario(email,
                modelMapper.map(atualizarDTO, Usuario.class));

        modelMapper.map(usuario, UsuarioDTO.class);
    }

}
