package br.com.udesc.desafio02.rest;

import br.com.udesc.desafio02.dao.DAOveiculo;
import br.com.udesc.desafio02.model.Veiculo;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
@Path("/veiculos")
public class Resource {

    private DAOveiculo persistencia = new DAOveiculo();

    public Resource() {
    }

    @GET
    @Path("/teste")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo getJson() {
        Veiculo monta = new Veiculo();
        monta.setId(1L);
        monta.setTipo("");
        monta.setMontadora("");
        monta.setModelo("");
        monta.setMotor("");
        monta.setCor("");
        monta.setKm(100);
        return monta;
    }

    @GET
    @Path("/getId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Veiculo buscaVeiculoId(@PathParam("id") int idVehicle) {
        Veiculo oVeiculo = null;
        try {
            oVeiculo = persistencia.getVeiculoById(idVehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oVeiculo;
    }

    @GET
    @Path("/filtros/{tipo}/{montadora}/{km}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> getFiltrado(
            @PathParam("tipo") String tipo,
            @PathParam("montadora") String montadora,
            @PathParam("km") int km) {
        List<Veiculo> pesquisa = new ArrayList<>();
        DAOveiculo procura = new DAOveiculo();
        pesquisa = procura.buscarVeiculoFiltros(tipo, montadora, km);
        return pesquisa;
    }

    @GET
    @Path("/getTipo/{tipo}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> buscaVeiculoTipo(@PathParam("tipo") String tipo) {
        List<Veiculo> veiculos = null;
        try {
            veiculos = persistencia.buscarVeiculoTipo(tipo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @GET
    @Path("/getMontadora/{montadora}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> buscaVeiculoMontadora(@PathParam("montadora") String montadora) {
        List<Veiculo> veiculos = null;
        try {
            veiculos = persistencia.buscarVeiculoMontadora(montadora);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @GET
    @Path("/getMotor/{motor}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Veiculo> buscaVeiculoMotor(@PathParam("motor") String motor) {
        List<Veiculo> veiculos = null;
        try {
            veiculos = persistencia.buscarVeiculoMotor(motor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addVehicle(Veiculo oVeiculo) {
        String feedback = "";
        try {
            persistencia.salvar(oVeiculo);
            feedback = "Os seguintes dados foram inseridos:\n" + oVeiculo.toString();
        } catch (Exception e) {
            e.printStackTrace();
            feedback = "Erro ao inserir dados!\n" + e.getMessage();
        }
        return feedback;
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateVehicle(Veiculo oVeiculo) {
        try {
            persistencia.modifyVeiculoById(oVeiculo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteVehicle(@PathParam("id") int idVehicle) {
        try {
            persistencia.removeVeiculoById(idVehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
