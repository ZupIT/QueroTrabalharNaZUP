package com.rossinimartins.gabriel.cepApi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Path("/rest-api/")
public class CepService {
	
	private Configuration config = new Configuration();
	private SessionFactory sessionFactory;
	
	public CepService() {

		// Configurando o Hibernate
		config.configure();
		config.addAnnotatedClass(CepEntry.class);

		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(ssrb.build());
		
	}

	@GET
	@Path("/busca/{cepEscolhido}")
	public Response busca(@PathParam("cepEscolhido") String cepEscolhido) {
		
		// Obtendo a sessão do Hibernate
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();

		String mensagem;
		int code = 200;
		
		try {

			// Verificando se o CEP é válido
			int cepComoInteiro = Integer.parseInt(cepEscolhido);
			if(cepComoInteiro < 10000000 || cepComoInteiro >  99999999) throw new IllegalArgumentException();
			
			// Recupera o valor do banco
			CepEntry resultado = (CepEntry) session.load(CepEntry.class, cepComoInteiro);

			// Converte o objeto para JSON
			ObjectMapper mapper = new ObjectMapper();
			Hibernate5Module hibernateModule = new Hibernate5Module();
			hibernateModule.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
			mapper.registerModule(hibernateModule);
			ObjectWriter writer = mapper.writer();
			
			// Acrescenta o status à mensagem
			StringBuilder sb = new StringBuilder(writer.writeValueAsString(resultado));
			sb.deleteCharAt(0);
			sb.insert(0, "{\"status\":\"SUCESSO\",");
			mensagem = sb.toString();
			
			transaction.commit();
			
		} catch(IllegalArgumentException exception) {
			code = 400;
			mensagem = "{\"status\": \"ERRO\",\"mensagem\": \"O CEP informado é inválido\"}";
		} catch(ObjectNotFoundException exception) {
			code = 400;
			mensagem = "{\"status\": \"ERRO\",\"mensagem\": \"O CEP informado não foi encontrado\"}";
		} catch(JsonProcessingException exception) {
			code = 400;
			mensagem = "{\"status\": \"ERRO\",\"mensagem\": \"O CEP informado não foi encontrado\"}";
		}
		
		return Response.status(code).type(MediaType.APPLICATION_JSON + "; charset=UTF-8").entity(mensagem).build();

	}
	
	@POST
	@Path("/cadastro/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastro(CepEntry CEP) {
		
		String mensagem;
		int code = 200;
		
		try {
			Session session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.save(CEP);
			session.getTransaction().commit();
			
			mensagem = "{\"status\": \"SUCESSO\",\"mensagem\": \"O endereço foi cadastrado com sucesso.\"}"; 
		} catch(ConstraintViolationException exception) {
			code = 400;
			mensagem = "{\"status\": \"ERRO\",\"mensagem\": \"O endereço informado já foi cadastrado.\"}";
		}
		
		return Response.status(code).type(MediaType.APPLICATION_JSON + "; charset=UTF-8").entity(mensagem).build();
	}
	
}
