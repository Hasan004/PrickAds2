package org.example.dao;

import org.example.domain.Advertentie;
import org.example.domain.AdvertentieDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AdvertentieDaoTest {

    @InjectMocks
    private AdvertentieDao target;

    @Mock
    private EntityManager emMock;

    @Mock
    private Advertentie aMock;

    @Mock
    private AdvertentieDto adMock;

    @Mock
    private TypedQuery<Advertentie> tqgMock;

    @Mock
    private TypedQuery<AdvertentieDto> tqMock;

    @Test
    void checkThatGetAllAdsWorks() {
        //given
        when(emMock.createNamedQuery("Advertentie.findAll", AdvertentieDto.class)).thenReturn(tqMock);
        //when
        target.getAllAds();
        //then
        verify(emMock).createNamedQuery(eq("Advertentie.findAll"), eq(AdvertentieDto.class));
        verify(tqMock).getResultList();
    }

    @Test
    void getAdsByUserId() {
        //given
        when(emMock.createNamedQuery("Advertentie.findById", AdvertentieDto.class)).thenReturn(tqMock);
        when(tqMock.getSingleResult()).thenReturn(adMock);
        //when
        AdvertentieDto g = target.getAdById(1);
        //then
        verify(emMock).createNamedQuery(eq("Gebruiker.zoekOpEmail"), eq(AdvertentieDto.class));
        verify(tqMock).getSingleResult();
    }

    @Test
    void add() {
        when(emMock.createQuery("Select a from Advertentie a")).thenReturn(tqMock);
        target.getAll();
        verify(emMock).createQuery("select a from Advertentie a");
        verify(tqMock).getResultList();
    }

    @Test
    void delete() {
        when(emMock.createQuery("delete from Advertentie a where a.id = :id  ")).thenReturn(tqMock);
        when(tqMock.getSingleResult()).thenReturn(adMock);
        //when
        AdvertentieDto g = target.getAdById(1);

        verify(emMock).createQuery("delete from Advertentie a where a.id = :id ");
        verify(tqMock).getResultList();
    }

}
