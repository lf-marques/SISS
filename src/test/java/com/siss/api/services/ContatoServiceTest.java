package com.siss.api.services;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.siss.api.entities.PessoaFisica;
import com.siss.api.entities.Contato;
import com.siss.api.exceptions.ConsistenciaException;
import com.siss.api.repositories.PessoaFisicaRepository;
import com.siss.api.repositories.ContatoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ContatoServiceTest {

	@MockBean
	private ContatoRepository contatoRepository;
	@MockBean
	private PessoaFisicaRepository usuarioRepository;

	@Autowired
	private ContatoService contatoService;

	@Test
	public void testBuscarPorIdExistente() throws ConsistenciaException {

		BDDMockito.given(contatoRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Contato()));

		Optional<Contato> resultado = contatoService.buscarPorId(1);

		assertTrue(resultado.isPresent());
	}

	@Test(expected = ConsistenciaException.class)
	public void testBuscarPorIdNaoExistente() throws ConsistenciaException {

		BDDMockito.given(contatoRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());

		contatoService.buscarPorId(1);
	}

	@Test
	public void testBuscarPorPessoaFisicaIdExistente() throws ConsistenciaException {

		List<Contato> lstContato = new ArrayList<Contato>();
		lstContato.add(new Contato());

		BDDMockito.given(contatoRepository.findByPessoaFisicaId(Mockito.anyInt())).willReturn((lstContato));

		Optional<List<Contato>> resultado = contatoService.buscarPorPessoaFisicaId(1);


		assertTrue(resultado.isPresent());
	}
 
	
 

 
	@Test(expected = ConsistenciaException.class)
	public void testBuscarPorPessoaFisicaIdNaoExistente() throws ConsistenciaException {

		BDDMockito.given(contatoRepository.findByPessoaFisicaId(Mockito.anyInt())).willReturn(null);

		contatoService.buscarPorPessoaFisicaId(1);
	}
	
	@Test
	public void testSalvarComSucesso() throws ConsistenciaException {
		
		PessoaFisica usuario = new PessoaFisica();
		Contato contato = new Contato();
		usuario.setId(1);
		contato.setPessoaFisica(usuario);
		
		BDDMockito.given(usuarioRepository.findById(Mockito.anyInt())).willReturn(Optional.of(usuario));
		
		BDDMockito.given(contatoRepository.save(Mockito.any(Contato.class))).willReturn(new Contato());
		
		Contato resultado = contatoService.salvar(contato);

		assertNotNull(resultado);
	}
	
	@Test(expected = ConsistenciaException.class)
	public void testSalvarSemSucesso() throws ConsistenciaException, ParseException {
		
		PessoaFisica usuario = new PessoaFisica();
		Contato contato = new Contato();
		usuario.setId(0);
		contato.setPessoaFisica(usuario);
		contato.setNome("Lorem ipsum dolor sit amet");
		contato.setCelular("99956548722");
		contato.setTelefone("3356548722");
		contato.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2021"));
		contato.setDataAlteracao(new SimpleDateFormat("dd/MM/yyyy").parse("12/12/2021"));
		
		BDDMockito.given(usuarioRepository.findById(Mockito.anyInt())).willReturn(Optional.empty());
		
		BDDMockito.given(contatoRepository.save(Mockito.any(Contato.class))).willReturn(new Contato());
		
		contatoService.salvar(contato);
	}
}
