package Mocks.PetsRepositorio;

import JDBC.ConnectionFactory;
import controller.ClienteController;
import controller.PetsController;
import model.Cliente;
import model.Pets;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddPetsRepositorioMock {

    @Mock
    private ConnectionFactory factory = Mockito.mock(ConnectionFactory.class);

    @Mock
    private Connection conn = Mockito.mock(Connection.class);

    @Mock
    private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);

    @Mock
    private ResultSet rs = Mockito.mock(ResultSet.class);

    private Cliente cliente = new Cliente("Adan","66666666655","85996788310");
    private Pets pet = new Pets("testinha", "teste",5);
    private ClienteController clienteController = new ClienteController(factory);
    private PetsController petsController = new PetsController(factory);


    @Before
    public void setUp () throws SQLException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(factory.getConnection()).thenReturn(conn);

        Mockito.when(conn.prepareStatement(Mockito.any(String.class))).thenReturn(stmt);

        Mockito.when(stmt.executeUpdate()).thenReturn(1);

        Mockito.when(stmt.executeQuery()).thenReturn(rs);

        Mockito.doNothing().when(stmt).close();

        Mockito.when(conn.prepareStatement(Mockito.startsWith("Select"))).thenReturn(stmt);

        Mockito.when(rs.getString("nome")).thenReturn(this.cliente.getNome());

        Mockito.when(rs.getString("CPF")).thenReturn(this.cliente.getCpf());

        Mockito.when(rs.getString("telefone")).thenReturn(this.cliente.getTelefone());

        Mockito.when(rs.getInt("idClientes")).thenReturn(this.cliente.getId());

        Mockito.when(rs.getString("nomePet")).thenReturn(this.pet.getNome());

        Mockito.when(rs.getString("racaPet")).thenReturn(this.pet.getRa??a());

        Mockito.when(rs.getInt("dono_id")).thenReturn(this.pet.getDono());


    }

    @Test
    public void addPetsValido() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets(pet.getNome(), pet.getRa??a(), 5);
        assertTrue(petsController.cadastrarPet(pet1));
        Mockito.verify(stmt, Mockito.times(1)).executeUpdate();
    }

    @Test
    public void addPetsComNomeInvalido() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets("123123123", pet.getRa??a(), 5);

        assertFalse(petsController.cadastrarPet(pet1));
        Mockito.verifyZeroInteractions(stmt);



    }

    @Test
    public void addPetsComNomeVazio() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets("", pet.getRa??a(), 5);

        assertFalse(petsController.cadastrarPet(pet1));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addPetsComRa??aInvalida() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets(pet.getNome(), "123123123", 5);

        assertFalse(petsController.cadastrarPet(pet1));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addPetsComRa??aVazia() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets(pet.getNome(), "", 5);

        assertFalse(petsController.cadastrarPet(pet1));
        Mockito.verifyZeroInteractions(stmt);
    }

    @Test
    public void addPetsComDonoInvalido() throws SQLException {
        PetsController petsController = new PetsController(factory);
        Pets pet1 = new Pets(pet.getNome(), pet.getRa??a(), -5);

        assertFalse(petsController.cadastrarPet(pet1));
        Mockito.verifyZeroInteractions(stmt);
    }


}
