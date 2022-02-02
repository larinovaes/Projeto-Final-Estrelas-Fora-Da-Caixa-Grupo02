package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import zup.com.br.ProjetofinalEstrelas.beneficios.Beneficio;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioService;
import zup.com.br.ProjetofinalEstrelas.enums.NivelZupper;
import zup.com.br.ProjetofinalEstrelas.exception.FuncionarioNaoEncontradoException;
import zup.com.br.ProjetofinalEstrelas.usuario.Usuario;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioRepository;
import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioService;

import java.time.LocalDate;
import java.util.*;


@SpringBootTest
public class FuncionarioServiceTest {
    @Autowired
    private FuncionarioService funcionarioService;

    @MockBean
    private FuncionarioRepository funcionarioRepository;
    @MockBean
    private UsuarioRepository usuarioRepository;
    @MockBean
    private UsuarioService usuarioService;
    @MockBean
    private BeneficioService beneficioService;

    private Funcionario funcionario;
    private Usuario usuario;
    private List<Beneficio> beneficios;
    private Beneficio beneficio;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setEmail("usuario@zup.com.br");
        usuario.setSenha("Usuario@123");

        beneficios = new ArrayList<>();

        beneficio = new Beneficio();
        beneficio.setId(1);
        beneficio.setNome("Gympass");
        beneficio.setDescricao("app para academia");
        beneficio.setNivelZupper(NivelZupper.ZUPPER3);

        beneficios.add(beneficio);

        funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setUsuario(usuario);
        funcionario.setNivelZupper(NivelZupper.ZUPPER3);
        funcionario.setDataDeContratacao(LocalDate.now());
        funcionario.setBeneficios(beneficios);
    }

    @Test
    public void testarSalvarFuncionario() {
        Mockito.when(funcionarioRepository.save(Mockito.any(Funcionario.class))).thenReturn(funcionario);

        Funcionario obejto = funcionarioService.salvarFuncionario(funcionario, Mockito.anyString());

        Mockito.verify(funcionarioRepository, Mockito.times(1))
                .save(Mockito.any(Funcionario.class));
    }

    @Test
    public void testarexibirTodosOsFuncionarios() {
        Iterable<Funcionario> funcionarios = Arrays.asList(funcionario);
        Mockito.when(funcionarioRepository.findAll()).thenReturn(funcionarios);

        Iterable<Funcionario> funcionarioDeInteresse = funcionarioService.exibirTodosOsFuncionarios();
        Mockito.verify(funcionarioRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testarBuscarFuncionarioPorEmailCaminhoPositivo() {
        Mockito.when(funcionarioRepository.findByUsuarioEmail(Mockito.anyString()))
                .thenReturn(Optional.of(funcionario));

        Funcionario funcionarioDeInteresse = funcionarioService.buscarFuncionarioPorEmail(Mockito.anyString());

        Mockito.verify(funcionarioRepository, Mockito.times(1))
                .findByUsuarioEmail(Mockito.anyString());
    }

    @Test
    public void testarBuscarFuncionarioPorEmailCaminhoNegativo() {
        Mockito.when(funcionarioRepository.findByUsuarioEmail(Mockito.anyString())).thenReturn(Optional.empty());

        FuncionarioNaoEncontradoException exception = Assertions.assertThrows(FuncionarioNaoEncontradoException.class,
                () -> {
            funcionarioService.buscarFuncionarioPorEmail(Mockito.anyString());
                });

    }

    @Test
    public void testarDeletarFuncionarioCaminhoNegativo() {
        Mockito.doNothing().when(funcionarioRepository).deleteById(Mockito.anyInt());

        FuncionarioNaoEncontradoException exception = Assertions.assertThrows(FuncionarioNaoEncontradoException.class,
                () -> {
            funcionarioService.deletarFuncionario("usuarioNaoExiste@zup.com.br");
        });
    }

    @Test
    public void testarDeletarFuncionario() {
        Mockito.when(funcionarioRepository.findByUsuarioEmail(funcionario.getUsuario().getEmail()))
                .thenReturn(Optional.of(funcionario));

        Mockito.doNothing().when(funcionarioRepository).deleteById(Mockito.anyInt());
        funcionarioService.deletarFuncionario(funcionario.getUsuario().getEmail());

        Mockito.verify(funcionarioRepository, Mockito.times(1)).deleteById(funcionario.getId());
    }

    @Test
    public void testarAtualizarCaminhoPositivo() {
        funcionario.setNivelZupper(NivelZupper.ZUPPER4);

        Mockito.when(funcionarioRepository.findByUsuarioEmail(Mockito.anyString()))
                .thenReturn(Optional.of(funcionario));

        funcionarioService.atualizarFuncionario(Mockito.anyString(), funcionario);

        Mockito.verify(funcionarioRepository, Mockito.times(1)).save(funcionario);
    }

    @Test
    public void TestarAtualizarCaminhoNegativo() {
        Mockito.when(funcionarioRepository.findByUsuarioEmail(Mockito.anyString())).thenReturn(Optional.empty());

        FuncionarioNaoEncontradoException exception = Assertions.assertThrows(FuncionarioNaoEncontradoException.class,
                () -> {
                    funcionarioService.atualizarFuncionario("usuarioNaoExiste@zup.com.br", funcionario);
                });
    }
}

