package zup.com.br.ProjetofinalEstrelas.usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    public UsuarioDTO cadastrarUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        usuarioService.salvarUsuario(usuario);

       return usuarioDTO;
    }

    @GetMapping
    public Iterable<Usuario> exibirUsuarios() {
        return usuarioService.exibirUsuarios();
    }

    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable String email) {
        usuarioService.deletarUsuario(email);
    }
}
