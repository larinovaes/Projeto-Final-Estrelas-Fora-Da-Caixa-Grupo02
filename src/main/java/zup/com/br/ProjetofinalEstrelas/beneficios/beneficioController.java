package zup.com.br.ProjetofinalEstrelas.beneficios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beneficio")
public class beneficioController {

@Autowired
private beneficioService beneficioService;


public Beneficio cadastrarBeneficio (@RequestBody Beneficio beneficio){ return beneficioService.salvarBeneficio(beneficio);}




}
