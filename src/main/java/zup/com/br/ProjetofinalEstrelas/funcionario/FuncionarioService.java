package zup.com.br.ProjetofinalEstrelas.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zup.com.br.ProjetofinalEstrelas.beneficios.BeneficioRepository;

import zup.com.br.ProjetofinalEstrelas.usuario.UsuarioRepository;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private BeneficioRepository beneficioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Funcionario salvarFuncionario(Funcionario funcionario) {
       return null;
    }
}
