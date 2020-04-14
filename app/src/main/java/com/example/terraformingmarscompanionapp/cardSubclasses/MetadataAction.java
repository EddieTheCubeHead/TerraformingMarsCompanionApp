package com.example.terraformingmarscompanionapp.cardSubclasses;

public interface MetadataAction extends ActionCard {
    /* Serveri-implementaatiossa on siirrettävä jotenkin toimintojen pelaamiseen liittyvät päätökset.
     * Onneksi kaikki nämä päätökset ovat kuvattavissa yhdellä kokonaisluvulla. Tarvittaessa
     * kortti voi override:aa tämän funktion, jotta toiminnon käyttäminen muilla pelissä olevilla onnistuu
     * ilman kortin sisällä tehtävää päätöstä.
     */
    boolean actionWithMetadata(Integer data);
}
