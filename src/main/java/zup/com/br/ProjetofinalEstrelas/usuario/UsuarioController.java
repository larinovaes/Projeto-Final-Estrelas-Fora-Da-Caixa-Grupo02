package zup.com.br.ProjetofinalEstrelas.usuario;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import zup.com.br.ProjetofinalEstrelas.config.security.UsuarioLogado;
import zup.com.br.ProjetofinalEstrelas.usuario.dtos.UsuarioDTO;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioService.salvarUsuario(usuario);
    }

    @GetMapping
    public Iterable<Usuario> exibirUsuarios() {
        return usuarioService.exibirUsuarios();
    }

    @GetMapping("/{email}")
    public UsuarioDTO buscarUsuarioPorEmail(@PathVariable String email) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.buscarUsuarioPeloOEmail(email);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable String email) {
        usuarioService.deletarUsuario(email);
    }

    @PutMapping
    public void atualizarUsuarios(@RequestBody UsuarioDTO usuarioDTO, Authentication authentication) {
        UsuarioLogado usuarioLogado = (UsuarioLogado) authentication.getPrincipal();

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.atualizarUsuario(usuario, usuarioLogado.getEmail());

        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
    }

}
