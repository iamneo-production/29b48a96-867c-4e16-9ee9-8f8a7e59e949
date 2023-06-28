package com.dshritama.springapp.service;

import java.util.List;


import com.dshritama.springapp.model.Pharmacy;
import com.dshritama.springapp.model.Patient;

public interface PharmacyService {
    List<Pharmacy> getAllPharmacies();

    Pharmacy getPharmacyById(Long id);

    Pharmacy createPharmacy(Pharmacy pharmacy);

    Pharmacy updatePharmacy(Long id, Pharmacy updatedPharmacy);

    void deletePharmacy(Long id);

    Patient getPatientByPharmacyId(Long id);
}

