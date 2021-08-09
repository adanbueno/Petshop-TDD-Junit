package TesteUnitarios;

import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.PetsController;
import controller.PetshopController;
import controller.ServiçoController;
import model.Cliente;
import model.Pets;
import model.Serviços;

import static org.junit.Assert.*;

public class PetshopControllerTest {
    private PetshopController petshopController = new PetshopController(new ConnectionFactory());
    private PetsController petsController = new PetsController(new ConnectionFactory());
    private ClienteController clienteController = new ClienteController(new ConnectionFactory());
    private ServiçoController serviçoController = new ServiçoController(new ConnectionFactory());




    @org.junit.Test
    public void addDinheiroCaixa() {
        boolean resultado = petshopController.addDinheiroCaixa(50);
        assertTrue(resultado);

        boolean resultado2 = petshopController.addDinheiroCaixa(-200);
        assertFalse(resultado2);
    }

    @org.junit.Test
    public void removerDinheiroCaixa() {
        petshopController.addDinheiroCaixa(1000.0);
        boolean resultado = petshopController.removerDinheiroCaixa(200);
        assertTrue(resultado);

        boolean resultado2 = petshopController.removerDinheiroCaixa(1900);
        assertFalse(resultado2);


    }

    @org.junit.Test
    public void solicitarServiço(){
        Cliente cliente = new Cliente("Renato","12312312312","8812121212");
        clienteController.cadastrarCliente(cliente);

        Pets pet = new Pets("Fernando","auau", 5);
        petsController.cadastrarPet(pet);

        Serviços serviço = new Serviços("Banhar o auau", 30);
        petshopController.solicitarServiço(pet,serviço);
    }
}
