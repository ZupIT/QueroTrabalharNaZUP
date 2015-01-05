/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cadastrodecep;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.hibernate.jpa.criteria.expression.function.SubstringFunction;
import org.json.JSONObject;

/**
 *
 * @author DiemersonFernando
 */
public class Manipulacao {
    String cep;
    String ender;
    String bairro;
    String cidade;
    String uf;
    
    public String resp;
  
    EntityManager banco = Persistence.createEntityManagerFactory("cep").createEntityManager();    
    StringBuilder resposta = new StringBuilder();
    
    public String gravaCep(String dados)
    {
        JSONObject JSON = new JSONObject(dados);        
        Cep entidade = new Cep();
        
        String valCep = JSON.getString("cep");
        
        if((valCep.length() - 1) != 8)
        {
            resposta.append("{");
            resposta.append("\"status\": ").append("\"Erro\"").append(", ");
            resposta.append("\"mensagem\": ").append("\"O CEP informado é inválido. Verifique!\"").append("");
            resposta.append("}");             
        }else
        {
            valCep = valCep.substring(0, 5)+valCep.substring(6, 9);            
        
            if (banco.find(Cep.class, valCep) != null)
            {
                resposta.append("{");
                resposta.append("\"status\": ").append("\"Erro\"").append(", ");
                resposta.append("\"mensagem\": ").append("\"O endereço informado já foi cadastrado.\"").append("");
                resposta.append("}");              
            }else
            {
                entidade.setCep(valCep);
                entidade.setEndereco(JSON.getString("endereco"));        
                entidade.setBairro(JSON.getString("bairro"));
                entidade.setCidade(JSON.getString("cidade"));
                entidade.setEstado(JSON.getString("estado"));

                banco.getTransaction().begin();
                banco.persist(entidade);
                banco.getTransaction().commit();

                resposta.append("{");
                resposta.append("\"status\": ").append("\"SUCESSO\"").append(", ");
                resposta.append("\"mensagem\": ").append("\"O endereço foi cadastrado com sucesso.\"").append("");
                resposta.append("}");             
            }
        }
        resp = resposta.toString();
        banco.close();          
        return resp; 
    }
    
    public String retornaCep(String serv)
    {       
               
        if(serv.isEmpty())
        {
            resposta.append("{");
            resposta.append("\"status\": ").append("\"Erro\"").append(", ");
            resposta.append("\"mensagem\": ").append("\"Nenhum CEP foi informado. Verifique!\"").append("");
            resposta.append("}");

        }else if(serv.length()!= 8)
        {
            resposta.append("{");
            resposta.append("\"status\": ").append("\"Erro\"").append(", ");
            resposta.append("\"mensagem\": ").append("\"O CEP informado é inválido\"").append("");
            resposta.append("}");
            
        }else 
        {
           
            if (banco.find(Cep.class, serv) == null)
            {
                resposta.append("{");
                resposta.append("\"status\": ").append("\"Erro\"").append(", ");
                resposta.append("\"mensagem\": ").append("\"O CEP informado não foi encontrado\"").append("");
                resposta.append("}");
            }else 
            {             
                Cep endereco = banco.find(Cep.class, serv);
                cep = endereco.getCep();
                
                ender = endereco.getEndereco();
                bairro = endereco.getBairro();
                cidade = endereco.getCidade();
                uf = endereco.getEstado();

                resposta.append("{");
                resposta.append("\"status\": ").append("\"Sucesso\"").append(", ");
                resposta.append("\"cep\": \"").append(cep.substring(0,5)).append("-").append(cep.substring(5, 8)).append("\", ");
                resposta.append("\"endereco\": \"").append(ender).append("\", ");
                resposta.append("\"bairro\": \"").append(bairro).append("\", ");
                resposta.append("\"cidade\": \"").append(cidade).append("\", ");               
                resposta.append("\"estado\": \"").append(uf).append("\"");
                resposta.append("}");   
            }           
        }  
        
        resp = resposta.toString();
        banco.close();
        return  resp;            
    }    
}
