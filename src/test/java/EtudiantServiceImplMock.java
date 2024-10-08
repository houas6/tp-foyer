import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplMock {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant = new Etudiant("houas", "aziz", 12345678L, new Date());

    private List<Etudiant> listEtudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant("bencheikh", "wassim", 87654321L, new Date()));
            add(new Etudiant("hamdi", "karim", 11223344L, new Date()));
        }
    };

    @Test
    public void testRetrieveAllEtudiants() {
        // Mocking the repository layer
        Mockito.when(etudiantRepository.findAll()).thenReturn(listEtudiants);

        // Calling the service method
        List<Etudiant> retrievedEtudiants = etudiantService.retrieveAllEtudiants();

        // Verifying the result
        Assertions.assertNotNull(retrievedEtudiants);
        Assertions.assertEquals(2, retrievedEtudiants.size());
    }

    @Test
    public void testRetrieveEtudiantById() {
        // Mocking the repository layer
        Mockito.when(etudiantRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(etudiant));

        // Calling the service method
        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1L);

        // Verifying the result
        Assertions.assertNotNull(retrievedEtudiant);
        Assertions.assertEquals("houas", retrievedEtudiant.getNomEtudiant());
    }

    @Test
    public void testAddEtudiant() {
        // Mocking the repository layer
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Calling the service method
        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        // Verifying the result
        Assertions.assertNotNull(addedEtudiant);
        Assertions.assertEquals("houas", addedEtudiant.getNomEtudiant());
    }

    @Test
    public void testModifyEtudiant() {
        // Mocking the repository layer
        Mockito.when(etudiantRepository.save(Mockito.any(Etudiant.class))).thenReturn(etudiant);

        // Modifying the etudiant object
        etudiant.setNomEtudiant("updatedName");

        // Calling the service method
        Etudiant modifiedEtudiant = etudiantService.modifyEtudiant(etudiant);

        // Verifying the result
        Assertions.assertNotNull(modifiedEtudiant);
        Assertions.assertEquals("updatedName", modifiedEtudiant.getNomEtudiant());
    }

    @Test
    public void testRemoveEtudiant() {
        long etudiantId = 1L;

        // Ensure you're calling doNothing on the mock repository
        Mockito.doNothing().when(etudiantRepository).deleteById(etudiantId);

        // Calling the service method
        etudiantService.removeEtudiant(etudiantId);

        // Verify that deleteById was called once with the expected argument
        Mockito.verify(etudiantRepository, Mockito.times(1)).deleteById(etudiantId);
    }
}
