package tn.esprit.tpfoyer.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FoyerServiceImplTest {

    @Mock
    private FoyerRepository foyerRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetrieveAllFoyers() {
        // Mock data
        Foyer foyer1 = new Foyer();
        Foyer foyer2 = new Foyer();
        List<Foyer> mockFoyers = Arrays.asList(foyer1, foyer2);

        when(foyerRepository.findAll()).thenReturn(mockFoyers);

        // Test
        List<Foyer> foyers = foyerService.retrieveAllFoyers();

        // Verify
        assertEquals(2, foyers.size());
        verify(foyerRepository).findAll();
    }

    @Test
    public void testRetrieveFoyer() {
        // Mock data
        Long foyerId = 1L;
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.findById(foyerId)).thenReturn(Optional.of(mockFoyer));

        // Test
        Foyer retrievedFoyer = foyerService.retrieveFoyer(foyerId);

        // Verify
        assertNotNull(retrievedFoyer);
        verify(foyerRepository).findById(foyerId);
    }

    @Test
    public void testAddFoyer() {
        // Mock data
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer);

        // Test
        Foyer addedFoyer = foyerService.addFoyer(mockFoyer);

        // Verify
        assertNotNull(addedFoyer);
        verify(foyerRepository).save(mockFoyer);
    }

    @Test
    public void testModifyFoyer() {
        // Mock data
        Foyer mockFoyer = new Foyer();
        when(foyerRepository.save(mockFoyer)).thenReturn(mockFoyer);

        // Test
        Foyer modifiedFoyer = foyerService.modifyFoyer(mockFoyer);

        // Verify
        assertNotNull(modifiedFoyer);
        verify(foyerRepository).save(mockFoyer);
    }

    @Test
    public void testRemoveFoyer() {
        // Mock data
        Long foyerId = 1L;

        // Test
        foyerService.removeFoyer(foyerId);

        // Verify that deleteById method is called with the correct ID
        verify(foyerRepository).deleteById(foyerId);
    }
}
