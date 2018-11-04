package br.com.aevc.loja.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.thoughtworks.xstream.XStream;

import br.com.aevc.loja.dao.ProjetoDAO;
import br.com.aevc.loja.modelo.Projeto;

@Path("projetos")
public class ProjetoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String busca(@PathParam("id")long id) {
		Projeto projeto = new ProjetoDAO().busca(id);
		return projeto.toJson();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public String adiciona(String conteudo) {
		Projeto projeto = (Projeto) new XStream().fromXML(conteudo);
		new ProjetoDAO().adiciona(projeto);
		return "<status>sucesso</status>";
	}
	
	@DELETE
	@Path("{id}")
	public Response removeProjeto(@PathParam("id") long id) {
		new ProjetoDAO().remove(id);
		return Response.ok().build(); 
	}
}
